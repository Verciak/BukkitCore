package pl.vertty.core.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import java.util.UUID;
import java.util.List;

public class VanishManager
{
    private static final List<UUID> vanished;
    
    public static void toggleVanish(final Player player) {
        if (VanishManager.vanished.contains(player.getUniqueId())) {
            VanishManager.vanished.remove(player.getUniqueId());
            showPlayer(player);
        }
        else {
            VanishManager.vanished.add(player.getUniqueId());
            hidePlayer(player);
        }
    }
    
    private static void hidePlayer(final Player player) {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("mhCore.vanish")) {
                p.showPlayer(player);
            }
            else {
                p.hidePlayer(player);
            }
        }
    }
    
    private static void showPlayer(final Player player) {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.showPlayer(player);
        }
    }
    
    public static boolean isVanished(final Player player) {
        return VanishManager.vanished.contains(player.getUniqueId());
    }
    
    static {
        vanished = new ArrayList<UUID>();
    }
}
