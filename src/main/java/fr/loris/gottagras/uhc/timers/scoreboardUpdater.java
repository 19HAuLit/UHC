package fr.loris.gottagras.uhc.timers;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.border;
import fr.loris.gottagras.uhc.infos.server;
import fr.loris.gottagras.uhc.infos.timers;
import fr.loris.gottagras.uhc.utils.teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import java.util.Map;
import java.util.TreeMap;

public class scoreboardUpdater extends BukkitRunnable implements Runnable {
    private final UHC plugin;

    public scoreboardUpdater(UHC uhc) {
        this.plugin = uhc;
    }

    @Override
    public void run() {
        // SIDE BorderSize
        try {
            plugin.scoreboard.getObjective("GameInfo").unregister();
        } catch (NullPointerException ignored) {
        }
        plugin.scoreboard.registerNewObjective("GameInfo", "GameInfo").setDisplaySlot(DisplaySlot.SIDEBAR);
        Objective objective = plugin.scoreboard.getObjective("GameInfo");
        objective.setDisplayName(plugin.prefixMsg.replace(" -", ""));
        Score PvETime;
        Score PvPTime;
        Score BorderTime;
        Score MeetupTime;
        Score numberOfTeam;
        Score numberOfPlayer;
        Score borderSize;
        Score finalBorderSize;
        switch (plugin.statue) {
            case WAITING:
            case STARTING:
            case TELEPORTING:
                PvETime = objective.getScore(ChatColor.GOLD + "PvE: " + ChatColor.GRAY + timers.PVE.getTime() / 60 + "min " + timers.PVE.getTime() % 60 + "s");
                PvPTime = objective.getScore(ChatColor.GOLD + "PvP: " + ChatColor.GRAY + timers.PVP.getTime() / 60 + "min " + timers.PVP.getTime() % 60 + "s");
                BorderTime = objective.getScore(ChatColor.GOLD + "Border: " + ChatColor.GRAY + timers.BORDER.getTime() / 60 + "min " + timers.BORDER.getTime() % 60 + "s");
                MeetupTime = objective.getScore(ChatColor.GOLD + "Meetup: " + ChatColor.GRAY + timers.MEETUP.getTime() / 60 + "min " + timers.MEETUP.getTime() % 60 + "s");
                numberOfTeam = objective.getScore(ChatColor.GOLD + "Teams: " + ChatColor.GRAY + server.NUMBER_OF_TEAM.get());
                numberOfPlayer = objective.getScore(ChatColor.GOLD + "Players: " + ChatColor.GRAY + Bukkit.getOnlinePlayers().size());
                borderSize = objective.getScore(ChatColor.GOLD + "Border: " + ChatColor.GRAY + ((int) border.INITIAL_SIZE.get()) + " blocks");
                finalBorderSize = objective.getScore(ChatColor.GOLD + "Final Border: " + ChatColor.GRAY + ((int) border.FINAL_SIZE.get()) + " blocks");
                MeetupTime.setScore(1);
                BorderTime.setScore(2);
                PvPTime.setScore(3);
                PvETime.setScore(4);
                finalBorderSize.setScore(5);
                borderSize.setScore(6);
                numberOfPlayer.setScore(7);
                numberOfTeam.setScore(8);
                break;
            default:
                if (timers.CURRENT.getTime() % 20 > 10 && plugin.playerKillCounter.size() != 0) {
                    Score topKill = objective.getScore(ChatColor.GOLD + "TOP KILLERS:");
                    topKill.setScore(15);
                    Map<String, Integer> topKillers = new TreeMap<>(plugin.playerKillCounter);
                    for (int i = 14; i > 0; i--) {
                        String playerName = null;
                        int kills = 0;
                        for (Map.Entry<String, Integer> entry : topKillers.entrySet()) {
                            if (kills < entry.getValue()) {
                                playerName = entry.getKey();
                                kills = entry.getValue();
                            }
                        }
                        if (playerName != null) {
                            topKillers.remove(playerName);
                            Score score = objective.getScore(ChatColor.AQUA + playerName + ": " + ChatColor.GRAY + kills + " kills");
                            score.setScore(i);
                        }
                    }
                } else {
                    // PVE
                    if ((timers.PVE.getTime() - timers.CURRENT.getTime()) <= 0) {
                        PvETime = objective.getScore(ChatColor.GOLD + "PvE actif");
                    } else
                        PvETime = objective.getScore(ChatColor.GOLD + "PvE: " + ChatColor.GRAY + (timers.PVE.getTime() - timers.CURRENT.getTime()) / 60 + "min " + (timers.PVE.getTime() - timers.CURRENT.getTime()) % 60 + "s");
                    // PVP
                    if ((timers.PVP.getTime() - timers.CURRENT.getTime()) <= 0) {
                        PvPTime = objective.getScore(ChatColor.GOLD + "PvP actif");
                    } else
                        PvPTime = objective.getScore(ChatColor.GOLD + "PvP: " + ChatColor.GRAY + (timers.PVP.getTime() - timers.CURRENT.getTime()) / 60 + "min " + (timers.PVP.getTime() - timers.CURRENT.getTime()) % 60 + "s");
                    // BORDER
                    if ((timers.BORDER.getTime() - timers.CURRENT.getTime()) <= 0) {
                        BorderTime = objective.getScore(ChatColor.GOLD + "Border active");
                    } else
                        BorderTime = objective.getScore(ChatColor.GOLD + "Border: " + ChatColor.GRAY + (timers.BORDER.getTime() - timers.CURRENT.getTime()) / 60 + "min " + (timers.BORDER.getTime() - timers.CURRENT.getTime()) % 60 + "s");
                    // MEETUP
                    if ((timers.MEETUP.getTime() - timers.CURRENT.getTime()) <= 0) {
                        MeetupTime = objective.getScore(ChatColor.GOLD + "Meetup actif");
                    } else
                        MeetupTime = objective.getScore(ChatColor.GOLD + "Meetup: " + ChatColor.GRAY + (timers.MEETUP.getTime() - timers.CURRENT.getTime()) / 60 + "min " + (timers.MEETUP.getTime() - timers.CURRENT.getTime()) % 60 + "s");
                    // TEAM
                    numberOfTeam = objective.getScore(ChatColor.GOLD + "Teams en vie: " + ChatColor.GRAY + new teams(plugin).numberOfAliveTeams());
                    // PLAYER
                    int alivePlayersNumber = 0;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.getGameMode() == GameMode.SURVIVAL) {
                            alivePlayersNumber++;
                        }
                    }
                    numberOfPlayer = objective.getScore(ChatColor.GOLD + "Players en vie: " + ChatColor.GRAY + alivePlayersNumber);
                    // BORDER
                    borderSize = objective.getScore(ChatColor.GOLD + "Border: " + ChatColor.GRAY + ((int) Bukkit.getWorld("uhc-world").getWorldBorder().getSize()) + " blocks");
                    // SCORE
                    MeetupTime.setScore(1);
                    BorderTime.setScore(2);
                    PvPTime.setScore(3);
                    PvETime.setScore(4);
                    borderSize.setScore(5);
                    numberOfPlayer.setScore(6);
                    numberOfTeam.setScore(7);
                }
                break;
        }
    }

    public void registerObjective() {
        // TAB HEAL
        try {
            plugin.scoreboard.getObjective("health").unregister();
            plugin.getLogger().info("Reset objective health");
        } catch (NullPointerException e) {
            plugin.getLogger().info("Create objective health");
        }
        plugin.scoreboard.registerNewObjective("health", "health").setDisplaySlot(DisplaySlot.PLAYER_LIST);
        // NAMETAG HEAL
        try {
            plugin.scoreboard.getObjective("HP").unregister();
            plugin.getLogger().info("Reset objective HP");
        } catch (NullPointerException e) {
            plugin.getLogger().info("Create objective HP");
        }
        plugin.scoreboard.registerNewObjective("HP", "health").setDisplaySlot(DisplaySlot.BELOW_NAME);
        // SIDE BorderSize
        try {
            plugin.scoreboard.getObjective("GameInfo").unregister();
            plugin.getLogger().info("Reset objective GameInfo");
        } catch (NullPointerException e) {
            plugin.getLogger().info("Create objective GameInfo");
        }
        plugin.scoreboard.registerNewObjective("GameInfo", "GameInfo").setDisplaySlot(DisplaySlot.SIDEBAR);
    }
}
