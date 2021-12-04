package fr.loris.gottagras.uhc;

import fr.loris.gottagras.uhc.infos.server;
import fr.loris.gottagras.uhc.infos.state;
import fr.loris.gottagras.uhc.utils.registerEvents;
import fr.loris.gottagras.uhc.utils.world;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

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

        new world(this).setWorldSettings(Bukkit.getWorld("world"));
        new registerEvents(this).run();
        new world(this).autoGenerateUHC();

        statue = state.WAITING;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
