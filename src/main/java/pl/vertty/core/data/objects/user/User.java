// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.data.objects.user;

import java.util.UUID;

public interface User
{
    UUID getIdentifier();
    
    String getLastName();
    
    void setLastName(final String p0);
}
