package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
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
        // SPAWN
        if (e.getPlayer().getWorld() == Bukkit.getWorld("world")) {
            // FILL FOOD
            e.getPlayer().setFoodLevel(20);
            e.getPlayer().setSaturation(20);

            // MAP LIMIT
            if (e.getPlayer().getLocation().getX() > 450 || e.getPlayer().getLocation().getX() < -450 || e.getPlayer().getLocation().getZ() > 450 || e.getPlayer().getLocation().getZ() < -450) {
                e.getPlayer().teleport(plugin.spawnLocation);
                e.getPlayer().sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "Vous allez trop loin !", ChatColor.GOLD + "De retour au spawn");
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ANVIL_BREAK, 100, 1);
            }
        }
        // ALLOW FLIGHT
        e.getPlayer().setAllowFlight(e.getPlayer().getWorld()==plugin.spawnLocation.getWorld());
    }
}
