// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.manager;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import pl.vertty.core.utils.*;
import pl.vertty.core.data.objects.drops.chest.ChestCase;
import pl.vertty.api.self.Count;
import pl.vertty.core.data.config.PluginConfiguration;
import com.google.common.collect.Sets;
import com.google.common.collect.Lists;
import pl.vertty.core.data.objects.drops.chest.ChestItem;
import java.util.List;
import java.util.Set;

import pl.vertty.core.CorePlugin;

public class ChestManager
{
    public static CorePlugin plugin;
    public static List<ChestItem> chestList;
    public static Set<Player> playerSet;
    public static ItemStack chestItem;
    
    public ChestManager(final CorePlugin plugin) {
        this.plugin = plugin;
        this.chestList = Lists.newLinkedList();
        this.playerSet = Sets.newLinkedHashSet();
        this.chestItem = new ItemBuilder(Material.ENDER_CHEST, 1).setTitle(ChatUtils.colored(PluginConfiguration.getInstance().getMychest_name())).addLores(PluginConfiguration.getInstance().getMychest_lore()).build();
        final Configuration fc = plugin.getConfig();
        for (final String string : fc.getConfigurationSection("mychest").getKeys(false)) {
            final String name = fc.getString("mychest." + string + ".name", "default_name");
            final ItemStack Item = ParserUtils.parseItemStack(fc.getString("mychest." + string + ".item", "Item:DIRT"));
            final Count count = Count.parse(fc.getString("mychest." + string + ".count", "1-1"));
            this.chestList.add(new ChestItem(name, count, Item));
        }
        plugin.getLogger().info("Zaladowano " + this.chestList.size() + " dropow z mychest!");
    }
    
    public void openChest(final Player player, final ChestCase chestCase) {
        if (this.playerSet.contains(player)) {
            return;
        }
        this.playerSet.add(player);
        /*
        player.openInventory(chestCase.getInventory());
         */
        if (!player.getInventory().contains(this.chestItem)) {
            return;
        }
        if (player.getInventory().getItemInHand().getAmount() > 1) {
            player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
        }
        else {
            player.getInventory().setItemInHand(new ItemStack(Material.AIR,1));
        }
        if (!this.playerSet.contains(player)) {
            return;
        }
        new BukkitRunnable() {
            public void run() {
                chestCase.setRepeat(chestCase.getRepeat() + 1);
                final ChestItem next = ChestManager.this.chestList.get(RandomUtils.getRandInt(0, ChestManager.this.chestList.size() - 1));
                for (int i = 9; i < 17; ++i) {

                    chestCase.getInventory().setItem(i, chestCase.getInventory().getItem(i + 1));

                }

//                chestCase.getInventory().setItem(17, next.getItem());

                if (chestCase.getRepeat() >= chestCase.getMaxRepeat()) {

                    final ItemStack item = chestCase.getInventory().getItem(13);
                    ItemUtils.giveItem(player, item);
                    MessageUtils.sendBroadcast(ChestManager.this.plugin.getPluginConfiguration().getMychest_message().replace("{PLAYER}", player.getName()).replace("{NAME}", item.getType().toString()));

                    new BukkitRunnable() {
                        public void run() {

                            player.closeInventory();

                        }
                    }.runTaskLater((Plugin)ChestManager.this.plugin, 5);
                    ChestManager.this.playerSet.remove(player);
                    this.cancel();
                }
            }
        }.runTaskTimerAsynchronously((Plugin)this.plugin, 5, 5);
    }
    
    public static List<ChestItem> getChestList() {
        return chestList;
    }
    
    public Set<Player> getPlayerSet() {
        return this.playerSet;
    }
    
    public ItemStack getChestItem() {
        return this.chestItem;
    }
}
