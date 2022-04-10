package pl.vertty.core.command;


import org.bukkit.command.CommandSender;
import pl.vertty.api.commands.Command;

import java.util.List;

public class CobblexCommand extends Command
{
    public CobblexCommand() {
        super("cobblex", "", "/cobblex", "mhCore", List.of(new String[]{"cx"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
//        CobblexMenu.CobblexMenu((Player) commandSender);
        return false;
    }
}
