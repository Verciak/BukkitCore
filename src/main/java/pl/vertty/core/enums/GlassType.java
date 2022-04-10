// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.enums;


import org.bukkit.Material;
import pl.vertty.core.utils.ItemBuilder;

public enum GlassType
{
    WHITE(new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE, 1)),
    ORANGE(new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE, 1)),
    MAGENTA(new ItemBuilder(Material.MAGENTA_STAINED_GLASS_PANE, 1)),
    LIGHT_BLUE(new ItemBuilder(Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1)),
    YELLOW(new ItemBuilder(Material.YELLOW_STAINED_GLASS_PANE,1)),
    LIME(new ItemBuilder(Material.LIME_STAINED_GLASS_PANE,1)),
    PINK(new ItemBuilder(Material.PINK_STAINED_GLASS_PANE,1)),
    GRAY(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE,1)),
    LIGHT_GRAY(new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE,1)),
    CYAN(new ItemBuilder(Material.CYAN_STAINED_GLASS_PANE,1)),
    PURPLE(new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE,1)),
    BLUE(new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE,1)),
    BROWN(new ItemBuilder(Material.BROWN_STAINED_GLASS_PANE,1)),
    GREEN(new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE,1)),
    RED(new ItemBuilder(Material.RED_STAINED_GLASS_PANE,1)),
    BLACK(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE,1));
    
    private final ItemBuilder itemStack;
    
    private GlassType(final ItemBuilder itemStack) {
        this.itemStack = itemStack;
    }
    
    public ItemBuilder getItemStack() {
        return this.itemStack;
    }
}
