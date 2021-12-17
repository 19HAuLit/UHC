package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.gui.teamGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class inventoryClick implements Listener {
    private UHC plugin;

    public inventoryClick(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        // GUIs
        if (e.getCurrentItem() != null){
            if (e.getCurrentItem().getType() != Material.AIR){
                if (Objects.equals(e.getClickedInventory().getName(), new teamGUI(plugin).inventory().getName())){
                    e.setCancelled(true);

                    Player player = (Player) e.getWhoClicked();
                    for (Team team:player.getScoreboard().getTeams()){
                        team.removePlayer(player);
                        player.setDisplayName(player.getName());
                        player.setPlayerListName(player.getName());
                    }

                    for (Team team:plugin.scoreboard.getTeams()){
                        if(Objects.equals(team.getName(), "uhc_team_" + (e.getSlot()+1))){
                            team.addPlayer(player);
                            player.setDisplayName(team.getPrefix() + player.getName());
                            player.setPlayerListName(team.getPrefix() + player.getName());
                        }
                    }
                }
            }
        }
    }
}
