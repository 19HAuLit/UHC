package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.gui.teamGUI;
import fr.loris.gottagras.uhc.utils.team;
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
        if (e.getCurrentItem() != null) {
            switch (plugin.statue) {
                case WAITING:
                    if (e.getWhoClicked().getGameMode() != GameMode.CREATIVE) {
                        // TEAM GUI
                        if (e.getClickedInventory().getName().equals(new teamGUI().inventory().getName())) {
                            if (e.getCurrentItem().getType() == Material.SIGN) {
                                new team().addPlayerToTeamWithLimit(e.getSlot() + "", (Player) e.getWhoClicked());
                                e.setCancelled(true);
                            }
                            e.getWhoClicked().openInventory(new teamGUI().inventory());
                        }
                    }
            }
        }
    }
}
