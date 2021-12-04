package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class serverListPing implements Listener {
    private UHC plugin;

    public serverListPing(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        switch (plugin.statue) {
            case LOADING:
                e.setMotd(plugin.prefixMsg + ChatColor.WHITE + ChatColor.BOLD + "L'UHC charge... Patientez quelques secondes !");
                break;
            case WAITING:
                e.setMotd(plugin.prefixMsg + ChatColor.WHITE + ChatColor.BOLD + "On attend des joueurs, rejoint nous !");
                break;
        }
    }
}
