// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.api.commands.Command;
import pl.vertty.core.manager.ComparatorManager;
import pl.vertty.core.menu.TopMenu;

import java.util.List;

public class TestCommand extends Command
{
    public TestCommand() {
        super("test", "", "/test", "mhCore", List.of(new String[]{"test"}));
    }


    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        Player p = (Player) commandSender;
        commandSender.sendMessage(p.getInventory().getItemInMainHand().getType().toString());

        return false;
    }
}
