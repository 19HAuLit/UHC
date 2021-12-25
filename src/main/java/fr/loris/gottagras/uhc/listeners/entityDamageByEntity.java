package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.state;
import fr.loris.gottagras.uhc.utils.game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Date;

public class entityDamageByEntity implements Listener {
    private final UHC plugin;

    public entityDamageByEntity(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            // CANCEL DAMAGE OF PLAYER ON PLAYER WHEN PVE TIME
            if (plugin.statue == state.PVE) {
                if (e.getDamager() instanceof Player) e.setCancelled(true);
                else if (e.getDamager() instanceof Projectile) {
                    if (((Projectile) e.getDamager()).getShooter() instanceof Player) e.setCancelled(true);
                }
            }
            // DEATH MSG IF IS ENTITY KILL
            if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK && e.getFinalDamage() >= player.getHealth()) {
                if (e.getDamager() instanceof Player) {
                    Player damager = ((Player) e.getDamager());
                    Bukkit.broadcastMessage(plugin.prefixMsg + player.getPlayerListName() + ChatColor.GRAY + " viens d'etre tuer par " + damager.getPlayerListName());
                    new game(plugin).updateKills(damager);
                }
                else if (e.getDamager() instanceof Projectile) {
                    if (((Projectile) e.getDamager()).getShooter() instanceof Player) {
                        Player damager = ((Player) ((Projectile) e.getDamager()).getShooter());
                        Bukkit.broadcastMessage(plugin.prefixMsg + player.getPlayerListName() + " viens d'etre tuer par " + damager.getPlayerListName());
                        new game(plugin).updateKills(damager);
                    }
                    else{
                        Bukkit.broadcastMessage(plugin.prefixMsg + player.getPlayerListName() + " viens d'etre tuer par une " + e.getDamager().getName());
                        new game(plugin).updateKillsPvE();
                    }
                }
                else{
                    Bukkit.broadcastMessage(plugin.prefixMsg + player.getPlayerListName() + " viens d'etre tuer par un " + e.getDamager().getName());
                    new game(plugin).updateKillsPvE();
                }
            }
            // LOG LAST HIT BY PLAYER
            if (e.getDamager() instanceof Player) {
                plugin.playerLastDamager.put(player.getUniqueId().toString(), e.getDamager().getUniqueId().toString());
                plugin.playerLastDamagerTime.put(player.getUniqueId().toString(), new Date().getTime());
            } else if (e.getDamager() instanceof Projectile) {
                if (((Projectile) e.getDamager()).getShooter() instanceof Player) {
                    plugin.playerLastDamager.put(player.getUniqueId().toString(), ((Player) ((Projectile) e.getDamager()).getShooter()).getUniqueId().toString());
                    plugin.playerLastDamagerTime.put(player.getUniqueId().toString(), new Date().getTime());
                }
            }
        }
    }
}
