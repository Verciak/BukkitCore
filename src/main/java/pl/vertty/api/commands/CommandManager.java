package pl.vertty.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.SimplePluginManager;
import pl.vertty.core.listener.others.UnknownCommandListener;
import pl.vertty.core.utils.Reflection;

import java.util.Arrays;
import java.util.HashMap;

public class CommandManager {

    public static final HashMap<String, Command> commands;
    private static final Reflection.FieldAccessor<SimpleCommandMap> f;
    private static CommandMap cmdMap;

    public static void register(Command cmd) {
        if (cmdMap == null)
            cmdMap = f.get(Bukkit.getPluginManager());
        cmdMap.register(cmd.getName(), cmd);
        commands.put(cmd.getName(), cmd);
        UnknownCommandListener.registeredCommands.add(cmd.getName());
        if (cmd.getAliases() != null) {
            UnknownCommandListener.registeredCommands.addAll(cmd.getAliases());
        }
    }

    static {
        commands = new HashMap<String, Command>();
        f = Reflection.getField(SimplePluginManager.class, "commandMap", SimpleCommandMap.class);
        CommandManager.cmdMap = (CommandMap)CommandManager.f.get(Bukkit.getServer().getPluginManager());
    }
}

