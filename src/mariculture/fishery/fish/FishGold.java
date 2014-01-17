package mariculture.fishery.fish;

import java.util.Random;

import mariculture.api.fishery.EnumRodQuality;
import mariculture.api.fishery.fish.EnumFishGroup;
import mariculture.api.fishery.fish.EnumFishWorkEthic;
import mariculture.api.fishery.fish.FishSpecies;
import mariculture.core.Core;
import mariculture.core.lib.MaterialsMeta;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FishGold extends FishSpecies {
	public FishGold(int id) {
		super(id);
	}

	@Override
	public EnumFishGroup getGroup() {
		return EnumFishGroup.DOMESTICATED;
	}

	@Override
	public int getLifeSpan() {
		return 3;
	}

	@Override
	public int getFertility() {
		return 14;
	}

	@Override
	public boolean isDominant() {
		return true;
	}
	
	@Override
	public ItemStack getProduct(Random rand) {
		return (rand.nextInt(18) == 0)? new ItemStack(Core.materials, 1, MaterialsMeta.DROP_WATER): null;
	}

	@Override
	public int getCatchChance() {
		return 3;
	}
	
	@Override
	public double getFishOilVolume() {
		return 1.125;
	}
	
	@Override
	public ItemStack getLiquifiedProduct() {
		return new ItemStack(Item.goldNugget);
	}

	@Override
	public int getLiquifiedProductChance() {
		return 20;
	}

	@Override
	public int[] getChestGenChance() {
		return new int[] { 1, 1, 5 };
	}
	
	@Override
	public int getBaseProductivity() {
		return EnumFishWorkEthic.HARDWORKER.getMultiplier();
	}
}
