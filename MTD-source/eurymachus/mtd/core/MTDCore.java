package eurymachus.mtd.core;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.registry.GameRegistry;
import eurymachus.mtd.blocks.BlockMTDoor;
import eurymachus.mtd.items.ItemMTDoor;
import eurymachus.mtd.tileentities.TileEntityMTDoor;
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
		MTDBlocks.mtDoor.me = (new BlockMTDoor(
				MTDBlocks.mtDoor.id,
					TileEntityMTDoor.class,
					0.5F,
					Block.soundStoneFootstep,
					true,
					true,
					"mtDoor",
					Material.iron));
		MTDBlocks.mtSensibleDoor.me = (new BlockMTDoor(
				MTDBlocks.mtSensibleDoor.id,
					TileEntityMTDoor.class,
					0.5F,
					Block.soundStoneFootstep,
					true,
					true,
					"mtDoor",
					Material.rock));
		MTDItems.mtdItemDoor.me = (new ItemMTDoor(
				MTDItems.mtdItemDoor.offsetID(), MTDBlocks.mtDoor.me)).setItemName("mtItemDoor");
		MTDItems.mtdItemSensibleDoor.me = (new ItemMTDoor(
				MTDItems.mtdItemDoor.offsetID(), MTDBlocks.mtSensibleDoor.me)).setItemName("mtItemDoor");
		GameRegistry.registerTileEntity(TileEntityMTDoor.class, "mtDoor");
		for (MTDItemDoors door : MTDItemDoors.values()) {
			door.me = new ItemStack(MTDItems.mtdItemDoor.me, 1, door.stackID);
		}
		for (MTDItemSensibleDoors door : MTDItemSensibleDoors.values()) {
			door.me = new ItemStack(MTDItems.mtdItemSensibleDoor.me, 1, door.stackID);
		}
	}

	public static void registerBlocks() {
		for (MTDBlocks block : MTDBlocks.values()) {
			if (block != null && block.me != null) {
				GameRegistry.registerBlock(block.me, block.name);
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
		sensibleDoorRecipes();
		nonsensibleDoorRecipes();
	}

	private static void nonsensibleDoorRecipes() {
		GameRegistry.addRecipe(MTDItemDoors.ironDoor.me, new Object[] {
				"XX",
				"XX",
				"XX",
				Character.valueOf('X'),
				Item.ingotIron});

		GameRegistry.addRecipe(MTDItemDoors.goldDoor.me, new Object[] {
				"X",
				"Y",
				"X",
				Character.valueOf('X'),
				Item.ingotGold,
				Character.valueOf('Y'),
				MTDItemDoors.ironDoor.me });

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

	private static void sensibleDoorRecipes() {
		GameRegistry.addRecipe(MTDItemSensibleDoors.oakWoodDoor.me, new Object[] {
				"XX",
				"XX",
				"XX",
				Character.valueOf('X'),
				new ItemStack(Block.wood.blockID, 1, 0)});
		GameRegistry.addRecipe(MTDItemSensibleDoors.spruceWoodDoor.me, new Object[] {
				"XX",
				"XX",
				"XX",
				Character.valueOf('X'),
				new ItemStack(Block.wood.blockID, 1, 1)});
		GameRegistry.addRecipe(MTDItemSensibleDoors.birchWoodDoor.me, new Object[] {
				"XX",
				"XX",
				"XX",
				Character.valueOf('X'),
				new ItemStack(Block.wood.blockID, 1, 2)});
		GameRegistry.addRecipe(MTDItemSensibleDoors.jungleWoodDoor.me, new Object[] {
				"XX",
				"XX",
				"XX",
				Character.valueOf('X'),
				new ItemStack(Block.wood.blockID, 1, 3)});
		GameRegistry.addRecipe(MTDItemSensibleDoors.smoothStoneDoor.me, new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('X'),
				Block.stone,
				Character.valueOf('Y'),
				MTDItemSensibleDoors.oakWoodDoor.me});
		GameRegistry.addRecipe(MTDItemSensibleDoors.smoothStoneDoor.me, new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('X'),
				Block.stone,
				Character.valueOf('Y'),
				MTDItemSensibleDoors.spruceWoodDoor.me});
		GameRegistry.addRecipe(MTDItemSensibleDoors.smoothStoneDoor.me, new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('X'),
				Block.stone,
				Character.valueOf('Y'),
				MTDItemSensibleDoors.birchWoodDoor.me});
		GameRegistry.addRecipe(MTDItemSensibleDoors.smoothStoneDoor.me, new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('X'),
				Block.stone,
				Character.valueOf('Y'),
				MTDItemSensibleDoors.jungleWoodDoor.me});
		GameRegistry.addRecipe(MTDItemSensibleDoors.polishedStoneDoor.me, new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('X'),
				Block.stoneSingleSlab,
				Character.valueOf('Y'),
				MTDItemSensibleDoors.oakWoodDoor.me});
		GameRegistry.addRecipe(MTDItemSensibleDoors.polishedStoneDoor.me, new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('X'),
				Block.stoneSingleSlab,
				Character.valueOf('Y'),
				MTDItemSensibleDoors.spruceWoodDoor.me});
		GameRegistry.addRecipe(MTDItemSensibleDoors.polishedStoneDoor.me, new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('X'),
				Block.stoneSingleSlab,
				Character.valueOf('Y'),
				MTDItemSensibleDoors.birchWoodDoor.me});
		GameRegistry.addRecipe(MTDItemSensibleDoors.polishedStoneDoor.me, new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('X'),
				Block.stoneSingleSlab,
				Character.valueOf('Y'),
				MTDItemSensibleDoors.jungleWoodDoor.me});
		GameRegistry.addRecipe(MTDItemSensibleDoors.cobbleStoneDoor.me, new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('X'),
				Block.cobblestone,
				Character.valueOf('Y'),
				MTDItemSensibleDoors.oakWoodDoor.me});
		GameRegistry.addRecipe(MTDItemSensibleDoors.cobbleStoneDoor.me, new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('X'),
				Block.cobblestone,
				Character.valueOf('Y'),
				MTDItemSensibleDoors.spruceWoodDoor.me});
		GameRegistry.addRecipe(MTDItemSensibleDoors.cobbleStoneDoor.me, new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('X'),
				Block.cobblestone,
				Character.valueOf('Y'),
				MTDItemSensibleDoors.birchWoodDoor.me});
		GameRegistry.addRecipe(MTDItemSensibleDoors.cobbleStoneDoor.me, new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('X'),
				Block.cobblestone,
				Character.valueOf('Y'),
				MTDItemSensibleDoors.jungleWoodDoor.me});
	}

	public static int configurationProperties() {
		configuration.load();
		MTDBlocks.mtDoor.id = Integer.parseInt(configuration.get(
				Configuration.CATEGORY_BLOCK,
				"mtDoor",
				Block.doorSteel.blockID).value);
		MTDBlocks.mtDoor.name = "Multi-Textured Door";
		MTDBlocks.mtSensibleDoor.id = Integer.parseInt(configuration.get(
				Configuration.CATEGORY_BLOCK,
				"mtSensibleDoor",
				Block.doorWood.blockID).value);
		MTDBlocks.mtSensibleDoor.name = "Multi-Textured Sensible Door";
		MTDItems.mtdItemDoor.setID(Integer.parseInt(configuration.get(
				Configuration.CATEGORY_ITEM,
				"mtDoorItem",
				7004).value));
		MTDItems.mtdItemDoor.name = "Multi-Textured Door";
		MTDItems.mtdItemSensibleDoor.setID(Integer.parseInt(configuration.get(
				Configuration.CATEGORY_ITEM,
				"mtDoorSensibleItem",
				7005).value));
		MTDItems.mtdItemSensibleDoor.name = "Multi-Textured Sensible Door";
		sensibleDoors();
		nonSensibleDoors();
		configuration.save();
		return MTDBlocks.mtSensibleDoor.id;
	}

	private static void nonSensibleDoors() {
		MTDItemDoors.ironDoor.name = "Iron Door";
		MTDItemDoors.ironDoor.stackID = 0;
		MTDItemDoors.ironDoor.setTextureIndex(19);
		MTDItemDoors.ironDoor.setBlockHardness(0.8F);
		MTDItemDoors.goldDoor.name = "Gold-Plated Door";
		MTDItemDoors.goldDoor.stackID = 1;
		MTDItemDoors.goldDoor.setTextureIndex(17);
		MTDItemDoors.goldDoor.setBlockHardness(0.8F);
		MTDItemDoors.diamondDoor.name = "Diamond-Lathered Door";
		MTDItemDoors.diamondDoor.stackID = 2;
		MTDItemDoors.diamondDoor.setTextureIndex(18);
		MTDItemDoors.diamondDoor.setBlockHardness(0.5F);
	}

	private static void sensibleDoors() {
		MTDItemSensibleDoors.oakWoodDoor.name = "Oak Wood Door";
		MTDItemSensibleDoors.oakWoodDoor.stackID = 0;
		MTDItemSensibleDoors.oakWoodDoor.setTextureIndex(20);
		MTDItemSensibleDoors.oakWoodDoor.setBlockHardness(0.4F);

		MTDItemSensibleDoors.spruceWoodDoor.name = "Spruce Wood Door";
		MTDItemSensibleDoors.spruceWoodDoor.stackID = 1;
		MTDItemSensibleDoors.spruceWoodDoor.setTextureIndex(21);
		MTDItemSensibleDoors.spruceWoodDoor.setBlockHardness(0.4F);

		MTDItemSensibleDoors.birchWoodDoor.name = "Birch Wood Door";
		MTDItemSensibleDoors.birchWoodDoor.stackID = 2;
		MTDItemSensibleDoors.birchWoodDoor.setTextureIndex(22);
		MTDItemSensibleDoors.birchWoodDoor.setBlockHardness(0.4F);

		MTDItemSensibleDoors.jungleWoodDoor.name = "Oak Wood Door";
		MTDItemSensibleDoors.jungleWoodDoor.stackID = 3;
		MTDItemSensibleDoors.jungleWoodDoor.setTextureIndex(23);
		MTDItemSensibleDoors.jungleWoodDoor.setBlockHardness(0.4F);

		MTDItemSensibleDoors.smoothStoneDoor.name = "Smooth Stone Door";
		MTDItemSensibleDoors.smoothStoneDoor.stackID = 4;
		MTDItemSensibleDoors.smoothStoneDoor.setTextureIndex(16);
		MTDItemSensibleDoors.smoothStoneDoor.setBlockHardness(0.4F);

		MTDItemSensibleDoors.polishedStoneDoor.name = "Polished Stone Door";
		MTDItemSensibleDoors.polishedStoneDoor.stackID = 5;
		MTDItemSensibleDoors.polishedStoneDoor.setTextureIndex(24);
		MTDItemSensibleDoors.polishedStoneDoor.setBlockHardness(0.6F);

		MTDItemSensibleDoors.cobbleStoneDoor.name = "Cobblestone Door";
		MTDItemSensibleDoors.cobbleStoneDoor.stackID = 6;
		MTDItemSensibleDoors.cobbleStoneDoor.setTextureIndex(25);
		MTDItemSensibleDoors.cobbleStoneDoor.setBlockHardness(0.6F);
	}
}
