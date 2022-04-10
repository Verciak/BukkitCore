// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.apache.logging.log4j.core.Core;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.config.SettingsConfiguration;
import pl.vertty.core.manager.TeleportManager;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.LocationUtils;

import java.util.List;

public class SpawnCommand extends Command
{
    private final TeleportManager teleportManager = CorePlugin.getPlugin().getTeleportManager();
    private final SettingsConfiguration settingsConfiguration = CorePlugin.getPlugin().getSettingsConfiguration();

    public SpawnCommand() {
        super("spawn", "", "/spawn", "mhCore", List.of(new String[0]));
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        final Player player = (Player)commandSender;
        teleportManager.createTeleport(player, LocationUtils.locFromString(CorePlugin.getPlugin().getConfig().getString("spawn-location")), 10);
        return false;
    }
}
