package mtd.tileentities;

import eurysmods.network.packets.core.PacketPayload;
import eurysmods.network.packets.core.PacketUpdate;
import eurysmods.tileentities.TileEntityMT;
import mtd.network.packets.PacketUpdateMTDoor;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet;
import net.minecraft.src.World;

public class TileEntityMTDoor extends TileEntityMT {
	public int doorPiece;

	public int getDoorPiece() {
		return this.doorPiece;
	}

	public void setDoorPiece(int piece) {
		this.doorPiece = piece;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("doorPiece", this.getDoorPiece());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		this.setDoorPiece(nbttagcompound.getInteger("doorPiece"));
	}

	public PacketPayload getPacketPayload() {
		PacketPayload p = new PacketPayload(2, 0, 0, 0);
		p.setIntPayload(0, this.getTextureValue());
		p.setIntPayload(1, this.getDoorPiece());
		return p;
	}

	@Override
	public Packet getUpdatePacket() {
		return new PacketUpdateMTDoor(this).getPacket();
	}

	@Override
	public void handleUpdatePacket(World world, PacketUpdate packet) {
		this.setDoorPiece(((PacketUpdateMTDoor) packet).getDoorPiece());
		super.handleUpdatePacket(world, packet);
	}
}
