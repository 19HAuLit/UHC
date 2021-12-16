package fr.loris.gottagras.uhc.commands;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.Objects;
import java.util.Random;

public class CommandTest implements CommandExecutor {
    private final UHC plugin;
    public CommandTest(UHC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            Random random = new Random();
            int randInt = random.nextInt(server.NUMBER_OF_TEAM.get());
            if (randInt == 0) randInt++;

            for (Team team:player.getScoreboard().getTeams()){
                team.removePlayer(player);
                player.setDisplayName(player.getName());
                player.setPlayerListName(player.getName());
            }

            for (Team team:plugin.scoreboard.getTeams()){
                if(Objects.equals(team.getName(), "uhc_team_" + randInt)){
                    team.addPlayer(player);
                    player.setDisplayName(team.getPrefix() + player.getName());
                    player.setPlayerListName(team.getPrefix() + player.getName());
                }
            }
        }
        return false;
    }
}
