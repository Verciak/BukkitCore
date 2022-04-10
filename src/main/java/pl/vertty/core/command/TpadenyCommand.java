// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;


import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.CorePlugin;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;

import java.util.List;

public class TpadenyCommand extends Command
{
    private final CorePlugin plugin = CorePlugin.getPlugin();

    public TpadenyCommand() {
        super("tpadeny", "", "/tpadeny", "mhCore", List.of(new String[0]));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length < 1) {
            ChatUtils.sendMessage(commandSender, "/tpadeny");
            return false;
        }
        final Player player = (Player)commandSender;
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cGracz jest offline!");
            return false;
        }
        final UserData u = this.plugin.getUserManager().getUser(o);
        if (u == null) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cGracza nie ma w bazie danych!");
            return false;
        }
        if (args[0].equalsIgnoreCase(commandSender.getName())) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cNie mozesz sie tepnac do siebie!");
            return false;
        }
        if (!u.getTpa().contains(player)) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cGracz nie wyslal ci prosby!");
            return false;
        }
        u.getTpa().remove(player);
        ChatUtils.sendMessage(commandSender, "&6Odrzuciles prosbe o teleport!");
        ChatUtils.sendMessage(o, "&c" + commandSender.getName() + " &6odrzucil prosbe o teleport!");
        return false;
    }
}
