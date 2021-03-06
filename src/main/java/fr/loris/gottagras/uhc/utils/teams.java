package fr.loris.gottagras.uhc.utils;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.server;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class teams {
    private final UHC plugin;

    public teams(UHC plugin) {
        this.plugin = plugin;
    }

    public void autoCreate() {
        plugin.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        if ((server.MAX_PLAYERS.get() % server.PLAYER_PER_TEAM.get()) == 0) {
            server.NUMBER_OF_TEAM.set(server.MAX_PLAYERS.get() / server.PLAYER_PER_TEAM.get());
        } else server.NUMBER_OF_TEAM.set(server.MAX_PLAYERS.get() / server.PLAYER_PER_TEAM.get() + 1);

        for (int i = 1; i <= server.NUMBER_OF_TEAM.get(); i++) {
            try {
                plugin.scoreboard.getTeam("uhc_team_" + i).unregister();
                plugin.getLogger().info("Reset team uhc_team_" + i);
            } catch (NullPointerException e) {
                plugin.getLogger().info("Create team uhc_team_" + i);
            }
            Team team = plugin.scoreboard.registerNewTeam("uhc_team_" + i);
            switch (i % 16) {
                case 0:
                    team.setPrefix(ChatColor.BLACK + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.BLACK + "Team " + i);
                    break;
                case 1:
                    team.setPrefix(ChatColor.DARK_BLUE + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.DARK_BLUE + "Team " + i);
                    break;
                case 2:
                    team.setPrefix(ChatColor.DARK_GREEN + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.DARK_GREEN + "Team " + i);
                    break;
                case 3:
                    team.setPrefix(ChatColor.DARK_AQUA + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.DARK_AQUA + "Team " + i);
                    break;
                case 4:
                    team.setPrefix(ChatColor.DARK_RED + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.DARK_RED + "Team " + i);
                    break;
                case 5:
                    team.setPrefix(ChatColor.DARK_PURPLE + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.DARK_PURPLE + "Team " + i);
                    break;
                case 6:
                    team.setPrefix(ChatColor.GOLD + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.GOLD + "Team " + i);
                    break;
                case 7:
                    team.setPrefix(ChatColor.GRAY + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.GRAY + "Team " + i);
                    break;
                case 8:
                    team.setPrefix(ChatColor.DARK_GRAY + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.DARK_GRAY + "Team " + i);
                    break;
                case 9:
                    team.setPrefix(ChatColor.BLUE + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.BLUE + "Team " + i);
                    break;
                case 10:
                    team.setPrefix(ChatColor.GREEN + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.GREEN + "Team " + i);
                    break;
                case 11:
                    team.setPrefix(ChatColor.AQUA + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.AQUA + "Team " + i);
                    break;
                case 12:
                    team.setPrefix(ChatColor.RED + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.RED + "Team " + i);
                    break;
                case 13:
                    team.setPrefix(ChatColor.LIGHT_PURPLE + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.LIGHT_PURPLE + "Team " + i);
                    break;
                case 14:
                    team.setPrefix(ChatColor.YELLOW + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.YELLOW + "Team " + i);
                    break;
                case 15:
                    team.setPrefix(ChatColor.WHITE + "Team " + i + " | ");
                    team.setDisplayName(ChatColor.WHITE + "Team " + i);
                    break;
            }
        }
    }

    public void joinTeamById(Player player, int teamId) {
        Team team = plugin.scoreboard.getTeam("uhc_team_" + teamId);
        team.addPlayer(player);
        player.setPlayerListName(team.getPrefix() + player.getName());
        player.setCustomNameVisible(true);
    }

    public void quitAllTeams(Player player) {
        for (Team team : plugin.scoreboard.getTeams()) {
            for (OfflinePlayer offlinePlayer : team.getPlayers()) {
                if (player.getName().equals(offlinePlayer.getName())) {
                    team.removePlayer(offlinePlayer);
                }
            }
        }
        player.setDisplayName(player.getName());
        player.setPlayerListName(player.getName());
        player.setCustomName(player.getName());
        player.setCustomNameVisible(false);
    }

    public boolean canJoinTeamBySize(int teamId) {
        Team team = plugin.scoreboard.getTeam("uhc_team_" + teamId);
        return team.getSize() < server.PLAYER_PER_TEAM.get();
    }

    public boolean hasTeam(Player player) {
        for (Team team : plugin.scoreboard.getTeams()) {
            if (team.getName().startsWith("uhc_team_")) {
                for (OfflinePlayer offlinePlayer : team.getPlayers()) {
                    if (Objects.equals(player.getName(), offlinePlayer.getName())) return true;
                }
            }
        }
        return false;
    }

    public int numberOfAliveTeams() {
        int numberOfAliveTeams = 0;
        for (Team team : plugin.scoreboard.getTeams()) {
            if (team.getName().startsWith("uhc_team_")) {
                boolean teamAlive = false;
                for (OfflinePlayer offlinePlayer : team.getPlayers()) {
                    if (offlinePlayer.isOnline()) {
                        Player player = offlinePlayer.getPlayer();
                        if (player.getGameMode() == GameMode.SURVIVAL) {
                            teamAlive = true;
                        }
                    }
                }
                if (teamAlive) numberOfAliveTeams++;
            }
        }
        return numberOfAliveTeams;
    }
}
