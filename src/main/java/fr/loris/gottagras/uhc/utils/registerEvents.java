package fr.loris.gottagras.uhc.utils;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.listeners.*;

public class registerEvents {
    private UHC plugin;

    public registerEvents(UHC plugin) {
        this.plugin = plugin;
    }

    public void run() {
        plugin.getServer().getPluginManager().registerEvents(new serverListPing(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new playerLogin(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new playerJoin(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new playerQuit(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new playerMove(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new playerInteract(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new blockBreak(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new entityDamage(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new playerDropItem(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new playerPickupItem(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new inventoryClick(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new asyncPlayerChat(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new entityDamageByEntity(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new playerDeath(plugin), plugin);
    }
}
