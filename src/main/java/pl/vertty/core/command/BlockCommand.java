// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;


import org.bukkit.command.CommandSender;
import pl.vertty.api.commands.Command;

import java.util.List;

public class BlockCommand extends Command
{

    public BlockCommand() {
        super("bloki", "", "/bloki", "mhCore", List.of(new String[]{"wymien"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
//        BlockChangeMenu.BlockChangeMenu((Player) commandSender);
        return false;
    }
}
