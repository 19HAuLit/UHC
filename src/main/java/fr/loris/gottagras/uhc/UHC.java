package fr.loris.gottagras.uhc;

import fr.loris.gottagras.uhc.infos.server;
import fr.loris.gottagras.uhc.utils.world;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class UHC extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        server.MAX_PLAYERS.set(Bukkit.getMaxPlayers());
        new world().autoGenerateUHC();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
