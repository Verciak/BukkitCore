package pl.vertty.core.manager;

import org.bukkit.entity.Player;
import pl.vertty.core.enums.TimeEnum;
import com.google.common.collect.Maps;
import pl.vertty.core.utils.TitleUtils;

import java.util.UUID;
import java.util.Map;

public class ProtectionManager
{
    private final Map<UUID, Long> protectionCache;
    
    public ProtectionManager() {
        this.protectionCache = Maps.newLinkedHashMap();
    }
    
    public void giveProtection(final Player player) {
        this.protectionCache.put(player.getUniqueId(), System.currentTimeMillis() + TimeEnum.MINUTE.getTime(3));
    }
    
    public boolean hasProtection(final Player player) {
        return this.protectionCache.get(player.getUniqueId()) != null && this.protectionCache.get(player.getUniqueId()) > System.currentTimeMillis();
    }
    
    public void endProtection(final Player player) {
        this.protectionCache.remove(player.getUniqueId());
        TitleUtils.sendTitle(player, "&6Ochrona", "&eTwoja ochrona dobiegla konca!", 20, 60, 20);
    }
    
    public Map<UUID, Long> getProtectionCache() {
        return this.protectionCache;
    }
}
