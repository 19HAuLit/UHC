package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class playerPickupItem implements Listener {
    private UHC plugin;
    public playerPickupItem(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent e){
        // NO PICKUP AT SPAWN
        e.setCancelled(e.getPlayer().getWorld() == plugin.spawnLocation.getWorld() && e.getPlayer().getGameMode() != GameMode.CREATIVE);
    }
}
