package pl.vertty.core.listener.others;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.vertty.core.utils.ChatUtils;

import java.util.ArrayList;

public class UnknownCommandListener implements Listener {
    @EventHandler
    private void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        boolean valid = false;
        if (p.hasPermission("ezhc.admin.unknowncmd"))
            return;
        for (String cmd : registeredCommands) {
            String[] args = e.getMessage().split(" ");
            if (args[0].equalsIgnoreCase("/" + cmd) || args[0].equalsIgnoreCase("/:" + cmd)) {
                valid = true;
                break;
            }
            if (args[0].equalsIgnoreCase("/register") || args[0].equalsIgnoreCase("/reg") || args[0].equalsIgnoreCase("/login") || args[0].equalsIgnoreCase("/l") || args[0].equalsIgnoreCase("/changepassword") || args[0].equalsIgnoreCase("/changepasswd") || args[0].equalsIgnoreCase("/changepass") || args[0].equalsIgnoreCase("/zmienhaslo") || args[0].equalsIgnoreCase("/haslo") || args[0].equalsIgnoreCase("/auth")) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            p.sendMessage(ChatUtils.colored("&cNie odnaleziono takiej komendy na serwerze &6/pomoc"));
            e.setCancelled(true);
        }
    }

    public static ArrayList<String> registeredCommands = new ArrayList<>();
}

