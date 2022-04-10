// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.data.objects.user;

public interface StatisticsUser extends DepositUser
{
    boolean isGodMode();
    
    void setGodMode(final boolean p0);
    
    double getSpendMoney();
    
    void setSpendMoney(final double p0);
    
    void addSpendMoney(final double p0);
    
    int getEatNotch();
    
    void setEatNotch(final int p0);
    
    void addEatNotch(final int p0);
    
    int getMinedStone();
    
    void setMinedStone(final int p0);
    
    void addMinedStone(final int p0);
    
    int getKills();
    
    void setKills(final int p0);
    
    void addKill(final int p0);
    
    double getDistance();
    
    void setDistance(final double p0);
    
    void addDistance(final double p0);
    
    int getDeaths();
    
    void setDeaths(final int p0);
    
    void addDeath(final int p0);
    
    int getPlacedBlocks();
    
    void setPlacedBlocks(final int p0);
    
    void addPlacedBlock(final int p0);
    
    double getCoins();
    
    void setCoins(final double p0);
    
    void addCoins(final double p0);
    
    int getMychest();
    
    void setMychest(final int p0);
    
    void addMychest(final int p0);
    
    int getPandora();
    
    void setPandora(final int p0);
    
    void addPandora(final int p0);
}
