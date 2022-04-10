// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.enums;

import java.util.Arrays;

public enum ShopType
{
    BUY("buy"), 
    SELL("sell");
    
    private final String name;
    
    private ShopType(final String name) {
        this.name = name;
    }
    
    public static ShopType get(final String name) {
        return Arrays.stream(values()).filter(shopType -> shopType.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    
    public String getName() {
        return this.name;
    }
}
