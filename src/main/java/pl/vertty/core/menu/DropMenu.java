package pl.vertty.core.menu;

import org.apache.logging.log4j.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.objects.drops.basic.Drop;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.enums.GlassType;
import pl.vertty.core.manager.DropManager;
import pl.vertty.core.manager.UserManager;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.DataUtils;
import pl.vertty.core.utils.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class DropMenu {
    public static DropManager dropManager = CorePlugin.getPlugin().getDropManager();
    public static UserManager userManager = CorePlugin.getPlugin().getUserManager();
    public static CorePlugin plugin = CorePlugin.getPlugin();

    public static void createInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 54, ChatUtils.colored("&9DROP"));
        UserData user = userManager.getUser(p);
        inv.clear();
        inv.clear();
        for(int x = 0; x < inv.getSize(); x++) {
            inv.clear(x);
        }
        ItemBuilder pane = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setTitle(ChatUtils.colored("&8*"));

        for(int i = 0; i < 10; i++) {
            inv.setItem(i, pane.build());
        }
        inv.setItem(17, pane.build());
        inv.setItem(18, pane.build());
        inv.setItem(26, pane.build());
        for(int i = 27; i < 37; i++) {
            inv.setItem(i, pane.build());
        }
        inv.setItem(40, pane.build());
        inv.setItem(43, pane.build());
        inv.setItem(44, pane.build());
        for(int i = 45; i < 54; i++) {
            inv.setItem(i, pane.build());
        }
        for (Drop drop : CorePlugin.getPlugin().getDropManager().getDropList()) {
            ItemBuilder item = drop.getItem().setTitle(ChatUtils.colored("&6" + drop.getName()));
            item.addLore(ChatUtils.colored("&7Szansa: &6" + drop.getChance() + "%"));
            item.addLore(ChatUtils.colored("&7Wypada pomiedzy &6" + drop.getHeight().getMin() + " &7a &6" + drop.getHeight().getMax() + " &7poziomem."));
            item.addLore(ChatUtils.colored("&7Wypada w ilosci od &6" + drop.getAmount().getMin() + " &7do &6" + drop.getAmount().getMax() + "&7."));
            item.addLore(ChatUtils.colored("&7Exp: &6" + drop.getExp() + ""));
            item.addLore(ChatUtils.colored("&7Szczescie: " + (drop.isFortune() ? "&a✔" : "&c✖")));
            item.addLore(ChatUtils.colored("&7Status: " + (user.isDisabled(drop) ? "&c✖" : "&a✔")));
            inv.setItem(drop.getSlot(), item.build());
        }
        ItemBuilder myChest = new ItemBuilder(Material.ENDER_CHEST).setTitle(ChatUtils.colored("&6MyChest")).addLores(List.of(new String[]{ChatUtils.colored("&8&7Szansa: &6" + plugin.getPluginConfiguration().getMychest_chance() + "%"),
                ChatUtils.colored("&8&7Wlaczony: " + (plugin.getSettingsConfiguration().isTurboMychest() ? "&a✔" : "&c✖"))}));
        inv.setItem(25, myChest.build());
        ItemBuilder enableAll = GlassType.LIME.getItemStack().setTitle(ChatUtils.colored("&aWlacz wszystkie dropy"));
        ItemBuilder disableAll = GlassType.RED.getItemStack().setTitle(ChatUtils.colored("&cWylacz wszystkie dropy"));
        ItemBuilder message = new ItemBuilder(Material.PAPER).setTitle(ChatUtils.colored("&eWiadomosci")).addLores(List.of(new String[]{ChatUtils.colored("&7Status: " + (user.isMessages() ? "&a✔" : "&c✖"))}));
        inv.setItem(37, enableAll.build());
        inv.setItem(38, disableAll.build());
        inv.setItem(39, message.build());
        ItemBuilder turbo = new ItemBuilder(Material.DIAMOND_PICKAXE).setTitle(ChatUtils.colored("&eDostepne eventy:")).addLores(List.of(new String[]{"",
                ChatUtils.colored("&7TurboDrop: &e" + DataUtils.durationToString(plugin.getSettingsConfiguration().getTurbodrop$time())),
                ChatUtils.colored("&7TurboExp: &e" + DataUtils.durationToString(plugin.getSettingsConfiguration().getTurboexp$time())), ""}));
        ItemBuilder cobble = new ItemBuilder(Material.COBBLESTONE).setTitle(ChatUtils.colored("&eCobblestone")).addLores(List.of(new String[]{ChatUtils.colored("&7Status: " + (user.isCobble() ? "&a✔" : "&c✖"))}));
        inv.setItem(41, turbo.build());
        inv.setItem(42, cobble.build());
        p.openInventory(inv);
    }
}

