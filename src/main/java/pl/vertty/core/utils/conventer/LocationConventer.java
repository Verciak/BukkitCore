
package pl.vertty.core.utils.conventer;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;

public final class LocationConventer
{
    public static Location deserialize(final String p0) {
        final String[] split = p0.split(";");
        return new Location(Bukkit.getWorld("world"), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]));
    }
    
    public static String serialize(final Location loc) {
        return "world;" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getYaw() + ";" + loc.getPitch();
    }
}
