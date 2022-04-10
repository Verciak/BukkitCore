// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.manager.ComparatorManager;
import pl.vertty.core.menu.TopMenu;
import pl.vertty.api.commands.Command;

import java.util.List;

public class TopCommand extends Command
{
    public TopCommand() {
        super("top", "", "/top", "mhCore", List.of(new String[]{"topki"}));
    }


    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        ComparatorManager.sortUsers();
        TopMenu.createInventory((Player) commandSender);
        return false;
    }
}
