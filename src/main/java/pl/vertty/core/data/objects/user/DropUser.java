// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.data.objects.user;

import pl.vertty.core.data.objects.drops.basic.Drop;

import java.util.List;

public interface DropUser extends User
{
    List<Drop> getDisabledDrops();
    
    boolean isDisabled(final Drop p0);
    
    boolean isCobble();
    
    void setCobble(final boolean p0);
    
    boolean isMessages();
    
    void setMessages(final boolean p0);
}
