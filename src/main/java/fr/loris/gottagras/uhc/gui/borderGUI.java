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

    public borderGUI(UHC plugin) {
        this.plugin = plugin;
    }

    public ItemStack item() {
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "Border");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "Initial Size: " + ChatColor.GRAY + ((int) border.INITIAL_SIZE.get()));
        lore.add(ChatColor.GOLD + "Final Size: " + ChatColor.GRAY + ((int) border.FINAL_SIZE.get()));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public Inventory inventory() {
        Inventory inventory = Bukkit.createInventory(null, 36, ChatColor.DARK_GRAY + "Border");
        // INITIAL SIZE
        ItemStack initialSizeMinus100 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta initialSizeMinus100ItemMeta = initialSizeMinus100.getItemMeta();
        initialSizeMinus100ItemMeta.setDisplayName(ChatColor.RED + "-100");
        initialSizeMinus100.setItemMeta(initialSizeMinus100ItemMeta);
        inventory.setItem(10, initialSizeMinus100);

        ItemStack initialSizeMinus10 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta initialSizeMinus10ItemMeta = initialSizeMinus10.getItemMeta();
        initialSizeMinus10ItemMeta.setDisplayName(ChatColor.RED + "-10");
        initialSizeMinus10.setItemMeta(initialSizeMinus10ItemMeta);
        inventory.setItem(11, initialSizeMinus10);

        ItemStack initialSizeMinus1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta initialSizeMinus1ItemMeta = initialSizeMinus1.getItemMeta();
        initialSizeMinus1ItemMeta.setDisplayName(ChatColor.RED + "-1");
        initialSizeMinus1.setItemMeta(initialSizeMinus1ItemMeta);
        inventory.setItem(12, initialSizeMinus1);

        ItemStack initialSize = new ItemStack(Material.SIGN);
        ItemMeta initialSizeMeta = initialSize.getItemMeta();
        initialSizeMeta.setDisplayName(ChatColor.GRAY + "Initial: " + ChatColor.GOLD + ((int) border.INITIAL_SIZE.get()) + " blocks");
        initialSize.setItemMeta(initialSizeMeta);
        inventory.setItem(13, initialSize);

        ItemStack initialSizePlus1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta initialSizePlus1ItemMeta = initialSizePlus1.getItemMeta();
        initialSizePlus1ItemMeta.setDisplayName(ChatColor.GREEN + "+1");
        initialSizePlus1.setItemMeta(initialSizePlus1ItemMeta);
        inventory.setItem(14, initialSizePlus1);

        ItemStack initialSizePlus10 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta initialSizePlus10ItemMeta = initialSizePlus10.getItemMeta();
        initialSizePlus10ItemMeta.setDisplayName(ChatColor.GREEN + "+10");
        initialSizePlus10.setItemMeta(initialSizePlus10ItemMeta);
        inventory.setItem(15, initialSizePlus10);

        ItemStack initialSizePlus100 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta initialSizePlus100ItemMeta = initialSizePlus100.getItemMeta();
        initialSizePlus100ItemMeta.setDisplayName(ChatColor.GREEN + "+100");
        initialSizePlus100.setItemMeta(initialSizePlus100ItemMeta);
        inventory.setItem(16, initialSizePlus100);

        // final SIZE
        ItemStack finalSizeMinus100 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta finalSizeMinus100ItemMeta = finalSizeMinus100.getItemMeta();
        finalSizeMinus100ItemMeta.setDisplayName(ChatColor.RED + "-100");
        finalSizeMinus100.setItemMeta(finalSizeMinus100ItemMeta);
        inventory.setItem(19, finalSizeMinus100);

        ItemStack finalSizeMinus10 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta finalSizeMinus10ItemMeta = finalSizeMinus10.getItemMeta();
        finalSizeMinus10ItemMeta.setDisplayName(ChatColor.RED + "-10");
        finalSizeMinus10.setItemMeta(finalSizeMinus10ItemMeta);
        inventory.setItem(20, finalSizeMinus10);

        ItemStack finalSizeMinus1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta finalSizeMinus1ItemMeta = finalSizeMinus1.getItemMeta();
        finalSizeMinus1ItemMeta.setDisplayName(ChatColor.RED + "-1");
        finalSizeMinus1.setItemMeta(finalSizeMinus1ItemMeta);
        inventory.setItem(21, finalSizeMinus1);

        ItemStack finalSize = new ItemStack(Material.SIGN);
        ItemMeta finalSizeMeta = finalSize.getItemMeta();
        finalSizeMeta.setDisplayName(ChatColor.GRAY + "Final: " + ChatColor.GOLD + ((int) border.FINAL_SIZE.get()) + " blocks");
        finalSize.setItemMeta(finalSizeMeta);
        inventory.setItem(22, finalSize);

        ItemStack finalSizePlus1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta finalSizePlus1ItemMeta = finalSizePlus1.getItemMeta();
        finalSizePlus1ItemMeta.setDisplayName(ChatColor.GREEN + "+1");
        finalSizePlus1.setItemMeta(finalSizePlus1ItemMeta);
        inventory.setItem(23, finalSizePlus1);

        ItemStack finalSizePlus10 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta finalSizePlus10ItemMeta = finalSizePlus10.getItemMeta();
        finalSizePlus10ItemMeta.setDisplayName(ChatColor.GREEN + "+10");
        finalSizePlus10.setItemMeta(finalSizePlus10ItemMeta);
        inventory.setItem(24, finalSizePlus10);

        ItemStack finalSizePlus100 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta finalSizePlus100ItemMeta = finalSizePlus100.getItemMeta();
        finalSizePlus100ItemMeta.setDisplayName(ChatColor.GREEN + "+100");
        finalSizePlus100.setItemMeta(finalSizePlus100ItemMeta);
        inventory.setItem(25, finalSizePlus100);
        return inventory;
    }
}
