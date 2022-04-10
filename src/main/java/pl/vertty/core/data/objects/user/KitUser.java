// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.data.objects.user;

import java.util.Map;
import pl.vertty.core.data.objects.Kit;

public interface KitUser extends StatisticsUser
{
    void addKit(final Kit p0, final long p1);
    
    long getKit(final Kit p0);
    
    boolean canTake(final Kit p0);
    
    Map<String, Long> getKitsMap();
}
