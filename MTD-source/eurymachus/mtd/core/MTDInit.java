package eurymachus.mtd.core;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.Configuration;
import slimevoidlib.ICommonProxy;
import slimevoidlib.core.SlimevoidCore;
import slimevoidlib.core.SlimevoidLib;
import slimevoidlib.util.BlockRemover;
import slimevoidlib.util.ItemRemover;
import slimevoidlib.util.RecipeRemover;
import eurymachus.mtd.core.lib.CoreLib;
import eurymachus.mtd.tileentities.TileEntityMTDoor;

public class MTDInit {
	private static boolean initialized = false;

	public static void initialize(ICommonProxy proxy) {
		if (initialized)
			return;
		initialized = true;
		MTDCore.configFile = new File(
				SlimevoidLib.proxy.getMinecraftDir(),
					"config/MultiTexturedDoors.cfg");
		MTDCore.configuration = new Configuration(MTDCore.configFile);
		load();
	}

	public static void load() {
		MTDCore.configurationProperties();
		SlimevoidCore.console(CoreLib.MOD_ID, "Removing Recipes...");
		RecipeRemover.registerItemRecipeToRemove(Block.doorWood);
		RecipeRemover.registerItemRecipeToRemove(Block.doorIron);
		RecipeRemover.removeCrafting();
		SlimevoidCore.console(CoreLib.MOD_ID, "Removing Blocks...");
		BlockRemover.removeVanillaBlock(Block.doorWood);
		BlockRemover.removeVanillaBlock(Block.doorIron);
		SlimevoidCore.console(CoreLib.MOD_ID, "Removing Items...");
		ItemRemover.removeVanillaItem(Item.doorWood);
		ItemRemover.removeVanillaItem(Item.doorIron);
		SlimevoidCore.console(CoreLib.MOD_ID, "Registering items...");
		MTDCore.addItems();
		SlimevoidCore.console(CoreLib.MOD_ID, "Registering blocks...");
		MTDCore.registerBlocks();
		MultiTexturedDoors.proxy.registerRenderInformation();
		SlimevoidCore.console(CoreLib.MOD_ID, "Naming items...");
		MTDCore.addItemNames();
		SlimevoidCore.console(CoreLib.MOD_ID, "Registering recipes...");
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
		return 0;
	}
}
