// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.comparators;

import java.util.ArrayList;
import java.util.List;
import pl.vertty.core.data.objects.user.UserData;
import java.util.Comparator;

public class MinedBlocksComparator
{
    private final Comparator<UserData> comparator;
    private final List<UserData> users;
    
    public MinedBlocksComparator() {
        this.users = new ArrayList<UserData>();
        this.comparator = ((rank1, rank) -> {
            int i = Double.compare(rank.getMinedStone(), rank1.getMinedStone());
            if (i == 0) {
                i = rank1.getLastName().compareTo(rank.getLastName());
            }
            return i;
        });
    }
    
    public void sortUsers() {
        this.users.sort(this.comparator);
    }
    
    public void addUser(final UserData user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
        }
        this.users.sort(this.comparator);
    }
    
    public void removeUser(final UserData u) {
        this.users.remove(u);
        this.users.sort(this.comparator);
    }
    
    public UserData getUser(final int i) {
        if (i - 1 < this.users.size()) {
            return this.users.get(i - 1);
        }
        return null;
    }
    
    public int getPosition(final UserData user) {
        for (int num = 0; num < this.users.size(); ++num) {
            if (this.users.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }
    
    public int getUser(final String name) {
        for (int i = 0; i < this.users.size(); ++i) {
            if (this.users.get(i).getLastName().equalsIgnoreCase(name)) {
                return i + 1;
            }
        }
        return 0;
    }
}
