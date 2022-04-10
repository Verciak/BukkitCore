// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.manager;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import pl.vertty.api.self.Count;
import pl.vertty.core.data.config.PluginConfiguration;
import com.google.common.collect.Lists;
import pl.vertty.core.data.objects.item.CobblexItem;
import java.util.List;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.ItemBuilder;
import pl.vertty.core.utils.ItemUtils;
import pl.vertty.core.utils.ParserUtils;

public class CobblexManager
{
    public static CorePlugin plugin;
    public static List<CobblexItem> cobblexSet;
    public static ItemStack cobblexItem;
    
    public CobblexManager(final CorePlugin plugin) {
        this.plugin = plugin;
        cobblexSet = Lists.newLinkedList();
        cobblexItem = new ItemBuilder(Material.MOSSY_STONE_BRICKS).setTitle(ChatUtils.colored(PluginConfiguration.getInstance().getCx_name())).addLores(ChatUtils.colored(PluginConfiguration.getInstance().getCx_lore())).build();
        final Configuration fc = plugin.getConfig();
        for (final String string : fc.getConfigurationSection("cobblex").getKeys(false)) {
            final String name = fc.getString("cobblex." + string + ".name", "default_name");
            final ItemStack Item = ParserUtils.parseItemStack(fc.getString("cobblex." + string + ".item", "Item:DIRT"));
            final Count count = Count.parse(fc.getString("cobblex." + string + ".count", "1-1"));
            cobblexSet.add(new CobblexItem(name, count, Item));
        }

        final ShapedRecipe recipe = new ShapedRecipe(cobblexItem);
        recipe.shape(new String[] { "CCC", "CCC", "CCC" });
        recipe.setIngredient('C', Material.COBBLESTONE);
        Bukkit.addRecipe(recipe);


        plugin.getLogger().info("Zaladowano " + cobblexSet.size() + " dropow z cobblex!");
    }
    
    public void giveItem(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        final CobblexItem cobblexItem = cobblexSet.get(RandomUtils.nextInt(0, cobblexSet.size()));
        Block block = event.getBlock();
        block.setType(Material.AIR);
        ItemUtils.giveItem(player, cobblexItem.getItemStack());
        ChatUtils.sendMessage(player, StringUtils.replace(plugin.getPluginConfiguration().getCx_message(), "{NAME}", cobblexItem.getName()));
    }
    
    public static List<CobblexItem> getCobblexSet() {
        return cobblexSet;
    }
    
    public ItemStack getCobblexItem() {
        return cobblexItem;
    }
}
