// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.manager.UserManager;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;

import java.util.List;

public class MoneyCommand extends Command
{
    private final UserManager userManager = CorePlugin.getPlugin().getUserManager();

    public MoneyCommand() {
        super("money", "", "/money <gracz> <money>", "mhCore.money", List.of(new String[]{"spendmoney"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length < 2) {
            ChatUtils.sendMessage(commandSender, "/money <gracz> <money>");
            return false;
        }
        final UserData user = this.userManager.getUser(Bukkit.getPlayer(args[0]));
        if (user == null) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cGracza nie ma w bazie danych!");
            return false;
        }
        final double count = Double.parseDouble(args[1]);
        user.addCoins(count);
        ChatUtils.sendMessage(commandSender, "&aGracz &f" + user.getLastName() + " &aotrzymal &f" + count);
        return false;
    }
}
