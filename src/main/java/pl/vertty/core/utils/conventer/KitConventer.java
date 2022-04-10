
package pl.vertty.core.utils.conventer;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;

public final class KitConventer
{
    private KitConventer() {
    }
    
    public static String serialize(final Map<String, Long> map) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final Map.Entry<String, Long> kits : map.entrySet()) {
            stringBuilder.append(kits.getKey()).append("=").append(kits.getValue()).append("@");
        }
        return stringBuilder.toString();
    }
    
    public static Map deserialize(final String serializedData) {
        final Map<String, Long> map = Maps.newLinkedHashMap();
        if (serializedData.isEmpty() || serializedData.equalsIgnoreCase("")) {
            return map;
        }
        final String[] split2;
        final String[] split = split2 = serializedData.split("@");
        for (final String string : split2) {
            final String[] mapSplit = string.split("=");
            if (mapSplit.length >= 1) {
                map.put(mapSplit[0], Long.parseLong(mapSplit[1]));
            }
        }
        return map;
    }
}
