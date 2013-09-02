package eurymachus.mtd.client.proxy;

import net.minecraft.client.Minecraft;
import slimevoidlib.IPacketHandling;
import cpw.mods.fml.client.registry.RenderingRegistry;
import eurymachus.mtd.client.network.ClientPacketHandler;
import eurymachus.mtd.client.render.BlockMTDoorRenderer;
import eurymachus.mtd.core.MTDCore;
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

	@Override
	public IPacketHandling getPacketHandler() {
		return new ClientPacketHandler();
	}
}
