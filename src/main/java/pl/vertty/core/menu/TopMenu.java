package pl.vertty.core.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.enums.GlassType;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.ItemBuilder;
import pl.vertty.core.utils.TopUtils;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.manager.ComparatorManager;

public class TopMenu
{
    public static ComparatorManager comparatorManager;
    public static Inventory inv = Bukkit.createInventory(null, 27, ChatUtils.colored("&9TOPKI"));

    public TopMenu(final CorePlugin plugin) {
        comparatorManager = plugin.getComparatorManager();
    }

    public static void createInventory(Player p) {
        ItemBuilder richs = (new ItemBuilder(Material.GOLD_NUGGET)).setTitle(ChatUtils.colored("&9Topka bogaczy"));
        for (int i = 1; i <= 10; ++i) {
            final UserData user = comparatorManager.getSpendMoneyComparator().getUser(i);
            if (user == null) {
                richs.addLore(ChatUtils.colored("&7" + i + ". &fBrak"));
            }
            else {
                richs.addLore(ChatUtils.colored("&7" + i + ". &f" + user.getLastName() + " &7- &9" + user.getCoins() + " pieniedzy"));
            }
        }
        ItemBuilder kox = new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, 0, (short) 1).setTitle(ChatUtils.colored("&9Topka zjedzonych koxow"));
        for (int j = 1; j <= 10; ++j) {
            final UserData user2 = comparatorManager.getEatNotchComparator().getUser(j);
            if (user2 == null) {
                kox.addLore(ChatUtils.colored("&7" + j + ". &fBrak"));
            }
            else {
                kox.addLore(ChatUtils.colored("&7" + j + ". &f" + user2.getLastName() + " &7- &9" + user2.getEatNotch() + " koxow"));
            }
        }
        final ItemBuilder stone = new ItemBuilder(Material.STONE).setTitle(ChatUtils.colored("&9Topka wykopanego stone'a"));
        for (int k = 1; k <= 10; ++k) {
            final UserData user3 = comparatorManager.getMinedBlocksComparator().getUser(k);
            if (user3 == null) {
                stone.addLore(ChatUtils.colored("&7" + k + ". &fBrak"));
            }
            else {
                stone.addLore(ChatUtils.colored("&7" + k + ". &f" + user3.getLastName() + " &7- &9" + user3.getMinedStone() + " kamienia"));
            }
        }
        final ItemBuilder kills = new ItemBuilder(Material.RED_DYE).setTitle(ChatUtils.colored("&9Topka zabojstw"));
        for (int l = 1; l <= 10; ++l) {
            final UserData user4 = comparatorManager.getKillsComparator().getUser(l);
            if (user4 == null) {
                kills.addLore(ChatUtils.colored("&7" + l + ". &fBrak"));
            }
            else {
                kills.addLore(ChatUtils.colored("&7" + l + ". &f" + user4.getLastName() + " &7- &9" + user4.getKills() + " zabojstw"));
            }
        }
        final ItemBuilder kms = new ItemBuilder(Material.DIAMOND_BOOTS).setTitle(ChatUtils.colored("&9Topka przebytych kilometrow"));
        for (int m = 1; m <= 10; ++m) {
            final UserData user5 = comparatorManager.getDistanceComparator().getUser(m);
            if (user5 == null) {
                kms.addLore(ChatUtils.colored("&7" + m + ". &fBrak"));
            }
            else {
                kms.addLore(ChatUtils.colored("&7" + m + ". &f" + user5.getLastName() + " &7- &9" + TopUtils.distanceToString((int)user5.getDistance())));
            }
        }
        final ItemBuilder deaths = new ItemBuilder(Material.PLAYER_HEAD).setTitle(ChatUtils.colored("&9Topka smierci"));
        for (int i2 = 1; i2 <= 10; ++i2) {
            final UserData user6 = comparatorManager.getDeathsComparator().getUser(i2);
            if (user6 == null) {
                deaths.addLore(ChatUtils.colored("&7" + i2 + ". &fBrak"));
            }
            else {
                deaths.addLore(ChatUtils.colored("&7" + i2 + ". &f" + user6.getLastName() + " &7- &9" + user6.getDeaths() + " smierci"));
            }
        }
        final ItemBuilder blocks = new ItemBuilder(Material.BRICK).setTitle(ChatUtils.colored("&9Topka postawionych blokow"));
        for (int i3 = 1; i3 <= 10; ++i3) {
            final UserData user7 = comparatorManager.getPlacedBlocksComparator().getUser(i3);
            if (user7 == null) {
                blocks.addLore(ChatUtils.colored("&7" + i3 + ". &fBrak"));
            }
            else {
                blocks.addLore(ChatUtils.colored("&7" + i3 + ". &f" + user7.getLastName() + " &7- &9" + user7.getPlacedBlocks() + " blokow"));
            }
        }
        final ItemBuilder wallpaper = GlassType.GRAY.getItemStack().setTitle(ChatUtils.colored("&8*"));
        inv.setItem(10, richs.build());
        inv.setItem(11, kox.build());
        inv.setItem(12, stone.build());
        inv.setItem(13, kills.build());
        inv.setItem(14, kms.build());
        inv.setItem(15, deaths.build());
        inv.setItem(16, blocks.build());
        for (int i4 = 0; i4 < 27; ++i4) {
            if (inv.getItem(i4) == null) {
                inv.setItem(i4, wallpaper.build());
            }
        }
        p.openInventory(inv);
    }
}
