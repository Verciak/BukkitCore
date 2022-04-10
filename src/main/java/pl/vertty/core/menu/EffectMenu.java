package pl.vertty.core.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.vertty.core.enums.GlassType;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.ItemBuilder;

import java.util.List;

public class EffectMenu {
    public static Inventory inv = Bukkit.createInventory(null, 27, ChatUtils.colored("&9EFEKTY"));

    public static void EffectMenu(Player p) {
        ItemBuilder night = new ItemBuilder(Material.ENDER_EYE).setTitle(ChatUtils.colored("&e&lWidzenie w ciemnosci")).addLores(List.of(new String[]{ChatUtils.colored("  &8&7Koszt: &e5 blokow emeraldow"), ChatUtils.colored("  &8&7Czas: &e4 minuty")}));
        ItemBuilder jump = new ItemBuilder(Material.SPIDER_EYE).setTitle(ChatUtils.colored("&e&lWysokie skakanie II")).addLores(List.of(new String[]{ChatUtils.colored("  &8&7Koszt: &e10 blokow emeraldow"), ChatUtils.colored("  &8&7Czas: &e2 minuty")}));
        ItemBuilder speed = new ItemBuilder(Material.FEATHER).setTitle(ChatUtils.colored("&e&lSzybkosc I")).addLores(List.of(new String[]{ChatUtils.colored("  &8&7Koszt: &e15 blokow emeraldow"), ChatUtils.colored("  &8&7Czas: &e2 minuty")}));
        ItemBuilder haste = new ItemBuilder(Material.FIRE_CHARGE).setTitle(ChatUtils.colored("&e&lSzybkie kopanie II")).addLores(List.of(new String[]{ChatUtils.colored("  &8&7Koszt: &e10 blokow emeraldow"), ChatUtils.colored("  &8&7Czas: &e2 minuty")}));
        ItemBuilder wallpaper = GlassType.GRAY.getItemStack().setTitle(ChatUtils.colored("&8*"));
        inv.setItem(10, night.build());
        inv.setItem(12, jump.build());
        inv.setItem(14, speed.build());
        inv.setItem(16, haste.build());
        for (int i = 0; i < 27; i++) {
            if (inv.getItem(i) == null)
                inv.setItem(i, wallpaper.build());
        }
        p.openInventory(inv);
    }
}

