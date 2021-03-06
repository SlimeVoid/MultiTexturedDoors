package eurymachus.mtd.client.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import slimevoid.lib.IPacketHandling;
import slimevoid.lib.network.PacketTileEntity;
import slimevoid.lib.network.PacketUpdate;
import eurymachus.mtd.tileentities.TileEntityMTDoor;

public class ClientPacketHandler implements IPacketHandling {
	@Override
	public void handleTileEntityPacket(PacketTileEntity packet, EntityPlayer entityplayer, World world) {
		if (packet != null && packet.targetExists(world)) {
			TileEntity tileentity = packet.getTileEntity(world);
			if ((tileentity != null) && (tileentity instanceof TileEntityMTDoor)) {
				TileEntityMTDoor tileentitymtdoor = (TileEntityMTDoor) tileentity;
				tileentitymtdoor.handleUpdatePacket(world, packet);
			}
		}
	}

	@Override
	public void handleGuiPacket(PacketUpdate packet, EntityPlayer entityplayer, World world) {
	}

	@Override
	public void handlePacket(PacketUpdate packet, EntityPlayer entityplayer, World world) {
	}
}
