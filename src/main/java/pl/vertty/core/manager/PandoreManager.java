// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.manager;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import pl.vertty.core.utils.*;
import pl.vertty.api.self.Count;
import com.google.common.collect.Lists;
import pl.vertty.core.data.objects.item.PandoreItem;
import java.util.List;
import pl.vertty.core.CorePlugin;

public class PandoreManager
{
    public static CorePlugin plugin;
    public static List<PandoreItem> pandoreList;
    public static ItemStack pandoreItem;
    
    public PandoreManager(final CorePlugin plugin) {
        this.plugin = plugin;
        this.pandoreList = Lists.newLinkedList();
        this.pandoreItem = new ItemBuilder(Material.DRAGON_EGG).setTitle(ChatUtils.colored(plugin.getPluginConfiguration().getPandora_name())).addLores(ChatUtils.colored(plugin.getPluginConfiguration().getPandora_lore())).build();
        plugin.getLogger().info("Ladowanie dropow z pandory...");
        final Configuration fc = CorePlugin.getPlugin().getConfig();
        for (final String string : fc.getConfigurationSection("pandore").getKeys(false)) {
            final String name = fc.getString("pandore." + string + ".name", "default_name");
            final ItemStack itemStack = ParserUtils.parseItemStack(fc.getString("pandore." + string + ".item", "material:DIRT"));
            final Count count = Count.parse(fc.getString("pandore." + string + ".count", "1-1"));
            this.pandoreList.add(new PandoreItem(name, count, itemStack));
        }
        plugin.getLogger().info("Zaladowano " + pandoreList.size() + " dropow z pandory!");
    }
    
    public void giveItem(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        final int itemCount = RandomUtils.getRandInt(1, 3);
        event.setCancelled(true);
        if (itemCount == 1) {
            final PandoreItem pandoreItem = pandoreList.get(RandomUtils.getRandInt(0, pandoreList.size() - 1));
            ItemUtils.giveItem(player, pandoreItem.getItemStack());
            MessageUtils.sendBroadcast(this.plugin.getPluginConfiguration().getPandora_message().replace("{PLAYER}", player.getName()).replace("{NAME}", pandoreItem.getName()));
        }
        else if (itemCount == 2) {
            final PandoreItem pandoreItem = pandoreList.get(RandomUtils.getRandInt(0, pandoreList.size() - 1));
            final PandoreItem pandoreItem2 = pandoreList.get(RandomUtils.getRandInt(0, pandoreList.size() - 1));
            ItemUtils.giveItem(player, pandoreItem.getItemStack(), pandoreItem2.getItemStack());
            MessageUtils.sendBroadcast(this.plugin.getPluginConfiguration().getPandora_message().replace("{PLAYER}", player.getName()).replace("{NAME}", pandoreItem.getName() + ", " + pandoreItem2.getName()));
        }
        else if (itemCount == 3) {
            final PandoreItem pandoreItem = pandoreList.get(RandomUtils.getRandInt(0, pandoreList.size() - 1));
            final PandoreItem pandoreItem2 = pandoreList.get(RandomUtils.getRandInt(0, pandoreList.size() - 1));
            final PandoreItem pandoreItem3 = pandoreList.get(RandomUtils.getRandInt(0, pandoreList.size() - 1));
            ItemUtils.giveItem(player, pandoreItem.getItemStack(), pandoreItem2.getItemStack(), pandoreItem3.getItemStack());
            MessageUtils.sendBroadcast(this.plugin.getPluginConfiguration().getPandora_message().replace("{PLAYER}", player.getName()).replace("{NAME}", pandoreItem.getName() + ", " + pandoreItem2.getName() + ", " + pandoreItem3.getName()));
        }
    }
    
    public static List<PandoreItem> getPandoreList() {
        return pandoreList;
    }
    
    public ItemStack getPandoreItem() {
        return this.pandoreItem;
    }
}
