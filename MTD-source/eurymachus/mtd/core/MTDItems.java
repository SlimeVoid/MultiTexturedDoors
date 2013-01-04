package eurymachus.mtd.core;

import net.minecraft.item.Item;

public enum MTDItems {
	mtdItemDoor, mtdItemSensibleDoor;

	public Item me;
	private int id;
	public String name;

	public void setID(int id) {
		this.id = id;
	}

	public int offsetID() {
		return this.id - 256;
	}

	public int getID() {
		return this.id;
	}
}
