package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class inventoryClick implements Listener {
    private UHC plugin;
    public inventoryClick(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
    }
}
