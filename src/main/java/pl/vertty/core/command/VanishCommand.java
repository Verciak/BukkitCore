// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;


import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.manager.VanishManager;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;

import java.util.List;

public class VanishCommand extends Command
{
    public VanishCommand() {
        super("vanish", "", "/vanish", "mhCore.vanish", List.of(new String[]{"v"}));
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        final Player p = (Player)commandSender;
        VanishManager.toggleVanish(p);
        for(Player player : Bukkit.getOnlinePlayers()){
            if (player.hasPermission("mhCore.vanish")) {
                ChatUtils.sendMessage(player, "&b" + p.getName() + " " + (VanishManager.isVanished(p) ? "wlaczyl" : "wylaczyl") + " vanish!");
            }
            return false;
        }
        ChatUtils.sendMessage(p, "&6Vanish zostal: " + (VanishManager.isVanished(p) ? "&awlaczony" : "&cwylaczony"));
        return false;
    }
}
