package eurymachus.mtd.tileentities;

import slimevoid.lib.network.PacketPayload;
import slimevoid.lib.network.PacketUpdate;
import slimevoid.lib.tileentity.TileEntityMT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.World;
import eurymachus.mtd.network.packets.PacketUpdateMTDoor;

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
		PacketPayload p = new PacketPayload(1, 0, 0, 0);
		p.setIntPayload(0, this.getDoorPiece());
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
