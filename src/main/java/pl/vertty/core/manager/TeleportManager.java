package pl.vertty.core.manager;


import com.google.common.collect.Maps;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.vertty.core.data.objects.Teleport;
import pl.vertty.core.utils.ChatUtils;

import java.util.UUID;
import java.util.Map;

public class TeleportManager
{
    private final Map<UUID, Teleport> teleportMap;
    
    public TeleportManager() {
        this.teleportMap = Maps.newHashMap();
    }
    
    public void createTeleport(final Player player, final Location location, final int secounds) {
        if (player.hasPermission("mhCore.teleport.bypass")) {
            player.teleport(location);
            return;
        }
        this.teleportMap.put(player.getUniqueId(), new Teleport(player, location, secounds));
        ChatUtils.sendMessage(player, "&6Teleportacja za: &6" + secounds + "sek");
    }
    
    public Teleport getTeleport(final Player p) {
        return this.teleportMap.values().stream().filter(tp -> tp.getPlayer().getUniqueId().equals(p.getUniqueId())).findFirst().orElse(null);
    }
    
    public void removeTeleport(final Player p) {
        if (this.teleportMap.get(p.getUniqueId()) != null) {
            this.teleportMap.remove(p.getUniqueId());
        }
    }
    
    public boolean hasTeleport(final Player player) {
        return this.teleportMap.containsKey(player.getUniqueId());
    }
    
    public boolean inTeleport(final Player p) {
        final Teleport t = this.getTeleport(p);
        if (t == null) {
            return false;
        }
        if (t.getTime() > System.currentTimeMillis()) {
            return true;
        }
        this.removeTeleport(p);
        return false;
    }
    
    public Map<UUID, Teleport> getTeleportMap() {
        return this.teleportMap;
    }
}
