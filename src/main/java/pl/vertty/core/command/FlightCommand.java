// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.TitleUtils;

import java.util.List;

public class FlightCommand extends Command
{
    public FlightCommand() {
        super("flight", "", "/fly [gracz]", "mhCore.fly", List.of(new String[]{"fly"}));
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 0) {
            final Player player = (Player)commandSender;
            player.setAllowFlight(!player.getAllowFlight());
            TitleUtils.sendTitle(player, "", "&6Fly zostalo: " + (player.getAllowFlight() ? "&awlaczone" : "&cwylaczone"), 20, 60, 20);
            return false;
        }
        final Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cGracz jest offline!");
            return false;
        }
        player.setAllowFlight(!player.getAllowFlight());
        ChatUtils.sendMessage(player, "&6Fly dla: &c" + player.getName() + " &6zostalo: " + (player.getAllowFlight() ? "&awlaczone" : "&cwylaczone"));
        TitleUtils.sendTitle(player, "", "&6Fly zostalo: " + (player.getAllowFlight() ? "&awlaczone" : "&cwylaczone"), 20, 60, 20);
        return false;
    }
}
