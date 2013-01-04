package eurymachus.mtd.core;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.Configuration;
import eurymachus.mtd.tileentities.TileEntityMTDoor;
import eurysmods.api.ICommonProxy;
import eurysmods.api.ICore;
import eurysmods.core.BlockRemover;
import eurysmods.core.Core;
import eurysmods.core.EurysCore;
import eurysmods.core.RecipeRemover;

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
		EurysCore.console(MTD.getModName(), "Removing Recipes...");
		RecipeRemover.registerItemRecipeToRemove(Block.doorWood);
		RecipeRemover.registerItemRecipeToRemove(Block.doorSteel);
		RecipeRemover.removeCrafting();
		EurysCore.console(MTD.getModName(), "Removing Blocks...");
		BlockRemover.removeVanillaBlock(Block.doorWood);
		BlockRemover.removeVanillaBlock(Block.doorSteel);
		EurysCore.console(MTD.getModName(), "Registering items...");
		MTDCore.addItems();
		EurysCore.console(MTD.getModName(), "Registering blocks...");
		MTDCore.registerBlocks();
		MTD.getProxy().registerRenderInformation();
		EurysCore.console(MTD.getModName(), "Naming items...");
		MTDCore.addItemNames();
		EurysCore.console(MTD.getModName(), "Registering recipes...");
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
