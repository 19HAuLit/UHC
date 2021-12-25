package fr.loris.gottagras.uhc;

import fr.loris.gottagras.uhc.infos.server;
import fr.loris.gottagras.uhc.infos.state;
import fr.loris.gottagras.uhc.timers.scoreboardUpdater;
import fr.loris.gottagras.uhc.timers.timePlayedUpdater;
import fr.loris.gottagras.uhc.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public final class UHC extends JavaPlugin {

    public state statue;
    public String prefixMsg = ChatColor.DARK_RED + "[" + ChatColor.BLUE + "GottaGras" + ChatColor.DARK_GRAY + "-" + ChatColor.GOLD + "UHC" + ChatColor.DARK_RED + "]" + ChatColor.DARK_GRAY + " - ";
    public Location spawnLocation;
    public ItemStack[] starterInventory;
    public ItemStack[] starterArmor;
    public Scoreboard scoreboard;
    public Map<String, String> playerLastDamager = new TreeMap<>();
    public Map<String, Long> playerLastDamagerTime = new TreeMap<>();
    public Map<String, Integer> playerKillCounter = new TreeMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        statue = state.LOADING;

        server.MAX_PLAYERS.set(Bukkit.getMaxPlayers());
        server.NUMBER_OF_TEAM.set(Bukkit.getMaxPlayers());
        server.PLAYER_PER_TEAM.set(1);
        spawnLocation = new Location(Bukkit.getWorld("world"), -13.5, 149, -104.5, 0f, 0f);

        // CONFIG FILE
        saveDefaultConfig();

        // START RESOURCE
        try {
            new mysql(this).start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new registerEvents(this).run();
        new registerCommands(this).run();
        new world(this).setWorldSettings(Bukkit.getWorld("world"));
        new world(this).autoGenerateUHC();
        new teams(this).autoCreate();

        // START TIMERS
        Bukkit.getScheduler().runTaskTimer(this, new timePlayedUpdater(this), 0, 20);
        Bukkit.getScheduler().runTaskTimer(this, new scoreboardUpdater(this), 0, 20);

        // REGISTER OBJECTIVE
        new scoreboardUpdater(this).registerObjective();

        // UPDATE STATUE
        statue = state.WAITING;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
