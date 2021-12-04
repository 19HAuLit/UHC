package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class playerMove implements Listener {
    private UHC plugin;

    public playerMove(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.getPlayer().getWorld() == Bukkit.getWorld("world")) {
            e.getPlayer().setFoodLevel(20);
            e.getPlayer().setSaturation(20);
        }
    }
}
