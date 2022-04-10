package pl.vertty.core.menu;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.objects.item.PandoreItem;
import pl.vertty.core.enums.GlassType;
import pl.vertty.core.manager.PandoreManager;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class PandoreMenu
{

    public static Inventory inv = Bukkit.createInventory(null, 54, ChatUtils.colored("&9PANDORA"));

    public static void PandoreMenu(Player p) {

        final ItemBuilder wallpaper = GlassType.GRAY.getItemStack().setTitle(ChatUtils.colored("&8*"));
        final ItemBuilder info = new ItemBuilder(Material.PAPER).setTitle(ChatUtils.colored("&6Pandore kupisz tylko na stronie blazepe.pl"));
        for (final PandoreItem pandoreItem : PandoreManager.getPandoreList()) {
            ItemBuilder itemBuilder = new ItemBuilder(pandoreItem.getItemStack().getType(), pandoreItem.getItemStack().getAmount(), pandoreItem.getItemStack().getDurability());
            itemBuilder.setTitle(ChatUtils.colored("&9" +pandoreItem.getName()));
            List<String> lore = new ArrayList<>();
            lore.add(ChatUtils.colored(""));
            lore.add(ChatUtils.colored("&8» &7Ilosc: &9" + pandoreItem.getCount()));
            itemBuilder.addLores(lore);
            inv.addItem(itemBuilder.build());
        }
        for (int i = 0; i < 54; ++i) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, wallpaper.build());
            }
        }
        inv.setItem(53, info.build());
        p.openInventory(inv);
    }
}
