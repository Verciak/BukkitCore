// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.command.CommandSender;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.manager.ChatManager;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;

import java.util.List;

public class ChatCommand extends Command
{
    private final ChatManager chatManager = CorePlugin.getPlugin().getChatManager();

    public ChatCommand() {
        super("chat", "", "/chat <switch/clear/slowmode> [liczba]", "mhCore.chat", List.of(new String[]{"chatmanage"}));
    }
    
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length < 1) {
            ChatUtils.sendMessage(commandSender, "/chat <switch/clear/slowmode> [liczba]");
            return false;
        }
        if (args[0].equalsIgnoreCase("switch")) {
            this.chatManager.switchChat(commandSender);
            return false;
        }
        if (args[0].equalsIgnoreCase("clear")) {
            this.chatManager.clearChat(commandSender);
            return false;
        }
        ChatUtils.sendMessage(commandSender, "/chat <switch/clear/slowmode> [liczba]");
        return false;
    }
}
