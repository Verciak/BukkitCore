package pl.vertty.core.utils;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import pl.vertty.core.data.config.PluginConfiguration;

public final class LocationUtils
{
    private LocationUtils() {
    }
    
    public static Location locFromString(final String string) {
        final String[] str2loc = string.split(";");
        final Location loc = new Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0, 0.0f, 0.0f);
        loc.setX(Double.parseDouble(str2loc[0]));
        loc.setY(Double.parseDouble(str2loc[1]));
        loc.setZ(Double.parseDouble(str2loc[2]));
        return loc;
    }
    
    public static String locToString(final Location loc) {
        return "world;" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getYaw() + ";" + loc.getPitch();
    }
    
    public static Location getRandomCords(Player player) {
        int x = RandomUtils.getRandInt(-PluginConfiguration.getInstance().getRandomtp_min(), PluginConfiguration.getInstance().getRandomtp_max());
        int z = RandomUtils.getRandInt(-PluginConfiguration.getInstance().getRandomtp_min(), PluginConfiguration.getInstance().getRandomtp_max());
        Location locc = new Location(player.getWorld(), x, player.getWorld().getHighestBlockYAt(x,z), z );
        return locc;
    }
    
    public static List<Player> findPlayers(final Location location, final int distance) {
        List<Player> players = new ArrayList<>();
        for (Player p : location.getWorld().getPlayers()) {
            if (location.distance(p.getLocation()) <= distance)
                players.add(p);
        }
        return players;
    }
}
