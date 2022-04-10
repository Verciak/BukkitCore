// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.listener.others;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.manager.TeleportManager;
import pl.vertty.core.utils.ChatUtils;

public class PlayerTeleportingListener implements Listener
{
    private final TeleportManager teleportManager;
    
    public PlayerTeleportingListener(final CorePlugin plugin) {
        this.teleportManager = plugin.getTeleportManager();
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onMove(final PlayerMoveEvent event) {
        if (event.getFrom().getX() == event.getTo().getX() && event.getFrom().getY() == event.getTo().getY() && event.getFrom().getZ() == event.getTo().getZ()) {
            return;
        }
        final Player player = event.getPlayer();
        if (this.teleportManager.hasTeleport(player)) {
            this.teleportManager.removeTeleport(player);
            ChatUtils.sendMessage(player, "&4Blad: &cRuszyles sie, teleportacja anulowana!");
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamage(final EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player)event.getEntity();
            if (this.teleportManager.hasTeleport(player)) {
                this.teleportManager.removeTeleport(player);
                ChatUtils.sendMessage(player, "&4Blad: &cRuszyles sie, teleportacja anulowana!");
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onQuit(final PlayerQuitEvent event) {
        if (this.teleportManager.hasTeleport(event.getPlayer())) {
            this.teleportManager.removeTeleport(event.getPlayer());
        }
    }
}
