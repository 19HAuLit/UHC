package fr.loris.gottagras.uhc.timers;

import fr.loris.gottagras.uhc.UHC;
import fr.loris.gottagras.uhc.utils.mysql;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;

public class timePlayedUpdater extends BukkitRunnable implements Runnable {
    private UHC plugin;
    public timePlayedUpdater(UHC plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player: Bukkit.getOnlinePlayers()) {
            try {
                new mysql(plugin).updateTimePlayed(player);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
