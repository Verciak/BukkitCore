package pl.vertty.core.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import pl.vertty.core.utils.DataUtils;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.manager.ProtectionManager;
import pl.vertty.core.utils.TitleUtils;

public class ProtectionTask extends BukkitRunnable
{
    private final ProtectionManager protectionManager;
    
    public ProtectionTask(final CorePlugin plugin) {
        this.protectionManager = plugin.getProtectionManager();
        this.runTaskTimerAsynchronously((Plugin)plugin, 20, 20);
    }
    
    public void run() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            if (this.protectionManager.getProtectionCache().get(player.getUniqueId()) != null) {
                if (this.protectionManager.getProtectionCache().get(player.getUniqueId()) > System.currentTimeMillis()) {
                    TitleUtils.sendActionbar(player, "&6Ochrona &e(" + DataUtils.durationToString(this.protectionManager.getProtectionCache().get(player.getUniqueId())) + ")");
                }
                else {
                    this.protectionManager.endProtection(player);
                }
            }
        }
    }
}
