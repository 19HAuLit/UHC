package fr.loris.gottagras.uhc.commands;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandTest implements CommandExecutor {
    private final UHC plugin;

    public CommandTest(UHC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        server.PLAYER_PER_TEAM.set(5);
        return false;
    }
}
