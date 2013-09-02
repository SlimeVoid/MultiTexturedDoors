package eurymachus.mtd.network.packets;

import slimevoidlib.network.PacketTileEntityMT;
import eurymachus.mtd.core.MTDInit;
import eurymachus.mtd.core.lib.CoreLib;
import eurymachus.mtd.tileentities.TileEntityMTDoor;

public class PacketUpdateMTDoor extends PacketTileEntityMT {
	public PacketUpdateMTDoor() {
		super(CoreLib.MOD_CHANNEL);
	}

	public PacketUpdateMTDoor(TileEntityMTDoor tileentitymtdoor) {
		super(CoreLib.MOD_CHANNEL, tileentitymtdoor);
		this.payload = tileentitymtdoor.getPacketPayload();
	}

	public void setDoorPiece(int doorPiece) {
		this.payload.setIntPayload(0, doorPiece);
	}

	public int getDoorPiece() {
		return this.payload.getIntPayload(0);
	}
}
