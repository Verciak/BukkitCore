// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.data.objects.user.BanUser;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.manager.BanManager;
import pl.vertty.core.utils.ChatUtils;

public class PlayerLoginListener implements Listener
{
    private final BanManager banManager;
    
    public PlayerLoginListener(final CorePlugin plugin) {
        this.banManager = plugin.getBanManager();
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void onLogin(final PlayerLoginEvent event) {
        final BanUser ban = this.banManager.getBan(event.getPlayer().getName());
        if (ban != null) {
            if (ban.getExpireTime() != 0L && ban.getExpireTime() < System.currentTimeMillis()) {
                this.banManager.removeBan(ban.getName());
                return;
            }
            event.getPlayer().kickPlayer(ChatUtils.colored(this.banManager.createMessage(ban)));
        }
    }
}
