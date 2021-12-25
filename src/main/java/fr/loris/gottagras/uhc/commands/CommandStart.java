package fr.loris.gottagras.uhc.commands;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.border;
import fr.loris.gottagras.uhc.infos.server;
import fr.loris.gottagras.uhc.infos.state;
import fr.loris.gottagras.uhc.infos.timers;
import fr.loris.gottagras.uhc.utils.mysql;
import fr.loris.gottagras.uhc.utils.teams;
import fr.loris.gottagras.uhc.utils.teleporting;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class CommandStart implements CommandExecutor {
    private final UHC plugin;

    public CommandStart(UHC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerRank = null;
            try {
                playerRank = new mysql(plugin).getRank(player);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (playerRank != null) {
                if (playerRank.equals("Admin") || playerRank.equals("Host")) {
                    startUHC();
                } else player.sendMessage(plugin.prefixMsg + "Vous ne pouvez pas execute cette commande !");
            } else player.sendMessage(plugin.prefixMsg + "Vous ne pouvez pas execute cette commande !");
        } else sender.sendMessage(plugin.prefixMsg + "Vous ne pouvez pas execute cette commande !");
        return false;
    }

    public void startUHC() {
        plugin.statue = state.STARTING;
        // CHECK PLAYER TEAM
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!new teams(plugin).hasTeam(player)) {
                for (int i = 1; i <= server.NUMBER_OF_TEAM.get(); i++) {
                    if (!new teams(plugin).hasTeam(player)) {
                        if (new teams(plugin).canJoinTeamBySize(i)) new teams(plugin).joinTeamById(player, i);
                    }
                }
            }
        }
        // SET WORLD BORDER
        Bukkit.getWorld("uhc-world").getWorldBorder().setSize(border.INITIAL_SIZE.get());
        Bukkit.getWorld("uhc-world").setGameRuleValue("naturalRegeneration", "false");
        Bukkit.getWorld("uhc-world").setTime(0);
        Bukkit.getWorld("uhc-world").setStorm(false);
        Bukkit.getWorld("uhc-world").setThundering(false);
        Bukkit.getWorld("uhc-world").setWeatherDuration(timers.MEETUP.getTime() * 100000);

        Bukkit.getWorld("uhc-nether").getWorldBorder().setSize(border.INITIAL_SIZE.get());
        Bukkit.getWorld("uhc-nether").setGameRuleValue("naturalRegeneration", "false");

        Bukkit.getWorld("uhc-end").getWorldBorder().setSize(border.INITIAL_SIZE.get());
        Bukkit.getWorld("uhc-end").setGameRuleValue("naturalRegeneration", "false");
        // GO TO TELEPORTING
        new teleporting(plugin).run();
    }
}
