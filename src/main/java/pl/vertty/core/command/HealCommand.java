// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.TitleUtils;

import java.util.List;

public class HealCommand extends Command
{
    public HealCommand() {
        super("heal", "", "/heal <gracz>", "mhCore.heal", List.of(new String[]{"ulecz"}));
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 0) {
            final Player player = (Player)commandSender;
            this.heal(player);
            TitleUtils.sendTitle(player, "", "&aUleczono", 20, 60, 20);
            return false;
        }
        final Player player = Bukkit.getPlayer(args[0]);
        final Player admin = (Player)commandSender;
        if (player == null) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cGracz jest offline!");
            return false;
        }
        this.heal(player);
        TitleUtils.sendTitle(admin, "", "&aUleczono: " + player.getName(), 20, 60, 20);
        TitleUtils.sendTitle(player, "", "&aUleczono", 20, 60, 20);
        return false;
    }
    
    private void heal(final Player player) {
        player.setHealth(20);
    }
}
