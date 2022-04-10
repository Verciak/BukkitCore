// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.CorePlugin;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;

import java.util.List;

public class TpacceptCommand extends Command
{
    private final CorePlugin plugin = CorePlugin.getPlugin();

    public TpacceptCommand() {
        super("tpaccept", "", "/tpaccept", "mhCore", List.of(new String[0]));
    }


    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        final Player p = (Player)commandSender;
        if (args.length < 1) {
            ChatUtils.sendMessage(commandSender, "/tpaccept");
            return false;
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            ChatUtils.sendMessage(p, "&4Blad: &cPodany gracz jest offline!");
            return false;
        }
        final UserData u = this.plugin.getUserManager().getUser(p);
        if (u == null) {
            ChatUtils.sendMessage(p, "&4Blad: &cGracza nie ma w bazie!");
            return false;
        }
        if (!u.getTpa().contains(o)) {
            ChatUtils.sendMessage(p, "&4Blad: &cNie masz oczekujacej prosby o teleportacje!");
            return false;
        }
        if (u.getTpa().contains(o)) {
            this.plugin.getTeleportManager().createTeleport(o, p.getLocation(), 10);
            u.getTpa().remove(o);
            ChatUtils.sendMessage(p, "&6Zakceptowano!");
            ChatUtils.sendMessage(o, "&c" + p.getName() + " &6zakceptowal prosbe o teleportacje!");
            return false;
        }
        ChatUtils.sendMessage(p, "&4Blad: &cNie masz oczekujacej prosby o teleportacje!");
        return false;
    }
}
