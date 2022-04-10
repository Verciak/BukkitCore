// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.objects.user.BanUser;
import pl.vertty.core.data.config.PluginConfiguration;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.DataUtils;

import java.util.List;
import java.util.Optional;
import pl.vertty.core.manager.BanManager;
import pl.vertty.api.commands.Command;

public class BanCommand extends Command
{
    private final BanManager banManager = CorePlugin.getPlugin().getBanManager();

    public BanCommand() {
        super("ban", "", "/ban <user> <time/0> [reason]", "mhCore.ban", List.of(new String[0]));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length < 2) {
            ChatUtils.sendMessage(commandSender, "/ban <user> <time/0> [reason]");
            return false;
        }
        final BanUser ban = this.banManager.getBan(args[0]);
        if (ban != null) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cTen gracz posiada juz bana!");
            return false;
        }
        final Player arg = Bukkit.getPlayer(args[0]);
        if (arg.hasPermission("mhCore.ban.bypass")) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cTego gracza nie mozna zbanowac! &7(mhCore.ban.bypass)");
            return false;
        }
        final long time = DataUtils.parseDateDiff(args[1], true);
        String reason = "Brak";
        if (args.length > 2) {
            reason = StringUtils.join((Object[])args, " ", 2, args.length);
        }
        if (time > System.currentTimeMillis()) {
            this.banManager.addBan(args[0], commandSender.getName(), reason, time);
            Bukkit.broadcastMessage(ChatUtils.colored(PluginConfiguration.getInstance().getBan_temp_message().replace("{TIME}", args[1]).replace("{ADMIN}", commandSender.getName()).replace("{REASON}", reason).replace("{PLAYER}", args[0])));
            return false;
        }
        this.banManager.addBan(args[0], commandSender.getName(), reason, 0L);
        Bukkit.broadcastMessage(ChatUtils.colored(PluginConfiguration.getInstance().getBan_perm_message().replace("{ADMIN}", commandSender.getName()).replace("{REASON}", reason).replace("{PLAYER}", args[0])));
        return false;
    }
}
