// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;


import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.api.commands.Command;
import pl.vertty.core.menu.KitMenu;

import java.util.List;

public class KitCommand extends Command
{
    public KitCommand() {
        super("kit", "", "/kit", "mhCore", List.of(new String[0]));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        KitMenu.KitMenu((Player) commandSender);
        return false;
    }
}
