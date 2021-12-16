package fr.loris.gottagras.uhc.gui;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.server;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class teamGUI {
    private final UHC plugin;
    public teamGUI(UHC plugin) {
        this.plugin = plugin;
    }

    public ItemStack item() {
        ItemStack itemStack = new ItemStack(Material.NAME_TAG);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD+"Teams");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public Inventory inventory() {
        // INVENTORY CREATOR
        int inventorySize = 0;
        int nbTeams = server.NUMBER_OF_TEAM.get();
        while (nbTeams > 0){
            inventorySize += 9;
            nbTeams -= 9;
        }
        Inventory inventory = Bukkit.createInventory(null, inventorySize, item().getItemMeta().getDisplayName());
        // TEAM ITEM
        for (int i = 1; i <= server.NUMBER_OF_TEAM.get(); i++){
            ItemStack itemStack = new ItemStack(Material.WOOL);
            switch (i%16){
                case 0:
                    itemStack = new ItemStack(Material.WOOL, 1, (short) 15);
                    break;
                case 1:
                    itemStack = new ItemStack(Material.WOOL, 1, (short) 11);
                    break;
                case 2:
                    itemStack = new ItemStack(Material.WOOL, 1, (short) 13);
                    break;
                case 3:
                case 11:
                    itemStack = new ItemStack(Material.WOOL, 1, (short) 9);
                    break;
                case 4:
                case 12:
                    itemStack = new ItemStack(Material.WOOL, 1, (short) 14);
                    break;
                case 5:
                    itemStack = new ItemStack(Material.WOOL, 1, (short) 10);
                    break;
                case 6:
                    itemStack = new ItemStack(Material.WOOL, 1, (short) 1);
                    break;
                case 7:
                    itemStack = new ItemStack(Material.WOOL, 1, (short) 8);
                    break;
                case 8:
                    itemStack = new ItemStack(Material.WOOL, 1, (short) 7);
                    break;
                case 9:
                    itemStack = new ItemStack(Material.WOOL, 1, (short) 3);
                    break;
                case 10:
                    itemStack = new ItemStack(Material.WOOL, 1, (short) 5);
                    break;
                case 13:
                    itemStack = new ItemStack(Material.WOOL, 1, (short) 2);
                    break;
                case 14:
                    itemStack = new ItemStack(Material.WOOL, 1, (short) 4);
                    break;
                case 15:
                    itemStack = new ItemStack(Material.WOOL, 1, (short) 0);
                    break;
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(plugin.scoreboard.getTeam("uhc_team_"+i).getDisplayName());
            itemStack.setItemMeta(itemMeta);
            inventory.addItem(itemStack);
        }
        return inventory;
    }
}
