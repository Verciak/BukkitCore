// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.IncognitoUtils;
import pl.vertty.core.manager.VanishManager;
import pl.vertty.core.CorePlugin;

public class PlayerJoinListener implements Listener
{
    private final CorePlugin plugin;
    
    public PlayerJoinListener(final CorePlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        event.setJoinMessage("");
        if (!event.getPlayer().hasPlayedBefore()) {
            this.plugin.getProtectionManager().giveProtection(event.getPlayer());
            this.plugin.getKitManager().giveKit(this.plugin.getUserManager().getUser(event.getPlayer()), this.plugin.getKitManager().getKit(new ItemStack(Material.CHAINMAIL_HELMET)));
        }
        for (final Player all : Bukkit.getOnlinePlayers()) {
            if (VanishManager.isVanished(all)) {
                event.getPlayer().hidePlayer(all);
            }
        }
        this.plugin.getUserManager().save(event.getPlayer());
        IncognitoUtils.join();
    }
    
    @EventHandler
    public void onLeave(final PlayerQuitEvent event) {
        event.setQuitMessage("");
        this.leave(event.getPlayer());
    }
    
    @EventHandler
    public void onKick(final PlayerKickEvent event) {
        event.setLeaveMessage("");
        this.leave(event.getPlayer());
    }
    
    private void leave(final Player player) {
        this.plugin.getUserManager().save(player);
        this.plugin.getChestManager().getPlayerSet().remove(player);
        if (this.plugin.getCombatManager().inCombat(player)) {
            player.setHealth(0);
            for (final Player all : Bukkit.getOnlinePlayers()) {
                ChatUtils.sendMessage(all, "&6Gracz: &c" + player.getName() + " &6wylogowal sie podczas walki!");
            }
        }
    }
}
