package mariculture.magic.enchantments;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;

import mariculture.api.core.MaricultureHandlers;
import mariculture.core.handlers.LogHandler;
import mariculture.core.helpers.EnchantHelper;
import mariculture.core.lib.MaricultureDamage;
import mariculture.magic.Magic;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class EnchantmentOneRing extends EnchantmentJewelry {
	public EnchantmentOneRing(int i, int weight, EnumEnchantmentType type) {
		super(i, weight, type);
		this.setName("oneRing");
	}

	@Override
	public int getMinEnchantability(int level) {
		return 15;
	}

	@Override
	public int getMaxEnchantability(int level) {
		return super.getMinEnchantability(level) + 50;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public boolean canApplyTogether(Enchantment enchantment) {
		return false;
	}

	public static void handlePacket(Packet250CustomPayload packet, EntityPlayer player) {
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

		int id;
		int entityID;
		boolean active;

		try {
			id = inputStream.readInt();
			entityID = inputStream.readInt();
			active = inputStream.readBoolean();
		} catch (IOException e) {
			LogHandler.log(Level.WARNING, "Mariculture ERROR while handling One Ring Enchantment Packet");
			return;
		}

		for (int i = 0; i < player.worldObj.playerEntities.size(); i++) {
			EntityPlayer aPlayer = (EntityPlayer) player.worldObj.playerEntities.get(i);

			if (aPlayer.entityId == entityID) {
				aPlayer.setInvisible(active);
			}
		}
	}

	public static void activate(EntityPlayer player) {
		if (!player.isPotionActive(Potion.invisibility)) {
			player.setInvisible(EnchantHelper.hasEnchantment(Magic.oneRing, player));
		}

		if (EnchantHelper.hasEnchantment(Magic.oneRing, player)) {
			player.getEntityData().setBoolean("oneRingActive", true);
		} else {
			if (player.getEntityData().hasKey("oneRingActive")) {
				player.getEntityData().removeTag("oneRingActive");

				if (player.worldObj.difficultySetting > 0) {
					int difficulty = player.worldObj.difficultySetting;
					player.addPotionEffect(new PotionEffect(Potion.hunger.id, (600 * difficulty), 0));
					player.addPotionEffect(new PotionEffect(Potion.wither.id, (66 * difficulty), 0));
					player.addPotionEffect(new PotionEffect(Potion.weakness.id, (200 * difficulty), 0));
					int foodDrop = difficulty * -7;
					int satDrop = difficulty * -14;
					player.getFoodStats().addStats(foodDrop, satDrop);
					player.attackEntityFrom(MaricultureDamage.oneRing, (player.worldObj.difficultySetting * 5));
				}
			}
		}
	}
}