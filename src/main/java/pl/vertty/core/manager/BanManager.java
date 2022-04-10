// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.vertty.core.data.base.Database;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.DataUtils;
import com.google.common.collect.Maps;
import pl.vertty.core.data.objects.user.BanUser;

import java.util.Map;

public class BanManager
{
    private final Map<String, BanUser> bansMap;
    
    public BanManager() {
        this.bansMap = Maps.newLinkedHashMap();
    }
    
    public String createMessage(final BanUser banUser) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((banUser.getExpireTime() == 0L) ? "&6Zostales zablokowany na stale." : ("&6Zostales zablokowany do " + DataUtils.days.format(banUser.getExpireTime()) + " " + DataUtils.time.format(banUser.getExpireTime()) + "."));
        stringBuilder.append("\n ").append("\n ").append("&cPowod &8» &7").append(banUser.getReason()).append("\n ").append("&8Jesli uwazesz, ze ban jest niesluszny skontaktuj sie z administracja:").append("\n &eDiscord &8» &cdc.blazepe.pl").append("\n").append("&aUNBANA MOZESZ KUPIC NA BLAZEPE.PL");
        return stringBuilder.toString();
    }
    
    public void addBan(final String user, final String admin, final String reason, final long expire) {
        final BanUser banUser = new BanUser(user, admin, reason, expire);
        this.bansMap.put(user, banUser);
        Database.getInstance().saveBan(banUser);
        final Player player = Bukkit.getPlayer(user);
        if (player != null) {
            player.kickPlayer(ChatUtils.colored(this.createMessage(banUser)));
        }
    }
    
    public void removeBan(final String user) {
        final BanUser ban = this.getBan(user);
        if (ban != null) {
            Database.getInstance().removeBan(ban);
            this.bansMap.remove(user);
        }
    }
    
    public BanUser getBan(final String bannedUser) {
        for (final BanUser ban : this.bansMap.values()) {
            if (ban.getName().equalsIgnoreCase(bannedUser)) {
                return ban;
            }
        }
        return null;
    }
    
    public Map<String, BanUser> getBansMap() {
        return this.bansMap;
    }
}
