package fr.loris.gottagras.uhc.utils;

import fr.loris.gottagras.uhc.infos.server;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.ChatPaginator;

public class team {
    public int maxPlayerPerTeam;

    public void create(int nbTeam, int start) {
        for (int i = start; i < nbTeam+start; i++) {
            Team team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(i + "");
            String prefix = "§" + Integer.toHexString(i % 16);
            int b2 = i / 16;
            switch (b2) {
                case 1:
                    prefix += "+";
                    break;
                case 2:
                    prefix += "*";
                    break;
            }
            team.setPrefix(prefix);
        }
    }

    public void autoCreate() {
        deleteAll();
        maxPlayerPerTeam = 1;
        while (maxPlayerPerTeam <= server.MAX_PLAYERS.get() / maxPlayerPerTeam) {
            maxPlayerPerTeam++;
        }
        int numberTeams = maxPlayerPerTeam;
        if (server.MAX_PLAYERS.get() / maxPlayerPerTeam == maxPlayerPerTeam) numberTeams++;
        create(numberTeams, 0);
    }

    public void deleteAll() {
        for (Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
            for (OfflinePlayer player : team.getPlayers()) {
                team.removePlayer(player);
                if (player.isOnline()) {
                    player.getPlayer().setDisplayName(player.getName());
                }
            }
            team.unregister();
        }
    }

    public String allTeamInfo() {
        StringBuilder teamInfo = new StringBuilder();
        int i = 0;
        teamInfo.append("TEAMS: ");
        for (Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
            teamInfo.append("\n==========")
                    .append("§")
                    .append(Integer.toHexString(i % 16))
                    .append("\nName: ")
                    .append(team.getName())
                    .append("\nDisplay Name: ")
                    .append(team.getDisplayName().replace("§", "&"))
                    .append("\nPrefix: ")
                    .append(team.getPrefix().replace("§", "&"))
                    .append("\nSuffix: ")
                    .append(team.getSuffix().replace("§", "&"))
                    .append("\nSize: ")
                    .append(team.getSize())
                    .append("\nPlayers: ");
            for (OfflinePlayer player : team.getPlayers()) {
                teamInfo.append(player.getName());
            }
            i++;
        }
        return teamInfo.toString();
    }

    public void resetTeams() {
        for (Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
            for (OfflinePlayer player : team.getPlayers()) {
                team.removePlayer(player);
                if (player.isOnline()) {
                    player.getPlayer().setDisplayName(player.getName());
                }
            }
        }
    }

    public void resetPlayerTeam(Player player) {
        for (Team team : player.getScoreboard().getTeams()) {
            team.removePlayer(player);
        }
        player.setDisplayName(player.getName());
    }

    public void addPlayerToTeam(String teamName, Player player) {
        for (Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
            if (teamName.equalsIgnoreCase(team.getName())) team.addPlayer(player);
            player.setDisplayName(team.getPrefix() + player.getName());
        }
    }

    public void addPlayerToTeamWithLimit(String teamName, Player player) {
        for (Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
            if (teamName.equalsIgnoreCase(team.getName()) && team.getSize() < server.MAX_PLAYERS.get() / Bukkit.getScoreboardManager().getMainScoreboard().getTeams().size()) {
                resetPlayerTeam(player);
                team.addPlayer(player);
            }
            player.setDisplayName(team.getPrefix() + player.getName());
        }
    }

    public Team getFirstTeam(Player player) {
        for (Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
            for (OfflinePlayer offlinePlayer : team.getPlayers()) {
                if (offlinePlayer.getName().equals(player.getName())) return team;
            }
        }
        return null;
    }
}
