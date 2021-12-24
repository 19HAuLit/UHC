package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.gui.borderGUI;
import fr.loris.gottagras.uhc.gui.settingsGUI;
import fr.loris.gottagras.uhc.gui.stuffGUI;
import fr.loris.gottagras.uhc.gui.teamsGUI;
import fr.loris.gottagras.uhc.infos.border;
import fr.loris.gottagras.uhc.infos.server;
import fr.loris.gottagras.uhc.utils.mysql;
import fr.loris.gottagras.uhc.utils.teams;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

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
        // BORDER
        if (Objects.equals(e.getCurrentItem().getItemMeta().getDisplayName(), new borderGUI(plugin).item().getItemMeta().getDisplayName())) {
            String playerRank = null;
            try {
                playerRank = new mysql(plugin).getRank(((Player) e.getWhoClicked()));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if (Objects.equals(playerRank, "Admin") || Objects.equals(playerRank, "Host")) {
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
                if (border.FINAL_SIZE.get() - 1 > 1) border.FINAL_SIZE.set(border.FINAL_SIZE.get() - 1);
                else border.FINAL_SIZE.set(1);
                break;
            case 20:
                if (border.FINAL_SIZE.get() - 10 > 1) border.FINAL_SIZE.set(border.FINAL_SIZE.get() - 10);
                else border.FINAL_SIZE.set(1);
                break;
            case 21:
                if (border.FINAL_SIZE.get() - 100 > 1) border.FINAL_SIZE.set(border.FINAL_SIZE.get() - 100);
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
}
