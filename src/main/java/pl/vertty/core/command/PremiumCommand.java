// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.command.CommandSender;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.config.PluginConfiguration;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;

import java.util.List;

public class PremiumCommand extends Command
{
    private final PluginConfiguration pluginConfiguration = CorePlugin.getPlugin().getPluginConfiguration();

    public PremiumCommand() {
        super("premium", "", "/premium", "mhCore", List.of(new String[]{"premiumu"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        this.pluginConfiguration.getCommand_premium().forEach(message -> ChatUtils.sendMessage(commandSender, message));
        return false;
    }
}
