package pl.vertty.core.tasks;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import pl.vertty.core.data.objects.Teleport;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.manager.TeleportManager;
import pl.vertty.core.utils.TitleUtils;

public class TeleportTask extends BukkitRunnable
{
    private final TeleportManager teleportManager;
    
    public TeleportTask(final CorePlugin plugin) {
        this.teleportManager = plugin.getTeleportManager();
        this.runTaskTimerAsynchronously((Plugin)plugin, 20, 20);
    }
    
    public void run() {
        for (final Teleport teleport : this.teleportManager.getTeleportMap().values()) {
            if (teleport != null && teleport.getPlayer() != null && teleport.getLongTime() < System.currentTimeMillis()) {
                teleport.getPlayer().teleport(teleport.getLocation());
                this.teleportManager.removeTeleport(teleport.getPlayer());
                TitleUtils.sendActionbar(teleport.getPlayer(), "");
                TitleUtils.sendTitle(teleport.getPlayer(), "", "&aPrzeteleportowano!", 20, 60, 20);
            }
        }
    }
}
