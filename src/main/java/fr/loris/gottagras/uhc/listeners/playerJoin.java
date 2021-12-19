package fr.loris.gottagras.uhc.listeners;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.gui.settingsGUI;
import fr.loris.gottagras.uhc.gui.teamsGUI;
import fr.loris.gottagras.uhc.utils.mysql;
import fr.loris.gottagras.uhc.utils.resetPlayer;
import org.bukkit.ChatColor;
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
            e.getPlayer().setOp(Objects.equals(rank, "Admin") || Objects.equals(rank, "Host"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        e.setJoinMessage(plugin.prefixMsg + ChatColor.AQUA + e.getPlayer().getName() + ChatColor.GRAY + " vient pour se battre !");
        switch (plugin.statue) {
            case LOADING:
                e.getPlayer().kickPlayer(ChatColor.RED + "Server is loading");
            case WAITING:
                new resetPlayer().resetAll(e.getPlayer());
                e.getPlayer().getInventory().setItem(0, new teamsGUI(plugin).item());
                e.getPlayer().getInventory().setItem(1, new settingsGUI(plugin).item());
                e.getPlayer().teleport(plugin.spawnLocation);
        }
    }
}
