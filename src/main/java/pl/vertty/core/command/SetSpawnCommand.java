// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;


import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.CorePlugin;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.LocationUtils;

import java.util.List;

public class SetSpawnCommand extends Command
{
    private final CorePlugin plugin = CorePlugin.getPlugin();

    public SetSpawnCommand() {
        super("setspawn", "", "/setspawn", "mhCore.setspawn", List.of(new String[]{"ustawspawn"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        final Player player = (Player)commandSender;
        Bukkit.getWorld("world").setSpawnLocation((int) player.getLocation().getX(), (int) player.getLocation().getY(), (int) player.getLocation().getZ());
        plugin.getConfig().set("spawn-location", LocationUtils.locToString(player.getLocation()));
        ChatUtils.sendMessage(player, "&aSpawn zostal ustawiony!");
        return false;
    }
}
