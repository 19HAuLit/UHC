package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class playerDropItem implements Listener {
    private UHC plugin;

    public playerDropItem(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        // NO DROP AT SPAWN
        e.setCancelled(e.getPlayer().getWorld() == plugin.spawnLocation.getWorld() && e.getPlayer().getGameMode() != GameMode.CREATIVE);
    }
}
