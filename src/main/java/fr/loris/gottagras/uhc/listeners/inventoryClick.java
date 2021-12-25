package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.gui.*;
import fr.loris.gottagras.uhc.infos.border;
import fr.loris.gottagras.uhc.infos.server;
import fr.loris.gottagras.uhc.infos.timers;
import fr.loris.gottagras.uhc.utils.mysql;
import fr.loris.gottagras.uhc.utils.teams;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.Objects;

public class inventoryClick implements Listener {
    private final UHC plugin;

    public inventoryClick(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        // SPAWN PROTECTION
        if (e.getWhoClicked().getWorld() == Bukkit.getWorld("world") && e.getWhoClicked().getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
        // GUIs
        if (e.getCurrentItem() != null) {
            if (e.getCurrentItem().getType() != Material.AIR) {
                // TEAM
                if (Objects.equals(e.getClickedInventory().getName(), new teamsGUI(plugin).inventory().getName())) {
                    teamsGUI(e);
                }
                // SETTINGS
                else if (Objects.equals(e.getClickedInventory().getName(), new settingsGUI(plugin).inventory().getName())) {
                    settingsGUI(e);
                }
                // BORDER
                else if (Objects.equals(e.getClickedInventory().getName(), new borderGUI(plugin).inventory().getName())) {
                    borderGUI(e);
                }
                // STUFF
                else if (Objects.equals(e.getClickedInventory().getName(), new stuffGUI(plugin).inventory().getName())) {
                    stuffGUI(e);
                }
                // CONFIG
                else if (Objects.equals(e.getClickedInventory().getName(), new configGUI(plugin).inventory().getName())) {
                    configGUI(e);
                }
                // TIMERS
                else if (Objects.equals(e.getClickedInventory().getName(), new timersGUI(plugin).inventory().getName())) {
                    timersGUI(e);
                }
            }
        }
    }

    public void teamsGUI(InventoryClickEvent e) {
        e.setCancelled(true);
        Player player = (Player) e.getWhoClicked();
        if (new teams(plugin).canJoinTeamBySize(e.getSlot() + 1)) {
            new teams(plugin).quitAllTeams(player);
            new teams(plugin).joinTeamById(player, e.getSlot() + 1);
        } else {
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 100, 1);
            player.sendMessage(plugin.prefixMsg + ChatColor.GRAY + "Cette team est pleine, vous ne pouvez pas la rejoindre !");
        }
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (Objects.equals(players.getOpenInventory().getTopInventory().getName(), new teamsGUI(plugin).inventory().getName())) {
                players.openInventory(new teamsGUI(plugin).inventory());
            }
        }
    }

    public void settingsGUI(InventoryClickEvent e) {
        String playerRank = null;
        try {
            playerRank = new mysql(plugin).getRank(((Player) e.getWhoClicked()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        boolean permAdminOrHost = Objects.equals(playerRank, "Admin") || Objects.equals(playerRank, "Host");
        // BORDER
        if (Objects.equals(e.getCurrentItem().getItemMeta().getDisplayName(), new borderGUI(plugin).item().getItemMeta().getDisplayName())) {
            if (permAdminOrHost) {
                e.getWhoClicked().openInventory(new borderGUI(plugin).inventory());
            }
        }
        // STUFF
        else if (Objects.equals(e.getCurrentItem().getItemMeta().getDisplayName(), new stuffGUI(plugin).item().getItemMeta().getDisplayName())) {
            if (e.getClick().isRightClick()) {
                ((Player) e.getWhoClicked()).chat("/stuff modify");
                e.getWhoClicked().closeInventory();
            } else e.getWhoClicked().openInventory(new stuffGUI(plugin).inventory());
        }
        // CONFIG
        else if (Objects.equals(e.getCurrentItem().getItemMeta().getDisplayName(), new configGUI(plugin).item().getItemMeta().getDisplayName()) && permAdminOrHost) {
            e.getWhoClicked().openInventory(new configGUI(plugin).inventory());
        }
        // TIMERS
        else if (Objects.equals(e.getCurrentItem().getItemMeta().getDisplayName(), new timersGUI(plugin).item().getItemMeta().getDisplayName()) && permAdminOrHost) {
            e.getWhoClicked().openInventory(new timersGUI(plugin).inventory());
        }
        // START UHC
        else if (Objects.equals(e.getCurrentItem().getItemMeta().getDisplayName(), new settingsGUI(plugin).startUHC().getItemMeta().getDisplayName())) {
            ((Player) e.getWhoClicked()).chat("/start");
        }
    }

    public void borderGUI(InventoryClickEvent e) {
        switch (e.getSlot()) {
            // INITIAL SIZE
            case 10:
                if (border.INITIAL_SIZE.get() - 100 > 1) border.INITIAL_SIZE.set(border.INITIAL_SIZE.get() - 100);
                else border.INITIAL_SIZE.set(1);
                break;
            case 11:
                if (border.INITIAL_SIZE.get() - 10 > 1) border.INITIAL_SIZE.set(border.INITIAL_SIZE.get() - 10);
                else border.INITIAL_SIZE.set(1);
                break;
            case 12:
                if (border.INITIAL_SIZE.get() - 1 > 1) border.INITIAL_SIZE.set(border.INITIAL_SIZE.get() - 1);
                else border.INITIAL_SIZE.set(1);
                break;
            case 13:
                border.INITIAL_SIZE.set(Math.pow(server.MAX_PLAYERS.get(), 0.5) * 600);
                break;
            case 14:
                border.INITIAL_SIZE.set(border.INITIAL_SIZE.get() + 1);
                break;
            case 15:
                border.INITIAL_SIZE.set(border.INITIAL_SIZE.get() + 10);
                break;
            case 16:
                border.INITIAL_SIZE.set(border.INITIAL_SIZE.get() + 100);
                break;

            // FINAL SIZE
            case 19:
                if (border.FINAL_SIZE.get() - 100 > 1) border.FINAL_SIZE.set(border.FINAL_SIZE.get() - 100);
                else border.FINAL_SIZE.set(1);
                break;
            case 20:
                if (border.FINAL_SIZE.get() - 10 > 1) border.FINAL_SIZE.set(border.FINAL_SIZE.get() - 10);
                else border.FINAL_SIZE.set(1);
                break;
            case 21:
                if (border.FINAL_SIZE.get() - 1 > 1) border.FINAL_SIZE.set(border.FINAL_SIZE.get() - 1);
                else border.FINAL_SIZE.set(1);
                break;
            case 22:
                border.FINAL_SIZE.set(100);
                break;
            case 23:
                border.FINAL_SIZE.set(border.FINAL_SIZE.get() + 1);
                break;
            case 24:
                border.FINAL_SIZE.set(border.FINAL_SIZE.get() + 10);
                break;
            case 25:
                border.FINAL_SIZE.set(border.FINAL_SIZE.get() + 100);
                break;
        }
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (Objects.equals(players.getOpenInventory().getTopInventory().getName(), new settingsGUI(plugin).inventory().getName())) {
                players.openInventory(new settingsGUI(plugin).inventory());
            } else if (Objects.equals(players.getOpenInventory().getTopInventory().getName(), new borderGUI(plugin).inventory().getName())) {
                players.openInventory(new borderGUI(plugin).inventory());
            }
        }
    }

    public void stuffGUI(InventoryClickEvent e) {
        e.getWhoClicked().openInventory(new stuffGUI(plugin).inventory());
    }

    public void configGUI(InventoryClickEvent e) {
        // CLASSICO
        if (Objects.equals(e.getCurrentItem().getItemMeta().getDisplayName(), new configGUI(plugin).uhc_classico().getItemMeta().getDisplayName())) {
            e.getWhoClicked().sendMessage(plugin.prefixMsg + "La configuration " + ChatColor.GOLD + "UHC Classico " + ChatColor.DARK_GRAY + "viens d'etre selectionee");
            // BORDER
            border.INITIAL_SIZE.set(Math.pow(server.MAX_PLAYERS.get(), 0.5) * 600);
            border.FINAL_SIZE.set(100);
            // STUFF
            Inventory inventory = Bukkit.createInventory(null, 36, "classico");
            inventory.addItem(new ItemStack(Material.STONE_AXE));
            inventory.addItem(new ItemStack(Material.STONE_PICKAXE));
            inventory.addItem(new ItemStack(Material.WATER_BUCKET));
            inventory.addItem(new ItemStack(Material.WATER_BUCKET));
            inventory.addItem(new ItemStack(Material.BOOK, 6));
            plugin.starterInventory = inventory.getContents();
            plugin.starterArmor = null;
            // TIMERS
            timers.PVE.setTime(30);
            timers.PVP.setTime(20 * 60);
            timers.BORDER.setTime(60 * 60);
            timers.MEETUP.setTime(80 * 60);
        }
        // MEETUP
        else if (Objects.equals(e.getCurrentItem().getItemMeta().getDisplayName(), new configGUI(plugin).uhc_meetup().getItemMeta().getDisplayName())) {
            e.getWhoClicked().sendMessage(plugin.prefixMsg + "La configuration " + ChatColor.GOLD + "UHC Meetup " + ChatColor.DARK_GRAY + "viens d'etre selectionee");
            // BORDER
            border.INITIAL_SIZE.set(500);
            border.FINAL_SIZE.set(100);
            // STUFF
            Inventory inventory = Bukkit.createInventory(null, 36, "meetup");
            inventory.addItem(new ItemStack(Material.DIAMOND_SWORD));
            inventory.addItem(new ItemStack(Material.WOOD, 64));
            inventory.addItem(new ItemStack(Material.WATER_BUCKET));
            inventory.addItem(new ItemStack(Material.WATER_BUCKET));
            inventory.addItem(new ItemStack(Material.GOLDEN_APPLE, 6));
            plugin.starterInventory = inventory.getContents();
            Inventory armorInventory = Bukkit.createInventory(null, 9, "meetup");
            armorInventory.addItem(new ItemStack(Material.DIAMOND_BOOTS));
            armorInventory.addItem(new ItemStack(Material.DIAMOND_LEGGINGS));
            armorInventory.addItem(new ItemStack(Material.DIAMOND_CHESTPLATE));
            armorInventory.addItem(new ItemStack(Material.DIAMOND_HELMET));
            plugin.starterArmor = armorInventory.getContents();
            // TIMERS
            timers.PVE.setTime(30);
            timers.PVP.setTime(60);
            timers.BORDER.setTime(5 * 60);
            timers.MEETUP.setTime(10 * 60);
        }
    }

    public void timersGUI(InventoryClickEvent e) {
        switch (e.getSlot()) {
            // PVE
            case 10:
                timers.PVE.setTime(Math.max(timers.PVE.getTime() - 100, 1));
                break;
            case 11:
                timers.PVE.setTime(Math.max(timers.PVE.getTime() - 10, 1));
                break;
            case 12:
                timers.PVE.setTime(Math.max(timers.PVE.getTime() - 1, 1));
                break;
            case 13:
                timers.PVE.setTime(30);
                break;
            case 14:
                timers.PVE.setTime(timers.PVE.getTime() + 1);
                break;
            case 15:
                timers.PVE.setTime(timers.PVE.getTime() + 10);
                break;
            case 16:
                timers.PVE.setTime(timers.PVE.getTime() + 100);
                break;
            // PVP
            case 19:
                timers.PVP.setTime(Math.max(timers.PVP.getTime() - 100, 1));
                break;
            case 20:
                timers.PVP.setTime(Math.max(timers.PVP.getTime() - 10, 1));
                break;
            case 21:
                timers.PVP.setTime(Math.max(timers.PVP.getTime() - 1, 1));
                break;
            case 22:
                timers.PVP.setTime(20 * 60);
                break;
            case 23:
                timers.PVP.setTime(timers.PVP.getTime() + 1);
                break;
            case 24:
                timers.PVP.setTime(timers.PVP.getTime() + 10);
                break;
            case 25:
                timers.PVP.setTime(timers.PVP.getTime() + 100);
                break;
            // BORDER
            case 28:
                timers.BORDER.setTime(Math.max(timers.BORDER.getTime() - 100, 1));
                break;
            case 29:
                timers.BORDER.setTime(Math.max(timers.BORDER.getTime() - 10, 1));
                break;
            case 30:
                timers.BORDER.setTime(Math.max(timers.BORDER.getTime() - 1, 1));
                break;
            case 31:
                timers.BORDER.setTime(60 * 60);
                break;
            case 32:
                timers.BORDER.setTime(timers.BORDER.getTime() + 1);
                break;
            case 33:
                timers.BORDER.setTime(timers.BORDER.getTime() + 10);
                break;
            case 34:
                timers.BORDER.setTime(timers.BORDER.getTime() + 100);
                break;
            // MEETUP
            case 37:
                timers.MEETUP.setTime(Math.max(timers.MEETUP.getTime() - 100, 1));
                break;
            case 38:
                timers.MEETUP.setTime(Math.max(timers.MEETUP.getTime() - 10, 1));
                break;
            case 39:
                timers.MEETUP.setTime(Math.max(timers.MEETUP.getTime() - 1, 1));
                break;
            case 40:
                timers.MEETUP.setTime(80 * 60);
                break;
            case 41:
                timers.MEETUP.setTime(timers.MEETUP.getTime() + 1);
                break;
            case 42:
                timers.MEETUP.setTime(timers.MEETUP.getTime() + 10);
                break;
            case 43:
                timers.MEETUP.setTime(timers.MEETUP.getTime() + 100);
                break;
        }
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (Objects.equals(players.getOpenInventory().getTopInventory().getName(), new settingsGUI(plugin).inventory().getName())) {
                players.openInventory(new settingsGUI(plugin).inventory());
            } else if (Objects.equals(players.getOpenInventory().getTopInventory().getName(), new timersGUI(plugin).inventory().getName())) {
                players.openInventory(new timersGUI(plugin).inventory());
            }
        }
    }
}
