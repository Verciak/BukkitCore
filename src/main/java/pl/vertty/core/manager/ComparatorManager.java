// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.manager;

import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.comparators.KillsComparator;
import pl.vertty.core.comparators.DeathsComparator;
import pl.vertty.core.comparators.EatNotchComparator;
import pl.vertty.core.comparators.DistanceComparator;
import pl.vertty.core.comparators.SpendMoneyComparator;
import pl.vertty.core.comparators.MinedBlocksComparator;
import pl.vertty.core.comparators.PlacedBlocksComparator;

public class ComparatorManager
{
    private static PlacedBlocksComparator placedBlocksComparator;
    private static MinedBlocksComparator minedBlocksComparator;
    private static SpendMoneyComparator spendMoneyComparator;
    private static DistanceComparator distanceComparator;
    private static EatNotchComparator eatNotchComparator;
    private static DeathsComparator deathsComparator;
    private static KillsComparator killsComparator;
    
    public ComparatorManager() {
        ComparatorManager.placedBlocksComparator = new PlacedBlocksComparator();
        ComparatorManager.minedBlocksComparator = new MinedBlocksComparator();
        ComparatorManager.spendMoneyComparator = new SpendMoneyComparator();
        ComparatorManager.distanceComparator = new DistanceComparator();
        ComparatorManager.eatNotchComparator = new EatNotchComparator();
        ComparatorManager.deathsComparator = new DeathsComparator();
        ComparatorManager.killsComparator = new KillsComparator();
    }
    
    public static void addUserToTop(final UserData user) {
        ComparatorManager.placedBlocksComparator.addUser(user);
        ComparatorManager.minedBlocksComparator.addUser(user);
        ComparatorManager.spendMoneyComparator.addUser(user);
        ComparatorManager.distanceComparator.addUser(user);
        ComparatorManager.eatNotchComparator.addUser(user);
        ComparatorManager.deathsComparator.addUser(user);
        ComparatorManager.killsComparator.addUser(user);
    }
    
    public static void sortUsers() {
        ComparatorManager.placedBlocksComparator.sortUsers();
        ComparatorManager.minedBlocksComparator.sortUsers();
        ComparatorManager.spendMoneyComparator.sortUsers();
        ComparatorManager.distanceComparator.sortUsers();
        ComparatorManager.eatNotchComparator.sortUsers();
        ComparatorManager.deathsComparator.sortUsers();
        ComparatorManager.killsComparator.sortUsers();
    }
    
    public PlacedBlocksComparator getPlacedBlocksComparator() {
        return ComparatorManager.placedBlocksComparator;
    }
    
    public MinedBlocksComparator getMinedBlocksComparator() {
        return ComparatorManager.minedBlocksComparator;
    }
    
    public SpendMoneyComparator getSpendMoneyComparator() {
        return ComparatorManager.spendMoneyComparator;
    }
    
    public DistanceComparator getDistanceComparator() {
        return ComparatorManager.distanceComparator;
    }
    
    public EatNotchComparator getEatNotchComparator() {
        return ComparatorManager.eatNotchComparator;
    }
    
    public DeathsComparator getDeathsComparator() {
        return ComparatorManager.deathsComparator;
    }
    
    public KillsComparator getKillsComparator() {
        return ComparatorManager.killsComparator;
    }
}
