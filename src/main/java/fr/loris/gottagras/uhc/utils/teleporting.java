package fr.loris.gottagras.uhc.utils;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.border;
import fr.loris.gottagras.uhc.infos.state;
import fr.loris.gottagras.uhc.timers.uhcTimer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.Team;

import java.util.Random;

public class teleporting {
    private final UHC plugin;

    public teleporting(UHC plugin) {
        this.plugin = plugin;
    }

    public void run() {
        plugin.statue = state.TELEPORTING;
        for (Team team : plugin.scoreboard.getTeams()) {
            if (team.getName().startsWith("uhc_team_")) {
                Random random = new Random();
                int x = (int) (border.INITIAL_SIZE.get() / 2 - random.nextInt((int) border.INITIAL_SIZE.get()));
                int z = (int) (border.INITIAL_SIZE.get() / 2 - random.nextInt((int) border.INITIAL_SIZE.get()));
                Location location = new Location(Bukkit.getWorld("uhc-world"), x, 256, z);
                for (OfflinePlayer offlinePlayer : team.getPlayers()) {
                    if (offlinePlayer.isOnline()) {
                        new resetPlayer().resetAll(offlinePlayer.getPlayer());
                        offlinePlayer.getPlayer().closeInventory();
                        offlinePlayer.getPlayer().teleport(location);
                        offlinePlayer.getPlayer().sendMessage(plugin.prefixMsg + ChatColor.GRAY + "Vous venez d'etre teleporte sur l'UHC");
                        if (plugin.starterInventory != null)
                            offlinePlayer.getPlayer().getInventory().setContents(plugin.starterInventory);
                        if (plugin.starterArmor != null) {
                            offlinePlayer.getPlayer().getInventory().setBoots(plugin.starterArmor[0]);
                            offlinePlayer.getPlayer().getInventory().setLeggings(plugin.starterArmor[1]);
                            offlinePlayer.getPlayer().getInventory().setChestplate(plugin.starterArmor[2]);
                            offlinePlayer.getPlayer().getInventory().setHelmet(plugin.starterArmor[3]);
                        }
                    }
                }
            }
        }
        // LANCEMENT REEL DE L'UHC
        plugin.statue = state.BEGINNING;
        Bukkit.getScheduler().runTaskTimer(plugin, new uhcTimer(plugin), 0, 20);
    }
}
