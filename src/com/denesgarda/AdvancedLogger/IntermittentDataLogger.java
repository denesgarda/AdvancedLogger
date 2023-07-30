package com.denesgarda.AdvancedLogger;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.Collection;

public class IntermittentDataLogger implements Runnable {
    private final Server server;

    public IntermittentDataLogger(Server server) {
        this.server = server;
    }
    @Override
    public void run() {
        if (server.getOnlinePlayers().size() > 0) {
            String s = "Online players and locations: {";
            for (Player player : server.getOnlinePlayers()) {
                s += "{" + player.getDisplayName() + " at " + LocationManager.xyz(player.getLocation()) + "}";
            }
            s += "}";
            Logger.log(Logger.Level.INTERMITTENT_DATA_LOGGER, "30s", s);
        }
    }
}
