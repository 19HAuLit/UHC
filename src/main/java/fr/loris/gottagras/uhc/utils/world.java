package fr.loris.gottagras.uhc.utils;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.infos.border;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import java.io.File;

public class world {
    private UHC plugin;
    public world(UHC plugin) {
        this.plugin = plugin;
    }

    public void autoGenerateUHC() {
        File uhcWorldFiles = new File(System.getProperty("user.dir") + "\\uhc-world");
        File uhcNetherFiles = new File(System.getProperty("user.dir") + "\\uhc-nether");
        File uhcEndFiles = new File(System.getProperty("user.dir") + "\\uhc-end");

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

    public void generateWorld(String name, World.Environment environment){
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.environment(environment);
        worldCreator.createWorld();
    }

    public boolean checkWorldPlayability(World world){
        int totalBlocks = 0;
        int oceanBlocks = 0;
        int centerBlocks = 0;
        int oceanCenterBlocks = 0;
        int jungleCenterBlocks = 0;
        int swamplandCenterBlocks = 0;
        for (int x = (int) (border.INITIAL_SIZE.get()*0.5*(-1)+border.INITIAL_X.get()); x < (int) (border.INITIAL_X.get()*0.5+border.INITIAL_X.get()); x++){
            for (int z = (int) (border.INITIAL_SIZE.get()*0.5*(-1)+border.INITIAL_Z.get()); z < (int) (border.INITIAL_Z.get()*0.5+border.INITIAL_Z.get()); z++){
                totalBlocks++;
                if (world.getBiome(x, z) == Biome.OCEAN || world.getBiome(x, z) == Biome.DEEP_OCEAN || world.getBiome(x, z) == Biome.FROZEN_OCEAN) oceanBlocks++;
                if (x > border.FINAL_SIZE.get() * 0.5 * (-1) + border.FINAL_X.get() && x < border.FINAL_SIZE.get() * 0.5 + border.FINAL_X.get() && z > border.FINAL_SIZE.get() * 0.5 * (-1) + border.FINAL_Z.get() && z < border.FINAL_SIZE.get() * 0.5 + border.FINAL_Z.get()){
                    centerBlocks++;
                    if (world.getBiome(x, z) == Biome.OCEAN || world.getBiome(x, z) == Biome.DEEP_OCEAN || world.getBiome(x, z) == Biome.FROZEN_OCEAN) oceanCenterBlocks++;
                    if (world.getBiome(x, z) == Biome.SWAMPLAND || world.getBiome(x, z) == Biome.SWAMPLAND_MOUNTAINS) swamplandCenterBlocks++;
                    if (world.getBiome(x, z) == Biome.JUNGLE || world.getBiome(x, z) == Biome.JUNGLE_HILLS || world.getBiome(x, z) == Biome.JUNGLE_MOUNTAINS) jungleCenterBlocks++;
                }
            }
        }
        float percentOcean = (float) (oceanBlocks / totalBlocks)*100;
        float percentCenter = (float) ((oceanCenterBlocks+jungleCenterBlocks+swamplandCenterBlocks)/centerBlocks)*100;
        plugin.getLogger().info("Percent of Ocean: "+percentOcean);
        plugin.getLogger().info("Percent of ocean, jungle, swampland on the center: "+percentCenter);
        return !(percentCenter > 30 || percentOcean > 30);
    }

    public void unloadWorld(World world){
        for (Player player: Bukkit.getOnlinePlayers()){
            if (player.getWorld() == world) player.kickPlayer(ChatColor.RED+"Unloading world ("+world.getName()+").\nTry to reconnect!");
        }
    }
}
