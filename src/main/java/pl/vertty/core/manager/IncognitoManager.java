package pl.vertty.core.manager;

import org.bukkit.entity.Player;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.IncognitoUtils;
import pl.vertty.core.data.objects.user.UserData;
import java.util.Optional;
import pl.vertty.core.CorePlugin;

public class IncognitoManager
{
    private final CorePlugin plugin;
    
    public IncognitoManager(final CorePlugin plugin) {
        this.plugin = plugin;
    }
    
    public void toggleIncognito(final Player player) {
        final Optional<UserData> user = Optional.ofNullable(this.plugin.getUserManager().getUser(player));
        if (user.isPresent()) {
            if (user.get().isIncognito()) {
                /*
                final User guildUser = User.get(player);
                user.get().toggleIncognito();
                final IndividualPrefix prefix = new IndividualPrefix(guildUser);
                prefix.initialize();
                guildUser.getCache().setIndividualPrefix(prefix);
                 */
                ChatUtils.sendMessage(player, "&cIncognito zostalo wylaczone!");
            }
            else {
                user.get().toggleIncognito();
                IncognitoUtils.applyName(player);
                ChatUtils.sendMessage(player, "&aIncognito zostalo wlaczone!");
            }
        }
    }
}
