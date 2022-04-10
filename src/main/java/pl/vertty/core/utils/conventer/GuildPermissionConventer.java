// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.utils.conventer;

import com.google.common.collect.Maps;
import pl.vertty.core.enums.GuildPermission;
import java.util.Map;

public final class GuildPermissionConventer
{
    private GuildPermissionConventer() {
    }
    
    public static String serialize(final Map<GuildPermission, Boolean> map) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final Map.Entry<GuildPermission, Boolean> permission : map.entrySet()) {
            stringBuilder.append(permission.getKey()).append("=").append(permission.getValue()).append("@");
        }
        return stringBuilder.toString();
    }
    
    public static Map deserialize(final String serializedData) {
        final Map<GuildPermission, Boolean> map = Maps.newLinkedHashMap();
        if (serializedData.isEmpty() || serializedData.equalsIgnoreCase("")) {
            return map;
        }
        final String[] split2;
        final String[] split = split2 = serializedData.split("@");
        for (final String string : split2) {
            final String[] mapSplit = string.split("=");
            if (mapSplit.length >= 1) {
                map.put(GuildPermission.findByName(mapSplit[0]), Boolean.parseBoolean(mapSplit[1]));
            }
        }
        return map;
    }
}
