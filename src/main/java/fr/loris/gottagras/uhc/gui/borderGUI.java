package fr.loris.gottagras.uhc.gui;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.border;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class borderGUI {
    private final UHC plugin;
    public borderGUI(UHC plugin){
        this.plugin = plugin;
    }

    public ItemStack item(){
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA+"BORDER");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("Initial Size: "+((int) border.INITIAL_SIZE.get()));
        lore.add("Final Size: "+((int) border.FINAL_SIZE.get()));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public Inventory inventory(){
        Inventory inventory = Bukkit.createInventory(null, 36, ChatColor.AQUA+"BORDER");
        // INITIAL SIZE
        ItemStack initialSizeMinus100 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta initialSizeMinus100ItemMeta = initialSizeMinus100.getItemMeta();
        initialSizeMinus100ItemMeta.setDisplayName(ChatColor.RED+"-100");
        initialSizeMinus100.setItemMeta(initialSizeMinus100ItemMeta);
        inventory.setItem(10, initialSizeMinus100);

        ItemStack initialSizeMinus10 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta initialSizeMinus10ItemMeta = initialSizeMinus10.getItemMeta();
        initialSizeMinus10ItemMeta.setDisplayName(ChatColor.RED+"-10");
        initialSizeMinus10.setItemMeta(initialSizeMinus10ItemMeta);
        inventory.setItem(11, initialSizeMinus10);

        ItemStack initialSizeMinus1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta initialSizeMinus1ItemMeta = initialSizeMinus1.getItemMeta();
        initialSizeMinus1ItemMeta.setDisplayName(ChatColor.RED+"-1");
        initialSizeMinus1.setItemMeta(initialSizeMinus1ItemMeta);
        inventory.setItem(12, initialSizeMinus1);
        // FINAL SIZE
        return inventory;
    }
}
