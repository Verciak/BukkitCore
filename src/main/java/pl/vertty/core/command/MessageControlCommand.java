// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;



import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.CorePlugin;
import pl.vertty.api.commands.Command;
import pl.vertty.core.menu.MessageControlMenu;

import java.util.List;

public class MessageControlCommand extends Command
{
    public MessageControlCommand() {
        super("messagecontrol", "", "/messagecontrol", "mhCore", List.of(new String[]{"wiadomosci"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        final Player player = (Player)commandSender;
        MessageControlMenu.MessageControlMenu(player);
        return false;
    }
}
