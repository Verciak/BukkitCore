// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;
/*
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.Inventory;
import net.pietreg.api.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import pl.vertty.api.commands.interfaces.CommandInfo;
import pl.vertty.api.commands.Command;

@CommandInfo(name = "inventoryopen", perm = "mhCore.io", player = true, usage = "<player> <inv/ender/armor>", aliases = { "io" })
public class InventoryOpenCommand extends Command
{
    public InventoryOpenCommand() {
        super(command.getName(), "", "", command.getAliases());
    }

    @Override
    public void onCommand(final CommandSender commandSender, final String... args) {
        if (args.length < 2) {
            this.sendUsage(commandSender);
            return;
        }
        final Player admin = (Player)commandSender;
        final Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            ChatUtils.sendMessage(admin, "&4Blad: &cGracz jest offline");
            return;
        }
        if (args[1].equalsIgnoreCase("inv")) {
            admin.openInventory((Inventory)player.getInventory());
            return;
        }
        if (args[1].equalsIgnoreCase("ender")) {
            admin.openInventory(player.getEnderChest());
            return;
        }
        if (args[1].equalsIgnoreCase("armor")) {
            final Inventory eq = Bukkit.createInventory((InventoryHolder)null, 9, "Zbroja gracza: " + player.getName());
            eq.setContents(player.getInventory().getArmorContents());
            admin.openInventory(eq);
        }
    }


}

 */
