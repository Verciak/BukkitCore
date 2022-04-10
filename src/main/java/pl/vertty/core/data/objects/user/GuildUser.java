// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.data.objects.user;

import java.util.Map;
import pl.vertty.core.enums.GuildPermission;

public interface GuildUser extends KitUser
{
    void resetPermissions();
    
    void togglePermission(final GuildPermission p0);
    
    boolean hasPermission(final GuildPermission p0);
    
    Map<GuildPermission, Boolean> getPermissions();
}
