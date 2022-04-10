package pl.vertty.core.utils;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class BlockReplacerUtils
{
    private BlockReplacerUtils() {
    }
    
    public static void replace(final Player player, final ItemStack from, final ItemStack to, final boolean msg) {
        final ItemStack itemStack = new ItemStack(from.getType(),9, (short) 0);
        final ItemStack blockItemStack = to;
        if (!player.getInventory().contains(itemStack)) {
            if (msg) {
                ChatUtils.sendMessage(player, "&4Blad: &cNie masz wymaganych przedmitow!");
            }
            return;
        }
        for (int i = 0; i < 256 && player.getInventory().contains(itemStack); ++i) {
            ItemUtils.removeItem(player, itemStack);
            ItemUtils.giveItem(player, blockItemStack);
        }
    }
}
