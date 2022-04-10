// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import com.google.common.cache.Cache;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.builder.MessageBuilder;
import pl.vertty.core.enums.TimeEnum;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.DataUtils;

import java.util.List;
import java.util.UUID;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.manager.PrivateMessageManager;
import pl.vertty.api.commands.Command;


public class ReplyCommand extends Command
{
    private final PrivateMessageManager privateMessageManager = CorePlugin.getPlugin().getPrivateMessageManager();

    public ReplyCommand() {
        super("reply", "", "/reply <wiadomosc>", "mhCore", List.of(new String[]{"r", "odpisz"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length < 1) {
            ChatUtils.sendMessage(commandSender, "/reply <wiadomosc>");
            return false;
        }
        final Player player = (Player)commandSender;
        if (this.privateMessageManager.getLastMessagesMap().get(player.getUniqueId()) == null) {
            ChatUtils.sendMessage(player, "&4Blad: &cNie masz do kogo pisac!");
            return false;
        }
        final Player other = Bukkit.getPlayer(this.privateMessageManager.getLastMessagesMap().get(player.getUniqueId()));
        if (other == null) {
            ChatUtils.sendMessage(player, "&4Blad: &cGracz jest offline!");
            return false;
        }
        final HashMap<UUID, UUID> lastMessagesMap = this.privateMessageManager.getLastMessagesMap();
        final Cache<UUID, Long> lastMessageCache = this.privateMessageManager.getLastMessageCache();
        final String message = StringUtils.join((Object[])args, " ", 0, args.length);
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
