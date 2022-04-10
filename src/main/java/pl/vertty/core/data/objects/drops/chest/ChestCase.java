// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.data.objects.drops.chest;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.vertty.core.utils.RandomUtils;

public final class ChestCase
{
    private final Inventory inventory;
    private final Player player;
    private final int maxRepeat;
    private int repeat;
    
    public ChestCase(final Player player, final Inventory inventory) {
        this.maxRepeat = RandomUtils.getRandInt(15, 25);
        this.player = player;
        this.inventory = inventory;
        this.repeat = 0;
    }
    
    public int getRepeat() {
        return this.repeat;
    }
    
    public void setRepeat(final int repeat) {
        this.repeat = repeat;
    }
    
    public int getMaxRepeat() {
        return this.maxRepeat;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public Inventory getInventory() {
        return this.inventory;
    }
}
