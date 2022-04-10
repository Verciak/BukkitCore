// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.manager;

import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import com.google.common.cache.Cache;
import java.util.UUID;
import java.util.HashMap;

public class PrivateMessageManager
{
    private HashMap<UUID, UUID> lastMessagesMap;
    private Cache<UUID, Long> lastMessageCache;
    
    public PrivateMessageManager() {
        this.lastMessagesMap = Maps.newHashMap();
        this.lastMessageCache = CacheBuilder.newBuilder().expireAfterWrite(20000L, TimeUnit.SECONDS).build();
    }
    
    public void setLastMessagesMap(final HashMap<UUID, UUID> lastMessagesMap) {
        this.lastMessagesMap = lastMessagesMap;
    }
    
    public void setLastMessageCache(final Cache<UUID, Long> lastMessageCache) {
        this.lastMessageCache = lastMessageCache;
    }
    
    public HashMap<UUID, UUID> getLastMessagesMap() {
        return this.lastMessagesMap;
    }
    
    public Cache<UUID, Long> getLastMessageCache() {
        return this.lastMessageCache;
    }
}
