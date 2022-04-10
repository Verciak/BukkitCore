package pl.vertty.core.utils;



import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

public final class ParserUtils {
    public static ItemStack parseItemStack(String string) {
        ItemStack is = new ItemStack(Material.AIR);
        String[] args = string.split(" ");
        for (String arg : args) {
            String[] splitArg = arg.split(":");
            String key = splitArg[0];
            String value = splitArg[1];
            if (key.equalsIgnoreCase("material")) {
                is = new ItemStack(Material.matchMaterial(value));
            } else if (key.equalsIgnoreCase("amount")) {
                if (IntegerUtils.isInt(value))
                    is.setAmount(Integer.parseInt(value));
            } else if (key.equalsIgnoreCase("name")) {
                String name = value.replace("_", " ");
                is.getItemMeta().setDisplayName(ChatUtils.colored(name));
            } else if (key.equalsIgnoreCase("lore")) {
                List<String> lore = new LinkedList<>();
                String[] splitLore = value.split("@nl@");
                for (String s : splitLore)
                    lore.add(ChatUtils.colored(s.replace("_", " ")));
                is.getItemMeta().setLore(lore);
            } else if (key.equalsIgnoreCase("data") || key.equalsIgnoreCase("durability")) {
                if (IntegerUtils.isInt(value)) {
                    int data = Integer.parseInt(value);
                    is.setDurability((short) data);
                }
            } else if (key.equalsIgnoreCase("enchants")) {
                String[] enchantmentArray = value.split("@nl@");
                for (String s : enchantmentArray) {
                    String[] enchantmentSplit = s.split(";");
                    if (enchantmentSplit.length >= 1) {
                        Enchantment ench = Enchantment.getByName(enchantmentSplit[0]);
                        if (IntegerUtils.isInt(enchantmentSplit[1])) {
                            int level = Integer.parseInt(enchantmentSplit[1]);
                            if (ench != null) {
                                is.addEnchantment(ench, level);
                            }
                        }
                    }
                }
            }
        }
        return is;
    }
}

