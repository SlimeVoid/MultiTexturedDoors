package mtd.core;

import java.io.File;

import eurysmods.api.ICommonProxy;
import eurysmods.api.ICore;
import eurysmods.core.Core;
import eurysmods.core.EurysCore;

import mtd.tileentities.TileEntityMTDoor;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.Configuration;

public class MTDInit {
	public static ICore Core;
	private static boolean initialized = false;

	public static void initialize(ICommonProxy proxy) {
		if (initialized)
			return;
		initialized = true;
		Core = new Core(proxy);
		Core.setModName("MultiTexturedDoors");
		Core.setModChannel("MTD");
		MTDCore.configFile = new File(
				MTDInit.Core.getProxy().getMinecraftDir(),
				"config/MultiTexturedDoors.cfg");
		MTDCore.configuration = new Configuration(MTDCore.configFile);
		load();
	}

	public static void load() {
		EurysCore.console(Core.getModName(), "Registering items...");
		MTDCore.addItems();
		EurysCore.console(Core.getModName(), "Registering blocks...");
		MTDCore.registerBlocks();
		Core.getProxy().registerRenderInformation();
		EurysCore.console(Core.getModName(), "Naming items...");
		MTDCore.addItemNames();
		EurysCore.console(Core.getModName(), "Registering recipes...");
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
