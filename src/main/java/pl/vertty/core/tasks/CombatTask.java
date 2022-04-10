package pl.vertty.core.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import pl.vertty.core.data.objects.user.Combat;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.manager.CombatManager;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.TitleUtils;

public class CombatTask extends BukkitRunnable
{
    private final CombatManager combatManager;
    
    public CombatTask(final CorePlugin plugin) {
        this.combatManager = plugin.getCombatManager();
        this.runTaskTimerAsynchronously((Plugin)plugin, 20, 20);
    }
    
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            final Combat combat = this.combatManager.getCombat(player);
            if (combat != null) {
                TitleUtils.sendActionbar(combat.getPlayer(), "&c&lAntyLogout&8: &e" + combat.getLeftTime() + "s");
                combat.setLeftTime(combat.getLeftTime() - 1);
                if (combat.getLeftTime() <= 0) {
                    ChatUtils.sendMessage(combat.getPlayer(), "&aSkanczyles walczyc!");
                    this.combatManager.removeCombat(combat);
                }
            }
        }
    }
}
