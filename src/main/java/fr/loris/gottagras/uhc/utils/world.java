package fr.loris.gottagras.uhc.utils;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.border;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import java.io.File;

public class world {
    private UHC plugin;

    public world(UHC plugin) {
        this.plugin = plugin;
    }

    public void setWorldSettings(World world){
        world.setGameRuleValue("doDaylightCycle", "false");
        world.setGameRuleValue("doMobSpawning", "false");
        world.setGameRuleValue("mobGriefing", "false");
        world.setGameRuleValue("doFireTick", "false");
        world.setTime(0);
        world.setAmbientSpawnLimit(0);
        world.setAnimalSpawnLimit(0);
        world.setMonsterSpawnLimit(0);
        world.setWaterAnimalSpawnLimit(0);
    }

    public void autoGenerateUHC() {
        for (Player player:Bukkit.getOnlinePlayers()){
            player.kickPlayer(plugin.prefixMsg+ChatColor.RED+"Le serveur est en chargement, veuillez patienter !");
        }

        unloadWorld(Bukkit.getWorld("uhc-world"));
        unloadWorld(Bukkit.getWorld("uhc-nether"));
        unloadWorld(Bukkit.getWorld("uhc-end"));

        File uhcWorldFiles = new File(System.getProperty("user.dir") + "/uhc-world");
        File uhcNetherFiles = new File(System.getProperty("user.dir") + "/uhc-nether");
        File uhcEndFiles = new File(System.getProperty("user.dir") + "/uhc-end");

        new file().fileDelete(uhcWorldFiles);
        new file().fileDelete(uhcNetherFiles);
        new file().fileDelete(uhcEndFiles);

        generateWorld("uhc-world", World.Environment.NORMAL);
        generateWorld("uhc-nether", World.Environment.NETHER);
        generateWorld("uhc-end", World.Environment.THE_END);

        if (!checkWorldPlayability(Bukkit.getWorld("uhc-world"))) {
            unloadWorld(Bukkit.getWorld("uhc-world"));
            unloadWorld(Bukkit.getWorld("uhc-nether"));
            unloadWorld(Bukkit.getWorld("uhc-end"));
            autoGenerateUHC();
        }
    }

    public void generateWorld(String name, World.Environment environment) {
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.environment(environment);
        worldCreator.createWorld();
    }

    public boolean checkWorldPlayability(World world) {
        int totalBlocks = 0;
        int oceanBlocks = 0;
        int centerBlocks = 0;
        int oceanCenterBlocks = 0;
        int jungleCenterBlocks = 0;
        int swamplandCenterBlocks = 0;
        for (int x = (int) (border.INITIAL_SIZE.get() * 0.5 * (-1) + border.INITIAL_X.get()); x < (int) (border.INITIAL_X.get() * 0.5 + border.INITIAL_X.get()); x++) {
            for (int z = (int) (border.INITIAL_SIZE.get() * 0.5 * (-1) + border.INITIAL_Z.get()); z < (int) (border.INITIAL_Z.get() * 0.5 + border.INITIAL_Z.get()); z++) {
                totalBlocks++;
                if (world.getBiome(x, z) == Biome.OCEAN || world.getBiome(x, z) == Biome.DEEP_OCEAN || world.getBiome(x, z) == Biome.FROZEN_OCEAN)
                    oceanBlocks++;
                if (x > border.FINAL_SIZE.get() * 0.5 * (-1) + border.FINAL_X.get() && x < border.FINAL_SIZE.get() * 0.5 + border.FINAL_X.get() && z > border.FINAL_SIZE.get() * 0.5 * (-1) + border.FINAL_Z.get() && z < border.FINAL_SIZE.get() * 0.5 + border.FINAL_Z.get()) {
                    centerBlocks++;
                    if (world.getBiome(x, z) == Biome.OCEAN || world.getBiome(x, z) == Biome.DEEP_OCEAN || world.getBiome(x, z) == Biome.FROZEN_OCEAN)
                        oceanCenterBlocks++;
                    if (world.getBiome(x, z) == Biome.SWAMPLAND || world.getBiome(x, z) == Biome.SWAMPLAND_MOUNTAINS)
                        swamplandCenterBlocks++;
                    if (world.getBiome(x, z) == Biome.JUNGLE || world.getBiome(x, z) == Biome.JUNGLE_HILLS || world.getBiome(x, z) == Biome.JUNGLE_MOUNTAINS)
                        jungleCenterBlocks++;
                }
            }
        }
        plugin.getLogger().info("Total blocks: " + totalBlocks);
        plugin.getLogger().info("Block of Ocean: " + oceanBlocks);
        plugin.getLogger().info("Total center blocks: " + centerBlocks);
        plugin.getLogger().info("Block of ocean, jungle, swampland on the center: " + (oceanCenterBlocks + jungleCenterBlocks + swamplandCenterBlocks));
        return oceanBlocks <= totalBlocks / 3 && (oceanCenterBlocks + jungleCenterBlocks + swamplandCenterBlocks) <= centerBlocks / 3;
    }

    public void unloadWorld(World world) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getWorld() == world){
                player.kickPlayer(plugin.prefixMsg+ChatColor.RED+"Le serveur est en chargement, veuillez patienter !");
            }
        }
        Bukkit.unloadWorld(world, false);
    }
}
