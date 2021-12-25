package fr.loris.gottagras.uhc.gui;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class configGUI {
    private final UHC plugin;

    public configGUI(UHC plugin) {
        this.plugin = plugin;
    }

    public ItemStack item() {
        ItemStack itemStack = new ItemStack(Material.BOOKSHELF);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "Configuration");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public Inventory inventory() {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Configuration");
        inventory.setItem(10, uhc_classico());
        return inventory;
    }

    public ItemStack uhc_classico() {
        ItemStack itemStack = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Classico");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
