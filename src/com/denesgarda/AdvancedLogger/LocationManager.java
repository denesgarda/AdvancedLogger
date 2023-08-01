package com.denesgarda.AdvancedLogger;

import org.bukkit.Location;

public class LocationManager {
    public static String xyz(Location location) {
        return "{" + (long) Math.floor(location.getX()) + " " + (long) Math.floor(location.getY()) + " " + (long) Math.floor(location.getZ()) + "}";
    }
}
