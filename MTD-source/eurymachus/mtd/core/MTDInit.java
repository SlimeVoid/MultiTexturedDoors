package eurymachus.mtd.core;

import java.io.File;

import slimevoid.lib.ICommonProxy;
import slimevoid.lib.ICore;
import slimevoid.lib.core.BlockRemover;
import slimevoid.lib.core.Core;
import slimevoid.lib.core.SlimevoidCore;
import slimevoid.lib.core.ItemRemover;
import slimevoid.lib.core.RecipeRemover;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.Configuration;
import eurymachus.mtd.tileentities.TileEntityMTDoor;

public class MTDInit {
	public static ICore MTD;
	private static boolean initialized = false;

	public static void initialize(ICommonProxy proxy) {
		if (initialized)
			return;
		initialized = true;
		MTD = new Core(proxy);
		MTD.setModName("MultiTexturedDoors");
		MTD.setModChannel("MTD");
		MTDCore.configFile = new File(
				MTDInit.MTD.getProxy().getMinecraftDir(),
					"config/MultiTexturedDoors.cfg");
		MTDCore.configuration = new Configuration(MTDCore.configFile);
		load();
	}

	public static void load() {
		MTDCore.configurationProperties();
		SlimevoidCore.console(MTD.getModName(), "Removing Recipes...");
		RecipeRemover.registerItemRecipeToRemove(Block.doorWood);
		RecipeRemover.registerItemRecipeToRemove(Block.doorSteel);
		RecipeRemover.removeCrafting();
		SlimevoidCore.console(MTD.getModName(), "Removing Blocks...");
		BlockRemover.removeVanillaBlock(Block.doorWood);
		BlockRemover.removeVanillaBlock(Block.doorSteel);
		SlimevoidCore.console(MTD.getModName(), "Removing Items...");
		ItemRemover.removeVanillaItem(Item.doorWood);
		ItemRemover.removeVanillaItem(Item.doorSteel);
		SlimevoidCore.console(MTD.getModName(), "Registering items...");
		MTDCore.addItems();
		SlimevoidCore.console(MTD.getModName(), "Registering blocks...");
		MTDCore.registerBlocks();
		MTD.getProxy().registerRenderInformation();
		SlimevoidCore.console(MTD.getModName(), "Naming items...");
		MTDCore.addItemNames();
		SlimevoidCore.console(MTD.getModName(), "Registering recipes...");
		MTDCore.addRecipes();
	}

	public static int getDamageValue(IBlockAccess world, int x, int y, int z) {
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		if (tileentity != null) {
			if (tileentity instanceof TileEntityMTDoor) {
				TileEntityMTDoor tileentitymtdoor = (TileEntityMTDoor) tileentity;
				return tileentitymtdoor.getTextureValue();
			}
		}
		return 1000;
	}
}
