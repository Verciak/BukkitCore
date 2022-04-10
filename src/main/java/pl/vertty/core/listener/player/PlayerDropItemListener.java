// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.CorePlugin;


public class PlayerDropItemListener implements Listener
{
    private final CorePlugin plugin;
    
    public PlayerDropItemListener(final CorePlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void onDrop(final PlayerDropItemEvent event) {
        if (this.plugin.getChestManager().getPlayerSet().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
