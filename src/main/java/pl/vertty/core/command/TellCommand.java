// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import com.google.common.cache.Cache;

import java.util.List;
import java.util.UUID;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.builder.MessageBuilder;
import pl.vertty.core.enums.TimeEnum;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.DataUtils;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.manager.UserManager;
import pl.vertty.core.manager.PrivateMessageManager;
import pl.vertty.api.commands.Command;


public class TellCommand extends Command
{
    private final PrivateMessageManager privateMessageManager = CorePlugin.getPlugin().getPrivateMessageManager();
    private final UserManager userManager = CorePlugin.getPlugin().getUserManager();

    public TellCommand() {
        super("tell", "", "/tell <gracz> <wiadomosc>", "mhCore", List.of(new String[]{"msg", "message"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length < 2) {
            ChatUtils.sendMessage(commandSender, "/msg <gracz> <wiadomosc>");
            return false;
        }
        final Player player = (Player)commandSender;
        final Player other = Bukkit.getPlayer(args[0]);
        if (other == null) {
            ChatUtils.sendMessage(player, "&4Blad: &cGracz jest offline");
            return false;
        }
        final HashMap<UUID, UUID> lastMessagesMap = this.privateMessageManager.getLastMessagesMap();
        final Cache<UUID, Long> lastMessageCache = this.privateMessageManager.getLastMessageCache();
        final String message = StringUtils.join((Object[])args, " ", 1, args.length);
        if (lastMessageCache.getIfPresent((Object)player.getUniqueId()) != null && (long)lastMessageCache.getIfPresent((Object)player.getUniqueId()) > System.currentTimeMillis()) {
            ChatUtils.sendMessage(player, new MessageBuilder().setText("&4Blad: &cNastepny raz na msg mozesz napisac za: {TIME}").addField("{TIME}", DataUtils.durationToString((long)lastMessageCache.getIfPresent((Object)player.getUniqueId()))).build());
            return false;
        }

        player.sendMessage(ChatUtils.colored(new MessageBuilder().setText("&b[&3{PLAYER} -> Ja&b] &3{MESSAGE}").addField("{PLAYER}", other.getName()).addField("{MESSAGE}", message).build()));
        other.sendMessage(ChatUtils.colored(new MessageBuilder().setText("&b[&3Ja -> {PLAYER}&b] &3{MESSAGE}").addField("{PLAYER}", player.getName()).addField("{MESSAGE}", message).build()));
        lastMessagesMap.remove(player.getUniqueId());
        lastMessagesMap.remove(other.getUniqueId());
        lastMessagesMap.put(player.getUniqueId(), other.getUniqueId());
        lastMessagesMap.put(other.getUniqueId(), player.getUniqueId());
        lastMessageCache.put(player.getUniqueId(), (System.currentTimeMillis() + TimeEnum.SECOND.getTime(15)));
        this.privateMessageManager.setLastMessagesMap(lastMessagesMap);
        this.privateMessageManager.setLastMessageCache(lastMessageCache);
        return false;
    }
}
