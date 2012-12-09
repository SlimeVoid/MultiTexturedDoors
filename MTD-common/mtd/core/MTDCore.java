package mtd.core;

import java.io.File;

import mtd.blocks.BlockMTDoor;
import mtd.items.ItemMTDoor;
import mtd.tileentities.TileEntityMTDoor;
import net.minecraft.src.Block;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.registry.GameRegistry;
import eurysmods.api.ICommonProxy;

public class MTDCore {
	public static File configFile;
	public static Configuration configuration;
	public static boolean initialized = false;
	public static int mtDoorRenderID;

	public static void initialize(ICommonProxy proxy) {
		if (initialized)
			return;
		initialized = true;
		MTDInit.initialize(proxy);
	}

	public static void addItems() {
		MTDBlocks.mtDoor.id = configurationProperties();
		MTDBlocks.mtDoor.me = 
				(new BlockMTDoor(
				MTDBlocks.mtDoor.id,
				TileEntityMTDoor.class,
				0.5F,
				Block.soundStoneFootstep,
				true,
				true,
				"mtDoor"));
		MTDItems.mtdItemDoor.me = 
				(new ItemMTDoor(
				MTDItems.mtdItemDoor.offsetID())).setItemName("mtItemDoor");
		GameRegistry.registerTileEntity(TileEntityMTDoor.class, "mtDoor");
		for (MTDItemDoors door : MTDItemDoors.values()) {
			door.me = new ItemStack(MTDItems.mtdItemDoor.me, 1, door.stackID);
		}
	}

	public static void registerBlocks() {
		for (MTDBlocks block : MTDBlocks.values()) {
			if (block != null && block.me != null) {
				GameRegistry.registerBlock(block.me);
				if (block.name != null) {
					ModLoader.addName(block.me, block.name);
				}
			}
		}
	}

	public static void addItemNames() {
		for (MTDItems item : MTDItems.values()) {
			if (item != null && item.me != null && item.name != null) {
				ModLoader.addName(item.me, item.name);
			}
		}
		for (MTDItemDoors door : MTDItemDoors.values()) {
			if (door != null && door.me != null && door.name != null) {
				ModLoader.addName(door.me, door.name);
			}
		}
	}

	public static void addRecipes() {
		GameRegistry.addRecipe(MTDItemDoors.stoneDoor.me, new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('X'),
				Block.stone,
				Character.valueOf('Y'),
				Item.doorWood });

		GameRegistry.addRecipe(MTDItemDoors.goldDoor.me, new Object[] {
				"X",
				"Y",
				"X",
				Character.valueOf('X'),
				Item.ingotGold,
				Character.valueOf('Y'),
				MTDItemDoors.stoneDoor.me });

		GameRegistry.addRecipe(MTDItemDoors.diamondDoor.me, new Object[] {
				"X",
				"Y",
				"X",
				Character.valueOf('X'),
				Item.diamond,
				Character.valueOf('Y'),
				MTDItemDoors.goldDoor.me });

		FurnaceRecipes.smelting().addSmelting(
				MTDItems.mtdItemDoor.getID(),
				0,
				new ItemStack(Block.stone, 8),
				1);
		FurnaceRecipes.smelting().addSmelting(
				MTDItems.mtdItemDoor.getID(),
				1,
				new ItemStack(Item.ingotGold, 2),
				2);
		FurnaceRecipes.smelting().addSmelting(
				MTDItems.mtdItemDoor.getID(),
				2,
				new ItemStack(Item.diamond, 2),
				3);
	}

	public static int configurationProperties() {
		configuration.load();
		MTDBlocks.mtDoor.id = Integer.parseInt(configuration
				.get(
						Configuration.CATEGORY_BLOCK,
						"mtDoor",
						196).value);
		MTDBlocks.mtDoor.name = "Multi-Textured Door";
		MTDItems.mtdItemDoor.setID(Integer.parseInt(configuration
				.get(
						Configuration.CATEGORY_ITEM,
						"mtDoorItem",
						7004).value));
		MTDItems.mtdItemDoor.name = "Multi-Textured Door";
		MTDItemDoors.stoneDoor.name = "Stone Door";
		MTDItemDoors.stoneDoor.stackID = 0;
		MTDItemDoors.goldDoor.name = "Gold-Plated Door";
		MTDItemDoors.goldDoor.stackID = 1;
		MTDItemDoors.diamondDoor.name = "Diamond-Lathered Door";
		MTDItemDoors.diamondDoor.stackID = 2;
		configuration.save();
		return MTDBlocks.mtDoor.id;
	}
}
