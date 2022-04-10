// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.listener.others;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.CorePlugin;

public class CraftItemListener implements Listener
{
    private final CorePlugin plugin;
    
    public CraftItemListener(final CorePlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
}
