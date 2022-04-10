// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.listener.entity;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.CorePlugin;


public class EntityExplodeListener implements Listener
{
    private final CorePlugin plugin;
    
    public EntityExplodeListener(final CorePlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void onExplode(final EntityExplodeEvent event) {
        if (!this.plugin.getSettingsConfiguration().isTnt()) {
            event.setCancelled(true);
        }
    }
}
