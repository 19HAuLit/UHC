package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.gui.teamGUI;
import fr.loris.gottagras.uhc.utils.teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class inventoryClick implements Listener {
    private final UHC plugin;

    public inventoryClick(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        // GUIs
        if (e.getCurrentItem() != null) {
            if (e.getCurrentItem().getType() != Material.AIR) {
                if (Objects.equals(e.getClickedInventory().getName(), new teamGUI(plugin).inventory().getName())) {
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
                        if (Objects.equals(players.getOpenInventory().getTopInventory().getName(), new teamGUI(plugin).inventory().getName())) {
                            players.openInventory(new teamGUI(plugin).inventory());
                        }
                    }
                }
            }
        }
    }
}
