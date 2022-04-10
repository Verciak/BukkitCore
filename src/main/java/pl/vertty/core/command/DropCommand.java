// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;


import org.bukkit.command.CommandSender;
import pl.vertty.api.commands.Command;

import java.util.List;

public class DropCommand extends Command
{
    public DropCommand() {
        super("drop", "", "/drop", "mhCore", List.of(new String[]{"stone"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
//        DropMenu.createInventory((Player) commandSender);
        return false;
    }
}
