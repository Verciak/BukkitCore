// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.builder.MessageBuilder;
import pl.vertty.core.enums.TimeEnum;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.DataUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import java.util.UUID;
import com.google.common.cache.Cache;
import pl.vertty.api.commands.Command;

public class HelpopCommand extends Command
{
    private final Cache<UUID, Long> helpopCache = CacheBuilder.newBuilder().expireAfterWrite(64000L, TimeUnit.SECONDS).build();

    public HelpopCommand() {
        super("helpop", "", "/helpop <wiadomosc>", "mhCore", List.of(new String[0]));
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length < 1) {
            ChatUtils.sendMessage(commandSender, "/helpop <wiadomosc>");
            return false;
        }
        final Player player = (Player)commandSender;
        if (!player.hasPermission("tools.helpop.view") && this.helpopCache.getIfPresent((Object)player.getUniqueId()) != null && (long)this.helpopCache.getIfPresent((Object)player.getUniqueId()) > System.currentTimeMillis()) {
            ChatUtils.sendMessage(player, new MessageBuilder().setText("&4Blad: &cNastepny raz na helpop mozesz napisac za: {TIME}").addField("{TIME}", DataUtils.durationToString((long)this.helpopCache.getIfPresent((Object)player.getUniqueId()))).build());
            return false;
        }
        final String message = ChatColor.stripColor(ChatUtils.colored(StringUtils.join((Object[])args, " ")));

        Bukkit.getOnlinePlayers().forEach(all -> {
            if (all.hasPermission("tools.helpop.view")) {
                all.sendMessage(ChatUtils.colored(new MessageBuilder().setText("&C[HELPOP] &6{PLAYER} &8- &6{MESSAGE}").addField("{PLAYER}", player.getName()).addField("{MESSAGE}", message).build()));
            }
            return;
        });
        this.helpopCache.put(player.getUniqueId(), (System.currentTimeMillis() + TimeEnum.SECOND.getTime(15)));
        ChatUtils.sendMessage(player, "&aWiadomosc zostala wyslana!");
        return false;
    }
}
