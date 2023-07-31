package com.denesgarda.AdvancedLogger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Logger {
    public static void log(Level level, String event, String message) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("adv.log", true));
            out.write("[" + new Date() + "] [" + level + "] [" + event + "]: " + message);
            out.newLine();
            out.flush();
            out.close();
            File file = new File("adv.log");
            long size = file.length();
            if (size >= 1073741824) {
                Main.logger.info("adv.log file size has reached 1 GB.");
                BukkitTask task = Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        Main.logger.warning("Packaging adv.log - DO NOT INTERRUPT!");
                    }
                }, 0, 20);
                Bukkit.getServer().getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                    try {
                        File f = new File("adv.log");
                        String fileName = new Date().toString().replaceAll(":", "_");
                        f.renameTo(new File(fileName + ".log"));
                        f.createNewFile();
                        f = new File(fileName + ".log");
                        File folder = new File("packages");
                        folder.mkdir();
                        FileOutputStream fos = new FileOutputStream("packages/" + fileName + ".zip");
                        ZipOutputStream zipOut = new ZipOutputStream(fos);
                        File fileToZip = f;
                        FileInputStream fis = new FileInputStream(fileToZip);
                        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                        zipOut.putNextEntry(zipEntry);
                        byte[] bytes = new byte[1024];
                        int length;
                        while ((length = fis.read(bytes)) >= 0) {
                            zipOut.write(bytes, 0, length);
                        }
                        zipOut.close();
                        fis.close();
                        fos.close();
                        f.delete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    task.cancel();
                    Main.logger.info("Adv.log has been successfully packaged.");
                });
            }
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
        WORLD,
        INTERMITTENT_DATA_LOGGER
    }
}
