package pl.vertty.api.commands;


import org.bukkit.command.CommandSender;
import pl.vertty.core.utils.ChatUtils;

import java.util.List;

public abstract class Command extends org.bukkit.command.Command {
    private final String name;

    private final String usage;

    private final String desc;

    private final String permission;

    public Command(String name, String desc, String usage, String permission, List<String> aliases) {
        super(name, desc, usage, aliases);
        this.name = name;
        this.usage = usage;
        this.desc = desc;
        this.permission = permission;
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission(this.permission)) {
            String msg = "&cNie masz dostepu do tej komendy! &7({PERM})";
            msg = msg.replace("{PERM}", getPermission());
            return ChatUtils.sendMessage(sender, msg);
        }
        return onExecute(sender, args);
    }

    public abstract boolean onExecute(CommandSender paramCommandSender, String[] paramArrayOfString);

    public String getName() {
        return this.name;
    }

    public String getUsage() {
        return this.usage;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getPermission() {
        return this.permission;
    }
}
