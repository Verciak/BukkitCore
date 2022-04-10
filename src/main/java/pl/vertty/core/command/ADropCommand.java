// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.DataUtils;
import pl.vertty.core.CorePlugin;
import pl.vertty.api.commands.Command;

import java.util.List;

public class ADropCommand extends Command
{


    public ADropCommand() {
        super("adrop", "", "/adrop <drop/exp/mychest> <time>", "mhCore.adrop", List.of(new String[0]));
    }

    private final CorePlugin plugin = CorePlugin.getPlugin();

    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length != 2) {
            ChatUtils.sendMessage(commandSender, "/adrop <drop/exp/mychest> <time>");
            return false;
        }
        final long time = DataUtils.parseDateDiff(args[1], true);
        if (args[0].equalsIgnoreCase("drop")) {
            this.plugin.getSettingsConfiguration().setTurbodrop$time(time);
            Bukkit.getOnlinePlayers().forEach(player -> ChatUtils.sendMessage(player, "&6Administrator: &e" + commandSender.getName() + " &6wlaczyl &bTURBODROP &6na &e" + args[1]));
            return false;
        }
        if (args[0].equalsIgnoreCase("exp")) {
            this.plugin.getSettingsConfiguration().setTurboexp$time(time);
            Bukkit.getOnlinePlayers().forEach(player -> ChatUtils.sendMessage(player, "&6Administrator: &e" + commandSender.getName() + " &6wlaczyl &bTURBOEXP &6na &e" + args[1]));
            return false;
        }
        if (args[0].equalsIgnoreCase("mychest")) {
            this.plugin.getSettingsConfiguration().setMychest$time(time);
            Bukkit.getOnlinePlayers().forEach(player -> ChatUtils.sendMessage(player, "&6Administrator: &e" + commandSender.getName() + " &6wlaczyl &bMYCHEST-DROP &6na &e" + args[1]));
            return false;
        }
        ChatUtils.sendMessage(commandSender, "/adrop <drop/exp/mychest> <time>");
        return false;
    }
}
