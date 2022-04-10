package pl.vertty.core.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import pl.vertty.core.data.objects.user.UserData;

import pl.vertty.core.CorePlugin;
import pl.vertty.core.utils.ChatUtils;


public class DepositTask extends BukkitRunnable
{
    private final CorePlugin plugin;
    
    public DepositTask(final CorePlugin plugin) {
        this.runTaskTimerAsynchronously((Plugin)(this.plugin = plugin), 20, 100);
    }
    
    public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
            UserData user = this.plugin.getUserManager().getUser(p);
            int koxSize = 0;
            int refSize = 0;
            int perSize = 0;
            int arrSize = 0;
            for (ItemStack pinv : p.getInventory().getContents()) {
                if (pinv != null) {
                    if (pinv.getType() == Material.ARROW) {
                        if (pinv.hasItemMeta())
                            return;
                        arrSize += pinv.getAmount();
                    }
                    if (pinv.getType() == Material.ENDER_PEARL) {
                        if (pinv.hasItemMeta())
                            return;
                        perSize += pinv.getAmount();
                    }
                    if (pinv.getType() == Material.ENCHANTED_GOLDEN_APPLE) {
                        if (pinv.hasItemMeta())
                            return;
                        koxSize += pinv.getAmount();
                    }
                    if (pinv.getType() == Material.GOLDEN_APPLE) {
                        if (pinv.hasItemMeta())
                            return;
                        refSize += pinv.getAmount();
                    }
                }
            }
            if (arrSize > this.plugin.getPluginConfiguration().getDeposit_arrow()) {
                int toRemove = arrSize - this.plugin.getPluginConfiguration().getDeposit_arrow();
                user.setArrow(user.getArrow() + (arrSize - this.plugin.getPluginConfiguration().getDeposit_arrow()));
                p.getInventory().removeItem(new ItemStack(Material.ARROW, toRemove, (short) 0));
                ChatUtils.sendMessage(p, "&6Masz za duzo strzal w ekwipunku! &7({ITEM} odlozono do schowka)".replace("{ITEM}", "" + (arrSize - this.plugin.getPluginConfiguration().getDeposit_arrow()) + " strzal"));
            }
            if (perSize > this.plugin.getPluginConfiguration().getDeposit_pearls()) {
                int toRemove = perSize - this.plugin.getPluginConfiguration().getDeposit_pearls();
                user.setPearl(user.getPearl() + perSize - this.plugin.getPluginConfiguration().getDeposit_pearls());
                p.getInventory().removeItem(new ItemStack(Material.ENDER_PEARL, toRemove, (short) 0));
                ChatUtils.sendMessage(p, "&6Masz za duzo perel w ekwipunku! &7({ITEM} odlozono do schowka)".replace("{ITEM}", "" + (perSize - this.plugin.getPluginConfiguration().getDeposit_pearls()) + " perel"));

            }
            if (koxSize > this.plugin.getPluginConfiguration().getDeposit_notch()) {
                int toRemove = koxSize - this.plugin.getPluginConfiguration().getDeposit_notch();
                user.setNotchApple(user.getNotchApple() + (koxSize - this.plugin.getPluginConfiguration().getDeposit_notch()));
                p.getInventory().removeItem(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, toRemove, (short) 0));
                ChatUtils.sendMessage(p, "&6Masz za duzo koxow w ekwipunku! &7({ITEM} odlozono do schowka)".replace("{ITEM}", "" + (koxSize - this.plugin.getPluginConfiguration().getDeposit_notch()) + " koxow"));
            }
            if (refSize > this.plugin.getPluginConfiguration().getDeposit_golden()) {
                int toRemove = refSize - this.plugin.getPluginConfiguration().getDeposit_golden();
                user.setGolden(user.getGolden() + (refSize - this.plugin.getPluginConfiguration().getDeposit_golden()));
                p.getInventory().removeItem(new ItemStack(Material.GOLDEN_APPLE, toRemove, (short) 0));
                ChatUtils.sendMessage(p, "&6Masz za duzo refili w ekwipunku! &7({ITEM} odlozono do schowka)".replace("{ITEM}", "" + (refSize - this.plugin.getPluginConfiguration().getDeposit_golden()) + " refili"));
            }
        }
    }
}
