// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.enums;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.vertty.core.utils.ChatUtils;

public enum GuildPermission
{
    BEACON(new ItemStack(Material.BEACON), "zarzadzania bekonem"),
    BLOCK_PLACE(new ItemStack(Material.GRASS), "stawiania klocow"),
    BLOCK_BREAK(new ItemStack(Material.STONE), "niszczenia klocow"),
    TNT_PLACEMENT(new ItemStack(Material.TNT), "stawiania tnt"),
    FLUID_PLACEMENT(new ItemStack(Material.BUCKET), "rozlewanie wody/lavy"),
    TELEPORTATION_USE(new ItemStack(Material.NETHER_STAR), "uzywanie /tpaccept na terenie gildii"),
    MEMBER_INVITE(new ItemStack(Material.PAPER), "zapraszanie czlonkow"),
    MEMBER_KICK(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE), "wyrzucanie czlonkow"),
    GUILD_PROLONG(new ItemStack(Material.BONE), "przedluzanie dlugosci gildii");
    
    private String message;
    private final ItemStack permissionIcon;
    private final String permissionName;
    
    private GuildPermission(final ItemStack permissionIcon, final String permissionName) {
        this.permissionIcon = permissionIcon;
        this.permissionName = permissionName;
    }
    
    public static GuildPermission findByName(final String str) {
        for (final GuildPermission permission : values()) {
            if (permission.name().equalsIgnoreCase(str)) {
                return permission;
            }
        }
        return null;
    }
    
    public ItemStack getPermissionIcon() {
        return this.permissionIcon;
    }
    
    public String getPermissionName() {
        return this.permissionName;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = ChatUtils.colored(message);
    }
}
