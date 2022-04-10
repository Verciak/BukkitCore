// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.apache.logging.log4j.core.Core;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.CorePlugin;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;

import java.util.List;

public class HomeCommand extends Command
{
    private final CorePlugin plugin = CorePlugin.getPlugin();

    public HomeCommand() {
        super("home", "", "/home <nazwa>", "mhCore", List.of(new String[0]));
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        final Player player = (Player)commandSender;
        final UserData user = this.plugin.getUserManager().getUser(player);
        if (args.length <= 0) {
            if (user.getHomes().isEmpty()) {
                ChatUtils.sendMessage(player, "&4Blad: &cNie masz zadnego home!");
                return false;
            }
            if (user.getHomes().size() > 1) {
                ChatUtils.sendMessage(player, "&6Dostepne home: &c" + String.join("&6, &c", user.getHomesList()));
                return false;
            }
            this.plugin.getTeleportManager().createTeleport(player, user.getHome(user.getHomesList().get(0)), 10);
        }
        else {
            if (user.getHomes().isEmpty()) {
                ChatUtils.sendMessage(player, "&4Blad: &cNie masz zadnego home!");
                return false;
            }
            final Location location = user.getHome(args[0]);
            if (location == null) {
                ChatUtils.sendMessage(player, "&4Blad: &cNie ma takiego home!");
                return false;
            }
            this.plugin.getTeleportManager().createTeleport(player, location, 10);
        }
        return false;
    }
}
