package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class entityDamage implements Listener {
    private UHC plugin;
    public entityDamage(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player){
            Player player = ((Player) e.getEntity()).getPlayer();
            // INVINCIBILITY AT SPAWN
            e.setCancelled(player.getWorld() == plugin.spawnLocation.getWorld() && e.getCause() != EntityDamageEvent.DamageCause.VOID);
        }
    }
}
