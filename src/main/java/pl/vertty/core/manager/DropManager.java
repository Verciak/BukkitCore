package pl.vertty.core.manager;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import pl.vertty.core.builder.MessageBuilder;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.utils.*;
import pl.vertty.core.data.config.PluginConfiguration;
import pl.vertty.api.self.Count;
import com.google.common.collect.Lists;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.objects.drops.basic.Drop;
import java.util.List;
import pl.vertty.core.data.config.SettingsConfiguration;

public class DropManager
{
    private final int stoneExp;
    private final int obsidianExp;
    private final UserManager userManager;
    private final SettingsConfiguration config;
    private final List<Drop> dropList;
    private final CorePlugin plugin;
    
    public DropManager(final CorePlugin plugin) {
        this.userManager = plugin.getUserManager();
        this.config = plugin.getSettingsConfiguration();
        this.dropList = Lists.newArrayList();
        this.plugin = plugin;
        this.stoneExp = plugin.getConfig().getInt("exps.stone");
        this.obsidianExp = plugin.getConfig().getInt("exps.obsidian");
        for (final String id : plugin.getConfig().getConfigurationSection("drops").getKeys(false)) {
            final ConfigurationSection section = plugin.getConfig().getConfigurationSection("drops." + id);
            final Count amount = Count.parse(section.getString("amount", "1-2"));
            final Count height = Count.parse(section.getString("height", "0-90"));
            final double chance = section.getDouble("chance", 5.0);
            final boolean fortune = section.getBoolean("fortune", true);
            final int exp = section.getInt("exp", 3);
            final int slot = section.getInt("slot");
            final ItemStack itemStack = ParserUtils.parseItemStack(section.getString("item"));
            ItemBuilder builder = new ItemBuilder(itemStack.getType(), itemStack.getAmount(), itemStack.getDurability());
            final String name = section.getString("name", "Diament");
            final Drop drop = new Drop(name, fortune, builder, chance, height, amount, exp, slot);
            this.dropList.add(drop);
        }
        plugin.getLogger().info("Zaladowano " + this.dropList.size() + " dropow ze stone!");
    }
    
    public void breakBlock(final BlockBreakEvent e) {
        if (e.getPlayer().getGameMode() != GameMode.SURVIVAL) {
            return;
        }
        final Player player = e.getPlayer();
        final UserData user = this.userManager.getUser(player);
        final Block block = e.getBlock();
        final ItemStack tool = e.getPlayer().getInventory().getItemInHand();
        if (block.getType() == Material.OBSIDIAN) {
            if (this.config.isTurboExp()) {
                player.giveExp(this.obsidianExp * 2);
            }
            else {
                player.giveExp(this.obsidianExp);
            }
        }
        else if (block.getType() == Material.STONE) {
            user.addMinedStone(1);
            if (this.config.isTurboExp()) {
                player.giveExp(this.stoneExp * 2);
            }
            else {
                player.giveExp(this.stoneExp);
            }
            if (this.config.isMychest() && RandomUtils.getChance(PluginConfiguration.getInstance().getMychest_chance())) {
                ItemUtils.giveItem(player, this.plugin.getChestManager().getChestItem());
            }
            e.setCancelled(true);
            block.setType(Material.AIR);
            if (user.isCobble()) {
                ItemStack item = player.getItemInHand();
                ItemUtils.giveItem(player, (new ItemStack(item.containsEnchantment(Enchantment.SILK_TOUCH) ? Material.STONE : Material.COBBLESTONE, 1)));
            }
            final ItemStack itemStack;
            final Player player2;
            final ItemStack itemStack2;
            final Player player3;
            final UserData userData = userManager.getUser(player);
            this.dropList.stream().filter(drop -> !user.isDisabled(drop) && e.getPlayer().getLocation().getY() <= drop.getHeight().getMax() && e.getPlayer().getLocation().getY() >= drop.getHeight().getMin()).filter(drop -> {
                double chance = drop.getChance();
                if (drop.isFortune() && tool != null && tool.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                    chance += tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                }
                if (this.config.isTurboDrop()) {
                    chance *= 2.0;
                }
                if (player.hasPermission("mhCore.drop.premium")) {
                    chance += chance / 10.0;
                }
                else if (player.hasPermission("mhCore.drop.premiumplus")) {
                    chance += chance / 20.0;
                }
                else if (player.hasPermission("mhCore.drop.sponsor")) {
                    chance += chance / 30.0;
                }
                return RandomUtils.getChance(chance);
            }).forEach(drop -> {
                if (tool != null) {
                    if (tool.getType().getMaxDurability() == 0) {
                        return;
                    }
                    else {
                        int enchantLevel = tool.getEnchantmentLevel(Enchantment.DURABILITY);
                        int d = tool.getDurability();
                        if (enchantLevel > 0) {
                            if (100 / (enchantLevel + 1) > RandomUtils.getRandInt(0, 100)) {
                                if (d == tool.getType().getMaxDurability()) {
                                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                                }
                                else {
                                    tool.setDurability((short) (d + 1));
                                }
                            }
                        }
                        else if (d == tool.getType().getMaxDurability()) {
                            player.getInventory().clear(player.getInventory().getHeldItemSlot());
                        }
                        else {
                            tool.setDurability((short) (d + 1));
                        }
                    }
                }
                int amount = RandomUtils.getRandInt(drop.getAmount().getMin(), drop.getAmount().getMax());
                int exp = drop.getExp();
                int exp2 = exp * amount;
                if (this.config.isTurboExp()) {
                    exp2 *= 2;
                }
                e.getPlayer().giveExp(exp2);
                if (userData.isMessages()) {
                    ChatUtils.sendMessage(player, new MessageBuilder().setText(PluginConfiguration.getInstance().getDrop_message()).addField("{EXP}", String.valueOf(exp2)).addField("{AMOUNT}", String.valueOf(amount)).addField("{DROP}", drop.getName()).build());
                }
                ItemUtils.giveItem(e.getPlayer(), new ItemStack(drop.getItem().getType(), amount));
            });
        }
    }
    
    public List<Drop> getDropList() {
        return this.dropList;
    }
}
