package pl.vertty.core.utils;


import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public final class TitleUtils {
    public static boolean sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(ChatUtils.colored(title), ChatUtils.colored(subtitle), fadeIn, stay, fadeOut);
        return true;
    }

    public static boolean sendActionbar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtils.colored(message)));
        return true;
    }
}
