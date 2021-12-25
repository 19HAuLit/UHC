package fr.loris.gottagras.uhc.gui;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.timers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class timersGUI {
    private final UHC plugin;

    public timersGUI(UHC plugin) {
        this.plugin = plugin;
    }

    public ItemStack item() {
        ItemStack itemStack = new ItemStack(Material.WATCH);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "Timers");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "PvE: " + ChatColor.GRAY + timers.PVE.getTime() + "s");
        lore.add(ChatColor.GOLD + "PvP: " + ChatColor.GRAY + timers.PVP.getTime() + "s");
        lore.add(ChatColor.GOLD + "Border: " + ChatColor.GRAY + timers.BORDER.getTime() + "s");
        lore.add(ChatColor.GOLD + "Meetup: " + ChatColor.GRAY + timers.MEETUP.getTime() + "s");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public Inventory inventory() {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Timers");
        PVE(inventory);
        PVP(inventory);
        BORDER(inventory);
        MEETUP(inventory);
        return inventory;
    }

    public void PVE(Inventory inventory) {
        ItemStack Minus100 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta Minus100ItemMeta = Minus100.getItemMeta();
        Minus100ItemMeta.setDisplayName(ChatColor.RED + "-100");
        Minus100.setItemMeta(Minus100ItemMeta);
        inventory.setItem(10, Minus100);

        ItemStack Minus10 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta Minus10ItemMeta = Minus10.getItemMeta();
        Minus10ItemMeta.setDisplayName(ChatColor.RED + "-10");
        Minus10.setItemMeta(Minus10ItemMeta);
        inventory.setItem(11, Minus10);

        ItemStack Minus1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta Minus1ItemMeta = Minus1.getItemMeta();
        Minus1ItemMeta.setDisplayName(ChatColor.RED + "-1");
        Minus1.setItemMeta(Minus1ItemMeta);
        inventory.setItem(12, Minus1);

        ItemStack itemStack = new ItemStack(Material.SIGN);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "PvE: " + ChatColor.GRAY + timers.PVE.getTime() + "s");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(13, itemStack);

        ItemStack Plus1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta Plus1ItemMeta = Plus1.getItemMeta();
        Plus1ItemMeta.setDisplayName(ChatColor.GREEN + "+1");
        Plus1.setItemMeta(Plus1ItemMeta);
        inventory.setItem(14, Plus1);

        ItemStack Plus10 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta Plus10ItemMeta = Plus10.getItemMeta();
        Plus10ItemMeta.setDisplayName(ChatColor.GREEN + "+10");
        Plus10.setItemMeta(Plus10ItemMeta);
        inventory.setItem(15, Plus10);

        ItemStack Plus100 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta Plus100ItemMeta = Plus100.getItemMeta();
        Plus100ItemMeta.setDisplayName(ChatColor.GREEN + "+100");
        Plus100.setItemMeta(Plus100ItemMeta);
        inventory.setItem(16, Plus100);
    }

    public void PVP(Inventory inventory) {
        ItemStack Minus100 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta Minus100ItemMeta = Minus100.getItemMeta();
        Minus100ItemMeta.setDisplayName(ChatColor.RED + "-100");
        Minus100.setItemMeta(Minus100ItemMeta);
        inventory.setItem(19, Minus100);

        ItemStack Minus10 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta Minus10ItemMeta = Minus10.getItemMeta();
        Minus10ItemMeta.setDisplayName(ChatColor.RED + "-10");
        Minus10.setItemMeta(Minus10ItemMeta);
        inventory.setItem(20, Minus10);

        ItemStack Minus1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta Minus1ItemMeta = Minus1.getItemMeta();
        Minus1ItemMeta.setDisplayName(ChatColor.RED + "-1");
        Minus1.setItemMeta(Minus1ItemMeta);
        inventory.setItem(21, Minus1);

        ItemStack itemStack = new ItemStack(Material.SIGN);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "PvP: " + ChatColor.GRAY + timers.PVP.getTime() + "s");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(22, itemStack);

        ItemStack Plus1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta Plus1ItemMeta = Plus1.getItemMeta();
        Plus1ItemMeta.setDisplayName(ChatColor.GREEN + "+1");
        Plus1.setItemMeta(Plus1ItemMeta);
        inventory.setItem(23, Plus1);

        ItemStack Plus10 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta Plus10ItemMeta = Plus10.getItemMeta();
        Plus10ItemMeta.setDisplayName(ChatColor.GREEN + "+10");
        Plus10.setItemMeta(Plus10ItemMeta);
        inventory.setItem(24, Plus10);

        ItemStack Plus100 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta Plus100ItemMeta = Plus100.getItemMeta();
        Plus100ItemMeta.setDisplayName(ChatColor.GREEN + "+100");
        Plus100.setItemMeta(Plus100ItemMeta);
        inventory.setItem(25, Plus100);
    }

    public void BORDER(Inventory inventory) {
        ItemStack Minus100 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta Minus100ItemMeta = Minus100.getItemMeta();
        Minus100ItemMeta.setDisplayName(ChatColor.RED + "-100");
        Minus100.setItemMeta(Minus100ItemMeta);
        inventory.setItem(28, Minus100);

        ItemStack Minus10 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta Minus10ItemMeta = Minus10.getItemMeta();
        Minus10ItemMeta.setDisplayName(ChatColor.RED + "-10");
        Minus10.setItemMeta(Minus10ItemMeta);
        inventory.setItem(29, Minus10);

        ItemStack Minus1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta Minus1ItemMeta = Minus1.getItemMeta();
        Minus1ItemMeta.setDisplayName(ChatColor.RED + "-1");
        Minus1.setItemMeta(Minus1ItemMeta);
        inventory.setItem(30, Minus1);

        ItemStack itemStack = new ItemStack(Material.SIGN);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Border: " + ChatColor.GRAY + timers.BORDER.getTime() + "s");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(31, itemStack);

        ItemStack Plus1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta Plus1ItemMeta = Plus1.getItemMeta();
        Plus1ItemMeta.setDisplayName(ChatColor.GREEN + "+1");
        Plus1.setItemMeta(Plus1ItemMeta);
        inventory.setItem(32, Plus1);

        ItemStack Plus10 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta Plus10ItemMeta = Plus10.getItemMeta();
        Plus10ItemMeta.setDisplayName(ChatColor.GREEN + "+10");
        Plus10.setItemMeta(Plus10ItemMeta);
        inventory.setItem(33, Plus10);

        ItemStack Plus100 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta Plus100ItemMeta = Plus100.getItemMeta();
        Plus100ItemMeta.setDisplayName(ChatColor.GREEN + "+100");
        Plus100.setItemMeta(Plus100ItemMeta);
        inventory.setItem(34, Plus100);
    }

    public void MEETUP(Inventory inventory) {
        ItemStack Minus100 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta Minus100ItemMeta = Minus100.getItemMeta();
        Minus100ItemMeta.setDisplayName(ChatColor.RED + "-100");
        Minus100.setItemMeta(Minus100ItemMeta);
        inventory.setItem(37, Minus100);

        ItemStack Minus10 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta Minus10ItemMeta = Minus10.getItemMeta();
        Minus10ItemMeta.setDisplayName(ChatColor.RED + "-10");
        Minus10.setItemMeta(Minus10ItemMeta);
        inventory.setItem(38, Minus10);

        ItemStack Minus1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemMeta Minus1ItemMeta = Minus1.getItemMeta();
        Minus1ItemMeta.setDisplayName(ChatColor.RED + "-1");
        Minus1.setItemMeta(Minus1ItemMeta);
        inventory.setItem(39, Minus1);

        ItemStack itemStack = new ItemStack(Material.SIGN);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Meetup: " + ChatColor.GRAY + timers.MEETUP.getTime() + "s");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(40, itemStack);

        ItemStack Plus1 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta Plus1ItemMeta = Plus1.getItemMeta();
        Plus1ItemMeta.setDisplayName(ChatColor.GREEN + "+1");
        Plus1.setItemMeta(Plus1ItemMeta);
        inventory.setItem(41, Plus1);

        ItemStack Plus10 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta Plus10ItemMeta = Plus10.getItemMeta();
        Plus10ItemMeta.setDisplayName(ChatColor.GREEN + "+10");
        Plus10.setItemMeta(Plus10ItemMeta);
        inventory.setItem(42, Plus10);

        ItemStack Plus100 = new ItemStack(Material.STAINED_CLAY, 1, (short) 13);
        ItemMeta Plus100ItemMeta = Plus100.getItemMeta();
        Plus100ItemMeta.setDisplayName(ChatColor.GREEN + "+100");
        Plus100.setItemMeta(Plus100ItemMeta);
        inventory.setItem(43, Plus100);
    }
}
