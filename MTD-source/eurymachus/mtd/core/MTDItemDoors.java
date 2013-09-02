package eurymachus.mtd.core;

import net.minecraft.item.ItemStack;

public enum MTDItemDoors {
	ironDoor,
	goldDoor,
	diamondDoor;

	public ItemStack me;
	public int stackID;
	public String name;
	private String textureLocation;
	private float hardness;

	public static ItemStack getStack(int itemDamage) {
		for (MTDItemDoors itemstack : MTDItemDoors.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.me;
			}
		}
		return null;
	}

	public static String getTexture(int itemDamage) {
		for (MTDItemDoors itemstack : MTDItemDoors.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.textureLocation;
			}
		}
		return null;
	}

	public static float getHardness(int itemDamage) {
		for (MTDItemDoors itemstack : MTDItemDoors.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.hardness;
			}
		}
		return 0.5F;
	}

	public void setTextureIndex(String textureLocation) {
		this.textureLocation = textureLocation;
	}

	public void setBlockHardness(float hardness) {
		this.hardness = hardness;
	}

	public static String[] getDoorNames() {
		String[] names = new String[MTDItemDoors.values().length];
		int i = 0;
		for (MTDItemDoors itemstack : MTDItemDoors.values()) {
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
