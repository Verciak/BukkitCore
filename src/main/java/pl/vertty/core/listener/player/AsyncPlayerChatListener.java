// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.listener.player;


import java.util.UUID;

import com.google.common.cache.Cache;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.builder.MessageBuilder;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.enums.TimeEnum;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.DataUtils;

public class AsyncPlayerChatListener implements Listener
{
    private final CorePlugin plugin;
    
    public AsyncPlayerChatListener(final CorePlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void onChat(final PlayerChatEvent event) {
        final Player player = event.getPlayer();
        final UserData user = this.plugin.getUserManager().getUser(player);
        if (!player.hasPermission("mhCore.chat.bypass") && !this.plugin.getChatManager().getChatStatue()) {
            ChatUtils.sendMessage(player, "&4Blad: &cChat jest wylaczony!");
            event.setCancelled(true);
        }
        final Cache<UUID, Long> chatCache = this.plugin.getChatManager().getChatCache();
        if (!player.hasPermission("mhCore.chat.slowmode") && chatCache.getIfPresent((Object)player.getUniqueId()) != null && (long)chatCache.getIfPresent((Object)player.getUniqueId()) > System.currentTimeMillis()) {
            ChatUtils.sendMessage(player, new MessageBuilder().setText("&4Blad: &cNastepny raz mozesz napisac za: {TIME}").addField("{TIME}", DataUtils.durationToString((long)chatCache.getIfPresent((Object)player.getUniqueId()))).build());
            event.setCancelled(true);
            return;
        }
        if (player.hasPermission("mhCore.chat.admin")) {
            final String format = new MessageBuilder().setText(this.plugin.getPluginConfiguration().getAdmin_format()).addField("{PLAYER}", "%1$s").addField("{MESSAGE}", "%2$s").build();
            event.setFormat(ChatUtils.colored(format));
            return;
        }
        final String format2 = new MessageBuilder().setText(this.plugin.getPluginConfiguration().getNormal_format()).addField("{PLAYER}", "%1$s").addField("{MESSAGE}", "%2$s").build();
        event.setFormat(ChatUtils.colored(format2));
        this.plugin.getChatManager().getChatCache().put(player.getUniqueId(), (System.currentTimeMillis() + TimeEnum.SECOND.getTime(15)));
    }
}
