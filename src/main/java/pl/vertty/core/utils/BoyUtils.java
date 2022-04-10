// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.utils;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;

public final class BoyUtils
{
    private BoyUtils() {
    }
    
    public static void farmer_kopacz(final Location location) {
        double y = location.getY();
        while (true) {
            final Location loc = new Location(location.getWorld(), location.getX(), y - 1.0, location.getZ());
            final Block block = loc.getBlock();
            if (block.getType() == Material.BEDROCK) {
                break;
            }
            block.setType(Material.AIR);
//            Level l = Server.getInstance().getDefaultLevel();
//            l.setBlock(new Vector3(loc.getX(), y, loc.getZ()), Block.get(0));
            --y;
        }
    }
    
    public static void farmer(final Location location) {
        double y = location.getY();
        while (true) {
            final Location loc = new Location(location.getWorld(), location.getX(), y - 1.0, location.getZ());
            final Block block = loc.getBlock();
            if (block.getType() == Material.BEDROCK) {
                break;
            }
            block.setType(Material.OBSIDIAN);
//            Level l = Server.getInstance().getDefaultLevel();
//            l.setBlock(new Vector3(loc.getX(), y, loc.getZ()), Block.get(49));
            --y;
        }
    }
    
    public static boolean parseLocation(final int minX, final int maxX, final int minZ, final int maxZ, final Location l) {
        final double[] dim = { minX, maxX };
        Arrays.sort(dim);
        if (l.getX() >= dim[1] || l.getX() <= dim[0]) {
            return false;
        }
        dim[0] = minZ;
        dim[1] = maxZ;
        Arrays.sort(dim);
        return l.getZ() < dim[1] && l.getZ() > dim[0];
    }
}
