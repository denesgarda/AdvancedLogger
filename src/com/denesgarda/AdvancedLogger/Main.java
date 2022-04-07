package com.denesgarda.AdvancedLogger;

import com.oracle.tools.packager.Log;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.raid.RaidFinishEvent;
import org.bukkit.event.raid.RaidSpawnWaveEvent;
import org.bukkit.event.raid.RaidStopEvent;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.bukkit.event.server.BroadcastMessageEvent;
import org.bukkit.event.server.RemoteServerCommandEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getLogger().info("Loading AdvancedLogger...");
        this.getServer().getPluginManager().registerEvents(this, this);
        File file = new File("adv.log");
        if (!file.exists()) {
            try {
                boolean successful = file.createNewFile();
                if (!successful) {
                    throw new Exception();
                }
            } catch (Exception e) {
                getLogger().info("Failed to create log file.");
            }
        }
        getLogger().info("Advanced logger has been enabled.");
        Logger.log(Logger.Level.PLUGIN, "Enable", "AdvancedLogger enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Advanced logger has been disabled.");
        Logger.log(Logger.Level.PLUGIN, "Disable", "AdvancedLogger disabled");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        String s = event.getPlayer().getDisplayName() + " broke " + event.getBlock().getType().name() + " at " + LocationManager.xyz(event.getBlock().getLocation());
        Logger.log(Logger.Level.BLOCK, "BlockBreakEvent", s);
    }

    @EventHandler
    public void onBlockCook(BlockCookEvent event) {
        String s = event.getBlock().getType().name() + " cooked at " + LocationManager.xyz(event.getBlock().getLocation());
        Logger.log(Logger.Level.BLOCK, "BlockCookEvent", s);
    }

    @EventHandler
    public void onBlockDispense(BlockDispenseEvent event) {
        String s = event.getItem().getType().name() + " got dispensed at " + LocationManager.xyz(event.getBlock().getLocation());
        Logger.log(Logger.Level.BLOCK, "BlockDispenseEvent", s);
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        String s = event.getBlock().getType().name() + " exploded at " + LocationManager.xyz(event.getBlock().getLocation());
        Logger.log(Logger.Level.BLOCK, "BlockExplodeEvent", s);
    }

    @EventHandler
    public void onBlockFertilize(BlockFertilizeEvent event) {
        String s = Objects.requireNonNull(event.getPlayer()).getDisplayName() + " fertilized " + event.getBlock().getType().name() + " at " + LocationManager.xyz(event.getBlock().getLocation());
        Logger.log(Logger.Level.BLOCK, "BlockFertilizeEvent", s);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        String s = event.getPlayer().getDisplayName() + " placed " + event.getBlock().getType().name() + " at " + LocationManager.xyz(event.getBlock().getLocation());
        Logger.log(Logger.Level.BLOCK, "BlockPlaceEvent", s);
    }

    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {
        String s = event.getEnchanter().getDisplayName() + " enchanted " + event.getItem().getType().name() + " with " + event.getEnchantsToAdd() + " at " + LocationManager.xyz(event.getEnchantBlock().getLocation());
        Logger.log(Logger.Level.ENCHANTMENT, "EnchantItemEvent", s);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        String s;
        if (event.getDamager() instanceof Player) {
            s = ((Player) event.getDamager()).getDisplayName() + " damaged " + event.getEntity().getType().name() + " by " + event.getDamage() + " using " + event.getCause().name() + " at " + LocationManager.xyz(event.getEntity().getLocation());
        } else {
            s = event.getDamager().getType().name() + " damaged " + event.getEntity().getType().name() + " by " + event.getDamage() + " using " + event.getCause().name() + " at " + LocationManager.xyz(event.getEntity().getLocation());
        }
        Logger.log(Logger.Level.ENTITY, "EntityDamageByEntityEvent", s);
    }

    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
        String s = Objects.requireNonNull(event.getDamager()).getType().name() + " damaged " + event.getEntity().getType().name() + " by " + event.getDamage() + " using " + event.getCause().name() + " at " + LocationManager.xyz(event.getEntity().getLocation());
        Logger.log(Logger.Level.ENTITY, "EntityDamageByBlockEvent", s);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        String s;
        if (event.getEntity() instanceof Player) {
            s = ((Player) event.getEntity()).getDisplayName() + " died at " + LocationManager.xyz(event.getEntity().getLocation());
        } else {
            s = event.getEntity().getType().name() + " died at " + LocationManager.xyz(event.getEntity().getLocation());
        }
        Logger.log(Logger.Level.ENTITY, "EntityDeathEvent", s);
    }

    @EventHandler
    public void onEntityPickupItemEvent(EntityPickupItemEvent event) {
        String s;
        if (event.getEntity() instanceof Player) {
            s = ((Player) event.getEntity()).getDisplayName() + " picked up " + event.getItem().getName() + " at " + LocationManager.xyz(event.getEntity().getLocation());
        } else {
            s = event.getEntity().getType().name() + " picked up " + event.getItem().getName() + " at " + LocationManager.xyz(event.getEntity().getLocation());
        }
        Logger.log(Logger.Level.ENTITY, "EntityDeathEvent", s);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        String s = event.getEntity().getType().name() + " exploded at " + LocationManager.xyz(event.getLocation());
        Logger.log(Logger.Level.ENTITY, "EntityDeathEvent", s);
    }

    @EventHandler
    public void onEntityTame(EntityTameEvent event) {
        String s = event.getEntity().getType().name() + " was tamed by " + event.getOwner().getName() + " at " + LocationManager.xyz(event.getEntity().getLocation());
        Logger.log(Logger.Level.ENTITY, "EntityTameEvent", s);
    }

    @EventHandler
    public void onEntityTeleport(EntityTeleportEvent event) {
        String s = event.getEntity().getType().name() + " teleported from " + LocationManager.xyz(event.getFrom()) + " to " + LocationManager.xyz(Objects.requireNonNull(event.getTo()));
        Logger.log(Logger.Level.ENTITY, "EntityTeleportEvent", s);
    }

    @EventHandler
    public void onItemDespawn(ItemDespawnEvent event) {
        String s = event.getEntity().getType().name() + " despawned at " + LocationManager.xyz(event.getLocation());
        Logger.log(Logger.Level.ENTITY, "ItemDespawnEvent", s);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        String s = event.getEntity().getDisplayName() + " died with death message \"" + event.getDeathMessage() + "\" at " + LocationManager.xyz(event.getEntity().getLocation());
        Logger.log(Logger.Level.ENTITY, "PlayerDeathEvent", s);
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        String s = event.getWhoClicked().getName() + " crafted " + event.getRecipe().getResult().getType().name() + " at " + LocationManager.xyz(event.getWhoClicked().getLocation());
        Logger.log(Logger.Level.INVENTORY, "CraftItemEvent", s);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        try {
            if (event.getAction() != InventoryAction.NOTHING) {
                String s = event.getWhoClicked().getName() + " did " + event.getAction() + " to " + Objects.requireNonNull(event.getCurrentItem()).getType().name() + " with " + Objects.requireNonNull(event.getCursor()).getType().name() + " in " + Objects.requireNonNull(event.getClickedInventory()).getType().name() + " at " + LocationManager.xyz(Objects.requireNonNull(event.getClickedInventory().getLocation()));
                Logger.log(Logger.Level.INVENTORY, "InventoryClickEvent", s);
            }
        } catch (Exception ignored) {}
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        String s = event.getPlayer().getDisplayName() + " sent \"" + event.getMessage() + "\" at " + LocationManager.xyz(event.getPlayer().getLocation());
        Logger.log(Logger.Level.PLAYER, "AsyncPlayerChatEvent", s);
    }

    @EventHandler
    public void onAsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event) {
        String s = event.getName() + " with UUID " + event.getUniqueId() + " attempted to connect from " + event.getAddress();
        Logger.log(Logger.Level.PLAYER, "AsyncPlayerPreLoginEvent", s);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String s;
        if (event.getPlayer().isOp()) {
            s = "Administrator " + event.getPlayer().getDisplayName();
        } else {
            s = "Player " + event.getPlayer().getDisplayName();
        }
        s += " executed \"" + event.getMessage() + "\" at " + LocationManager.xyz(event.getPlayer().getLocation());
        Logger.log(Logger.Level.PLAYER, "PlayerCommandPreprocessEvent", s);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        String s = event.getPlayer().getDisplayName() + " dropped " + event.getItemDrop().getName() + " at " + LocationManager.xyz(event.getItemDrop().getLocation());
        Logger.log(Logger.Level.PLAYER, "PlayerDropItemEvent", s);
    }

    @EventHandler
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
        String s = "Gamemode of " + event.getPlayer().getDisplayName() + " changed to " + event.getNewGameMode() + " at " + LocationManager.xyz(event.getPlayer().getLocation());
        Logger.log(Logger.Level.PLAYER, "PlayerGameModeChangeEvent", s);
    }

    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
        try {
            String s = event.getPlayer().getDisplayName() + " interacted with " + event.getRightClicked().getType().name() + " at " + LocationManager.xyz(event.getPlayer().getLocation());
            Logger.log(Logger.Level.PLAYER, "PlayerInteractEntityEvent", s);
        } catch (Exception ignored) {}
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        try {
            ArrayList<Material> applicable = new ArrayList<>();
            applicable.add(Material.ANVIL);
            applicable.add(Material.JUKEBOX);
            applicable.add(Material.LECTERN);
            applicable.add(Material.BLAST_FURNACE);
            applicable.add(Material.LODESTONE);
            applicable.add(Material.BREWING_STAND);
            applicable.add(Material.LOOM);
            applicable.add(Material.NOTE_BLOCK);
            applicable.add(Material.CAULDRON);
            applicable.add(Material.CHEST);
            applicable.add(Material.PUMPKIN);
            applicable.add(Material.COMMAND_BLOCK);
            applicable.add(Material.COMPOSTER);
            applicable.add(Material.CRAFTING_TABLE);
            applicable.add(Material.RESPAWN_ANCHOR);
            applicable.add(Material.ENCHANTING_TABLE);
            applicable.add(Material.END_PORTAL_FRAME);
            applicable.add(Material.SMITHING_TABLE);
            applicable.add(Material.SMOKER);
            applicable.add(Material.FURNACE);
            applicable.add(Material.GRINDSTONE);
            applicable.add(Material.TNT);
            applicable.add(Material.TRAPPED_CHEST);
            applicable.add(Material.ITEM_FRAME);
            if (applicable.contains(Objects.requireNonNull(event.getClickedBlock()).getType())) {
                String s = event.getPlayer().getDisplayName() + " interacted with " + Objects.requireNonNull(event.getClickedBlock()).getType().name() + " at " + LocationManager.xyz(Objects.requireNonNull(event.getClickedBlock()).getLocation());
                Logger.log(Logger.Level.PLAYER, "PlayerInteractEvent", s);
            }
        } catch (Exception ignored) {}
    }

    @EventHandler
    public void onPlayerItemBreak(PlayerItemBreakEvent event) {
        String s = event.getPlayer().getDisplayName() + " broke item " + event.getBrokenItem().getType().name() + " at " + LocationManager.xyz(event.getPlayer().getLocation());
        Logger.log(Logger.Level.PLAYER, "PlayerItemBreakEvent", s);
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        String s = event.getPlayer().getDisplayName() + " consumed " + event.getItem().getType().name() + " at " + LocationManager.xyz(event.getPlayer().getLocation());
        Logger.log(Logger.Level.PLAYER, "PlayerItemConsumeEvent", s);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String s = event.getPlayer().getDisplayName() + " joined the server at " + LocationManager.xyz(event.getPlayer().getLocation());
        Logger.log(Logger.Level.PLAYER, "PlayerJoinEvent", s);
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        String s = event.getPlayer().getDisplayName() + " was kicked for " + event.getReason();
        Logger.log(Logger.Level.PLAYER, "PlayerKickEvent", s);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String s = event.getPlayer().getDisplayName() + " left the server at " + LocationManager.xyz(event.getPlayer().getLocation());
        Logger.log(Logger.Level.PLAYER, "PlayerQuitEvent", s);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        String s = event.getPlayer().getDisplayName() + " respawned at " + LocationManager.xyz(event.getRespawnLocation());
        Logger.log(Logger.Level.PLAYER, "PlayerRespawnEvent", s);
    }

    @EventHandler
    public void onPlayerTakeLecternBook(PlayerTakeLecternBookEvent event) {
        String s = event.getPlayer().getDisplayName() + " took a book from a lectern at " + LocationManager.xyz(event.getLectern().getLocation());
        Logger.log(Logger.Level.PLAYER, "PlayerTakeLecternBookEvent", s);
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        String s = event.getPlayer().getDisplayName() + " teleported from " + LocationManager.xyz(event.getFrom()) + " to " + LocationManager.xyz(Objects.requireNonNull(event.getTo())) + " due to \"" + event.getCause().name() + "\"";
        Logger.log(Logger.Level.PLAYER, "PlayerTeleportEvent", s);
    }

    @EventHandler
    public void onRaidFinish(RaidFinishEvent event) {
        ArrayList<String> winners = new ArrayList<>();
        event.getWinners().forEach((winner) -> winners.add(winner.getDisplayName()));
        String s = "Raid finished with winners " + winners;
        Logger.log(Logger.Level.RAID, "RaidFinishEvent", s);
    }

    @EventHandler
    public void onRaidSpawnWave(RaidSpawnWaveEvent event) {
        String s = "Raid spawned new wave";
        Logger.log(Logger.Level.RAID, "RaidSpawnWaveEvent", s);
    }

    @EventHandler
    public void onRaidStop(RaidStopEvent event) {
        String s = "Raid stopped for reason \"" + event.getReason() + "\"";
        Logger.log(Logger.Level.RAID, "RaidStopEvent", s);
    }

    @EventHandler
    public void onRaidTrigger(RaidTriggerEvent event) {
        String s = "Raid triggered by " + event.getPlayer().getDisplayName() + " at " + LocationManager.xyz(event.getPlayer().getLocation());
        Logger.log(Logger.Level.RAID, "RaidTriggerEvent", s);
    }

    @EventHandler
    public void onBroadcastMessage(BroadcastMessageEvent event) {
        ArrayList<String> recipients = new ArrayList<>();
        event.getRecipients().forEach((recipient) -> recipients.add(recipient.getName()));
        String s = "Message \"" + event.getMessage() + "\" broadcast to " + recipients;
        Logger.log(Logger.Level.SERVER, "BroadcastMessageEvent", s);
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        String s = "Server executed \"" + event.getCommand() + "\"";
        Logger.log(Logger.Level.SERVER, "ServerCommandEvent", s);
    }

    @EventHandler
    public void onRemoteServerCommand(RemoteServerCommandEvent event) {
        String s = "Remote server executed \"" + event.getCommand() + "\"";
        Logger.log(Logger.Level.SERVER, "RemoteServerCommandEvent", s);
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        String s = event.getAddress() + " pinged the server";
        Logger.log(Logger.Level.SERVER, "ServerListPingEvent", s);
    }

    @EventHandler
    public void onLightningStrike(LightningStrikeEvent event) {
        String s = "Lightning struck at " + LocationManager.xyz(event.getLightning().getLocation()) + " due to " + event.getCause().name();
        Logger.log(Logger.Level.WEATHER, "LightningStrikeEvent", s);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        String s = "Weather changed";
        Logger.log(Logger.Level.WEATHER, "WeatherChangeEvent", s);
    }

    @EventHandler
    public void onWorldSave(WorldSaveEvent event) {
        String s = "World saved";
        Logger.log(Logger.Level.WORLD, "WorldSaveEvent", s);
    }
}
