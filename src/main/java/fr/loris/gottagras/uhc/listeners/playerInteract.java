package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class playerInteract implements Listener {
    private UHC plugin;

    public playerInteract(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
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