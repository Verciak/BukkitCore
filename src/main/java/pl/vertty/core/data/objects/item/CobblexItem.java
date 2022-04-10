// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.data.objects.item;

import org.bukkit.inventory.ItemStack;
import pl.vertty.api.self.Count;

public final class CobblexItem
{
    private final String name;
    private final Count count;
    private final ItemStack itemStack;
    
    public CobblexItem(final String name, final Count count, final ItemStack itemStack) {
        this.name = name;
        this.count = count;
        this.itemStack = itemStack;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Count getCount() {
        return this.count;
    }
    
    public ItemStack getItemStack() {
        return this.itemStack;
    }
}
