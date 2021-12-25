package fr.loris.gottagras.uhc.utils;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.entity.Player;

public class game {
    private UHC plugin;

    public game(UHC plugin) {
        this.plugin = plugin;
    }

    public void updateKills(Player player) {
        if (plugin.playerKillCounter.get(player.getName()) == null) {
            plugin.playerKillCounter.put(player.getName(), 1);
        } else {
            int kills = plugin.playerKillCounter.get(player.getName());
            kills++;
            plugin.playerKillCounter.put(player.getName(), kills);
        }
    }

    public void updateKillsPvE() {
        if (plugin.playerKillCounter.get("PvE") == null) {
            plugin.playerKillCounter.put("PvE", 1);
        } else {
            int kills = plugin.playerKillCounter.get("PvE");
            kills++;
            plugin.playerKillCounter.put("PvE", kills);
        }
    }
}
