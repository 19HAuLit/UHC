package fr.loris.gottagras.uhc;

import fr.loris.gottagras.uhc.infos.server;
import fr.loris.gottagras.uhc.infos.state;
import fr.loris.gottagras.uhc.timers.timePlayedUpdater;
import fr.loris.gottagras.uhc.utils.mysql;
import fr.loris.gottagras.uhc.utils.registerEvents;
import fr.loris.gottagras.uhc.utils.world;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class UHC extends JavaPlugin {

    public state statue;
    public String prefixMsg = ChatColor.DARK_RED + "[" + ChatColor.BLUE + "GottaGras" + ChatColor.DARK_GRAY + "-" + ChatColor.GOLD + "UHC" + ChatColor.DARK_RED + "]" + ChatColor.DARK_GRAY + " - ";
    public Location spawnLocation;

    @Override
    public void onEnable() {
        // Plugin startup logic
        statue = state.LOADING;

        server.MAX_PLAYERS.set(Bukkit.getMaxPlayers());
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
        new world(this).setWorldSettings(Bukkit.getWorld("world"));
        new world(this).autoGenerateUHC();

        // START TIMERS
        Bukkit.getScheduler().runTaskTimer(this, new timePlayedUpdater(this),0,20);

        statue = state.WAITING;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
