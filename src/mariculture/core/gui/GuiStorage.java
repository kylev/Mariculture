package mariculture.core.gui;

import mariculture.core.items.ItemStorage;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GuiStorage extends GuiMariculture {
	private final InventoryStorage storage;

	public GuiStorage(IInventory playerInv, InventoryStorage storage, World world, String gui) {
		super(new ContainerStorage(playerInv, storage, world), gui);
		this.storage = storage;
		((ItemStorage)storage.player.getCurrentEquippedItem().getItem()).addFeatures(features);
	}

	@Override
	public String getName() {
		if(storage != null) {
			ItemStack stack = storage.player.getCurrentEquippedItem();
			if(stack != null & stack.getItem() instanceof ItemStorage) {
				ItemStorage item = (ItemStorage)stack.getItem();
				return item.getName(stack);
			}
		}
		
		return "";
	}

	@Override
	public int getX() {
		if(storage != null) {
			ItemStack stack = storage.player.getCurrentEquippedItem();
			if(stack != null & stack.getItem() instanceof ItemStorage) {
				ItemStorage item = (ItemStorage)stack.getItem();
				return item.getX(stack);
			}
		}
		
		return 0;
	}
}