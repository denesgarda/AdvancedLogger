package com.denesgarda.AdvancedLogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;

public class Logger {
    public static void log(Level level, String event, String message) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("adv.log", true));
            out.write("[" + new Date() + "] [" + level + "] [" + event + "]: " + message);
            out.newLine();
            out.flush();
            out.close();
        } catch (Exception ignored) {}
    }

    public enum Level {
        PLUGIN,
        BLOCK,
        ENCHANTMENT,
        ENTITY,
        INVENTORY,
        PLAYER,
        RAID,
        SERVER,
        WEATHER,
        WORLD
    }
}
