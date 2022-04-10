// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.manager;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import java.util.UUID;
import com.google.common.cache.Cache;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.utils.ChatUtils;

public class ChatManager
{
    private final Cache<UUID, Long> chatCache;
    private final char[] bigMessage;
    private boolean chatStatue;
    
    public ChatManager() {
        this.chatCache = CacheBuilder.newBuilder().expireAfterWrite(15000L, TimeUnit.SECONDS).build();
        Arrays.fill(this.bigMessage = new char[10000], ' ');
        this.chatStatue = true;
    }
    
    public void clearChat(final CommandSender commandSender) {
        for(Player p : Bukkit.getOnlinePlayers()){
            ChatUtils.sendMessage(p, String.valueOf(this.bigMessage));
            ChatUtils.sendMessage(p, "&6Chat zostal wyczyszczony przez: &c" + commandSender.getName());
        }
    }
    
    public void switchChat(final CommandSender commandSender) {
        this.chatStatue = !this.chatStatue;
        for(Player p : Bukkit.getOnlinePlayers()){
            ChatUtils.sendMessage(p, "&6Chat zostal: " + (this.chatStatue ? "&awlaczony" : "&cwylaczony") + " &6przez: &c" + commandSender.getName());
        }
    }
    
    public boolean getChatStatue() {
        return this.chatStatue;
    }
    
    public Cache<UUID, Long> getChatCache() {
        return this.chatCache;
    }
}
