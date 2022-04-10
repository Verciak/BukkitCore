// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;


import org.apache.logging.log4j.core.Core;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.manager.ProtectionManager;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.DataUtils;

import java.util.List;

public class OchronaCommand extends Command
{
    private final ProtectionManager protectionManager = CorePlugin.getPlugin().getProtectionManager();

    public OchronaCommand() {
        super("ochrona", "", "/ochrona", "mhCore", List.of(new String[]{"protection"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        final Player player = (Player)commandSender;
        if (!this.protectionManager.hasProtection(player)) {
            ChatUtils.sendMessage(player, "&4Blad: &cNie masz ochrony!");
            return false;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("wylacz")) {
            this.protectionManager.endProtection(player);
            ChatUtils.sendMessage(player, "&6Wylaczono ochrone!");
            return false;
        }
        ChatUtils.sendMessage(player, "&6Pozostaly czas ochrony: " + DataUtils.durationToString((long)this.protectionManager.getProtectionCache().get(player.getUniqueId())));
        ChatUtils.sendMessage(player, "&e/ochrona wylacz &6- jezeli chcesz wylaczyc ochrone!");
        return false;
    }
}
