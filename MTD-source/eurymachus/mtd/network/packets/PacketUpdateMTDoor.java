package eurymachus.mtd.network.packets;

import slimevoid.lib.network.PacketTileEntityMT;
import eurymachus.mtd.core.MTDInit;
import eurymachus.mtd.tileentities.TileEntityMTDoor;

public class PacketUpdateMTDoor extends PacketTileEntityMT {
	public PacketUpdateMTDoor() {
		super(MTDInit.MTD.getModChannel());
	}

	public PacketUpdateMTDoor(TileEntityMTDoor tileentitymtdoor) {
		super(MTDInit.MTD.getModChannel(), tileentitymtdoor);
		this.payload = tileentitymtdoor.getPacketPayload();
	}

	public void setDoorPiece(int doorPiece) {
		this.payload.setIntPayload(0, doorPiece);
	}

	public int getDoorPiece() {
		return this.payload.getIntPayload(0);
	}
}
