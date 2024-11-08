package codes.shiftmc.coding.server;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.block.Block;

public class Server {

    public static void main(String[] args) {
        System.setProperty("minestom.chunk-view-distance", "16");
        var server = MinecraftServer.init();
        MinecraftServer.setBrandName("ShiftSad");

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instance = instanceManager.createInstanceContainer();
        instance.setGenerator(unit -> unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK));

        var geh = MinecraftServer.getGlobalEventHandler();
        geh.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            event.setSpawningInstance(instance);
            event.getPlayer().setRespawnPoint(new Pos(0, 45, 0));
        });

        server.start("0.0.0.0", 25565);
    }
}
