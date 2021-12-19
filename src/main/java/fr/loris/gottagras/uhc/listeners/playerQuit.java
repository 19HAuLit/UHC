package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.gui.teamsGUI;
import fr.loris.gottagras.uhc.utils.teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class playerQuit implements Listener {
    private UHC plugin;

    public playerQuit(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(plugin.prefixMsg + ChatColor.RED + e.getPlayer().getName() + ChatColor.GRAY + " vient de nous quitter !");
        switch (plugin.statue) {
            case WAITING:
                new teams(plugin).quitAllTeams(e.getPlayer());
                for (Player players : Bukkit.getOnlinePlayers()) {
                    if (Objects.equals(players.getOpenInventory().getTopInventory().getName(), new teamsGUI(plugin).inventory().getName())) {
                        players.openInventory(new teamsGUI(plugin).inventory());
                    }
                }
                break;
        }
    }
}
