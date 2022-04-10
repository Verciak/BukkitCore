package pl.vertty.core.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import pl.vertty.core.data.objects.Kit;
import pl.vertty.core.manager.KitManager;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.ItemBuilder;

public class KitMenu {
    public static Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, ChatUtils.colored("&9KITY"));

    public static void KitMenu(Player p) {
        for (Kit kit : KitManager.getKitMap().values()) {
            ItemBuilder kitItem = new ItemBuilder(kit.getIcon().getType());
            inv.setItem(kit.getSlot(), kitItem.setTitle(ChatUtils.colored(kit.getGuiName())).build());
        }
        p.openInventory(inv);
    }
}
