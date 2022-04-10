
package pl.vertty.core.manager;

import com.google.common.collect.Sets;
import org.bukkit.entity.Player;
import pl.vertty.core.data.objects.user.Combat;
import java.util.Set;

public class CombatManager
{
    private final Set<Combat> combatSet;
    
    public CombatManager() {
        this.combatSet = Sets.newLinkedHashSet();
    }
    
    public void createCombat(final Player player) {
        final Combat combat = this.getCombat(player);
        if (combat != null) {
            this.combatSet.remove(combat);
        }
        this.combatSet.add(new Combat(player.getUniqueId(), 20));
    }
    
    public boolean inCombat(final Player player) {
        final Combat combat = this.getCombat(player);
        if (combat == null) {
            return false;
        }
        if (combat.getLeftTime() > 0) {
            return true;
        }
        this.removeCombat(combat);
        return false;
    }
    
    public void removeCombat(final Combat combat) {
        if (this.getCombat(combat.getPlayer()) != null) {
            this.combatSet.remove(combat);
        }
    }
    
    public Combat getCombat(final Player player) {
        return this.combatSet.stream().filter(combat -> combat.getIdentifier().equals(player.getUniqueId())).findFirst().orElse(null);
    }
    
    public Set<Combat> getCombatSet() {
        return this.combatSet;
    }
}
