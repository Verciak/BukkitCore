package pl.vertty.core.utils;

import java.util.Random;

public final class RandomUtils
{
    public static final Random RANDOM_INSTANCE;
    
    private RandomUtils() {
    }
    
    public static boolean getChance(final double chance) {
        return chance >= 100.0 || chance >= getRandDouble(0.0, 100.0);
    }
    
    public static Double getRandDouble(final double min, final double max) {
        return RandomUtils.RANDOM_INSTANCE.nextDouble() * (max - min) + min;
    }
    
    public static int getRandInt(final int min, final int max) {
        return RandomUtils.RANDOM_INSTANCE.nextInt(max - min + 1) + min;
    }
    
    public static Double getRandomDouble(final double min, final double max) {
        return RandomUtils.RANDOM_INSTANCE.nextDouble() * (max - min) + min;
    }
    
    static {
        RANDOM_INSTANCE = new Random();
    }
}
