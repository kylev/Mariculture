package mariculture.magic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMagicMirror extends ItemMirror {
	int minLevel;
	int maxLevel;
	
	public ItemMagicMirror(int id, int min, int max, String str) {
		super(id, str);
		minLevel = min;
		maxLevel = max;
		setMaxDamage(0);
		setHasSubtypes(true);
		setNoRepair();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
	
	@Override
	public String getItemDisplayName(ItemStack stack) {
		stack = get(stack);
		return StatCollector.translateToLocal(getUnlocalizedName(stack) + ".name") 
				+ " (" + stack.getTagCompound().getIntArray("Levels")[2] + ")";
	}
	
	public ItemStack get(ItemStack stack) {
		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			stack.stackTagCompound.setIntArray("Levels", new int[] { minLevel, minLevel + 1, minLevel + 2 });
		}
		
		return stack;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!stack.hasTagCompound()) {
			stack = get(stack);
		}
		
		if(player.isSneaking()) {
			int[] vals = stack.stackTagCompound.getIntArray("Levels");
			if(vals[2] + 1 > maxLevel) {
				vals[0] = minLevel;
			} else {
				vals[0]+=3;
			}
				
			vals[1] = vals[0] + 1;
			vals[2] = vals[1] + 1;
				
			stack.stackTagCompound.setIntArray("Levels", vals);
		}
		
		if (world.isRemote && player.isSneaking()) {
			if(!stack.hasTagCompound()) {
				stack.setTagCompound(new NBTTagCompound());
			}
			
			if (stack.stackTagCompound.getBoolean("Display") == false) {
				stack.stackTagCompound.setBoolean("Display", true);
			} else {
				stack.stackTagCompound.setBoolean("Displays", false);
			}
		}
		
		return super.onItemRightClick(stack, world, player);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {
		return false;
	}
}