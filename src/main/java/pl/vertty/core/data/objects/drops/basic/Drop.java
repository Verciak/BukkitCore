// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.data.objects.drops.basic;

import org.bukkit.inventory.ItemStack;
import pl.vertty.api.self.Count;
import pl.vertty.core.utils.ItemBuilder;

public final class Drop
{
    private final ItemBuilder Item;
    private final boolean fortune;
    private final String name;
    private final double chance;
    private final Count height;
    private final Count amount;
    private final int exp;
    private final int slot;
    
    public Drop(final String name, final boolean fortune, final ItemBuilder Item, final double chance, final Count height, final Count amount, final int exp, final int slot) {
        this.Item = Item;
        this.fortune = fortune;
        this.name = name;
        this.chance = chance;
        this.height = height;
        this.amount = amount;
        this.exp = exp;
        this.slot = slot;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isFortune() {
        return this.fortune;
    }
    
    public ItemBuilder getItem() {
        return this.Item;
    }
    
    public double getChance() {
        return this.chance;
    }
    
    public Count getHeight() {
        return this.height;
    }
    
    public Count getAmount() {
        return this.amount;
    }
    
    public int getExp() {
        return this.exp;
    }

    public int getSlot() {
        return this.slot;
    }

}
