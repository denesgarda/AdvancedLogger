package com.denesgarda.AdvancedLogger;

import org.bukkit.Location;

public class LocationManager {
    public static String xyz(Location location) {
        return "{" + Math.round(location.getX()) + " " + Math.round(location.getY()) + " " + Math.round(location.getZ()) + "}";
    }
}
