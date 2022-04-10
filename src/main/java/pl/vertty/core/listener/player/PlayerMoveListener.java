package pl.vertty.core.listener.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.utils.BoyUtils;
import pl.vertty.core.manager.UserManager;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.utils.ChatUtils;

import java.util.Vector;

public class PlayerMoveListener implements Listener
{
    private final CorePlugin plugin;
    private final UserManager userManager;
    private final int size;
    
    public PlayerMoveListener(final CorePlugin plugin) {
        this.plugin = plugin;
        this.userManager = plugin.getUserManager();
        this.size = plugin.getPluginConfiguration().getSpawn_size();
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }

//    public static void knockLinePvP(Player p) {
//        double x = (p.getLocation().getX() - p.getWorld().getSpawnLocation().getX());
//        double z = (p.getLocation().getZ() - p.getWorld().getSpawnLocation().getZ());
//        p.knockBack((Entity)p, 0.0D, x, z, 0.5D);
//    }
    
    @EventHandler
    public void onMovee(final PlayerMoveEvent e) {
        if (e.isCancelled()) {
            return;
        }
        this.userManager.getUser(e.getPlayer()).addDistance(1.0);
        if (this.plugin.getCombatManager().inCombat(e.getPlayer())) {
            final Location to = e.getTo();
            final Location from = e.getFrom();
            if ((from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ()) && BoyUtils.parseLocation(-this.size, this.size, this.size, -this.size, to)) {
//                knockLinePvP(e.getPlayer());
                ChatUtils.sendMessage(e.getPlayer(), "&4Blad: &cNie mozesz wejsc na spawn podczas pvp!");
                e.setCancelled(true);
            }
        }
    }
}
