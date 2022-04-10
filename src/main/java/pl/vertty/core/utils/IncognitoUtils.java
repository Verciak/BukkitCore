package pl.vertty.core.utils;


import net.dzikoysk.funnyguilds.user.User;
import net.dzikoysk.funnyguilds.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import pl.vertty.core.data.objects.user.UserData;
import pl.vertty.core.CorePlugin;

public final class IncognitoUtils
{
    public static void join() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            final UserData user = CorePlugin.getPlugin().getUserManager().getUser(player);
            if (user.isIncognito()) {
                applyName(player);
            }
            else {

//                final User guildUser = UserManager.ge(player);
//                final IndividualPrefix prefix = new IndividualPrefix(guildUser);
//                prefix.initialize();
//                guildUser.getCache().setIndividualPrefix(prefix);

            }
        }
    }
    
    public static void applyName(final Player player) {

        final Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam("incognito");
        if (team == null) {
            team = scoreboard.registerNewTeam("incognito");
        }
        team.setPrefix(ChatUtils.colored("&k"));
        team.addPlayer((OfflinePlayer)player);
        player.setDisplayName(ChatUtils.colored("&k" + player.getName()));
        Bukkit.getOnlinePlayers().forEach(online -> online.setScoreboard(scoreboard));
    }
}
