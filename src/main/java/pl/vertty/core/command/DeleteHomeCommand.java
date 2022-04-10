// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.CorePlugin;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;

import java.util.List;

public class DeleteHomeCommand extends Command
{
    private final CorePlugin plugin = CorePlugin.getPlugin();

    public DeleteHomeCommand() {
        super("deletehome", "", "/deletehome <nazwa>", "mhCore", List.of(new String[]{"delhome", "removehome", "usundom"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length < 1) {
            ChatUtils.sendMessage(commandSender, "/deletehome <nazwa>");
            return false;
        }
        final Player player = (Player)commandSender;
        final UserData user = this.plugin.getUserManager().getUser(player);
        if (user.getHomes().isEmpty()) {
            ChatUtils.sendMessage(player, "&4Blad: &cNie masz zadnego home!");
            return false;
        }
        if (!user.getHomesList().contains(args[0])) {
            ChatUtils.sendMessage(player, "&4Blad: &cNie masz takiego home!");
            return false;
        }
        user.deleteHome(args[0]);
        ChatUtils.sendMessage(player, "&6Poprawnie usunieto home o nazwie: &c" + args[0]);
        return false;
    }
}
