package pl.vertty.core.data.objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.vertty.core.enums.TimeEnum;

public class Teleport
{
    private Location location;
    private Player player;
    private Long longTime;
    private Integer time;
    
    public Teleport(final Player player, final Location location, final int time) {
        this.player = player;
        this.location = location;
        this.longTime = System.currentTimeMillis() + TimeEnum.SECOND.getTime(time);
        this.time = time;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(final Location location) {
        this.location = location;
    }
    
    public Long getLongTime() {
        return this.longTime;
    }
    
    public void setLongTime(final Long longTime) {
        this.longTime = longTime;
    }
    
    public Integer getTime() {
        return this.time;
    }
    
    public void setTime(final Integer time) {
        this.time = time;
    }
    
    public void setPlayer(final Player player) {
        this.player = player;
    }
    
    public Player getPlayer() {
        return this.player;
    }
}
