package pl.vertty.core.tasks;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import pl.vertty.core.manager.ComparatorManager;
import pl.vertty.core.CorePlugin;

public class TopTask extends BukkitRunnable
{
    public TopTask(final CorePlugin plugin) {
        this.runTaskTimerAsynchronously((Plugin)plugin, 6000, 1200);
    }
    
    public void run() {
        ComparatorManager.sortUsers();
    }
}
