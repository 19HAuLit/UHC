package fr.loris.gottagras.uhc;

import fr.loris.gottagras.uhc.infos.server;
import fr.loris.gottagras.uhc.infos.state;
import fr.loris.gottagras.uhc.utils.registerEvents;
import fr.loris.gottagras.uhc.utils.world;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class UHC extends JavaPlugin {

    public state statue;

    @Override
    public void onEnable() {
        // Plugin startup logic
        statue = state.LOADING;

        server.MAX_PLAYERS.set(Bukkit.getMaxPlayers());

        new world(this).autoGenerateUHC();
        new registerEvents(this).run();

        statue = state.WAITING;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
