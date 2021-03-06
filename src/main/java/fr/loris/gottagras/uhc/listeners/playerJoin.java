package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.gui.settingsGUI;
import fr.loris.gottagras.uhc.gui.teamsGUI;
import fr.loris.gottagras.uhc.utils.mysql;
import fr.loris.gottagras.uhc.utils.resetPlayer;
import fr.loris.gottagras.uhc.utils.teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;
import java.util.Objects;

public class playerJoin implements Listener {

    private final UHC plugin;

    public playerJoin(UHC plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        try {
            new mysql(plugin).registerPlayer(e.getPlayer());
            String rank = new mysql(plugin).getRank(e.getPlayer());
            e.getPlayer().setOp(Objects.equals(rank, "Admin"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        e.setJoinMessage(plugin.prefixMsg + ChatColor.AQUA + e.getPlayer().getName() + ChatColor.GRAY + " vient pour se battre !");
        switch (plugin.statue) {
            case LOADING:
                e.getPlayer().kickPlayer(ChatColor.RED + "Server is loading");
                break;
            case WAITING:
                waitingStuff(e.getPlayer());
                break;
            default:
                if (new teams(plugin).hasTeam(e.getPlayer())) {

                } else {
                    new resetPlayer().resetAll(e.getPlayer());
                    e.getPlayer().setGameMode(GameMode.SPECTATOR);
                    e.getPlayer().teleport(new Location(Bukkit.getWorld("uhc-world"), 0, 100, 0));
                }
                break;
        }
    }

    public void waitingStuff(Player player) {
        new resetPlayer().resetAll(player);
        player.getInventory().setItem(0, new teamsGUI(plugin).item());
        player.getInventory().setItem(1, new settingsGUI(plugin).item());
        player.teleport(plugin.spawnLocation);
    }
}
