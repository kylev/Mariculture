package mariculture.core.blocks;

import java.util.Random;

import mariculture.Mariculture;
import mariculture.core.Core;
import mariculture.core.items.ItemFluidContainer;
import mariculture.core.lib.FluidContainerMeta;
import mariculture.core.util.Rand;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.FakePlayer;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidMari extends BlockFluidClassic {
	public Icon[] still;
	public Icon[] flowing;
	
	public BlockFluidMari(int id, Fluid fluid, Material material) {
		super(id, fluid, material);
		quantaPerBlock = 16;
		quantaPerBlockFloat = 16;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return meta == 0 ? Block.waterStill.getIcon(side, meta) : Block.waterMoving.getIcon(side, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		flowing = new Icon[FluidContainerMeta.COUNT];
		still = new Icon[FluidContainerMeta.COUNT];

		for (int i = 0; i < flowing.length; i++) {			
			ItemStack bottle = new ItemStack(Core.liquidContainers, 1, i);
			FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(bottle);
			if(fluid != null) {
				String name = (fluid.getFluid()).getName().replaceAll("\\.","");
				
				if(name.contains("molten")) {
					name = "molten" + name.replaceAll("molten", "");
				}

				if(name.contains("fish")) {
					name = "fish" + name.replaceAll("fish", "");
				}
				
				if(name.contains("natural")) {
					name = "natural" + name.replaceAll("natural", "");
				}
				
				if(!name.contains("normal") && !name.contains("fastwater")) {
					flowing[i] = iconRegister.registerIcon(Mariculture.modid + ":liquids/" + name + "_flow");
					still[i] = iconRegister.registerIcon(Mariculture.modid + ":liquids/" + name + "_still");
				}
			}
		}
	}
	
	@Override
    public FluidStack drain(World world, int x, int y, int z, boolean doDrain) {
        return null;
    }
	
	private int tick;
	@Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		tick++;
		
		if(tick % 25 == 0 && !world.isRemote) {
			int x2 = x + rand.nextInt(3) - 1;
			int y2 = y - rand.nextInt(2);
			int z2 = z + rand.nextInt(3) - 1;
			Block block = Block.blocksList[world.getBlockId(x2, y, z2)];
			if(block != null) {
				float hardnessMax = Block.stone.blockHardness;
				float blockHardness = block.getBlockHardness(world, x2, y2, z2);
				if(blockHardness <= hardnessMax) {
					int meta = world.getBlockMetadata(x2, y2, z2);
					FakePlayer player = new FakePlayer(world, "hpwater");
					if (block.removeBlockByPlayer(world, player, x2, y2, z2)) {
                            block.onBlockDestroyedByPlayer(world, x2, y2, z2, meta);
                    }
						
                    block.harvestBlock(world, player, x2, y2, z2, meta);
                    block.onBlockHarvested(world, x2, y2, z2, meta, player);
				}
			}
		}
    }

	@Override
	public void velocityToAddToEntity(World world, int x, int y, int z, Entity entity, Vec3 vec) {
		Vec3 vec_flow = this.getFlowVector(world, x, y, z);

		double xSpeed = vec_flow.xCoord * 0.0845;
		double zSpeed = vec_flow.zCoord * 0.0845;

		entity.addVelocity(xSpeed, 0, zSpeed);
	}
}
