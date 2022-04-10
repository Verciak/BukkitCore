package pl.vertty.core.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.enums.GlassType;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.ItemBuilder;

public class ItemShopMenu {
    public static CorePlugin plugin = CorePlugin.getPlugin();

    public static Inventory inv = Bukkit.createInventory(null, 27, ChatUtils.colored("&9ItemShop"));

    public static void createInventory(Player player) {
        UserData user = plugin.getUserManager().getUser(player);
        ItemBuilder pandora = new ItemBuilder(Material.DRAGON_EGG).setTitle(ChatUtils.colored(plugin.getPluginConfiguration().getPandora_name())).addLore(ChatUtils.colored("&8&6Do odebrania: &e" + user.getPandora()));
        ItemBuilder mychest = new ItemBuilder(Material.ENDER_CHEST).setTitle(ChatUtils.colored(plugin.getPluginConfiguration().getMychest_name())).addLore(ChatUtils.colored("&8&6Do odebrania: &e" + user.getMychest()));
        ItemBuilder wallpaper = GlassType.GRAY.getItemStack().setTitle(ChatUtils.colored("&8*"));
        inv.setItem(11, pandora.build());
        inv.setItem(15, mychest.build());
        for (int i = 0; i < 27; i++) {
            if (inv.getItem(i) == null)
                inv.setItem(i, wallpaper.build());
        }
        player.openInventory(inv);
    }
}

