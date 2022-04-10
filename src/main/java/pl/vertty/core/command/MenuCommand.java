// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.command.CommandSender;
import pl.vertty.api.commands.Command;

import java.util.List;

public class MenuCommand extends Command
{
    public MenuCommand() {
        super("menu", "", "/menu", "mhCore", List.of(new String[0]));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
//        MainMenu.MainMenu((Player) commandSender);
        return false;
    }
}
