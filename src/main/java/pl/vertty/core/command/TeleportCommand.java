// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;



import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;

import java.util.List;

public class TeleportCommand extends Command
{
    public TeleportCommand() {
        super("teleport", "", "/teleport [kto] <do kogo>  lub  [kto] <x> <y> <z>", "mhCore.teleport", List.of(new String[]{"tp"}));
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length < 1) {
            ChatUtils.sendMessage(commandSender, "/teleport [kto] <do kogo>  lub  [kto] <x> <y> <z>");
            return false;
        }
        final Player player = (Player)commandSender;
        switch (args.length) {
            case 0: {
                ChatUtils.sendMessage(commandSender, "/teleport [kto] <do kogo>  lub  [kto] <x> <y> <z>");
            }
            case 1: {
                final Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    ChatUtils.sendMessage(player, "&4Blad: &cGracz jest offline!");
                    return false;
                }
                player.teleport(o.getLocation());
                ChatUtils.sendMessage(player, "&8\u25b8 &7Przeleportowano do gracza: &a" + o.getName());
            }
            case 2: {
                final Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    ChatUtils.sendMessage(player, "&4Blad: &cGracz jest offline!");
                    return false;
                }
                final Player o2 = Bukkit.getPlayer(args[1]);
                if (o2 == null) {
                    ChatUtils.sendMessage(player, "&4Blad: &cGracz jest offline!");
                    return false;
                }
                o.teleport(o2.getLocation());
                ChatUtils.sendMessage(player, "&8\u25b8 &7Przeleportowano gracza: &a" + o2.getName() + " &7do &a" + o.getName());
            }
            case 3: {
                final double x = Double.parseDouble(args[0]);
                final double y = Double.parseDouble(args[1]);
                final double z = Double.parseDouble(args[2]);
                if (Double.isNaN(x) && Double.isNaN(y) && Double.isNaN(z)) {
                    ChatUtils.sendMessage(player, "&4Blad: &cTo nie kordy!");
                    return false;
                }
                player.teleport(new Location(player.getWorld(),x, y, z));
                ChatUtils.sendMessage(player, "&8\u25b8 &7Przeleportowano na kordy!");
            }
            case 4: {
                final Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    ChatUtils.sendMessage(player, "&4Blad: &cGracz jest offline!");
                    return false;
                }
                final double x2 = Double.parseDouble(args[1]);
                final double y2 = Double.parseDouble(args[2]);
                final double z2 = Double.parseDouble(args[3]);
                if (Double.isNaN(x2) && Double.isNaN(y2) && Double.isNaN(z2)) {
                    ChatUtils.sendMessage(player, "&4Blad: &cTo nie kordy!");
                    return false;
                }
                o.teleport(new Location(player.getWorld(),x2, y2, z2));
                ChatUtils.sendMessage(player, "&8\u25b8 &7Przeleportowano na kordy!");
            }
            default: {
                ChatUtils.sendMessage(commandSender, "/teleport [kto] <do kogo>  lub  [kto] <x> <y> <z>");
            }
        }
        return false;
    }
}
