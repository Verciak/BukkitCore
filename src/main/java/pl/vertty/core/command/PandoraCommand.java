// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;



import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.manager.PandoreManager;
import pl.vertty.core.menu.PandoreMenu;
import pl.vertty.api.commands.Command;

import java.util.List;

public class PandoraCommand extends Command
{

    public PandoraCommand() {
        super("pandora", "", "/pandora", "mhCore", List.of(new String[]{"pandorka"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        PandoreMenu.PandoreMenu((Player) commandSender);
        return false;
    }
}
