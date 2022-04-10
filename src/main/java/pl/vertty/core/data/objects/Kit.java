package pl.vertty.core.data.objects;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Kit
{
    private final String name;
    private final String guiName;
    private final String perm;
    private final List<ItemStack> items;
    private final ItemStack icon;
    private final int slot;
    private final int deley;
    
    public Kit(final String kitName, final String guiName, final String kitPerm, final List<ItemStack> kitItems, final ItemStack kitIcon, final int kitSlot, final int kitDeley) {
        this.name = kitName;
        this.guiName = guiName;
        this.perm = kitPerm;
        this.items = kitItems;
        this.icon = kitIcon;
        this.slot = kitSlot;
        this.deley = kitDeley;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getGuiName() {
        return this.guiName;
    }
    
    public String getPerm() {
        return this.perm;
    }
    
    public List<ItemStack> getItems() {
        return this.items;
    }
    
    public ItemStack getIcon() {
        return this.icon;
    }
    
    public int getSlot() {
        return this.slot;
    }
    
    public int getDeley() {
        return this.deley;
    }
}
