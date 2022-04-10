// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.listener.player;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.data.objects.user.User;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.ItemUtils;
import pl.vertty.core.utils.LocationUtils;
import pl.vertty.core.CorePlugin;

public class PlayerInteractListener implements Listener
{
    private final CorePlugin plugin;
    
    public PlayerInteractListener(final CorePlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getClickedBlock();
//        if (event.getItem() != null && event.getPlayer().getInventory().getItemInHand() == this.plugin.getItemManager().getAntiNogi()) {
//            final User user = User.get(player);
//            ItemUtils.removeItem(player, this.plugin.getItemManager().getAntiNogi());
//            final User userGuild;
//            final Player player2;
//            final User user2;
//            player.getWorld().getPlayers().forEach(playerWorld -> {
//                userGuild = User.get(playerWorld);
//                if (playerWorld != player2 && player2.getLocation().distance(playerWorld.getLocation()) < 10.0 && user2.getGuild() != userGuild.getGuild()) {
//                    player2.teleport(playerWorld.getLocation());
//                }
//                return;
//            });
//        }
        if (block == null) {
            return;
        }
        if (this.plugin.getCombatManager().inCombat(event.getPlayer()) && (block.getType() == Material.ENDER_CHEST || block.getType() == Material.CHEST)) {
            event.setCancelled(true);
            ChatUtils.sendMessage(event.getPlayer(), "&4Blad: &cNie mozesz tego otworzyc podczas pvp!");
        }
        if (block.getType() == Material.OAK_BUTTON && this.plugin.getButtonManager().isSoloButton(block.getLocation())) {
            player.teleport(LocationUtils.getRandomCords(player));
            ChatUtils.sendMessage(player, "&aPrzeteleportowano w losowe kordynaty.");
        }
        else if (block.getType() == Material.STONE_BUTTON && this.plugin.getButtonManager().isGroupButton(block.getLocation())) {
            if (!player.getLocation().getBlock().getType().toString().contains("PLATE")) {
                return;
            }
            final Location location = LocationUtils.getRandomCords(player);
            player.teleport(location);
            LocationUtils.findPlayers(block.getLocation(), 5).forEach(players -> {
                players.teleport(location);
                ChatUtils.sendMessage(players, "&aPrzeteleportowano w losowe kordynaty.");
            });
        }
    }


}
