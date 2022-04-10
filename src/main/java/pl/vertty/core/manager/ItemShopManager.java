// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.manager;

import java.util.List;

import com.google.common.collect.Maps;
import org.bukkit.configuration.Configuration;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.objects.ItemShop;

import java.util.Map;

public class ItemShopManager
{
    private final Map<String, ItemShop> serviceMap;
    private final Configuration itemShopConfiguration;
    
    public ItemShopManager(final CorePlugin plugin) {
        this.itemShopConfiguration = plugin.getConfig();
        this.serviceMap = Maps.newHashMap();
        this.itemShopConfiguration.getConfigurationSection("services").getKeys(false).forEach(itemshop -> {
            List chatMessages = this.itemShopConfiguration.getStringList("services." + itemshop + ".chatMessages");
            List commands = this.itemShopConfiguration.getStringList("services." + itemshop + ".commands");
            this.serviceMap.put(itemshop, new ItemShop(itemshop, chatMessages, commands));
            return;
        });
        plugin.getLogger().info("Zaladowano " + this.serviceMap.size() + " uslug!");
    }
    
    public ItemShop getItemShop(final String name) {
        return this.serviceMap.values().stream().filter(itemShop -> itemShop.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    
    public Map<String, ItemShop> getServiceMap() {
        return this.serviceMap;
    }
}
