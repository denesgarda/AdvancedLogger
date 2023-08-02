package com.denesgarda.AdvancedLogger;

import org.bukkit.Location;

import java.util.Objects;

public class LocationManager {
    public static String xyz(Location location) {
        String world = Objects.requireNonNull(location.getWorld()).getEnvironment().name();
        return world + "{" + (long) Math.floor(location.getX()) + " " + (long) Math.floor(location.getY()) + " " + (long) Math.floor(location.getZ()) + "}";
    }
}
