package pl.vertty.core.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.config.PluginConfiguration;
import pl.vertty.core.utils.ChatUtils;

public class AutoMessageTask extends BukkitRunnable
{
    private final PluginConfiguration pluginConfiguration;
    private int index;
    
    public AutoMessageTask(final CorePlugin plugin) {
        this.index = 0;
        this.pluginConfiguration = plugin.getPluginConfiguration();
        this.runTaskTimerAsynchronously((Plugin)plugin, 20L * this.pluginConfiguration.getAutomessage_time(), 20 * this.pluginConfiguration.getAutomessage_time());
    }
    
    public void run() {
        if (this.index == this.pluginConfiguration.getAutomessage_messages().size()) {
            this.index = 0;
        }
        if (this.pluginConfiguration.getAutomessage_messages().isEmpty()) {
            return;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            ChatUtils.sendMessage(p, (String)this.pluginConfiguration.getAutomessage_messages().get(this.index));
            ++this.index;
        }
    }
}
