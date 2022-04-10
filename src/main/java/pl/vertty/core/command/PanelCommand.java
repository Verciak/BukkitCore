// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;


import org.bukkit.command.CommandSender;
import pl.vertty.api.commands.Command;

import java.util.List;

public class PanelCommand extends Command
{

    public PanelCommand() {
        super("gmpanel", "", "/gmpanel", "mhCore.panel", List.of(new String[]{"apanel"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
//        AdminSettingsMenu.createInventory((Player) commandSender);
        return false;
    }
}
