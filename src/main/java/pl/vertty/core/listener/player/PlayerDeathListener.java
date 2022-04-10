// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.manager.UserManager;
import pl.vertty.core.manager.CombatManager;

public class PlayerDeathListener implements Listener
{
    private final CombatManager combatManager;
    private final UserManager userManager;
    
    public PlayerDeathListener(final CorePlugin plugin) {
        this.userManager = plugin.getUserManager();
        this.combatManager = plugin.getCombatManager();
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void onDeath(final PlayerDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            this.userManager.getUser(event.getEntity().getPlayer()).addDeath(1);
        }
        if (event.getEntity().getKiller() instanceof Player) {
            final Player player = (Player) event.getEntity().getKiller();
            this.userManager.getUser(player).addKill(1);
        }
    }
    
    @EventHandler
    public void onRespawn(final PlayerRespawnEvent event) {
        if (this.combatManager.inCombat(event.getPlayer())) {
            this.combatManager.removeCombat(this.combatManager.getCombat(event.getPlayer()));
        }
    }
}
