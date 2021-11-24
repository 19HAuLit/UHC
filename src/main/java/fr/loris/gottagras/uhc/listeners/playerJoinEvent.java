package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class playerJoinEvent implements Listener {

    private final UHC plugin;

    public playerJoinEvent(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        switch (plugin.statue) {
            case LOADING:
                e.getPlayer().kickPlayer(ChatColor.RED + "Server is loading");
            case WAITING:
                e.getPlayer().setGameMode(GameMode.SURVIVAL);
                Location spawnLocation = new Location(Bukkit.getWorld("uhc-world"), 0, Bukkit.getWorld("uhc-world").getHighestBlockYAt(0, 0), 0);
                e.getPlayer().teleport(spawnLocation);
        }
    }
}
