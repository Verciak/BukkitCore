// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;


import org.bukkit.command.CommandSender;
import pl.vertty.core.CorePlugin;
import pl.vertty.api.commands.Command;

import java.util.List;

public class DepositCommand extends Command
{
    public DepositCommand() {
        super("depozyt", "", "/depozyt", "mhCore", List.of(new String[]{"schowek"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
//        DepositMenu.createInventory((Player) commandSender);
        return false;
    }
}
