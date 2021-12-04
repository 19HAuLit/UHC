package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class blockBreak implements Listener {
    private UHC plugin;

    public blockBreak(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        e.setCancelled(e.getPlayer().getWorld() == Bukkit.getWorld("world") && e.getPlayer().getGameMode() != GameMode.CREATIVE);
    }
}
