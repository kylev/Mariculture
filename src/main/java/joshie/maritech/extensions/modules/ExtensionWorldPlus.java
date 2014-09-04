package joshie.maritech.extensions.modules;

import joshie.mariculture.core.lib.Modules;
import joshie.mariculture.world.WorldPlus;
import joshie.maritech.util.IModuleExtension;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class ExtensionWorldPlus implements IModuleExtension {
    @Override
    public void preInit() {
        return;
    }

    @Override
    public void init() {
        return;
    }

    @Override
    public void postInit() {
        if (Modules.isActive(Modules.factory)) {
            ChestGenHooks.addItem(WorldPlus.OCEAN_CHEST, new WeightedRandomChestContent(new ItemStack(ExtensionFactory.fludd), 1, 2, 1));
        }
    }
}
