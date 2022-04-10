// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.manager.UserManager;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.IntegerUtils;
import pl.vertty.core.utils.TitleUtils;

import java.util.List;

public class AitemshopCommand extends Command
{
    private final UserManager userManager = CorePlugin.getPlugin().getUserManager();

    public AitemshopCommand() {
        super("aitemshop", "", "aitemshop <gracz> <pandora/mychest> <ilosc>", "mhCore.aitemshop", List.of(new String[]{"giveis"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length < 3) {
            ChatUtils.sendMessage(commandSender, "/aitemshop <gracz> <pandora/mychest> <ilosc>");
            return false;
        }
        final Player player = Bukkit.getPlayer(args[0]);
        final UserData user = this.userManager.getUser(player);
        if (user == null) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cGracza nie ma w bazie danych!");
            return false;
        }
        if (!IntegerUtils.isInt(args[2])) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cArgument nie jest liczba!");
            return false;
        }
        final int count = Integer.parseInt(args[2]);
        if (args[1].equalsIgnoreCase("pandora")) {
            user.addPandora(count);
            ChatUtils.sendMessage(commandSender, "&fGracz: " + player.getName() + " otrzymal " + count + " pandor");
            if (player != null) {
                TitleUtils.sendTitle(player, "&fOdbierz itemy pod:", "&6/is", 30, 60, 30);
            }
            return false;
        }
        if (args[1].equalsIgnoreCase("mychest")) {
            user.addMychest(count);
            ChatUtils.sendMessage(commandSender, "&fGracz: " + player.getName() + " otrzymal " + count + " mychest");
            if (player != null) {
                TitleUtils.sendTitle(player, "&fOdbierz itemy pod:", "&6/is", 30, 60, 30);
            }
            return false;
        }
        ChatUtils.sendMessage(commandSender, "/aitemshop <gracz> <pandora/mychest> <ilosc>");
        return false;
    }
}
