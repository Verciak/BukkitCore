package pl.vertty.core.utils;



import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public final class ItemUtils {
    public static void giveItem(Player player, ItemStack item) {
        if (player.getInventory().firstEmpty() == -1)
            player.getWorld().dropItem(player.getLocation(), item);
        player.getInventory().addItem(item);
    }

    public static void giveItem(Player player, ItemStack... items) {
        Arrays.<ItemStack>stream(items).forEach(item -> giveItem(player, item));
    }

    public static int getAmountOfItem(final Player player, final ItemStack material, final short durability) {
        int amount = 0;
        ItemStack[] contents;
        for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack itemStack = contents[i];
            if (itemStack != null && itemStack.getType().equals(material) && itemStack.getDurability() == durability) {
                amount += itemStack.getAmount();
            }
        }
        return amount;
    }

    public static int getAmountOfItem(final ItemStack material, final Player player, final short durability) {
        int amount = 0;
        ItemStack[] contents;
        for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack itemStack = contents[i];
            if (itemStack != null && itemStack.getType().equals((Object)material) && itemStack.getDurability() == durability) {
                amount += itemStack.getAmount();
            }
        }
        return amount;
    }

    public static boolean hasItem(Player player, ItemStack... ItemList) {
        for (ItemStack item : ItemList) {
            if (!hasItem(player, item, item.getAmount()))
                return false;
        }
        return true;
    }

    public static boolean removeItems(Player player, List<ItemStack> items) {
        for (ItemStack is : items) {
            ItemStack type = is;
            int amount = is.getAmount();
            removeItem(player, type, amount);
        }
        return false;
    }

    public static boolean hasItem(Player player, ItemStack is) {
        return hasItem(player, is, is.getAmount());
    }

    public static boolean hasItem(Player player, ItemStack type, int amount) {
        for (ItemStack is : player.getInventory().getContents()) {
            if (is != null &&
                    is == type) {
                amount -= is.getAmount();
                if (amount <= 0)
                    return true;
            }
        }
        return (amount <= 0);
    }

    public static void removeItem(Player player, ItemStack is) {
        removeItem(player, is, is.getAmount());
    }

    public static void removeItem(Player player, ItemStack type, int amount) {
        for (ItemStack is : player.getInventory().getContents()) {
            if (is != null &&
                    is == type) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    is.setAmount(newAmount);
                    break;
                }
                player.getInventory().remove(is);
                amount = -newAmount;
                if (amount == 0)
                    break;
            }
        }
    }
}

