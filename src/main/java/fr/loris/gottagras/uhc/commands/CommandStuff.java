package fr.loris.gottagras.uhc.commands;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.gui.stuffGUI;
import fr.loris.gottagras.uhc.listeners.playerJoin;
import fr.loris.gottagras.uhc.utils.mysql;
import fr.loris.gottagras.uhc.utils.resetPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.Objects;

public class CommandStuff implements CommandExecutor {
    private final UHC plugin;

    public CommandStuff(UHC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
                if (Objects.equals(new mysql(plugin).getRank(player), "Admin") || Objects.equals(new mysql(plugin).getRank(player), "Host")) {
                    if (args.length == 1) {
                        switch (args[0]) {
                            case "modify":
                                new resetPlayer().resetAll(player);
                                if (plugin.starterInventory != null) {
                                    for (ItemStack itemStack : plugin.starterInventory) {
                                        if (itemStack == null)
                                            player.getInventory().addItem(new ItemStack(Material.AIR));
                                        else player.getInventory().addItem(itemStack);
                                    }
                                }
                                if (plugin.starterArmor != null) {
                                    int armorSlot = 0;
                                    for (ItemStack itemStack : plugin.starterArmor) {
                                        switch (armorSlot) {
                                            case 0:
                                                player.getInventory().setBoots(itemStack);
                                                break;
                                            case 1:
                                                player.getInventory().setLeggings(itemStack);
                                                break;
                                            case 2:
                                                player.getInventory().setChestplate(itemStack);
                                                break;
                                            case 3:
                                                player.getInventory().setHelmet(itemStack);
                                                break;
                                        }
                                        armorSlot++;
                                    }
                                }
                                player.setGameMode(GameMode.CREATIVE);
                                player.sendMessage(plugin.prefixMsg + "N'oubliez pas de " + ChatColor.GOLD + "/stuff save" + ChatColor.DARK_GRAY + " et de " + ChatColor.GOLD + "/stuff finish" + ChatColor.DARK_GRAY + " a la fin de la modification du stuff de depart");
                                break;
                            case "save":
                                plugin.starterInventory = player.getInventory().getContents();
                                plugin.starterArmor = player.getInventory().getArmorContents();
                                player.sendMessage(plugin.prefixMsg + "Stuff de  depart enregistre");
                                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                    if (Objects.equals(onlinePlayer.getOpenInventory().getTopInventory().getName(), new stuffGUI(plugin).inventory().getName())) {
                                        onlinePlayer.openInventory(new stuffGUI(plugin).inventory());
                                    }
                                }
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
