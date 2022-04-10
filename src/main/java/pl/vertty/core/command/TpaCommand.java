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

public class TpaCommand extends Command
{
    private final CorePlugin plugin = CorePlugin.getPlugin();

    public TpaCommand() {
        super("tpa", "", "/tpa <gracz>", "mhCore", List.of(new String[0]));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        final Player p = (Player)commandSender;
        if (args.length < 1) {
            ChatUtils.sendMessage(commandSender, "/tpa <gracz>");
            return false;
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            ChatUtils.sendMessage(p, "&4Blad: &cGracz jest offline!");
            return false;
        }
        final UserData u = this.plugin.getUserManager().getUser(o);
        if (u == null) {
            ChatUtils.sendMessage(p, "&4Blad: &cGracza nie ma w bazie danych!");
            return false;
        }
        if (args[0].equalsIgnoreCase(commandSender.getName())) {
            ChatUtils.sendMessage(p, "&4Blad: &cNie mozesz tepac sie do siebie!");
            return false;
        }
        if (u.getTpa().contains(p)) {
            ChatUtils.sendMessage(p, "&4Blad: &cTen gracz ma juz zaproszenie do teleportu od Ciebie!");
            return false;
        }
        u.getTpa().add(p);
        ChatUtils.sendMessage(p, "&6Wyslano prosbe o teleportacje do: &c" + o.getName());
        ChatUtils.sendMessage(o, "&c" + p.getName() + " &6chce sie do ciebie przeteleportowac!");
        ChatUtils.sendMessage(o, "&6Wpisz &c/tpaccept &6albo &c/tpadeny");
        return false;
    }
}
