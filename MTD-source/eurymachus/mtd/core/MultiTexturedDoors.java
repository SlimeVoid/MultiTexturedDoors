package eurymachus.mtd.core;

import slimevoidlib.ICommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import eurymachus.mtd.proxy.CommonProxy;

@Mod(
		modid = "MultiTexturedDoors",
		name = "Multi-Textured Doors",
		dependencies = "after:SlimevoidLib",
		version = "2.0.0.1")
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false,
		channels = { "MTD" },
		packetHandler = CommonProxy.class,
		connectionHandler = CommonProxy.class)
public class MultiTexturedDoors {
	@SidedProxy(
			clientSide = "eurymachus.mtd.client.proxy.ClientProxy",
			serverSide = "eurymachus.mtd.proxy.CommonProxy")
	public static ICommonProxy proxy;

	@EventHandler
	public void MultiTexturedDoorsPreInit(FMLPreInitializationEvent event) {
	}

	@EventHandler
	public void MultiTexturedDoorsInit(FMLInitializationEvent event) {
	}

	@EventHandler
	public void MultiTexturedDoorsPostInit(FMLPostInitializationEvent event) {
		MTDCore.initialize(proxy);
	}
}
