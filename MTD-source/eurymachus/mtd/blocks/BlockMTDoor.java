package eurymachus.mtd.blocks;

import java.util.Random;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import eurymachus.mtd.core.MTDBlocks;
import eurymachus.mtd.core.MTDCore;
import eurymachus.mtd.core.MTDInit;
import eurymachus.mtd.core.MTDItemDoors;
import eurymachus.mtd.core.MTDItemSensibleDoors;
import eurymachus.mtd.tileentities.TileEntityMTDoor;
import eurysmods.api.IContainer;

public class BlockMTDoor extends BlockDoor implements IContainer {
	Class mtDoorEntityClass;

	public BlockMTDoor(int par1, Class doorClass, float hardness, StepSound sound, boolean disableStats, boolean requiresSelfNotify, String blockName, Material material) {
		super(par1, material);
		this.setBlockName(blockName);
		this.isBlockContainer = true;
		this.blockIndexInTexture = 1;
		mtDoorEntityClass = doorClass;
		setHardness(hardness);
		setStepSound(sound);
		if (disableStats) {
			disableStats();
		}
		if (requiresSelfNotify) {
			setRequiresSelfNotify();
		}
	}

	@Override
	public TileEntity createNewTileEntity(World par1World) {
		try {
			return (TileEntity) this.mtDoorEntityClass.newInstance();
		} catch (Exception var3) {
			throw new RuntimeException(var3);
		}
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return createNewTileEntity(world);
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		par1World.setBlockTileEntity(
				par2,
				par3,
				par4,
				this.createTileEntity(
						par1World,
						par1World.getBlockMetadata(par2, par3, par4)));
	}

	/**
	 * Retrieves the block texture to use based on the display side. Args:
	 * iBlockAccess, x, y, z, side
	 */
	@Override
	public int getBlockTexture(IBlockAccess blockaccess, int x, int y, int z, int side) {
		int index = this.blockIndexInTexture;
		int staticIndex = index;
		if (blockaccess.getBlockId(x, y, z) == MTDBlocks.mtDoor.id) {
			index = MTDItemDoors.getTexture(MTDInit.getDamageValue(
					blockaccess,
					x,
					y,
					z));
			staticIndex = index;
		} else {
			index = MTDItemSensibleDoors.getTexture(MTDInit.getDamageValue(
					blockaccess,
					x,
					y,
					z));
			staticIndex = index;
		}
		if (side != 0 && side != 1) {
			int fullMeta = this.getFullMetadata(blockaccess, x, y, z);
			if ((fullMeta & 8) != 0) {
				index -= 16;
			}

			int doorSide = fullMeta & 3;
			boolean state = (fullMeta & 4) != 0;

			if (!state) {
				if (doorSide == 0 && side == 5) {
					index = -index;
				} else if (doorSide == 1 && side == 3) {
					index = -index;
				} else if (doorSide == 2 && side == 4) {
					index = -index;
				} else if (doorSide == 3 && side == 2) {
					index = -index;
				}

				if ((fullMeta & 16) != 0) {
					index = -index;
				}
			} else if (doorSide == 0 && side == 2) {
				index = -index;
			} else if (doorSide == 1 && side == 5) {
				index = -index;
			} else if (doorSide == 2 && side == 3) {
				index = -index;
			} else if (doorSide == 3 && side == 4) {
				index = -index;
			}

			return index;
		} else {
			return staticIndex;
		}
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return MTDInit.MTD.getProxy().getBlockTextureFromMetadata(par2);
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType() {
		return MTDCore.mtDoorRenderID;
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, int a, int b) {
		TileEntity tileentity = world.getBlockTileEntity(i, j, k);
		if (tileentity != null && tileentity instanceof TileEntityMTDoor) {
			int doorPiece = ((TileEntityMTDoor) tileentity).getDoorPiece();
			if (doorPiece == 0) {
				if (world.getWorldInfo().getGameType() != EnumGameType.CREATIVE) {
					int door = ((TileEntityMTDoor) tileentity)
							.getTextureValue();
					ItemStack itemstack = MTDItemDoors.getStack(door);
					EntityItem entityitem = new EntityItem(
							world,
								i,
								j,
								k,
								new ItemStack(
										itemstack.itemID,
											1,
											itemstack.getItemDamage()));
					world.spawnEntityInWorld(entityitem);
				}
			}
			super.breakBlock(world, i, j, k, a, b);
			world.removeBlockTileEntity(i, j, k);
		}
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public String getTextureFile() {
		return MTDInit.MTD.getBlockSheet();
	}
}
