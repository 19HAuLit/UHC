package fr.loris.gottagras.uhc.commands;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.listeners.playerJoin;
import fr.loris.gottagras.uhc.utils.resetPlayer;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandStuff implements CommandExecutor {
    private UHC plugin;

    public CommandStuff(UHC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                if (args.length == 1) {
                    switch (args[0]) {
                        case "modify":
                            new resetPlayer().resetAll(player);
                            if (plugin.starterInventory != null) {
                                for (ItemStack itemStack : plugin.starterInventory) {
                                    if (itemStack == null) player.getInventory().addItem(new ItemStack(Material.AIR));
                                    else player.getInventory().addItem(itemStack);
                                }
                            }
                            player.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(plugin.prefixMsg + "N'oubliez pas de " + ChatColor.GOLD + "/stuff save" + ChatColor.DARK_GRAY + " et de " + ChatColor.GOLD + "/stuff finish" + ChatColor.DARK_GRAY + " a la fin de la modification du stuff de depart");
                            break;
                        case "save":
                            plugin.starterInventory = player.getInventory().getContents();
                            player.sendMessage(plugin.prefixMsg + "Stuff de  depart enregistre");
                            break;
                        case "finish":
                            new playerJoin(plugin).waitingStuff(player);
                            player.sendMessage(plugin.prefixMsg + "Vous venez de quitter la modification du stuff de depart");
                            break;
                        default:
                            player.sendMessage(plugin.prefixMsg + "/stuff [modify/save/finish]");
                            break;
                    }
                } else player.sendMessage(plugin.prefixMsg + "/stuff [modify/save/finish]");
            } else player.sendMessage(plugin.prefixMsg + "Vous ne pouvez pas execute cette commande !");
        }
        return false;
    }
}
