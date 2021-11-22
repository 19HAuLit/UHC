package fr.loris.gottagras.uhc;

import org.bukkit.plugin.java.JavaPlugin;

public final class UHC extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("UHC Started");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
