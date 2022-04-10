package pl.vertty.core.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.vertty.core.enums.GlassType;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.ItemBuilder;

public class MessageControlMenu {

    public static Inventory inv = Bukkit.createInventory(null, 27, ChatUtils.colored("&9WIADOMOSCI"));

    public static void MessageControlMenu(Player p) {
        ItemBuilder on = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE, Integer.valueOf(1)).setTitle(ChatUtils.colored("&aKliknij aby wlaczyc wiadomosci z MyChest"));
        ItemBuilder off = new ItemBuilder(Material.RED_STAINED_GLASS_PANE, Integer.valueOf(1)).setTitle(ChatUtils.colored("&cKliknij aby wylaczyc wiadomosci z MyChest"));
        ItemBuilder wallpaper = GlassType.GRAY.getItemStack().setTitle(ChatUtils.colored("&8*"));
        inv.setItem(12, on.build());
        inv.setItem(14, off.build());
        for (int i = 0; i < 27; i++) {
            if (inv.getItem(i) == null)
                inv.setItem(i, wallpaper.build());
        }
        p.openInventory(inv);
    }
}