package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.utils.game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Date;
import java.util.Objects;

import static org.bukkit.Material.AIR;

public class entityDamage implements Listener {
    private UHC plugin;

    public entityDamage(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = ((Player) e.getEntity()).getPlayer();
            // INVINCIBILITY AT SPAWN
            e.setCancelled(player.getWorld() == plugin.spawnLocation.getWorld() && e.getCause() != EntityDamageEvent.DamageCause.VOID);
            // GAME
            switch (plugin.statue) {
                case WAITING:
                case STARTING:
                case TELEPORTING:
                case BEGINNING:
                    e.setCancelled(true);
                    break;
                default:
                    if (e.getFinalDamage() >= ((Player) e.getEntity()).getHealth()) {
                        // DROP STUFF
                        for (ItemStack itemStack : ((Player) e.getEntity()).getInventory().getArmorContents()) {
                            if (itemStack != null) {
                                if (itemStack.getType() != AIR) {
                                    Bukkit.getWorld("uhc-world").dropItemNaturally(e.getEntity().getLocation(), itemStack);
                                }
                            }
                        }
                        for (ItemStack itemStack : ((Player) e.getEntity()).getInventory().getContents()) {
                            if (itemStack != null) {
                                if (itemStack.getType() != AIR) {
                                    Bukkit.getWorld("uhc-world").dropItemNaturally(e.getEntity().getLocation(), itemStack);
                                }
                            }
                        }
                        // CANCEL EVENT AND PLAY SOUND
                        e.setCancelled(true);
                        e.getEntity().getLocation().getWorld().playSound(e.getEntity().getLocation(), Sound.EXPLODE, 100, 1);
                        // DEATH MSG IF NOT ENTITY KILL
                        if (e.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                            if (plugin.playerLastDamagerTime.get(e.getEntity().getUniqueId().toString()) != null) {
                                if (new Date().getTime() - plugin.playerLastDamagerTime.get(e.getEntity().getUniqueId().toString()) <= 30000) {
                                    for (Player damager : Bukkit.getOnlinePlayers()) {
                                        if (Objects.equals(damager.getUniqueId().toString(), plugin.playerLastDamager.get(e.getEntity().getUniqueId().toString()))) {
                                            Bukkit.broadcastMessage(plugin.prefixMsg + player.getPlayerListName() + ChatColor.GRAY + " viens d'etre tuer par " + damager.getPlayerListName());
                                            new game(plugin).updateKills(damager);
                                        }
                                    }
                                } else {
                                    Bukkit.broadcastMessage(plugin.prefixMsg + ((Player) e.getEntity()).getPlayerListName() + ChatColor.GRAY + " viens de mourrir (" + e.getCause().name() + ")");
                                    new game(plugin).updateKillsPvE();
                                }
                            } else {
                                Bukkit.broadcastMessage(plugin.prefixMsg + ((Player) e.getEntity()).getPlayerListName() + ChatColor.GRAY + " viens de mourrir (" + e.getCause().name() + ")");
                                new game(plugin).updateKillsPvE();
                            }
                        }
                        ((Player) e.getEntity()).setGameMode(GameMode.SPECTATOR);
                    }
            }
        }
    }
}
