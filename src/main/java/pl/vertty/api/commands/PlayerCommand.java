package pl.vertty.api.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.utils.ChatUtils;

import java.util.List;

public abstract class PlayerCommand extends Command {
    public PlayerCommand(String name, String desc, String usage, String permission, List<String> aliases) {
        super(name, desc, usage, permission, aliases);
    }

    public boolean onExecute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player))
            return ChatUtils.sendMessage(sender, "&cTej komendy nie mozesz uzyc z poziomu konsoli!");
        return onCommand((Player)sender, args);
    }

    public abstract boolean onCommand(Player paramPlayer, String[] paramArrayOfString);
}
