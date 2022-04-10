package pl.vertty.core.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;


public final class ChatUtils {
    public static String colored(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> colored(List<String> strings) {
        return (List<String>)strings.stream()
                .map(ChatUtils::colored)
                .collect(Collectors.toList());
    }

    public static boolean sendMessage(Player player, String message) {
        player.sendMessage(colored(message));
        return true;
    }

    public static boolean sendMessage(CommandSender commandSender, String message) {
        commandSender.sendMessage(colored(message));
        return true;
    }
}
