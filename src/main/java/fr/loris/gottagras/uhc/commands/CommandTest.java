package fr.loris.gottagras.uhc.commands;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTest implements CommandExecutor {
    private final UHC plugin;

    public CommandTest(UHC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            plugin.starterInventory = player.getInventory().getContents();
        }
        return false;
    }
}
