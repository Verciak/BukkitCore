// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.TitleUtils;

import java.util.List;

public class BroadCastCommand extends Command
{
    
    public BroadCastCommand() {
        super("broadcast", "", "/broadcast <chat/actionbar/title> <wiadomosc>", "mhCore.broadcast", List.of(new String[]{"bc"}));
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length < 2) {
            ChatUtils.sendMessage(commandSender, "/broadcast <chat/actionbar/title> <wiadomosc>");
            return false;
        }
        final String message = StringUtils.join((Object[])args, " ", 1, args.length);
        if (args[0].equalsIgnoreCase("chat")) {
            Bukkit.getOnlinePlayers().forEach(player -> ChatUtils.sendMessage(player, message));
            return false;
        }
        if (args[0].equalsIgnoreCase("actionbar")) {
            Bukkit.getOnlinePlayers().forEach(player -> TitleUtils.sendActionbar(player, message));
            return false;
        }
        if (args[0].equalsIgnoreCase("title")) {
            Bukkit.getOnlinePlayers().forEach(player -> TitleUtils.sendTitle(player, "", message, 20, 60, 20));
            return false;
        }
        ChatUtils.sendMessage(commandSender, "/broadcast <chat/actionbar/title> <wiadomosc>");
        return false;
    }
}
