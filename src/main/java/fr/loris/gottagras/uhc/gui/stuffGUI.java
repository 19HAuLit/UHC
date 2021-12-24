package fr.loris.gottagras.uhc.gui;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class stuffGUI {
    private final UHC plugin;

    public stuffGUI(UHC plugin) {
        this.plugin = plugin;
    }

    public ItemStack item() {
        ItemStack itemStack = new ItemStack(Material.CHEST);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "Inventaire de depart");
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(ChatColor.GOLD + "Click Gauche: " + ChatColor.GRAY + "Voir");
        Lore.add(ChatColor.GOLD + "Click Droit: " + ChatColor.GRAY + "Modifier");
        itemMeta.setLore(Lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public Inventory inventory() {
        Inventory inventory = Bukkit.createInventory(null, 45, ChatColor.DARK_GRAY + "Inventaire de depart");
        if (plugin.starterInventory != null) {
            for (ItemStack itemStack : plugin.starterInventory) {
                if (itemStack == null) inventory.addItem(new ItemStack(Material.AIR));
                else inventory.addItem(itemStack);
            }
        }
        if (plugin.starterArmor != null) {
            int slot = 36;
            for (ItemStack itemStack : plugin.starterArmor) {
                if (itemStack == null) inventory.setItem(slot, new ItemStack(Material.AIR));
                else inventory.setItem(slot, itemStack);
                slot++;
            }
        }
        return inventory;
    }
}
