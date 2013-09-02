package eurymachus.mtd.blocks;

import java.util.Random;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import slimevoidlib.IContainer;
import eurymachus.mtd.core.MTDBlocks;
import eurymachus.mtd.core.MTDCore;
import eurymachus.mtd.core.MTDInit;
import eurymachus.mtd.core.MTDItemDoors;
import eurymachus.mtd.core.MTDItemSensibleDoors;
import eurymachus.mtd.tileentities.TileEntityMTDoor;

public class BlockMTDoor extends BlockDoor implements IContainer {
	private Icon[][] iconListUpper;
	private Icon[][] iconListLower;
	Class mtDoorEntityClass;

	public BlockMTDoor(int par1, Class doorClass, float hardness, StepSound sound, boolean disableStats, boolean requiresSelfNotify, String blockName, Material material) {
		super(par1, material);
		this.setUnlocalizedName(blockName);
		this.isBlockContainer = true;
		mtDoorEntityClass = doorClass;
		setHardness(hardness);
		setStepSound(sound);
		if (disableStats) {
			disableStats();
		}
		if (requiresSelfNotify) {
			//setRequiresSelfNotify();
		}
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		if (this.blockID == MTDBlocks.mtDoor.id) {
			this.iconListUpper = new Icon[MTDItemDoors.values().length][2];
			for (int i = 0; i < iconListUpper.length; i++) {
				this.iconListUpper[i][0] = iconRegister.registerIcon(MTDItemDoors.getTexture(i) + "_upper");
			}
			for (int i = 0; i < iconListUpper.length; i++) {
				this.iconListUpper[i][1] = new IconFlipped(this.iconListUpper[i][0], true, false);
			}
			this.iconListLower = new Icon[MTDItemDoors.values().length][2];
			for (int i = 0; i < iconListLower.length; i++) {
				this.iconListLower[i][0] = iconRegister.registerIcon(MTDItemDoors.getTexture(i) + "_lower");
			}
			for (int i = 0; i < iconListLower.length; i++) {
				this.iconListLower[i][1] = new IconFlipped(this.iconListLower[i][0], true, false);
			}
		} else {
			this.iconListUpper = new Icon[MTDItemSensibleDoors.values().length][2];
			for (int i = 0; i < iconListUpper.length; i++) {
				this.iconListUpper[i][0] = iconRegister.registerIcon(MTDItemSensibleDoors.getTexture(i) + "_upper");
			}
			for (int i = 0; i < iconListUpper.length; i++) {
				this.iconListUpper[i][1] = new IconFlipped(this.iconListUpper[i][0], true, false);
			}
			this.iconListLower = new Icon[MTDItemSensibleDoors.values().length][2];
			for (int i = 0; i < iconListLower.length; i++) {
				this.iconListLower[i][0] = iconRegister.registerIcon(MTDItemSensibleDoors.getTexture(i) + "_lower");
			}
			for (int i = 0; i < iconListLower.length; i++) {
				this.iconListLower[i][1] = new IconFlipped(this.iconListLower[i][0], true, false);
			}
		}
	}

	@Override
	public Icon getBlockTexture(IBlockAccess blockaccess, int x, int y, int z, int side) {
		if (side != 1 && side != 0)
        {
            int fullMeta = this.getFullMetadata(blockaccess, x, y, z);
            int doorSide = fullMeta & 3;
            boolean isOpen = (fullMeta & 4) != 0;
            boolean isFront = false;
            boolean isUpper = (fullMeta & 8) != 0;

            if (isOpen)
            {
                if (doorSide == 0 && side == 2)
                {
                    isFront = !isFront;
                }
                else if (doorSide == 1 && side == 5)
                {
                    isFront = !isFront;
                }
                else if (doorSide == 2 && side == 3)
                {
                    isFront = !isFront;
                }
                else if (doorSide == 3 && side == 4)
                {
                    isFront = !isFront;
                }
            }
            else
            {
                if (doorSide == 0 && side == 5)
                {
                    isFront = !isFront;
                }
                else if (doorSide == 1 && side == 3)
                {
                    isFront = !isFront;
                }
                else if (doorSide == 2 && side == 4)
                {
                    isFront = !isFront;
                }
                else if (doorSide == 3 && side == 2)
                {
                    isFront = !isFront;
                }

                if ((fullMeta & 16) != 0)
                {
                    isFront = !isFront;
                }
            }

            return isUpper
            		? this.iconListUpper[MTDInit.getDamageValue(blockaccess, x, y, z)][isFront ? 1 : 0]
            		: this.iconListLower[MTDInit.getDamageValue(blockaccess, x, y, z)][isFront ? 1 : 0];
        }
        else
        {
            return this.iconListUpper[0][0];
        }
	}

	@Override
	public Icon getIcon(int side, int metadata) {
		return null;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int side, int metadata) {
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		if (tileentity != null && tileentity instanceof TileEntityMTDoor) {
			int id = ((TileEntityMTDoor) tileentity)
					.getTextureValue();
			int piece =((TileEntityMTDoor) tileentity)
					.getDoorPiece(); 
			if (piece == 0) {
				ItemStack itemstack;
				if (this.blockID == MTDBlocks.mtDoor.id) {
					itemstack = MTDItemDoors.getStack(id);
				} else {
					itemstack = MTDItemSensibleDoors.getStack(id);
				}
				EntityItem entityitem = new EntityItem(
						world,
							x,
							y,
							z,
							new ItemStack(
									itemstack.itemID,
										1,
										itemstack.getItemDamage()));
				world.spawnEntityInWorld(entityitem);
			}
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
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType() {
		return MTDCore.mtDoorRenderID;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}
}
