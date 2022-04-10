package pl.vertty.core.manager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.inventory.ItemStack;
import pl.vertty.core.enums.TimeEnum;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.objects.Kit;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.ItemUtils;

import java.util.List;
import java.util.Map;

public class KitManager {
    public static Map<String, Kit> kitMap = Maps.newHashMap();

    public KitManager(CorePlugin plugin) {
        Configuration fc = plugin.getConfig();
        fc.getConfigurationSection("kits").getKeys(false).forEach(kit -> {
            String name = fc.getString("kits." + kit + ".kitName");
            String perm = fc.getString("kits." + kit + ".perm");
            ItemStack icon = new ItemStack(Material.getMaterial(fc.getString("kits." + kit + ".icon")));
            int deley = fc.getInt("kits." + kit + ".deley");
            int slot = fc.getInt("kits." + kit + ".slot");
            List<ItemStack> items = Lists.newArrayList();
            fc.getStringList("kits." + kit + ".items");
            if (this.kitMap.size() > 5)
                return;
            this.kitMap.put(kit.toLowerCase(), new Kit(kit.toLowerCase(), name, perm, items, icon, slot, deley));
        });
        plugin.getLogger().info("Zaladowano " + this.kitMap.size() + " kitow!");
    }

    public Kit getKit(ItemStack icon) {
        return this.kitMap.values()
                .stream()
                .filter(kit -> (kit.getIcon().getType() == icon.getType()))
                .findFirst()
                .orElse(null);
    }

    public void giveKit(UserData user, Kit kit) {
        user.addKit(kit, System.currentTimeMillis() + TimeEnum.MINUTE.getTime(kit.getDeley()));
        kit.getItems().forEach(itemStack -> user.getPlayer().getInventory().addItem(itemStack));
        ChatUtils.sendMessage(user.getPlayer(), "&aOtrzymano kit: &f" + kit.getName());
    }

    public static Map<String, Kit> getKitMap() {
        return kitMap;
    }
}
