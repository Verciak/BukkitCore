package pl.vertty.core.listener.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.manager.CombatManager;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.utils.ChatUtils;

public class EntityDamageByEntityListener implements Listener
{
    private final CorePlugin plugin;
    private final CombatManager combatManager;
    
    public EntityDamageByEntityListener(final CorePlugin plugin) {
        this.combatManager = plugin.getCombatManager();
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void onDamager(final EntityDamageByEntityEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        final Player d = getDamager(event);
        if (d == null) {
            return;
        }
        final Player p = (Player)event.getEntity();
        if (p.equals(d)) {
            return;
        }
        if (this.isProtected(p, d)) {
            event.setCancelled(true);
        }
    }
    
    private boolean isProtected(final Player p, final Player d) {
        if (this.plugin.getProtectionManager().hasProtection(p)) {
            ChatUtils.sendMessage(d, "&4Blad: &cTen gracz posiada ochrone!");
            return true;
        }
        if (this.plugin.getProtectionManager().hasProtection(d)) {
            ChatUtils.sendMessage(d, "&4Blad: &cPosiadasz jeszcze ochrone!");
            return true;
        }
        return false;
    }
    
    public static Player getDamager(final EntityDamageByEntityEvent e) {
        final Entity damager = e.getDamager();
        if (damager instanceof Player) {
            return (Player)damager;
        }
        return null;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final Entity entity = event.getEntity();
        final Entity damager = event.getDamager();
        if (!(entity instanceof Player)) {
            return;
        }
        Player attacker = null;
        if (damager instanceof Player) {
            attacker = (Player)damager;
        }
        if (attacker == null) {
            return;
        }
        this.combatManager.createCombat(((Player)entity).getPlayer());
        this.combatManager.createCombat(attacker);
    }
}
