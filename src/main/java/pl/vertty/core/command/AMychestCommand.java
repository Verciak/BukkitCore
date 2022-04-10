// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.vertty.core.CorePlugin;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.IntegerUtils;
import pl.vertty.core.utils.ItemUtils;

import java.util.List;

public class AMychestCommand extends Command
{
    private final CorePlugin plugin = CorePlugin.getPlugin();

    public AMychestCommand() {
        super("amychest", "", "/amychest <gracz/all> <ilosc>", "mhCore.amychest", List.of(new String[]{"achest"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length != 2) {
            ChatUtils.sendMessage(commandSender, "/amychest <gracz/all> <ilosc>");
            return false;
        }
        if (!IntegerUtils.isInt(args[1])) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cArgument nie jest liczba!");
            return false;
        }
        final ItemStack itemStack = this.plugin.getChestManager().getChestItem();
        itemStack.setAmount(Integer.parseInt(args[1]));
        if (args[0].equalsIgnoreCase("all")) {
            Bukkit.getOnlinePlayers().forEach(player -> ItemUtils.giveItem(player, itemStack));
            ChatUtils.sendMessage(commandSender, "&6Rozdano mychest dla wszystkich.");
            return false;
        }
        final Player player2 = Bukkit.getPlayer(args[0]);
        if (player2 == null) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cGracz jest offline!");
            return false;
        }
        ItemUtils.giveItem(player2, itemStack);
        ChatUtils.sendMessage(commandSender, "&6" + player2.getName() + " otrzymal mychest w ilosci: " + itemStack.getAmount());
        return false;
    }
}
