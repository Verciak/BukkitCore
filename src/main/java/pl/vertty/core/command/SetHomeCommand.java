// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import java.util.List;
import java.util.Map;


import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.CorePlugin;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;

public class SetHomeCommand extends Command
{
    private final CorePlugin plugin = CorePlugin.getPlugin();

    public SetHomeCommand() {
        super("sethome", "", "/sethome <nazwa>", "mhCore", List.of(new String[]{"ustawdom"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        final Player player = (Player)commandSender;
        final UserData user = this.plugin.getUserManager().getUser(player);
        if (args.length <= 0) {
            if (user.getHomes().size() > this.getMaxHomes(player)) {
                ChatUtils.sendMessage(player, "&4Blad: &cOsiagnales limit home!");
                return false;
            }
            user.addHome("home", player.getLocation());
            ChatUtils.sendMessage(player, "&aUtworzono home!");
        }
        else {
            if (user.getHomes().size() > this.getMaxHomes(player)) {
                ChatUtils.sendMessage(player, "&4Blad: &cOsiagnales limit home!");
                return false;
            }
            if (user.getHomesList().contains(args[0])) {
                ChatUtils.sendMessage(player, "&4Blad: &cPosiadasz juz taki home!");
                return false;
            }
            user.addHome(args[0], player.getLocation());
            ChatUtils.sendMessage(player, "&aUtworzono home o nazwie: &f" + args[0] + "&a!");
        }
        return false;
    }
    
    public int getMaxHomes(final Player player) {
        int i = this.plugin.getPluginConfiguration().getHome_default();
        for (final Map.Entry<String, Integer> e : this.plugin.getPluginConfiguration().getHomes().entrySet()) {
            if (player.hasPermission("mhCore.home." + e.getKey()) && i < e.getValue()) {
                i = e.getValue();
            }
        }
        return i;
    }
}
