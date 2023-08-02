package com.denesgarda.AdvancedLogger;

import org.bukkit.Location;

import java.util.Objects;

public class LocationManager {
    public static String xyz(Location location) {
        String world = Objects.requireNonNull(location.getWorld()).getEnvironment().name();
        return world + "{" + Math.floor(location.getX()) + " " + Math.floor(location.getY()) + " " + Math.floor(location.getZ()) + "}";
    }
}
