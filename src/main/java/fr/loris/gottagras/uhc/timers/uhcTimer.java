package fr.loris.gottagras.uhc.timers;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.border;
import fr.loris.gottagras.uhc.infos.state;
import fr.loris.gottagras.uhc.infos.timers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class uhcTimer implements Runnable {
    private UHC plugin;

    public uhcTimer(UHC plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        // PVE
        if (timers.CURRENT.getTime() == timers.PVE.getTime()) {
            plugin.statue = state.PVE;
            Bukkit.broadcastMessage(plugin.prefixMsg + ChatColor.GRAY + "Invincibilite desactivee");
        }
        // PVP
        if (timers.CURRENT.getTime() == timers.PVP.getTime()) {
            plugin.statue = state.PVP;
            Bukkit.broadcastMessage(plugin.prefixMsg + ChatColor.GRAY + "PvP active");
            // FINAL HEAL
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.setHealth(player.getMaxHealth());
            }
        }
        // BORDER
        if (timers.CURRENT.getTime() == timers.BORDER.getTime()) {
            plugin.statue = state.BORDER;
            Bukkit.broadcastMessage(plugin.prefixMsg + ChatColor.GRAY + "La border commence a bouger");
            Bukkit.getWorld("uhc-world").getWorldBorder().setSize(border.FINAL_SIZE.get(), Math.max(timers.MEETUP.getTime() - timers.BORDER.getTime(), 1));
            Bukkit.getWorld("uhc-nether").getWorldBorder().setSize(border.FINAL_SIZE.get(), Math.max(timers.MEETUP.getTime() - timers.BORDER.getTime(), 1));
            Bukkit.getWorld("uhc-end").getWorldBorder().setSize(border.FINAL_SIZE.get(), Math.max(timers.MEETUP.getTime() - timers.BORDER.getTime(), 1));
        }
        // MEETUP
        if (timers.CURRENT.getTime() == timers.MEETUP.getTime()) {
            plugin.statue = state.MEETUP;
            Bukkit.broadcastMessage(plugin.prefixMsg + ChatColor.GRAY + "La border a fini de bouger, TAPEZ-VOUS !!!!");
        }
        // UPDATE TIMER
        timers.CURRENT.setTime(timers.CURRENT.getTime() + 1);
    }
}
