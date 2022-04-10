// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.vertty.core.builder.MessageBuilder;
import pl.vertty.core.CorePlugin;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.IntegerUtils;
import pl.vertty.core.utils.ItemUtils;
import pl.vertty.core.utils.TitleUtils;

import java.util.List;

public class ApandoreCommand extends Command
{
    private final CorePlugin plugin = CorePlugin.getPlugin();

    public ApandoreCommand() {
        super("apandora", "", "/amychest <gracz/all> <ilosc>", "mhCore.apandora", List.of(new String[]{"apandore"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length != 2) {
            ChatUtils.sendMessage(commandSender, "/apandora <gracz/all> <ilosc>");
            return false;
        }
        if (!IntegerUtils.isInt(args[1])) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cArgument nie jest liczba!");
            return false;
        }
        final ItemStack itemStack = this.plugin.getPandoreManager().getPandoreItem();
        itemStack.setAmount(Integer.parseInt(args[1]));
        if (args[0].equalsIgnoreCase("all")) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                ItemUtils.giveItem(player, itemStack);
                TitleUtils.sendTitle(player, "", new MessageBuilder().setText(this.plugin.getPluginConfiguration().getPandora_all()).addField("{AMOUNT}", Integer.toString(itemStack.getAmount())).build(), 20, 60, 20);
            });
            return false;
        }
        final Player player2 = Bukkit.getPlayer(args[0]);
        if (player2 == null) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cGracz jest offline!");
            return false;
        }
        ItemUtils.giveItem(player2, itemStack);
        ChatUtils.sendMessage(player2, new MessageBuilder().setText(this.plugin.getPluginConfiguration().getPandora_get_message()).addField("{AMOUNT}", Integer.toString(itemStack.getAmount())).build());
    return false;
    }
}
