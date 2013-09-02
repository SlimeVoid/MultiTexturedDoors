package eurymachus.mtd.client.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;
import slimevoidlib.IPacketHandling;
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
		return Minecraft.getMinecraft().mcDataDir.getPath();
	}

	@Override
	public void registerRenderInformation() {
		MTDCore.mtDoorRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new BlockMTDoorRenderer());
	}

	@SideOnly(Side.CLIENT)
	private static Minecraft mc = ModLoader.getMinecraftInstance();

	@Override
	public IPacketHandling getPacketHandler() {
		return new ClientPacketHandler();
	}
}
