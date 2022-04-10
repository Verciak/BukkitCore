package pl.vertty.core.utils.conventer;


import com.google.common.collect.Maps;
import org.bukkit.Location;

import java.util.Map;

public final class HomeConventer
{
    private HomeConventer() {
    }
    
    public static String serialize(final Map<String, Location> map) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final Map.Entry<String, Location> entry : map.entrySet()) {
            stringBuilder.append(entry.getKey()).append("=").append(LocationConventer.serialize(entry.getValue())).append("@");
        }
        return stringBuilder.toString();
    }
    
    public static Map<String, Location> deserialize(final String serializedData) {
        final Map<String, Location> map = Maps.newLinkedHashMap();
        if (serializedData.isEmpty() || serializedData.equalsIgnoreCase("")) {
            return map;
        }
        final String[] split2;
        final String[] split = split2 = serializedData.split("@");
        for (final String string : split2) {
            final String[] mapSplit = string.split("=");
            if (mapSplit.length >= 1) {
                map.put(mapSplit[0], LocationConventer.deserialize(mapSplit[1]));
            }
        }
        return map;
    }
}
