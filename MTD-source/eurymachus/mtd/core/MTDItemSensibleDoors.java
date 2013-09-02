package eurymachus.mtd.core;

import net.minecraft.item.ItemStack;

public enum MTDItemSensibleDoors {
	oakWoodDoor,
	spruceWoodDoor,
	birchWoodDoor,
	jungleWoodDoor,
	smoothStoneDoor,
	polishedStoneDoor,
	cobbleStoneDoor;

	public ItemStack me;
	public int stackID;
	public String name;
	private String textureLocation;
	private float hardness;

	public static ItemStack getStack(int itemDamage) {
		for (MTDItemSensibleDoors itemstack : MTDItemSensibleDoors.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.me;
			}
		}
		return null;
	}

	public static String getTexture(int itemDamage) {
		for (MTDItemSensibleDoors itemstack : MTDItemSensibleDoors.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.textureLocation;
			}
		}
		return null;
	}

	public void setTextureIndex(String textureLocation) {
		this.textureLocation = textureLocation;
	}

	public void setBlockHardness(float hardness) {
		this.hardness = hardness;
	}

	public static String[] getDoorNames() {
		String[] names = new String[MTDItemSensibleDoors.values().length];
		int i = 0;
		for (MTDItemSensibleDoors itemstack : MTDItemSensibleDoors.values()) {
			if (itemstack != null && itemstack.name != null && !itemstack.name
					.isEmpty()) {
				names[i] = itemstack.name;
			} else {
				names[i] = "";
			}
			i++;
		}
		return names;
	}
}
