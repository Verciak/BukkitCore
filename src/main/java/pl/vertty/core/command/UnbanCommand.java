// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.objects.user.BanUser;
import pl.vertty.core.data.config.PluginConfiguration;
import pl.vertty.core.manager.BanManager;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;

import java.util.List;

public class UnbanCommand extends Command
{
    private final BanManager banManager = CorePlugin.getPlugin().getBanManager();

    public UnbanCommand() {
        super("unban", "", "/unban <user>", "mhCore.unban", List.of(new String[0]));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length < 1) {
            ChatUtils.sendMessage(commandSender, "/unban <user>");
            return false;
        }
        final BanUser user = this.banManager.getBan(args[0]);
        if (user == null) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cGracz nie posiada bana!");
            return false;
        }
        this.banManager.removeBan(args[0]);
        Bukkit.broadcastMessage(ChatUtils.colored(PluginConfiguration.getInstance().getUnban__message().replace("{PLAYER}", args[0]).replace("{ADMIN}", commandSender.getName())));
        return false;
    }
}
