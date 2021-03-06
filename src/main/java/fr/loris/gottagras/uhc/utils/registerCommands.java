package fr.loris.gottagras.uhc.utils;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.commands.CommandStart;
import fr.loris.gottagras.uhc.commands.CommandStuff;
import fr.loris.gottagras.uhc.commands.CommandTest;
import org.bukkit.Bukkit;

public class registerCommands {
    private final UHC plugin;

    public registerCommands(UHC plugin) {
        this.plugin = plugin;
    }

    public void run() {
        Bukkit.getServer().getPluginCommand("test").setExecutor(new CommandTest(plugin));
        Bukkit.getServer().getPluginCommand("stuff").setExecutor(new CommandStuff(plugin));
        Bukkit.getServer().getPluginCommand("start").setExecutor(new CommandStart(plugin));
    }
}
