package pl.vertty.core.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpMap;
import org.bukkit.help.HelpTopic;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.utils.ChatUtils;

public class PlayerCommandPreprocessListener implements Listener
{
    private final CorePlugin plugin;
    
    public PlayerCommandPreprocessListener(final CorePlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void onCmd(final PlayerCommandPreprocessEvent event) {
        final String command = event.getMessage().split(" ")[0];
        final Player player = event.getPlayer();
        HelpTopic helpMap = Bukkit.getHelpMap().getHelpTopic(command);
        if (this.plugin.getCombatManager().inCombat(player) && this.plugin.getPluginConfiguration().getAntilogout_commands().contains(command.toLowerCase())) {
            event.setCancelled(true);
            ChatUtils.sendMessage(player, "&4Blad: &cKomenda nieaktywna w tracie pvp!");
            return;
        }

        if (helpMap == null) {
            event.setCancelled(true);
            ChatUtils.sendMessage(player, "&4Blad: &cNie znaleziono takiej komendy!");
            return;
        }


        if (!player.hasPermission("Core.commands.management") && this.plugin.getPluginConfiguration().getBlocked_commands().contains(command.toLowerCase())) {
            event.setCancelled(true);
            ChatUtils.sendMessage(player, "&4Blad: &cNie masz uprawnien! &7(Core.commands.management)");
        }
    }
}
