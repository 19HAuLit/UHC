package fr.loris.gottagras.uhc.gui;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class settingsGUI {
    private final UHC plugin;

    public settingsGUI(UHC plugin) {
        this.plugin = plugin;
    }

    public ItemStack item() {
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.RED + "Settings");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public Inventory inventory() {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.RED + "Settings");
        inventory.setItem(10, new borderGUI(plugin).item());
        inventory.setItem(11, new stuffGUI(plugin).item());
        return inventory;
    }
}
