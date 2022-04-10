package pl.vertty.core.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.vertty.core.data.config.PluginConfiguration;
import pl.vertty.core.enums.DepositType;
import pl.vertty.core.data.objects.user.UserData;

public final class DepositUtils
{
    private static final int notch;
    private static final int golden;
    private static final int pearls;
    private static final int arrow;
    
    public static int calculateWithdrawl(final UserData depositUser, final DepositType depositType) {
        switch (depositType) {
            case NOTCH: {
                final int i = ItemUtils.getAmountOfItem(depositUser.getPlayer(), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 0, (short) 0), (short)0);
                if (i >= DepositUtils.notch) {
                    return 0;
                }
                if (i < DepositUtils.notch) {
                    return (i - DepositUtils.notch) * -1;
                }
                if (depositUser.getNotchApple() < DepositUtils.notch) {
                    return depositUser.getNotchApple();
                }
                break;
            }
            case GOLDEN: {
                final int i = ItemUtils.getAmountOfItem(depositUser.getPlayer(), new ItemStack(Material.GOLDEN_APPLE, 0, (short) 0), (short)0);
                if (i >= DepositUtils.golden) {
                    return 0;
                }
                if (i < DepositUtils.golden) {
                    return (i - DepositUtils.golden) * -1;
                }
                if (depositUser.getGolden() < DepositUtils.golden) {
                    return depositUser.getGolden();
                }
                break;
            }
            case PEARL: {
                final int i = ItemUtils.getAmountOfItem(depositUser.getPlayer(), new ItemStack(Material.ENDER_PEARL, 0, (short) 0), (short)0);
                if (i >= DepositUtils.pearls) {
                    return 0;
                }
                if (i < DepositUtils.pearls) {
                    return (i - DepositUtils.pearls) * -1;
                }
                if (depositUser.getPearl() < DepositUtils.pearls) {
                    return depositUser.getPearl();
                }
                break;
            }
            case ARROW: {
                final int i = ItemUtils.getAmountOfItem(depositUser.getPlayer(), new ItemStack(Material.ARROW, 0, (short) 0), (short)0);
                if (i >= DepositUtils.arrow) {
                    return 0;
                }
                if (i < DepositUtils.arrow) {
                    return (i - DepositUtils.arrow) * -1;
                }
                if (depositUser.getPearl() < DepositUtils.arrow) {
                    return depositUser.getArrow();
                }
                break;
            }
            default: {
                return 0;
            }
        }
        return 0;
    }
    
    public static void takeWithdrawl(final UserData depositUser, final DepositType depositType, final boolean m) {
        switch (depositType) {
            case NOTCH: {
                final int maxNotch = DepositUtils.notch;
                if (depositUser.getNotchApple() <= 0) {
                    if (m) {
                        ChatUtils.sendMessage(depositUser.getPlayer(), "&4Blad: &cNie masz tego przedmiotu w schowku!");
                        break;
                    }
                    break;
                }
                else {
                    final int notch = ItemUtils.getAmountOfItem(depositUser.getPlayer(), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 0, (short) 0), (short)0);
                    if (notch >= maxNotch) {
                        if (m) {
                            ChatUtils.sendMessage(depositUser.getPlayer(), "&4Blad: &cPosiadasz juz limit tego przedmiotu!");
                            break;
                        }
                        break;
                    }
                    else {
                        int i = maxNotch - notch;
                        if (i > depositUser.getNotchApple()) {
                            i = depositUser.getNotchApple();
                        }
                        depositUser.setNotchApple(depositUser.getNotchApple() - i);
                        ItemUtils.giveItem(depositUser.getPlayer(), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, i, (short) 0));
                        if (m) {
                            ChatUtils.sendMessage(depositUser.getPlayer(), "&6Wyplaciles: &c" + i + " &6koxow!");
                            break;
                        }
                        break;
                    }
                }
            }
            case GOLDEN: {
                final int maxGolden = DepositUtils.golden;
                if (depositUser.getGolden() <= 0) {
                    if (m) {
                        ChatUtils.sendMessage(depositUser.getPlayer(), "&4Blad: &cNie masz tego przedmiotu w schowku!");
                        break;
                    }
                    break;
                }
                else {
                    final int golden = ItemUtils.getAmountOfItem(depositUser.getPlayer(), new ItemStack(Material.GOLDEN_APPLE, 0, (short) 0), (short)0);
                    if (golden >= maxGolden) {
                        if (m) {
                            ChatUtils.sendMessage(depositUser.getPlayer(), "&4Blad: &cPosiadasz juz limit tego przedmiotu!");
                            break;
                        }
                        break;
                    }
                    else {
                        int i = maxGolden - golden;
                        if (i > depositUser.getGolden()) {
                            i = depositUser.getGolden();
                        }
                        depositUser.setGolden(depositUser.getGolden() - i);
                        ItemUtils.giveItem(depositUser.getPlayer(), new ItemStack(Material.GOLDEN_APPLE, i, (short) 0));
                        if (m) {
                            ChatUtils.sendMessage(depositUser.getPlayer(), "&6Wyplaciles: &c" + i + " &6refili!");
                            break;
                        }
                        break;
                    }
                }
            }
            case PEARL: {
                final int maxPearl = DepositUtils.pearls;
                if (depositUser.getPearl() <= 0) {
                    if (m) {
                        ChatUtils.sendMessage(depositUser.getPlayer(), "&4Blad: &cNie masz tego przedmiotu w schowku!");
                        break;
                    }
                    break;
                }
                else {
                    final int pearl = ItemUtils.getAmountOfItem(depositUser.getPlayer(), new ItemStack(Material.ENDER_PEARL, 0, (short) 0), (short) 0);
                    if (pearl >= maxPearl) {
                        if (m) {
                            ChatUtils.sendMessage(depositUser.getPlayer(), "&4Blad: &cPosiadasz juz limit tego przedmiotu!");
                            break;
                        }
                        break;
                    }
                    else {
                        int i = maxPearl - pearl;
                        if (i > depositUser.getPearl()) {
                            i = depositUser.getPearl();
                        }
                        depositUser.setPearl(depositUser.getPearl() - i);
                        ItemUtils.giveItem(depositUser.getPlayer(), new ItemStack(Material.ENDER_PEARL, i, (short) 0));
                        if (m) {
                            ChatUtils.sendMessage(depositUser.getPlayer(), "&6Wyplaciles: &c" + i + " &6perel!");
                            break;
                        }
                        break;
                    }
                }
            }
            case ARROW: {
                final int maxArrow = DepositUtils.arrow;
                if (depositUser.getArrow() <= 0) {
                    if (m) {
                        ChatUtils.sendMessage(depositUser.getPlayer(), "&4Blad: &cNie masz tego przedmiotu w schowku!");
                        break;
                    }
                    break;
                }
                else {
                    final int arrow = ItemUtils.getAmountOfItem(depositUser.getPlayer(), new ItemStack(Material.ARROW, 0, (short) 0), (short) 0);
                    if (arrow >= maxArrow) {
                        if (m) {
                            ChatUtils.sendMessage(depositUser.getPlayer(), "&4Blad: &cPosiadasz juz limit tego przedmiotu!");
                            break;
                        }
                        break;
                    }
                    else {
                        int i = maxArrow - arrow;
                        if (i > depositUser.getArrow()) {
                            i = depositUser.getArrow();
                        }
                        depositUser.setArrow(depositUser.getArrow() - i);
                        ItemUtils.giveItem(depositUser.getPlayer(), new ItemStack(Material.ARROW, i, (short) 0));
                        if (m) {
                            ChatUtils.sendMessage(depositUser.getPlayer(), "&6Wyplaciles: &c" + i + " &6strzal!");
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }
    
    static {
        notch = PluginConfiguration.getInstance().getDeposit_notch();
        golden = PluginConfiguration.getInstance().getDeposit_golden();
        pearls = PluginConfiguration.getInstance().getDeposit_pearls();
        arrow = PluginConfiguration.getInstance().getDeposit_arrow();
    }
}
