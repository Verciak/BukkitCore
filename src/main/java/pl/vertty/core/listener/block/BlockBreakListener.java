package pl.vertty.core.listener.block;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import pl.vertty.core.manager.DropManager;
import pl.vertty.core.manager.UserManager;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.utils.ItemUtils;

public class BlockBreakListener implements Listener
{
    private final CorePlugin plugin;
    private final UserManager userManager;
    private final DropManager dropManager;
    
    public BlockBreakListener(final CorePlugin plugin) {
        this.plugin = plugin;
        this.dropManager = plugin.getDropManager();
        this.userManager = plugin.getUserManager();
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBreak(final BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (event.getBlock().getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType() == this.plugin.getItemManager().getGenerator().getType()) {
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.AIR);
                }
            }.runTaskLater((Plugin)this.plugin, 25);
        }
        if (event.getBlock().getType() == Material.END_STONE) {
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
            ItemUtils.giveItem(event.getPlayer(), this.plugin.getItemManager().getGenerator());
        }
        this.dropManager.breakBlock(event);
    }
}
