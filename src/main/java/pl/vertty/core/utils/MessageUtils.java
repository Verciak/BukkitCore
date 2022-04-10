package pl.vertty.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.CorePlugin;

public final class MessageUtils
{
    private MessageUtils() {
    }
    
    public static void sendBroadcast(final String message) {
        for(Player p : Bukkit.getOnlinePlayers()){
            final UserData user = CorePlugin.getPlugin().getUserManager().getUser(p);
            if (user.isChestMessages()) {
                ChatUtils.sendMessage(p, message);
            }
        }
    }
}
