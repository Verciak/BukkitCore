package pl.vertty.core.data.objects.user;



import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Combat
{
    private final UUID identifier;
    private int leftTime;
    
    public Combat(final UUID identifier, final int time) {
        this.identifier = identifier;
        this.leftTime = time;
    }
    
    public UUID getIdentifier() {
        return this.identifier;
    }
    
    public int getLeftTime() {
        return this.leftTime;
    }
    
    public void setLeftTime(final int leftTime) {
        this.leftTime = leftTime;
    }
    
    public Player getPlayer() {
        return Bukkit.getPlayer(this.identifier);
    }
}
