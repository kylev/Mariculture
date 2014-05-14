package mariculture.factory.tile;

import java.util.ArrayList;

import mariculture.core.util.PowerHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

public class TileCustomPowered extends TileCustom implements IEnergyHandler {
	private int tick;
	private ForgeDirection cameFrom;
	private EnergyStorage storage = new EnergyStorage(6000);
	
public static ArrayList<Object[]> handlers = new ArrayList();
	
	static {
		handlers.add(new Object[] { -1, 0, 0, ForgeDirection.WEST });
		handlers.add(new Object[] { 0, 0, 1, ForgeDirection.SOUTH });
		handlers.add(new Object[] { 0, 1, 0, ForgeDirection.UP });
		handlers.add(new Object[] { 0, 0, -1, ForgeDirection.NORTH });
		handlers.add(new Object[] { 1, 0, 0, ForgeDirection.EAST });
		handlers.add(new Object[] { 0, -1, 0, ForgeDirection.DOWN });
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		storage.readFromNBT(tagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		storage.writeToNBT(tagCompound);
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		cameFrom = from;
		if(this.storage.getEnergyStored() > 0) {
			IEnergyHandler handler = null;
			Object[] res = PowerHelper.getNextEnergyHandler(cameFrom, worldObj, xCoord, yCoord, zCoord);
			if(res[0] != null) {
				handler = (IEnergyHandler) res[0];
				cameFrom = (ForgeDirection) res[1];
			}
					
			if(handler != null) {
				if(handler.canConnectEnergy(cameFrom)) {
		            this.storage.modifyEnergyStored(-(handler).receiveEnergy(cameFrom.getOpposite(), Math.min(this.storage.getMaxEnergyStored(), this.storage.getEnergyStored()), false));
		       }
			}
		}
		
		return this.storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return this.storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return this.storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return this.storage.getMaxEnergyStored();
	}
}
