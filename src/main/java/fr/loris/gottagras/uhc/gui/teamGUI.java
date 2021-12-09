package fr.loris.gottagras.uhc.gui;

import fr.loris.gottagras.uhc.infos.server;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class teamGUI {
    public Inventory inventory() {
        Inventory inventory = Bukkit.createInventory(null, 9 * 8, ChatColor.GRAY + "Teams");
        int i = 0;
        for (Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
            if (i <= server.MAX_PLAYERS.get() / Bukkit.getScoreboardManager().getMainScoreboard().getTeams().size()) {
                ItemStack itemStack = new ItemStack(Material.SIGN);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(team.getPrefix() + team.getName());
                ArrayList<String> playersInTeam = new ArrayList<>();
                for (OfflinePlayer player : team.getPlayers()) {
                    playersInTeam.add(player.getName());
                }
                itemMeta.setLore(playersInTeam);
                itemStack.setItemMeta(itemMeta);
                inventory.setItem(i, itemStack);
            }
            i++;
        }
        return inventory;
    }

    public ItemStack itemMenu() {
        ItemStack itemStack = new ItemStack(Material.BANNER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Teams");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
