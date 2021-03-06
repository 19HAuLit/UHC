package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.gui.settingsGUI;
import fr.loris.gottagras.uhc.gui.teamsGUI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class playerInteract implements Listener {
    private final UHC plugin;

    public playerInteract(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        // ITEM INTERACT
        if (e.getItem() != null) {
            if (e.getItem().hasItemMeta()) {
                // OPEN TEAM GUI
                if (Objects.equals(e.getItem().getItemMeta().getDisplayName(), new teamsGUI(plugin).item().getItemMeta().getDisplayName())) {
                    e.getPlayer().openInventory(new teamsGUI(plugin).inventory());
                }
                // OPEN SETTINGS GUI
                else if (Objects.equals(e.getItem().getItemMeta().getDisplayName(), new settingsGUI(plugin).item().getItemMeta().getDisplayName())) {
                    e.getPlayer().openInventory(new settingsGUI(plugin).inventory());
                }
            }
        }
        // SPAWN PROTECTION
        if (e.getPlayer().getWorld() == Bukkit.getWorld("world")) {
            if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
                e.setCancelled(false);
            } else if (e.getClickedBlock() != null) {
                if (e.getClickedBlock().getType() == Material.BIRCH_DOOR || e.getClickedBlock().getType() == Material.ACACIA_DOOR || e.getClickedBlock().getType() == Material.DARK_OAK_DOOR || e.getClickedBlock().getType() == Material.JUNGLE_DOOR || e.getClickedBlock().getType() == Material.SPRUCE_DOOR || e.getClickedBlock().getType() == Material.WOOD_DOOR || e.getClickedBlock().getType() == Material.WOODEN_DOOR) {
                    e.setCancelled(e.getAction() != Action.RIGHT_CLICK_BLOCK);
                } else e.setCancelled(true);
            } else e.setCancelled(true);
        }
    }
}
