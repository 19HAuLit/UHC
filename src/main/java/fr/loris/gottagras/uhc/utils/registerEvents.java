package fr.loris.gottagras.uhc.utils;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.listeners.playerJoinEvent;

public class registerEvents {
    private UHC plugin;

    public registerEvents(UHC plugin) {
        this.plugin = plugin;
    }

    public void run() {
        plugin.getServer().getPluginManager().registerEvents(new playerJoinEvent(plugin), plugin);
    }
}
