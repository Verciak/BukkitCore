// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.manager;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.ItemBuilder;

public class ItemManager
{
    private static ItemManager itemManager;
    private final ItemStack generator;
    private final ItemStack boyFarmer;
    private final ItemStack sandFarmer;
    private final ItemStack kopaczFarmer;
    private final ItemStack antiNogi;
    private final ItemStack myCream;
    private final ItemStack fishingRod;
    
    public ItemManager() {
        ItemManager.itemManager = this;
        this.generator = new ItemBuilder(Material.END_STONE).setTitle(ChatUtils.colored("&7Generator &6Kamienia")).build();
        this.boyFarmer = new ItemBuilder(Material.END_PORTAL_FRAME).setTitle(ChatUtils.colored("&9Boy&3Farmer")).build();
        this.sandFarmer = new ItemBuilder(Material.SANDSTONE).setTitle(ChatUtils.colored("&eSand&6Farmer")).build();
        this.kopaczFarmer = new ItemBuilder(Material.BRICK).setTitle(ChatUtils.colored("&9Kopacz&6Fosy")).build();
        this.antiNogi = new ItemBuilder(Material.TRIPWIRE_HOOK).setTitle(ChatUtils.colored("&cAnty&6Nogi")).build();
        this.myCream = new ItemBuilder(Material.MAGMA_CREAM).setTitle(ChatUtils.colored("&cMyCream")).build();
        this.fishingRod = new ItemBuilder(Material.FISHING_ROD).setTitle(ChatUtils.colored("&bFreeze&6Rod")).addLore(ChatUtils.colored("&6Mozesz uzyc tylko &c3 razy!" )).setData((short) 61).build();
    }
    
    public static ItemManager getInstance() {
        return ItemManager.itemManager;
    }
    
    public ItemStack getGenerator() {
        return this.generator;
    }
    
    public ItemStack getBoyFarmer() {
        return this.boyFarmer;
    }
    
    public ItemStack getSandFarmer() {
        return this.sandFarmer;
    }
    
    public ItemStack getKopaczFarmer() {
        return this.kopaczFarmer;
    }
    
    public ItemStack getAntiNogi() {
        return this.antiNogi;
    }
    
    public ItemStack getMyCream() {
        return this.myCream;
    }

    public ItemStack getFishingRod() {
        return this.fishingRod;
    }
}
