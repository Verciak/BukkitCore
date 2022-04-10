package pl.vertty.core.listener.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.data.objects.drops.chest.ChestCase;
import pl.vertty.core.utils.BoyUtils;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.manager.UserManager;
import pl.vertty.core.manager.ChestManager;
import pl.vertty.core.manager.PandoreManager;
import pl.vertty.core.manager.CobblexManager;
import pl.vertty.core.utils.ChatUtils;

public class BlockPlaceListener implements Listener
{
//    private final CobblexManager cobblexManager;
    private final PandoreManager pandoreManager;
    private final ChestManager chestManager;
    private final UserManager userManager;
//    private final CreateCaseMenu createCaseMenu;
    private final CorePlugin plugin;
    
    public BlockPlaceListener(final CorePlugin plugin) {
//        this.cobblexManager = plugin.getCobblexManager();
        this.pandoreManager = plugin.getPandoreManager();
        this.chestManager = plugin.getChestManager();
        this.userManager = plugin.getUserManager();
//        this.createCaseMenu = plugin.getCreateCaseMenu();
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlace(final BlockPlaceEvent event) {
        if (event.isCancelled()) {
            return;
        }
        this.userManager.getUser(event.getPlayer()).addPlacedBlock(1);
        final Player player = event.getPlayer();
        if (event.getBlock().getType() == Material.TNT) {
            if (!this.plugin.getSettingsConfiguration().isTnt()) {
                event.setCancelled(true);
                ChatUtils.sendMessage(player, "&4Blad: &cTNT jest tymczasowo wylaczone!");
            }
        }
        else if (player.getInventory().getItemInHand() == this.plugin.getItemManager().getGenerator()) {
            final Block block = event.getBlock().getLocation().add(0.0, 1.0, 0.0).getBlock();
            block.setType(Material.STONE);
        }
        else if (player.getInventory().getItemInHand() == this.plugin.getItemManager().getBoyFarmer()) {
            if (event.getBlock().getLocation().getY() > 80.0) {
                event.setCancelled(true);
                ChatUtils.sendMessage(player, "&4Blad: &cFarmery mozna stawiac ponizej 80 kratki!");
                return;
            }
            event.getBlock().setType(Material.OBSIDIAN);
            BoyUtils.farmer(event.getBlock().getLocation());
        }
        else if (player.getInventory().getItemInHand() == this.plugin.getItemManager().getSandFarmer()) {
            if (event.getBlock().getLocation().getY() > 80.0) {
                event.setCancelled(true);
                ChatUtils.sendMessage(player, "&4Blad: &cFarmery mozna stawiac ponizej 80 kratki!");
                return;
            }
            event.getBlock().setType(Material.SAND);
            BoyUtils.farmer(event.getBlock().getLocation());
        }
        else if (player.getInventory().getItemInHand() == this.plugin.getItemManager().getKopaczFarmer()) {
            if (event.getBlock().getLocation().getY() > 80.0) {
                event.setCancelled(true);
                ChatUtils.sendMessage(player, "&4Blad: &cFarmery mozna stawiac ponizej 80 kratki!");
                return;
            }
            event.getBlock().setType(Material.AIR);
            BoyUtils.farmer_kopacz(event.getBlock().getLocation());
        }
//        else if (player.getInventory().getItemInHand() == this.cobblexManager.getCobblexItem()) {
//            this.cobblexManager.giveItem(event);
//        }
        else if (player.getInventory().getItemInHand() == this.pandoreManager.getPandoreItem()) {
            if (!this.plugin.getSettingsConfiguration().isPandore()) {
                event.setCancelled(true);
                ChatUtils.sendMessage(player, "&4Blad: &cPandory sa tymczasowo wylaczone!");
                return;
            }
            this.pandoreManager.giveItem(event);
        }
//        else if (player.getInventory().getItemInHand().getId() == Item.ENDER_CHEST && player.getInventory().getItemInHand().hasCustomName() && player.getInventory().getItemInHand().getCustomName().contains(ChatUtils.colored(this.plugin.getPluginConfiguration().getMychest_name()))) {
//            if (!this.plugin.getSettingsConfiguration().isMychest()) {
//                event.setCancelled(true);
//                ChatUtils.sendMessage(player, "&4Blad: &cMychesty sa tymczasowo wylaczone!");
//                return;
//            }
//            this.chestManager.openChest(player, new ChestCase(player, CreateCaseMenu.inventoryMenu));
//            event.setCancelled(true);
//        }
    }
}
