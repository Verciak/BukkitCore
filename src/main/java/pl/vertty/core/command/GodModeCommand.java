// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;


import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.manager.UserManager;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.TitleUtils;

import java.util.List;

public class GodModeCommand extends Command
{
    private final UserManager userManager = CorePlugin.getPlugin().getUserManager();
    
    public GodModeCommand() {
        super("god", "", "/god [gracz]", "mhCore.god", List.of(new String[]{"godmode"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 0) {
            final Player player = (Player)commandSender;
            final UserData user = this.userManager.getUser(player);
            user.setGodMode(!user.isGodMode());
            TitleUtils.sendTitle(player, "", "&6GodMode zostal: " + (user.isGodMode() ? "&awlaczony" : "&cwylaczony"), 20, 60, 20);
            return false;
        }
        final Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cGracz jest offline!");
            return false;
        }
        final UserData user = this.userManager.getUser(player);
        user.setGodMode(!user.isGodMode());
        TitleUtils.sendTitle(player, "", "&6GodMode zostal: " + (user.isGodMode() ? "&awlaczony" : "&cwylaczony"), 20, 60, 20);
        return false;
    }
}
