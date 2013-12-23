package mariculture.factory.gui;

import mariculture.api.core.IItemUpgrade;
import mariculture.core.gui.SlotUpgrade;
import mariculture.core.util.ContainerInteger;
import mariculture.factory.Factory;
import mariculture.factory.blocks.TilePressureVessel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPressureVessel extends ContainerInteger {
	private TilePressureVessel tile;

	public ContainerPressureVessel(TilePressureVessel tile, InventoryPlayer playerInventory) {
		this.tile = tile;

		this.addSlotToContainer(new Slot(tile, 0, 34, 34));

		for (int i = 0; i < 3; i++) {
			addSlotToContainer(new SlotUpgrade(tile, i + 1, 148, 16 + (i * 18)));
		}

		bindPlayerInventory(playerInventory);
	}

	private void bindPlayerInventory(final InventoryPlayer playerInventory) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < crafters.size(); i++) {
			tile.sendGUINetworkData(this, (EntityPlayer) crafters.get(i));
		}

	}

	@Override
	public void updateProgressBar(final int par1, final int par2) {
		tile.getGUINetworkData(par1, par2);
	}

	@Override
	public boolean canInteractWith(final EntityPlayer player) {
		return tile.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
		int size = tile.getSizeInventory();
		int low = size + 27;
		int high = low + 9;
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotID);

		if (slot != null && slot.getHasStack()) {
			ItemStack stack = slot.getStack();
			itemstack = stack.copy();

			if (slotID < size) {
				if (!this.mergeItemStack(stack, size, high, true)) {
					return null;
				}

				slot.onSlotChange(stack, itemstack);
			} else if (slotID >= size) {
				if (itemstack.itemID == Factory.fludd.itemID) {
					if (!this.mergeItemStack(stack, 0, 1, false)) { // Slot 0-0
						return null;
					}
				} else if (stack.getItem() instanceof IItemUpgrade) {
					if (!this.mergeItemStack(stack, 1, 4, false)) { // Slot 1-3
						return null;
					}
				} else if (slotID >= size && slotID < low) {
					if (!this.mergeItemStack(stack, low, high, false)) {
						return null;
					}
				} else if (slotID >= low && slotID < high && !this.mergeItemStack(stack, high, low, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(stack, size, high, false)) {
				return null;
			}

			if (stack.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (stack.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, stack);
		}

		return itemstack;
	}
}