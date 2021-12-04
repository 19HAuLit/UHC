package fr.loris.gottagras.uhc.utils;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class resetPlayer {
    public void resetAll(Player player) {
        resetInventory(player);
        resetEffect(player);
        resetFood(player);
        resetHeal(player);
        resetLevel(player);
        resetGameMode(player);
    }

    public void resetInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setBoots(new ItemStack(Material.AIR));
    }

    public void resetEffect(Player player) {
        for (PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
    }

    public void resetFood(Player player) {
        player.setFoodLevel(20);
        player.setSaturation(20);
    }

    public void resetHeal(Player player) {
        player.setHealthScale(20);
        player.setHealth(20);
    }

    public void resetLevel(Player player) {
        player.setLevel(0);
        player.setExp(0);
        player.setTotalExperience(0);
    }

    public void resetGameMode(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
    }
}
