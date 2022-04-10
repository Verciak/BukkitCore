// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.api.commands.Command;
import pl.vertty.core.menu.ItemShopMenu;

import java.util.List;

public class ItemShopCommand extends Command
{
    public ItemShopCommand() {
        super("itemshop", "", "/itemshop", "mhCore", List.of(new String[]{"is"}));
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        ItemShopMenu.createInventory((Player) commandSender);
        return false;
    }
}
