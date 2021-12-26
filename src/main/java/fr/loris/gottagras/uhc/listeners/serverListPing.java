package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.server;
import fr.loris.gottagras.uhc.utils.teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
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
            default:
                int alivePlayersNumber = 0;
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getGameMode() == GameMode.SURVIVAL) {
                        alivePlayersNumber++;
                    }
                }
                e.setMotd(plugin.prefixMsg + ChatColor.WHITE + ChatColor.BOLD + "Game en cours\n" + new teams(plugin).numberOfAliveTeams() + "/" + server.NUMBER_OF_TEAM.get() + " Teams en vie | " + alivePlayersNumber + " Joueurs en vie");
                break;
        }
    }
}
