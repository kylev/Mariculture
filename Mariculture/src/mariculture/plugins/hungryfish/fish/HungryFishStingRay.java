package hungryfish.fish;

import mariculture.fishery.fish.FishStingRay;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class HungryFishStingRay extends FishStingRay {
	public HungryFishStingRay(int id) {
		super(id);
	}
	@Override
	public String getSpecies() {
		return (this.getClass().getSimpleName().toLowerCase()).substring(10);
	}

	@Override
	public void onConsumed(World world, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.poison.id, 600, 0));
		player.getFoodStats().addStats(2, 0.7F);
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
	}
}