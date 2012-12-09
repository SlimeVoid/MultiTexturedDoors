package mtd.core;

import net.minecraft.src.ItemStack;

public enum MTDItemDoors {
	stoneDoor, goldDoor, diamondDoor;

	public ItemStack me;
	public int stackID;
	public String name;

	public static ItemStack getStack(int itemDamage) {
		for (MTDItemDoors itemstack : MTDItemDoors.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.me;
			}
		}
		return null;
	}
}
