package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.server;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Objects;

public class asyncPlayerChat implements Listener {
    private final UHC plugin;
    public asyncPlayerChat(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.SPECTATOR){
            e.setCancelled(true);
            for (Player player: Bukkit.getOnlinePlayers()){
                if (player.getGameMode() == GameMode.SPECTATOR){
                    player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "SPEC" + ChatColor.DARK_GRAY + "] " + e.getPlayer().getPlayerListName() + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + e.getMessage());
                }
            }
        }
        else{
            boolean hasTeam = false;
            for (Team team:plugin.scoreboard.getTeams()){
                if (team.getName().startsWith("uhc_team_")){
                    for (OfflinePlayer offlinePlayer:team.getPlayers()){
                        if (Objects.equals(offlinePlayer.getName(), e.getPlayer().getName())){
                            hasTeam = true;
                        }
                    }
                }
            }
            if (hasTeam && server.PLAYER_PER_TEAM.get() != 1){
                if (e.getMessage().startsWith("!")){
                    e.setFormat(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "All" + ChatColor.DARK_GRAY + "] " + e.getPlayer().getPlayerListName() + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + e.getMessage().replaceFirst("!", ""));
                }
                else{
                    for (Team team:plugin.scoreboard.getTeams()){
                        if (team.getName().startsWith("uhc_team")){
                            for (OfflinePlayer offlinePlayer:team.getPlayers()){
                                if (offlinePlayer.getName().equals(e.getPlayer().getName())){
                                    for (OfflinePlayer teamMate:team.getPlayers()){
                                        Player player = teamMate.getPlayer();
                                        player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "TEAM" + ChatColor.DARK_GRAY + "] " + e.getPlayer().getPlayerListName() + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + e.getMessage());
                                    }
                                }
                            }
                        }
                    }
                    e.setCancelled(true);
                }
            }
            else e.setFormat(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "All" + ChatColor.DARK_GRAY + "] " + e.getPlayer().getPlayerListName() + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + e.getMessage());
        }
    }
}
