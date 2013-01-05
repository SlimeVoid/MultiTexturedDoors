package eurymachus.mtd.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import eurymachus.mtd.network.MTDConnection;
import eurysmods.api.ICommonProxy;

@Mod(
		modid = "MultiTexturedDoors",
		name = "Multi-Textured Doors",
		dependencies = "after:EurysCore",
		version = "2.0.0.0")
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false,
		channels = { "MTD" },
		packetHandler = MTDConnection.class,
		connectionHandler = MTDConnection.class)
public class MultiTexturedDoors {
	@SidedProxy(
			clientSide = "eurymachus.mtd.client.proxy.ClientProxy",
			serverSide = "eurymachus.mtd.proxy.CommonProxy")
	public static ICommonProxy proxy;

	@PreInit
	public void MultiTexturedDoorsPreInit(FMLPreInitializationEvent event) {
	}

	@Init
	public void MultiTexturedDoorsInit(FMLInitializationEvent event) {
	}

	@PostInit
	public void MultiTexturedDoorsPostInit(FMLPostInitializationEvent event) {
		MTDCore.initialize(proxy);
	}
}
