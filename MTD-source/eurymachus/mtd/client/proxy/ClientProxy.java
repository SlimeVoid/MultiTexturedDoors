package eurymachus.mtd.client.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;
import slimevoid.lib.IPacketHandling;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eurymachus.mtd.client.network.ClientPacketHandler;
import eurymachus.mtd.client.render.BlockMTDoorRenderer;
import eurymachus.mtd.core.MTDCore;
import eurymachus.mtd.core.MTDInit;
import eurymachus.mtd.proxy.CommonProxy;

public class ClientProxy extends CommonProxy {

	@Override
	public String getMinecraftDir() {
		return Minecraft.getMinecraftDir().toString();
	}

	@Override
	public void registerRenderInformation() {
		MinecraftForgeClient.preloadTexture(MTDInit.MTD.getBlockSheet());
		MinecraftForgeClient.preloadTexture(MTDInit.MTD.getItemSheet());
		MTDCore.mtDoorRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new BlockMTDoorRenderer());
	}

	@Override
	public void displayTileEntityGui(EntityPlayer entityplayer, TileEntity tileentity) {
		if (!entityplayer.worldObj.isRemote)
			super.displayTileEntityGui(entityplayer, tileentity);
	}

	@SideOnly(Side.CLIENT)
	private static Minecraft mc = ModLoader.getMinecraftInstance();

	@Override
	public int getMouseOver() {
		if (mc.objectMouseOver != null) {
			int xPosition = mc.objectMouseOver.blockX;
			int yPosition = mc.objectMouseOver.blockY;
			int zPosition = mc.objectMouseOver.blockZ;
			return MTDInit.getDamageValue(
					mc.theWorld,
					xPosition,
					yPosition,
					zPosition);
		}
		return 0;
	}

	@Override
	public int getBelowPlayer(EntityPlayer player) {
		int playerX = (int) player.posX;
		int playerY = (int) player.posY;
		int playerZ = (int) player.posZ;
		return MTDInit.getDamageValue(
				mc.theWorld,
				playerX,
				playerY - 1,
				playerZ);
	}

	@Override
	public int getAtPlayer(EntityPlayer player) {
		int playerX = (int) player.posX;
		int playerY = (int) player.posY;
		int playerZ = (int) player.posZ;
		return MTDInit.getDamageValue(mc.theWorld, playerX, playerY, playerZ);
	}

	@Override
	public int getBlockTextureFromMetadata(int i) {
		int texture = -1;
		EntityPlayer player = mc.thePlayer;
		if (player.onGround) {
			texture = getMouseOver();
		}
		if (texture == -1 && player.isAirBorne) {
			texture = getMouseOver();
		}
		if (texture == -1 && player.isAirBorne) {
			texture = getBelowPlayer(player);
		}
		if (texture == -1 && player.isAirBorne) {
			texture = getAtPlayer(player);
		}
		if (texture == -1) {
			texture = 0;
		}
		return texture + 1;
	}

	@Override
	public IPacketHandling getPacketHandler() {
		return new ClientPacketHandler();
	}
}
