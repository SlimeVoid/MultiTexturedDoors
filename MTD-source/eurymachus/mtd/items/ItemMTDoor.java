package eurymachus.mtd.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import eurymachus.mtd.core.MTDBlocks;
import eurymachus.mtd.core.MTDInit;
import eurymachus.mtd.core.MTDItemDoors;
import eurymachus.mtd.core.MTDItemSensibleDoors;
import eurymachus.mtd.core.MTDItems;
import eurymachus.mtd.tileentities.TileEntityMTDoor;

public class ItemMTDoor extends ItemDoor {
	private String[] doorNames;
	private final Block blockRef;

	public ItemMTDoor(int i, Block blockRef) {
		super(i, blockRef.blockMaterial);
		if (blockRef.blockID == MTDBlocks.mtDoor.id) {
			this.doorNames = MTDItemDoors.getDoorNames();
		} else {
			this.doorNames = MTDItemSensibleDoors.getDoorNames();
		}
		this.blockRef = blockRef;
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setNoRepair();
	}

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return (new StringBuilder())
				.append(super.getItemName())
					.append(".")
					.append(doorNames[itemstack.getItemDamage()])
					.toString();
	}

	public int filterData(int i) {
		return i;
	}

	/**
	 * sets the array of strings to be used for name lookups from item damage to
	 * metadata
	 */
	public ItemMTDoor setBlockNames(String[] par1ArrayOfStr) {
		this.doorNames = par1ArrayOfStr;
		return this;
	}

	/**
	 * Gets an icon index based on an item's damage value
	 */
	@Override
	public int getIconFromDamage(int damage) {
		if (this.blockRef.blockID == MTDBlocks.mtDoor.id) {
			return damage;
		} else {
			return damage + 32;
		}
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float a, float b, float c) {
		if (l != 1) {
			return false;
		}
		if (itemstack.stackSize == 0) {
			return false;
		} else {
			++j;
			if (entityplayer.canPlayerEdit(i, j, k, l, itemstack) && entityplayer
					.canPlayerEdit(i, j + 1, k, l, itemstack)) {
				if (blockRef.canPlaceBlockAt(world, i, j, k)) {
					int var10 = MathHelper
							.floor_double(((entityplayer.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
					placeDoorBlock(
							world,
							i,
							j,
							k,
							var10,
							blockRef,
							itemstack.getItemDamage());
					--itemstack.stackSize;
					return true;
				}
			}
		}
		return false;
	}

	public static void placeDoorBlock(World par0World, int par1, int par2, int par3, int par4, Block par5Block, int damage) {
		byte var6 = 0;
		byte var7 = 0;

		if (par4 == 0) {
			var7 = 1;
		}

		if (par4 == 1) {
			var6 = -1;
		}

		if (par4 == 2) {
			var7 = -1;
		}

		if (par4 == 3) {
			var6 = 1;
		}

		int var8 = (par0World.isBlockNormalCube(par1 - var6, par2, par3 - var7) ? 1 : 0) + (par0World
				.isBlockNormalCube(par1 - var6, par2 + 1, par3 - var7) ? 1 : 0);
		int var9 = (par0World.isBlockNormalCube(par1 + var6, par2, par3 + var7) ? 1 : 0) + (par0World
				.isBlockNormalCube(par1 + var6, par2 + 1, par3 + var7) ? 1 : 0);
		boolean var10 = par0World.getBlockId(par1 - var6, par2, par3 - var7) == par5Block.blockID || par0World
				.getBlockId(par1 - var6, par2 + 1, par3 - var7) == par5Block.blockID;
		boolean var11 = par0World.getBlockId(par1 + var6, par2, par3 + var7) == par5Block.blockID || par0World
				.getBlockId(par1 + var6, par2 + 1, par3 + var7) == par5Block.blockID;
		boolean var12 = false;

		if (var10 && !var11) {
			var12 = true;
		} else if (var9 > var8) {
			var12 = true;
		}

		par0World.editingBlocks = true;
		par0World.setBlockAndMetadataWithNotify(
				par1,
				par2,
				par3,
				par5Block.blockID,
				par4);
		TileEntity tileentity = par0World.getBlockTileEntity(par1, par2, par3);
		if (tileentity != null && tileentity instanceof TileEntityMTDoor) {
			TileEntityMTDoor tileentitymtdoor = (TileEntityMTDoor) tileentity;
			tileentitymtdoor.setTextureValue(damage);
			tileentitymtdoor.setDoorPiece(0);
			tileentitymtdoor.onInventoryChanged();
		}
		par0World.setBlockAndMetadataWithNotify(
				par1,
				par2 + 1,
				par3,
				par5Block.blockID,
				8 | (var12 ? 1 : 0));
		TileEntity tileentity1 = par0World.getBlockTileEntity(
				par1,
				par2 + 1,
				par3);
		if (tileentity1 != null && tileentity1 instanceof TileEntityMTDoor) {
			TileEntityMTDoor tileentitymtdoor1 = (TileEntityMTDoor) tileentity1;
			tileentitymtdoor1.setTextureValue(damage);
			tileentitymtdoor1.setDoorPiece(1);
			tileentitymtdoor1.onInventoryChanged();
		}
		par0World.editingBlocks = false;
		par0World.notifyBlocksOfNeighborChange(
				par1,
				par2,
				par3,
				par5Block.blockID);
		par0World.notifyBlocksOfNeighborChange(
				par1,
				par2 + 1,
				par3,
				par5Block.blockID);
	}

	@Override
	public String getTextureFile() {
		return MTDInit.MTD.getItemSheet();
	}
	
	@SuppressWarnings("unchecked")
	@Override
    public void getSubItems(int itemID, CreativeTabs creativeTabs, List list) {
		if (itemID == MTDItems.mtdItemDoor.getID()) {
			for (MTDItemDoors door : MTDItemDoors.values()) {
		        list.add(new ItemStack(itemID, 1, door.stackID));	
			}	
		} else {
			for (MTDItemSensibleDoors door : MTDItemSensibleDoors.values()) {
		        list.add(new ItemStack(itemID, 1, door.stackID));	
			}
		}
    }
}
