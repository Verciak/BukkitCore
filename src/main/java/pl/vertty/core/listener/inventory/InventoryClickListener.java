package pl.vertty.core.listener.inventory;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.InventoryView;
import pl.vertty.core.data.objects.Kit;
import pl.vertty.core.data.objects.user.UserData;
import org.bukkit.inventory.Inventory;
import pl.vertty.core.utils.*;
import pl.vertty.core.enums.DepositType;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import pl.vertty.core.enums.DiscoType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import pl.vertty.core.enums.GuildPermission;
import pl.vertty.core.data.objects.drops.basic.Drop;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.CorePlugin;
import org.bukkit.event.Listener;

public class InventoryClickListener implements Listener
{
    private final CorePlugin plugin;
    
    public InventoryClickListener(final CorePlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        final InventoryView inventory = event.getView();
        final ItemStack itemStack = event.getCurrentItem();
        final int itemslot = event.getSlot();
        final Player player = (Player)event.getWhoClicked();
        final UserData user = this.plugin.getUserManager().getUser(player);
//        if (inventory.getTitle().equalsIgnoreCase(ChatUtils.colored("&9DROP")) && itemStack != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().getDisplayName() != null) {
//            final String itemName = ChatUtils.colored(event.getCurrentItem().getItemMeta().getDisplayName());
//            for (final Drop drop : this.plugin.getDropManager().getDropList()) {
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&6" + drop.getName()))) {
//                    if (user.isDisabled(drop)) {
//                        user.getDisabledDrops().remove(drop);
//                    }
//                    else {
//                        user.getDisabledDrops().add(drop);
//                    }
//                    event.setCancelled(true);
//                    this.plugin.getDropMenu().createInventory(player);
//                }
//            }
//            if (itemName.equalsIgnoreCase(ChatUtils.colored("&6MyChest"))) {
//                event.setCancelled(true);
//            }
//            if (itemName.equalsIgnoreCase(ChatUtils.colored("&eCobblestone"))) {
//                event.setCancelled(true);
//                user.setCobble(!user.isCobble());
//                this.plugin.getDropMenu().createInventory(player);
//            }
//            if (itemName.equalsIgnoreCase(ChatUtils.colored("&eWiadomosci"))) {
//                event.setCancelled(true);
//                user.setMessages(!user.isMessages());
//                this.plugin.getDropMenu().createInventory(player);
//            }
//            if (itemName.equalsIgnoreCase(ChatUtils.colored("&aWlacz wszystkie dropy"))) {
//                event.setCancelled(true);
//                user.getDisabledDrops().clear();
//                this.plugin.getDropMenu().createInventory(player);
//            }
//            if (itemName.equalsIgnoreCase(ChatUtils.colored("&cWylacz wszystkie dropy"))) {
//                event.setCancelled(true);
//                user.getDisabledDrops().addAll(this.plugin.getDropManager().getDropList());
//                this.plugin.getDropMenu().createInventory(player);
//            }
//            if (itemName.equalsIgnoreCase(ChatUtils.colored("&eDostepne eventy:"))) {
//                event.setCancelled(true);
//            }
//            if (itemName.equalsIgnoreCase(ChatUtils.colored("&8*"))) {
//                event.setCancelled(true);
//            }
//            if (itemName.equalsIgnoreCase(ChatUtils.colored("&6Menu"))) {
//                event.setCancelled(true);
//                player.openInventory(this.plugin.getMainMenu().getInventory());
//            }
//        }
//        if (inventory.getName().contains(ChatUtils.colored("&8[&6EZHC&8] &7- &6Uprawnienia gracza: &c"))) {
//            event.setCancelled(true);
//            final UserData userData = this.plugin.getUserManager().findUserByName(inventory.getName().replace(ChatUtils.colored("&8[&6MY&fHC&8] &7- &6Uprawnienia gracza: &c"), ""));
//            if (userData == null) {
//                return;
//            }
//            for (final GuildPermission permission : GuildPermission.values()) {
//                if (itemStack != null && itemStack.getType() == permission.getPermissionIcon()) {
//                    userData.togglePermission(permission);
//                    player.openInventory(this.plugin.getGuildPermMenu().createInventory(userData));
//                }
//            }
//        }
//        if (inventory.equals(this.plugin.getMainMenu().getInventory())) {
//            event.setCancelled(true);
//            if (itemStack != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().getDisplayName() != null) {
//                final String itemName = ChatUtils.colored(itemStack.getItemMeta().getDisplayName());
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&6Przydatne Craftingi"))) {
//                    player.openInventory(this.plugin.getCraftMenu().getMainInventory());
//                }
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&6Wymien bloki"))) {
//                    player.openInventory(this.plugin.getBlockChangeMenu().getInventory());
//                }
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&6Topki"))) {
//                    player.openInventory(this.plugin.getTopMenu().createInventory());
//                }
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&2&lCobbleX"))) {
//                    player.openInventory(this.plugin.getCobblexMenu().getInventory());
//                }
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&5&lPandora"))) {
//                    player.openInventory(this.plugin.getPandoreMenu().getInventory());
//                }
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&6&lMyChest"))) {
//                    player.openInventory(this.plugin.getCaseMenu().getInventory());
//                }
//            }
//        }
//        if (inventory.equals(this.plugin.getBlockChangeMenu().getInventory())) {
//            event.setCancelled(true);
//            if (itemStack != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().getDisplayName() != null) {
//                final String itemName = ChatUtils.colored(itemStack.getItemMeta().getDisplayName());
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&9Wymiana lapisu na bloki."))) {
//                    BlockReplacerUtils.replaceLapis(player, true);
//                }
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&fWymiana zelaza na bloki."))) {
//                    BlockReplacerUtils.replace(player, new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_BLOCK), true);
//                }
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&bWymiana diamentow na bloki."))) {
//                    BlockReplacerUtils.replace(player, new ItemStack(Material.DIAMOND), new ItemStack(Material.DIAMOND_BLOCK), true);
//                }
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&eWymiana zlota na bloki."))) {
//                    BlockReplacerUtils.replace(player, new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_BLOCK), true);
//                }
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&aWymiana emeraldow na bloki."))) {
//                    BlockReplacerUtils.replace(player, new ItemStack(Material.EMERALD), new ItemStack(Material.EMERALD_BLOCK), true);
//                }
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&2Wymien wszystkie przedmioty na bloki."))) {
//                    BlockReplacerUtils.replaceLapis(player, false);
//                    BlockReplacerUtils.replace(player, new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_BLOCK), false);
//                    BlockReplacerUtils.replace(player, new ItemStack(Material.DIAMOND), new ItemStack(Material.DIAMOND_BLOCK), false);
//                    BlockReplacerUtils.replace(player, new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_BLOCK), false);
//                    BlockReplacerUtils.replace(player, new ItemStack(Material.EMERALD), new ItemStack(Material.EMERALD_BLOCK), false);
//                    return;
//                }
//            }
//        }
        if (inventory.getTitle().equalsIgnoreCase(ChatUtils.colored("&9EFEKTY"))) {
            event.setCancelled(true);
            if (itemStack != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().getDisplayName() != null) {
                final String itemName = ChatUtils.colored(itemStack.getItemMeta().getDisplayName());
                if (itemName.equalsIgnoreCase(ChatUtils.colored("&e&lWidzenie w ciemnosci"))) {
                    if (!ItemUtils.hasItem(player, new ItemStack(Material.EMERALD_BLOCK, 5))) {
                        ChatUtils.sendMessage(player, "&4Blad: &cNie masz wymaganych przedmiotow!");
                        player.closeInventory();
                        return;
                    }
                    if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    }
                    ItemUtils.removeItem(player, new ItemStack(Material.EMERALD_BLOCK, 5));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 4800, 0));
                    ChatUtils.sendMessage(player, "&aZakupiono efekt: Widzenie w ciemnosci");
                    player.closeInventory();
                }
                if (itemName.equalsIgnoreCase(ChatUtils.colored("&e&lWysokie skakanie II"))) {
                    if (!ItemUtils.hasItem(player, new ItemStack(Material.EMERALD_BLOCK, 10))) {
                        ChatUtils.sendMessage(player, "&4Blad: &cNie masz wymaganych przedmiotow!");
                        player.closeInventory();
                        return;
                    }
                    if (player.hasPotionEffect(PotionEffectType.JUMP)) {
                        player.removePotionEffect(PotionEffectType.JUMP);
                    }
                    ItemUtils.removeItem(player, new ItemStack(Material.EMERALD_BLOCK, 10));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2400, 1));
                    ChatUtils.sendMessage(player, "&aZakupiono efekt: Wysokie skanie II");
                    player.closeInventory();
                }
                if (itemName.equalsIgnoreCase(ChatUtils.colored("&e&lSzybkosc I"))) {
                    if (!ItemUtils.hasItem(player, new ItemStack(Material.EMERALD_BLOCK, 15))) {
                        ChatUtils.sendMessage(player, "&4Blad: &cNie masz wymaganych przedmiotow!");
                        player.closeInventory();
                        return;
                    }
                    if (player.hasPotionEffect(PotionEffectType.SPEED)) {
                        player.removePotionEffect(PotionEffectType.SPEED);
                    }
                    ItemUtils.removeItem(player, new ItemStack(Material.EMERALD_BLOCK, 15));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2400, 0));
                    ChatUtils.sendMessage(player, "&aZakupiono efekt: Szybkosc I");
                    player.closeInventory();
                }
                if (itemName.equalsIgnoreCase(ChatUtils.colored("&e&lSzybkie kopanie II"))) {
                    if (!ItemUtils.hasItem(player, new ItemStack(Material.EMERALD_BLOCK, 10))) {
                        ChatUtils.sendMessage(player, "&4Blad: &cNie masz wymaganych przedmiotow!");
                        player.closeInventory();
                        return;
                    }
                    if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
                        player.removePotionEffect(PotionEffectType.FAST_DIGGING);
                    }
                    ItemUtils.removeItem(player, new ItemStack(Material.EMERALD_BLOCK, 10));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 2400, 1));
                    ChatUtils.sendMessage(player, "&aZakupiono efekt: Szybkie kopanie II");
                    player.closeInventory();
                }
            }
        }
//        if (inventory.equals(this.plugin.getCraftMenu().getMainInventory())) {
//            event.setCancelled(true);
//            if (itemStack != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().getDisplayName() != null) {
//                final String itemName = ChatUtils.colored(itemStack.getItemMeta().getDisplayName());
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&cAnty&6Nogi"))) {
//                    player.openInventory(this.plugin.getCraftMenu().getAntiNogiInventory());
//                }
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&7Generator &6Kamienia"))) {
//                    player.openInventory(this.plugin.getCraftMenu().getGeneratorInventory());
//                }
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&9Boy&3Farmer"))) {
//                    player.openInventory(this.plugin.getCraftMenu().getBoyfarmerInventory());
//                }
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&eSand&6Farmer"))) {
//                    player.openInventory(this.plugin.getCraftMenu().getSandfarmerInventory());
//                }
//                if (itemName.equalsIgnoreCase(ChatUtils.colored("&9Kopacz&6Fosy"))) {
//                    player.openInventory(this.plugin.getCraftMenu().getKopaczfarmerInventory());
//                }
//            }
//        }
//        if (inventory.getName().equalsIgnoreCase(ChatUtils.colored("&8[&6EZHC&8] &7- &6Admin Panel"))) {
//            if (itemStack != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().getDisplayName() != null) {
//                final String itemName = ChatUtils.colored(itemStack.getItemMeta().getDisplayName());
//                if (itemName.startsWith(ChatUtils.colored("&4TNT"))) {
//                    this.plugin.getSettingsConfiguration().setTnt(!this.plugin.getSettingsConfiguration().isTnt());
//                    player.openInventory(this.plugin.getAdminSettingsMenu().createInventory());
//                }
//                if (itemName.startsWith(ChatUtils.colored("&5Pandory"))) {
//                    this.plugin.getSettingsConfiguration().setPandore(!this.plugin.getSettingsConfiguration().isPandore());
//                    player.openInventory(this.plugin.getAdminSettingsMenu().createInventory());
//                }
//                if (itemName.startsWith(ChatUtils.colored("&6MyChest"))) {
//                    this.plugin.getSettingsConfiguration().setMychest(!this.plugin.getSettingsConfiguration().isMychest());
//                    player.openInventory(this.plugin.getAdminSettingsMenu().createInventory());
//                }
//                if (itemName.startsWith(ChatUtils.colored("&6Kity"))) {
//                    this.plugin.getSettingsConfiguration().setKits(!this.plugin.getSettingsConfiguration().isKits());
//                    player.openInventory(this.plugin.getAdminSettingsMenu().createInventory());
//                }
//                if (itemName.startsWith(ChatUtils.colored("&6Efekty"))) {
//                    this.plugin.getSettingsConfiguration().setEffects(!this.plugin.getSettingsConfiguration().isEffects());
//                    player.openInventory(this.plugin.getAdminSettingsMenu().createInventory());
//                }
//                if (itemName.startsWith(ChatUtils.colored("&6Enchanty"))) {
//                    this.plugin.getSettingsConfiguration().setEnchants(!this.plugin.getSettingsConfiguration().isEnchants());
//                    player.openInventory(this.plugin.getAdminSettingsMenu().createInventory());
//                }
//                this.plugin.getSettingsConfiguration().saveConfig();
//            }
//            event.setCancelled(true);
//        }
        if (inventory.getTitle().equalsIgnoreCase(ChatUtils.colored("&9WIADOMOSCI"))) {
            event.setCancelled(true);
            if (itemStack != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().getDisplayName() != null) {
                final String itemName = ChatUtils.colored(itemStack.getItemMeta().getDisplayName());
                if (itemName.equalsIgnoreCase(ChatUtils.colored("&aKliknij aby wlaczyc wiadomosci z MyChest"))) {
                    user.setChestMessages(true);
                    ChatUtils.sendMessage(player, "&aWiadomosci zostaly wlaczone!");
                    player.closeInventory();
                }
                else if (itemName.equalsIgnoreCase(ChatUtils.colored("&cKliknij aby wylaczyc wiadomosci z MyChest"))) {
                    user.setChestMessages(false);
                    ChatUtils.sendMessage(player, "&cWiadomosci zostaly wylaczone");
                    player.closeInventory();
                }
            }
        }
//        if (inventory.equals(this.plugin.getDiscoMenu().getInventory())) {
//            event.setCancelled(true);
//            if (event.getSlot() == 10) {
//                DiscoManager.getDisco().put(player.getName(), DiscoType.GRAY);
//                DiscoManager.getLastColor().put(player.getName(), Color.fromRGB(255, 0, 0));
//                ChatUtils.sendMessage(player, "&8\u25b8 &7Ustawion tryb: &aGray&7!");
//                player.closeInventory();
//            }
//            if (event.getSlot() == 12) {
//                DiscoManager.getDisco().put(player.getName(), DiscoType.RANDOM);
//                ChatUtils.sendMessage(player, "&8\u25b8 &7Ustawion tryb: &aRandom&7!");
//                player.closeInventory();
//            }
//            if (event.getSlot() == 14) {
//                DiscoManager.getDisco().put(player.getName(), DiscoType.SMOOTH);
//                DiscoManager.getLastColor().put(player.getName(), Color.fromRGB(255, 0, 0));
//                ChatUtils.sendMessage(player, "&8\u25b8 &7Ustawion tryb: &aSmooth&7!");
//                player.closeInventory();
//            }
//            if (event.getSlot() == 16) {
//                DiscoManager.getDisco().put(player.getName(), DiscoType.ULTRA);
//                ChatUtils.sendMessage(player, "&8\u25b8 &7Ustawion tryb: &aUltra&7!");
//                player.closeInventory();
//            }
//            if (event.getSlot() == 22) {
//                for (int i = 0; i < 4; ++i) {
//                    for (final Player pl : Bukkit.getOnlinePlayers()) {
//                        if (!pl.getName().equalsIgnoreCase(player.getName())) {
//                            DiscoUtils.sendEquipment(pl, player.getEntityId(), 1 + i, player.getInventory().getArmorContents()[i]);
//                        }
//                    }
//                }
//                DiscoManager.getDisco().remove(player.getName());
//                DiscoManager.getLastColor().remove(player.getName());
//                DiscoManager.getShiftArmor().remove(player.getName());
//                ChatUtils.sendMessage(player, "&8\u25b8 &7Wylaczono disco!");
//            }
//        }
        if (inventory.getTitle().equalsIgnoreCase(ChatUtils.colored("&9TOPKI"))) {
            event.setCancelled(true);
        }
        if (inventory.getTitle().equalsIgnoreCase(ChatUtils.colored("&9ItemShop"))) {
            event.setCancelled(true);
            if (itemStack != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().getDisplayName() != null) {
                final String itemName = ChatUtils.colored(itemStack.getItemMeta().getDisplayName());
                if (itemName.equalsIgnoreCase(ChatUtils.colored(this.plugin.getPluginConfiguration().getPandora_name()))) {
                    if (user.getPandora() == 0) {
                        player.closeInventory();
                        ChatUtils.sendMessage(player, "&4Blad: &cNie masz pandor w itemshopie!");
                        return;
                    }
                    final ItemStack pandora = this.plugin.getPandoreManager().getPandoreItem();
                    pandora.setAmount(user.getPandora());
                    player.getInventory().addItem(pandora);
                    player.closeInventory();
                    ChatUtils.sendMessage(player, "&eWyplaciles &6" + user.getPandora() + " &epandor.");
                    user.setPandora(0);
                    return;
                }
                else if (itemName.equalsIgnoreCase(ChatUtils.colored(this.plugin.getPluginConfiguration().getMychest_name()))) {
                    if (user.getMychest() == 0) {
                        player.closeInventory();
                        ChatUtils.sendMessage(player, "&4Blad: &cNie masz pandor w itemshopie!");
                        return;
                    }
                    final ItemStack pandora = this.plugin.getChestManager().getChestItem();
                    pandora.setAmount(user.getMychest());
                    player.getInventory().addItem(pandora);
                    player.closeInventory();
                    ChatUtils.sendMessage(player, "&eWyplaciles &6" + user.getMychest() + " &emychestow.");
                    user.setMychest(0);
                    return;
                }
            }
        }
        if (inventory.getTitle().equalsIgnoreCase(ChatUtils.colored("&9KITY"))) {
            event.setCancelled(true);
            if (itemStack == null) {
                return;
            }
            final Kit kit = this.plugin.getKitManager().getKit(itemStack);
            if (kit != null && player.hasPermission(kit.getPerm())) {
                if (user.canTake(kit)) {
                    this.plugin.getKitManager().giveKit(user, kit);
                    player.closeInventory();
                }
                else {
                    player.closeInventory();
                    ChatUtils.sendMessage(player, "&4Blad: &cTen kit mozesz odebrac za: " + DataUtils.durationToString(user.getKit(kit)));
                }
            }
        }
//        if (inventory.getName().equalsIgnoreCase(ChatUtils.colored("&8[&6EZHC&8] &7- &6SCHOWEK"))) {
//            event.setCancelled(true);
//            if (itemStack != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().getDisplayName() != null) {
//                final String name = itemStack.getItemMeta().getDisplayName();
//                if (name.equalsIgnoreCase(ChatUtils.colored("&6Koxy"))) {
//                    DepositUtils.takeWithdrawl(user, DepositType.NOTCH, true);
//                }
//                else if (name.equalsIgnoreCase(ChatUtils.colored("&6Perly"))) {
//                    DepositUtils.takeWithdrawl(user, DepositType.PEARL, true);
//                }
//                else if (name.equalsIgnoreCase(ChatUtils.colored("&6Refile"))) {
//                    DepositUtils.takeWithdrawl(user, DepositType.GOLDEN, true);
//                }
//                else if (name.equalsIgnoreCase(ChatUtils.colored("&6Strzaly"))) {
//                    DepositUtils.takeWithdrawl(user, DepositType.ARROW, true);
//                }
//                else if (name.equalsIgnoreCase(ChatUtils.colored("&6Dobierz limit"))) {
//                    DepositUtils.takeWithdrawl(user, DepositType.NOTCH, false);
//                    DepositUtils.takeWithdrawl(user, DepositType.GOLDEN, false);
//                    DepositUtils.takeWithdrawl(user, DepositType.PEARL, false);
//                    DepositUtils.takeWithdrawl(user, DepositType.ARROW, false);
//                }
//            }
//        }
//        if (inventory.getName().equalsIgnoreCase(ChatUtils.colored("&6Otwieranie..."))) {
//            event.setCancelled(true);
//        }
//        if (inventory.equals(this.plugin.getCobblexMenu().getInventory())) {
//            event.setCancelled(true);
//        }
        if (inventory.getTitle().equalsIgnoreCase(ChatUtils.colored("&9PANDORA"))) {
            event.setCancelled(true);
        }
//        if (inventory.equals(this.plugin.getCaseMenu().getInventory())) {
//            event.setCancelled(true);
//        }
//        if (inventory.equals(this.plugin.getCraftMenu().getAntiNogiInventory())) {
//            event.setCancelled(true);
//        }
//        if (inventory.equals(this.plugin.getCraftMenu().getGeneratorInventory())) {
//            event.setCancelled(true);
//        }
//        if (inventory.equals(this.plugin.getCraftMenu().getBoyfarmerInventory())) {
//            event.setCancelled(true);
//        }
//        if (inventory.equals(this.plugin.getCraftMenu().getSandfarmerInventory())) {
//            event.setCancelled(true);
//        }
//        if (inventory.equals(this.plugin.getCraftMenu().getKopaczfarmerInventory())) {
//            event.setCancelled(true);
//        }
//        if (inventory.equals(this.plugin.getCraftMenu().getEnderchestInventory())) {
//            event.setCancelled(true);
//        }
    }
    
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory().getType() != InventoryType.ANVIL) {
            return;
        }
        final ItemStack item = e.getCurrentItem();
        if (e.getRawSlot() == 2) {
            if (item == null || item.getEnchantments().size() == 0) {
                return;
            }
            if (item.containsEnchantment(Enchantment.FIRE_ASPECT) && item.getEnchantmentLevel(Enchantment.FIRE_ASPECT) > 1) {
                item.removeEnchantment(Enchantment.FIRE_ASPECT);
                item.addEnchantment(Enchantment.FIRE_ASPECT, 1);
                e.setCancelled(true);
            }
            if (item.containsEnchantment(Enchantment.ARROW_INFINITE)) {
                item.removeEnchantment(Enchantment.ARROW_INFINITE);
                e.setCancelled(true);
            }
        }
    }
}