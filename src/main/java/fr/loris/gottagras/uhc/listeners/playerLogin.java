package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.state;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class playerLogin implements Listener {
    private UHC plugin;

    public playerLogin(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLogin(AsyncPlayerPreLoginEvent e) {
        if (plugin.statue == state.LOADING) {
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST, plugin.prefixMsg + ChatColor.RED + "Le serveur est en chargement, veuillez patienter !");
        }
    }
}
