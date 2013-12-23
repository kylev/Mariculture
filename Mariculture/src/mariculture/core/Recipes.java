package mariculture.core;

import mariculture.api.core.MaricultureHandlers;
import mariculture.api.core.RecipeFreezer;
import mariculture.api.core.RecipeSmelter;
import mariculture.api.core.RecipeSmelter.SmelterOutput;
import mariculture.core.blocks.BlockLimestoneSlab;
import mariculture.core.items.ItemBattery;
import mariculture.core.lib.*;
import mariculture.core.util.FluidDictionary;
import mariculture.fishery.Fishery;
import mariculture.plugins.PluginTinkersConstruct;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {
	public static void add() {
		RecipesSmelting.add();
		addCraftingItems();
		addMetalRecipes();
		addUpgradeRecipes();

		GameRegistry.addRecipe(new ItemStack(Core.oreBlocks, 4, OresMeta.LIMESTONE_BRICK), new Object[] { 
			"## ", "## ", Character.valueOf('#'), (new ItemStack(Core.oreBlocks, 1, OresMeta.LIMESTONE)) });

		GameRegistry.addRecipe(new ItemStack(Core.oreBlocks, 4, OresMeta.LIMESTONE_CHISELED), new Object[] { "## ",
				"## ", Character.valueOf('#'), (new ItemStack(Core.oreBlocks, 1, OresMeta.LIMESTONE_SMOOTH)) });

		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(ItemBattery.make(new ItemStack(Core.battery), 50000), true, new Object[] { " I ", "TRT", "TRT",
						Character.valueOf('I'), Item.ingotIron, Character.valueOf('R'), Item.redstone,
						Character.valueOf('T'), "ingotTitanium" }));
		
			GameRegistry.addShapelessRecipe(ItemBattery.make(new ItemStack(Core.battery), 50000),
					new Object[] { new ItemStack(Core.battery, 1, OreDictionary.WILDCARD_VALUE), Item.redstone, Item.redstone, Item.redstone });

		GameRegistry.addShapelessRecipe(new ItemStack(Core.liquidContainers, 8, FluidContainerMeta.BOTTLE_VOID),
				new Object[] { Item.glassBottle, Item.redstone, new ItemStack(Item.dyePowder, 1, Dye.INK) });

		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.utilBlocks, 1, UtilMeta.BOOKSHELF), new Object[] { 
					"SPS", "PCP", "SSS", 
					Character.valueOf('P'), "plankWood", 
					Character.valueOf('S'), Block.bookShelf,
					Character.valueOf('C'), Block.chest }));

		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.oreBlocks, 1, OresMeta.BASE_BRICK), new Object[] { 
					"IGI", "G G", "IGI", 
					Character.valueOf('I'), Item.netherrackBrick, 
					Character.valueOf('G'), Block.fenceIron }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.oreBlocks, 1, OresMeta.BASE_IRON), new Object[] { 
					"IGI", "G G", "IGI", 
					Character.valueOf('I'), Item.ingotIron, 
					Character.valueOf('G'), Block.thinGlass }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.oreBlocks, 1, OresMeta.BASE_WOOD), new Object[] { 
					"IGI", "G G", "IGI", 
					Character.valueOf('I'), "logWood", 
					Character.valueOf('G'), Block.fence }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.utilBlocks, 1, UtilMeta.LIQUIFIER), new Object[] { 
					" B ", "ITI", "HMH", 
					Character.valueOf('T'), "glass", 
					Character.valueOf('H'), new ItemStack(Core.craftingItem, 1, CraftingMeta.HEATER), 
					Character.valueOf('M'), new ItemStack(Core.oreBlocks, 1, OresMeta.BASE_BRICK), 
					Character.valueOf('B'), Item.bucketLava,
					Character.valueOf('I'), Item.netherrackBrick }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.utilBlocks, 1, UtilMeta.SETTLER), new Object[] {
					" B ", "ITI", "HMH", 
					Character.valueOf('T'), "glass", 
					Character.valueOf('H'), new ItemStack(Core.craftingItem, 1, CraftingMeta.COOLER), 
					Character.valueOf('M'), new ItemStack(Core.oreBlocks, 1, OresMeta.BASE_IRON), 
					Character.valueOf('B'), Item.bucketWater,
					Character.valueOf('I'), Item.ingotIron }));
		
		if(!Loader.isModLoaded("ThermalExpansion")) {
			CraftingManager
					.getInstance()
					.getRecipeList()
					.add(new ShapedOreRecipe(new ItemStack(Core.utilBlocks, 1, UtilMeta.PURIFIER), new Object[] { 
						"TMR", 
						Character.valueOf('T'), Block.torchRedstoneActive, 
						Character.valueOf('R'), Item.redstone, 
						Character.valueOf('M'), new ItemStack(Core.oreBlocks, 1, OresMeta.BASE_IRON) }));
		}

        if(BlockIds.limestoneStairs != 0) {
            GameRegistry.addRecipe(new ItemStack(Core.limestoneStairs, 4), new Object[] { "B  ", "BB ", "BBB", Character.valueOf('B'),
                            new ItemStack(Core.oreBlocks, 1, OresMeta.LIMESTONE) });

            GameRegistry.addRecipe(new ItemStack(Core.limestoneBrickStairs, 4), new Object[] { "B  ", "BB ", "BBB",
                    Character.valueOf('B'), new ItemStack(Core.oreBlocks, 1, OresMeta.LIMESTONE_BRICK) });

            GameRegistry.addRecipe(new ItemStack(Core.limestoneSmoothStairs, 4), new Object[] { "B  ", "BB ", "BBB",
                    Character.valueOf('B'), new ItemStack(Core.oreBlocks, 1, OresMeta.LIMESTONE_SMOOTH) });

            GameRegistry.addRecipe(new ItemStack(Core.limestoneChiseledStairs, 4), new Object[] { "B  ", "BB ", "BBB",
                    Character.valueOf('B'), new ItemStack(Core.oreBlocks, 1, OresMeta.LIMESTONE_CHISELED) });

            GameRegistry.addRecipe(new ItemStack(Core.limestoneSlabs, 6, BlockLimestoneSlab.BlockType.ROUGH.metadata()),
                    new Object[] { "BBB", Character.valueOf('B'), new ItemStack(Core.oreBlocks, 1, OresMeta.LIMESTONE) });

            GameRegistry.addRecipe(new ItemStack(Core.limestoneSlabs, 6, BlockLimestoneSlab.BlockType.BRICK.metadata()),
                    new Object[] { "BBB", Character.valueOf('B'), new ItemStack(Core.oreBlocks, 1, OresMeta.LIMESTONE_BRICK) });

            GameRegistry.addRecipe(new ItemStack(Core.limestoneSlabs, 6, BlockLimestoneSlab.BlockType.SMOOTH.metadata()),
                    new Object[] { "BBB", Character.valueOf('B'), new ItemStack(Core.oreBlocks, 1, OresMeta.LIMESTONE_SMOOTH) });

            GameRegistry.addRecipe(new ItemStack(Core.limestoneSlabs, 6, BlockLimestoneSlab.BlockType.CHISELED.metadata()),
                    new Object[] { "BBB", Character.valueOf('B'), new ItemStack(Core.oreBlocks, 1, OresMeta.LIMESTONE_CHISELED) });
        }

		FurnaceRecipes.smelting().addSmelting(Core.oreBlocks.blockID, OresMeta.LIMESTONE,
				new ItemStack(Core.oreBlocks, 1, OresMeta.LIMESTONE_SMOOTH), 0.1F);

		for (int i = 0; i < 12; i++) {
			GameRegistry.addRecipe(new ItemStack(Core.pearlBrick, 1, i),
					new Object[] { "PP ", "PP ", Character.valueOf('P'), new ItemStack(Core.pearls, 1, i) });
			
			GameRegistry.addShapelessRecipe(new ItemStack(Core.pearls, 4, i),
					new Object[] { new ItemStack(Core.pearlBrick, 1, i) });
		}

		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Block.pistonBase, 1), new Object[] { "TTT", "#X#", "#R#",
						Character.valueOf('#'), Block.cobblestone, Character.valueOf('X'), "ingotAluminum",
						Character.valueOf('R'), Item.redstone, Character.valueOf('T'), "plankWood" }));
	}

	private static void addCraftingItems() {
		//Golden Silk
		MaricultureHandlers.freezer.addRecipe(new RecipeFreezer(FluidRegistry.getFluidStack(FluidDictionary.gold, MetalRates.INGOT * 5), 
				new ItemStack(Item.silk), new ItemStack(Core.craftingItem, 1, CraftingMeta.GOLDEN_SILK)));
		
		//Golden Thread
		GameRegistry.addRecipe(new ItemStack(Core.craftingItem, 1, CraftingMeta.GOLDEN_THREAD), new Object[] { "ABA", "ABA", 
				Character.valueOf('A'), new ItemStack(Core.craftingItem, 1, CraftingMeta.GOLDEN_SILK), 
				Character.valueOf('B'), new ItemStack(Core.craftingItem, 1, CraftingMeta.POLISHED_STICK) });
		//Polished Stick		
		MaricultureHandlers.freezer.addRecipe(new RecipeFreezer(FluidRegistry.getFluidStack(FluidDictionary.fish_oil, FluidContainerRegistry.BUCKET_VOLUME * 5), 
				new ItemStack(Item.stick), new ItemStack(Core.craftingItem, 1, CraftingMeta.POLISHED_STICK)));
		
		//Titanium Rod	
		if(Loader.isModLoaded("TConstruct")) {
			PluginTinkersConstruct.addRod = true;
		} else {
			MaricultureHandlers.freezer.addRecipe(new RecipeFreezer(FluidRegistry.getFluidStack(FluidDictionary.titanium, MetalRates.NUGGET * 12), 
					new ItemStack(Core.craftingItem, 2, CraftingMeta.POLISHED_STICK), new ItemStack(Core.craftingItem, 1, CraftingMeta.ROD_TITANIUM)));
		}
		//Neoprene
		GameRegistry.addRecipe(new ItemStack(Core.craftingItem, 1, CraftingMeta.NEOPRENE), new Object[] { "IPI", "PEP", "IPI", 
				Character.valueOf('I'), new ItemStack(Item.dyePowder, 1, Dye.INK), 
				Character.valueOf('P'), new ItemStack(Core.pearls, 1, OreDictionary.WILDCARD_VALUE), 
				Character.valueOf('E'), new ItemStack(Core.liquidContainers, 1, FluidContainerMeta.BOTTLE_GAS) });
		//Plastic
		MaricultureHandlers.freezer.addRecipe(new RecipeFreezer(FluidRegistry.getFluidStack(FluidDictionary.natural_gas, FluidContainerRegistry.BUCKET_VOLUME * 32), 
				new ItemStack(Core.oreBlocks, 16, OresMeta.LIMESTONE), new ItemStack(Core.craftingItem, 1, CraftingMeta.PLASTIC)));
		//Plastic Lens
		GameRegistry.addRecipe(new ItemStack(Core.craftingItem, 1, CraftingMeta.LENS), new Object[] { " N ", "NGN", " N ", 
				Character.valueOf('N'), new ItemStack(Core.craftingItem, 1, CraftingMeta.NEOPRENE), 
				Character.valueOf('G'), new ItemStack(Core.glassBlocks, 1, GlassMeta.PLASTIC) });
		//Aluminum Sheet
		GameRegistry.addRecipe(new ItemStack(Core.craftingItem, 3, CraftingMeta.ALUMINUM_SHEET), new Object[] { "A A", " F ", "A A", 
				Character.valueOf('A'), new ItemStack(Core.materials, 1, MaterialsMeta.INGOT_ALUMINUM),
				Character.valueOf('F'), Item.flintAndSteel });
		//Heating
		GameRegistry.addRecipe(new ItemStack(Core.craftingItem, 1, CraftingMeta.HEATER), new Object[] { "CCC", "CCC",
				Character.valueOf('C'), new ItemStack(Core.craftingItem, 1, CraftingMeta.CARBIDE) });
		//Cooling
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.craftingItem, 1, CraftingMeta.COOLER), new Object[] { "  P", "PI ", "  P", 
					Character.valueOf('P'), "plankWood", 
					Character.valueOf('I'), Item.ingotIron }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.craftingItem, 1, CraftingMeta.COOLER), new Object[] { " P ", " I ", "P P", 
					Character.valueOf('P'), "plankWood", 
					Character.valueOf('I'), Item.ingotIron }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.craftingItem, 1, CraftingMeta.COOLER), new Object[] { "P  ", " IP", "P  ", 
					Character.valueOf('P'), "plankWood", 
					Character.valueOf('I'), Item.ingotIron }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.craftingItem, 1, CraftingMeta.COOLER), new Object[] { "P P", " I ", " P ", 
					Character.valueOf('P'), "plankWood", 
					Character.valueOf('I'), Item.ingotIron }));
		//Carbide
		GameRegistry.addRecipe(new ItemStack(Core.craftingItem, 3, CraftingMeta.CARBIDE), new Object[] { "CSF", "FBS", "SFC", 
				Character.valueOf('C'), Item.clay, 
				Character.valueOf('F'), new ItemStack(Item.coal, 1, 0),
				Character.valueOf('S'), Block.sand,
				Character.valueOf('B'), Block.blockClay});
		//Wheel
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.craftingItem, 1, CraftingMeta.WHEEL), new Object[] { " I ", "ISI", " I ", 
					Character.valueOf('S'), "slabWood", 
					Character.valueOf('I'), Item.ingotIron }));
		//Wicker
		CraftingManager
			.getInstance()
			.getRecipeList()
			.add(new ShapedOreRecipe(new ItemStack(Core.craftingItem, 1, CraftingMeta.WICKER), new Object[] { "CAC", "ACA", "CAC", 
				Character.valueOf('A'), "stickWood", 
				Character.valueOf('C'), Item.reed }));
		//Yellow Plastic
		MaricultureHandlers.freezer.addRecipe(new RecipeFreezer(FluidRegistry.getFluidStack(FluidDictionary.gold, MetalRates.BLOCK), 
				new ItemStack(Core.craftingItem, 4, CraftingMeta.PLASTIC), new ItemStack(Core.craftingItem, 1, CraftingMeta.PLASTIC_YELLOW)));
		//Melt Yellow/Gold plastic back to a block of gold and 1 piece of plastic
		MaricultureHandlers.smelter.addRecipe(new RecipeSmelter(new ItemStack(Core.craftingItem, 1, CraftingMeta.PLASTIC_YELLOW), RecipesSmelting.gold, 
				new SmelterOutput(FluidRegistry.getFluidStack(FluidDictionary.gold, MetalRates.BLOCK), 
							new ItemStack(Core.craftingItem, 1, CraftingMeta.PLASTIC), 1)));
	}
	
	private static void addMetalRecipes() {		
		MaricultureHandlers.freezer.addRecipe(new RecipeFreezer(FluidRegistry.getFluidStack(FluidRegistry.WATER.getName(), FluidContainerRegistry.BUCKET_VOLUME * 25), 
				new ItemStack(Core.oreBlocks, 32, OresMeta.LIMESTONE), new ItemStack(Core.materials, 1, MaterialsMeta.INGOT_MAGNESIUM)));

		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.oreBlocks, 1, OresMeta.ALUMINUM_BLOCK), true, new Object[] { "###", "###", "###", 
						Character.valueOf('#'), "ingotAluminum" }));

		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapelessOreRecipe(new ItemStack(Core.materials, 9, MaterialsMeta.INGOT_ALUMINUM), new Object[] { "blockAluminum" }));

		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.oreBlocks, 1, OresMeta.COPPER_BLOCK), new Object[] { "###", "###", "###", 
					Character.valueOf('#'), "ingotCopper" }));

		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapelessOreRecipe(new ItemStack(Core.materials, 9, MaterialsMeta.INGOT_COPPER), new Object[] { "blockCopper" }));

		FurnaceRecipes.smelting().addSmelting(Core.oreBlocks.blockID, OresMeta.COPPER, new ItemStack(Core.materials, 1, MaterialsMeta.INGOT_COPPER), 0.5F);

		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapelessOreRecipe(new ItemStack(Core.materials, 9, MaterialsMeta.INGOT_TITANIUM), new Object[] { "blockTitanium" }));

		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.oreBlocks, 1, OresMeta.TITANIUM_BLOCK), true, new Object[] { "###", "###", "###", 
					Character.valueOf('#'), "ingotTitanium" }));

		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapelessOreRecipe(new ItemStack(Core.materials, 9, MaterialsMeta.INGOT_MAGNESIUM), new Object[] { "blockMagnesium" }));

		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.oreBlocks, 1, OresMeta.MAGNESIUM_BLOCK), true, new Object[] { "###", "###", "###", 
					Character.valueOf('#'), "ingotMagnesium" }));
	}
	
	private static void addUpgradeRecipes() {
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.BASIC_STORAGE), new Object[] {
						"WPW", "DCD", "WPW", Character.valueOf('D'), 
						Block.dirt, Character.valueOf('P'), "plankWood",
						Character.valueOf('C'), Block.chest, 
						Character.valueOf('W'), new ItemStack(Core.craftingItem, 1, CraftingMeta.WICKER) }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.STANDARD_STORAGE), new Object[] {
						"PWP", "SUS", "PWP", 
						Character.valueOf('S'), "ingotCopper", 
						Character.valueOf('P'), "slabWood",
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.BASIC_STORAGE),
						Character.valueOf('W'), new ItemStack(Core.craftingItem, 1, CraftingMeta.WICKER) }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.ADVANCED_STORAGE), new Object[] {
						"AWA", "MUM", "WAW", 
						Character.valueOf('A'), "ingotCopper", 
						Character.valueOf('M'), "ingotAluminum", 
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.STANDARD_STORAGE), 
						Character.valueOf('W'), new ItemStack(Core.craftingItem, 1, CraftingMeta.WICKER) }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.ULTIMATE_STORAGE), new Object[] {
						"ATA", "WUW", "TCT", 
						Character.valueOf('A'), "ingotCopper", 
						Character.valueOf('T'), "ingotAluminum", 
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.ADVANCED_STORAGE), 
						Character.valueOf('W'), new ItemStack(Core.craftingItem, 1, CraftingMeta.WICKER), 
						Character.valueOf('C'), new ItemStack(Core.craftingItem, 1, CraftingMeta.WHEEL) }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.BASIC_COOLING), new Object[] {
						" S ", "CBC", " S ", 
						Character.valueOf('S'), Item.snowball, 
						Character.valueOf('B'), Block.blockSnow, 
						Character.valueOf('C'), new ItemStack(Core.craftingItem, 1, CraftingMeta.COOLER) }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.STANDARD_COOLING), new Object[] {
						"ACA", "SUS", "CAC", 
						Character.valueOf('S'), Item.snowball, 
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.BASIC_COOLING), 
						Character.valueOf('C'), new ItemStack(Core.craftingItem, 1, CraftingMeta.COOLER), 
						Character.valueOf('A'), "ingotAluminum" }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.ADVANCED_COOLING), new Object[] {
						"CTC", "IUI", "TRT", 
						Character.valueOf('I'), Block.ice, 
						Character.valueOf('R'), Item.ingotIron,
						Character.valueOf('C'), new ItemStack(Core.craftingItem, 1, CraftingMeta.COOLER),
						Character.valueOf('T'), "ingotTitanium", 
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.STANDARD_COOLING) }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.ULTIMATE_COOLING), new Object[] {
						"TCT", "IUI", "GDG", 
						Character.valueOf('I'), Block.ice, 
						Character.valueOf('G'), Item.ingotGold,
						Character.valueOf('C'), new ItemStack(Core.craftingItem, 1, CraftingMeta.COOLER),
						Character.valueOf('T'), "ingotTitanium", 
						Character.valueOf('D'), Item.diamond,
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.ADVANCED_COOLING) }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.BASIC_HEATING), new Object[] {
						"HIH", 
						Character.valueOf('I'), Item.ingotIron, 
						Character.valueOf('H'), new ItemStack(Core.craftingItem, 1, CraftingMeta.HEATER) }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.STANDARD_HEATING), new Object[] {
						"A A", "HUH", " A ", 
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.BASIC_HEATING), 
						Character.valueOf('H'), new ItemStack(Core.craftingItem, 1, CraftingMeta.HEATER), 
						Character.valueOf('A'), "ingotAluminum" }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.ADVANCED_HEATING), new Object[] {
						"IHI", "TUT", "IHI", 
						Character.valueOf('T'), "ingotTitanium", 
						Character.valueOf('I'), Item.ingotIron, 
						Character.valueOf('H'), new ItemStack(Core.craftingItem, 1, CraftingMeta.HEATER), 
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.STANDARD_HEATING) }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.ULTIMATE_HEATING), new Object[] {
						"TDT", "HUH", "GTG", 
						Character.valueOf('G'), Item.ingotGold, 
						Character.valueOf('H'), new ItemStack(Core.craftingItem, 1, CraftingMeta.HEATER), 
						Character.valueOf('T'),
						"ingotTitanium", 
						Character.valueOf('D'), Item.diamond, 
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.ADVANCED_HEATING) }));
		
		ItemStack heart = (Modules.fishery.isActive())? new ItemStack(Core.materials, 1, MaterialsMeta.DROP_HEALTH): new ItemStack(Item.potion, 1, 8197);
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.BASIC_PURITY), new Object[] {
						"MPM", "PIP", "MPM", 
						Character.valueOf('P'), new ItemStack(Core.pearls, 1, PearlColor.WHITE),
						Character.valueOf('I'), heart, 
						Character.valueOf('M'), "ingotAluminum" }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.STANDARD_PURITY), new Object[] {
						"PHP", "NUN", "MHM", 
						Character.valueOf('P'), new ItemStack(Core.pearls, 1, PearlColor.WHITE),
						Character.valueOf('H'), heart, 
						Character.valueOf('M'), "ingotAluminum",
						Character.valueOf('N'), "ingotTitanium",
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.BASIC_PURITY) }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.ADVANCED_PURITY), new Object[] {
						"PSP", "AUA", "TPT", 
						Character.valueOf('P'), new ItemStack(Core.pearls, 1, PearlColor.WHITE),
						Character.valueOf('S'), new ItemStack(Core.craftingItem, 1, CraftingMeta.GOLDEN_SILK),
						Character.valueOf('T'), "ingotTitanium", 
						Character.valueOf('A'), heart,
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.STANDARD_PURITY) }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.ULTIMATE_PURITY), new Object[] {
						"PSP", "TUT", "PAP", 
						Character.valueOf('P'), new ItemStack(Core.pearls, 1, PearlColor.WHITE),
						Character.valueOf('S'), new ItemStack(Core.craftingItem, 1, CraftingMeta.GOLDEN_THREAD),
						Character.valueOf('T'), "ingotTitanium", 
						Character.valueOf('A'), heart,
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.ADVANCED_PURITY) }));
		
		ItemStack attack = (Modules.fishery.isActive())? new ItemStack(Core.materials, 1, MaterialsMeta.DROP_ATTACK): new ItemStack(Item.potion, 1, 8204);
		ItemStack poison = (Modules.fishery.isActive())? new ItemStack(Core.materials, 1, MaterialsMeta.DROP_POISON): new ItemStack(Item.potion, 1, 8228);
		ItemStack night = (Modules.fishery.isActive())? new ItemStack(Fishery.fishyFood, 1, Fishery.night.fishID): new ItemStack(Item.enderPearl);
		ItemStack ender = (Modules.fishery.isActive())? new ItemStack(Fishery.fishyFood, 1, Fishery.ender.fishID): new ItemStack(Item.eyeOfEnder);
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.BASIC_IMPURITY), new Object[] {
						"NWN", "ESE", "NGN", 
						Character.valueOf('N'), Block.netherrack, 
						Character.valueOf('W'), Item.netherStalkSeeds, 
						Character.valueOf('E'), Item.fermentedSpiderEye, 
						Character.valueOf('S'), attack,
						Character.valueOf('G'), Item.ingotGold }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.STANDARD_IMPURITY), new Object[] {
						"TGT", "SUS", "PFP", 
						Character.valueOf('T'), Item.ghastTear, 
						Character.valueOf('F'), Item.fermentedSpiderEye,
						Character.valueOf('P'), poison, 
						Character.valueOf('S'), attack, 
						Character.valueOf('G'),
						new ItemStack(Core.craftingItem, 1, CraftingMeta.GOLDEN_SILK), 
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.BASIC_IMPURITY) }));
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.ADVANCED_IMPURITY), new Object[] {
						"HGH", "FUF", "SPS", 
						Character.valueOf('F'), night, 
						Character.valueOf('P'), poison, 
						Character.valueOf('H'), attack, 
						Character.valueOf('S'), Item.fermentedSpiderEye,
						Character.valueOf('G'), Block.blockGold, 
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.STANDARD_IMPURITY) }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.ULTIMATE_IMPURITY), new Object[] {
						"SGS", "PUP", "FSF", 
						Character.valueOf('F'), ender, 
						Character.valueOf('P'), poison, 
						Character.valueOf('S'), attack,
						Character.valueOf('G'), new ItemStack(Core.craftingItem, 1, CraftingMeta.GOLDEN_THREAD),
						Character.valueOf('U'), new ItemStack(Core.upgrade, 1, UpgradeMeta.ADVANCED_IMPURITY) }));
		
		CraftingManager
				.getInstance()
				.getRecipeList()
				.add(new ShapedOreRecipe(new ItemStack(Core.upgrade, 1, UpgradeMeta.ETHEREAL), new Object[] { "PUP",
						"GEG", "PDP", 
						Character.valueOf('P'), Item.enderPearl, 
						Character.valueOf('G'), Item.ingotGold,
						Character.valueOf('E'), Item.eyeOfEnder, 
						Character.valueOf('D'), Item.diamond,
						Character.valueOf('U'), Block.torchRedstoneActive }));
	}
}