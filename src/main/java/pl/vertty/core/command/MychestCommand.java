// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;


import org.bukkit.command.CommandSender;
import pl.vertty.core.data.objects.drops.chest.ChestCase;
import pl.vertty.api.commands.Command;

import java.util.List;

public class MychestCommand extends Command
{
    public MychestCommand() {
        super("mychest", "", "/mychest", "mhCore", List.of(new String[]{"chest"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
//        CaseMenu.CaseMenu((Player) commandSender);
        return false;
    }
}
