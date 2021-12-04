package fr.loris.gottagras.uhc.utils;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.listeners.serverListPing;
import fr.loris.gottagras.uhc.listeners.playerJoin;
import fr.loris.gottagras.uhc.listeners.playerMove;

public class registerEvents {
    private UHC plugin;

    public registerEvents(UHC plugin) {
        this.plugin = plugin;
    }

    public void run() {
        plugin.getServer().getPluginManager().registerEvents(new serverListPing(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new playerJoin(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new playerMove(plugin), plugin);
    }
}
