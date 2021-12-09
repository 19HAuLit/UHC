package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.gui.teamGUI;
import fr.loris.gottagras.uhc.utils.resetPlayer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class playerJoin implements Listener {

    private final UHC plugin;

    public playerJoin(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(plugin.prefixMsg + ChatColor.AQUA + e.getPlayer().getName() + ChatColor.GRAY + " vient pour se battre !");
        switch (plugin.statue) {
            case LOADING:
                e.getPlayer().kickPlayer(ChatColor.RED + "Server is loading");
            case WAITING:
                new resetPlayer().resetAll(e.getPlayer());
                e.getPlayer().teleport(plugin.spawnLocation);
                e.getPlayer().getInventory().setItem(0, new teamGUI().itemMenu());
        }
    }
}
