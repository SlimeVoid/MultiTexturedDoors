package eurymachus.mtd.core;

import slimevoidlib.ICommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import eurymachus.mtd.core.lib.CoreLib;
import eurymachus.mtd.proxy.CommonProxy;

@Mod(
		modid = CoreLib.MOD_ID,
		name = CoreLib.MOD_NAME,
		dependencies = CoreLib.MOD_DEPENDENCIES,
		version = CoreLib.MOD_VERSION)
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false,
		channels = { CoreLib.MOD_CHANNEL },
		packetHandler = CommonProxy.class,
		connectionHandler = CommonProxy.class)
public class MultiTexturedDoors {
	@SidedProxy(
			clientSide = CoreLib.CLIENT_PROXY,
			serverSide = CoreLib.COMMON_PROXY)
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
