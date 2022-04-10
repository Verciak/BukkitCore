package pl.vertty.core.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import pl.vertty.core.data.base.Database;
import com.google.common.collect.Maps;
import pl.vertty.core.data.objects.user.UserData;

import java.util.UUID;
import java.util.Map;

public class UserManager
{
    private final Map<UUID, UserData> userMap;
    
    public UserManager() {
        this.userMap = Maps.newHashMap();
    }
    
    public UserData getUser(final Player player) {
        UserData user = this.userMap.get(player.getUniqueId());
        if (user == null) {
            this.userMap.put(player.getUniqueId(), user = new UserData(player));
            Database.getInstance().saveUser(user);
        }
        return user;
    }
    
    public UserData findUserByName(final String name) {
        return this.userMap.values().stream().filter(user -> user.getLastName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    
    public void save(final Player player) {
        final UserData user = this.getUser(player);
        user.setLastName(player.getName());
        Database.getInstance().saveUser(user);
    }
    
    public Map<UUID, UserData> getUserMap() {
        return this.userMap;
    }
    
    public List<UserData> getUserList() {
        return new ArrayList<UserData>(this.userMap.values());
    }
}
