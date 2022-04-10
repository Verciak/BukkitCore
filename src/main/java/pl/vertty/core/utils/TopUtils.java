package pl.vertty.core.utils;

public final class TopUtils
{
    private TopUtils() {
    }
    
    public static String distanceToString(final int distance) {
        if (distance < 1000) {
            return distance + " m";
        }
        return distance / 1000.0 + " km";
    }
}
