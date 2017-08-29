package subaraki.umbralux.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import subaraki.umbralux.network.PacketTransformMob.PacketTransformMobHandler;

public class PacketHandler {

	public static final String CHANNEL = "umbralux_channel";
	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL);

	public PacketHandler() {
		NETWORK.registerMessage(PacketTransformMobHandler.class, PacketTransformMob.class, 0, Side.SERVER);
	}
}
