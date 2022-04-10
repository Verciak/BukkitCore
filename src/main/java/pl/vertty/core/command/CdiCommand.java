// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.command.CommandSender;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.data.config.SettingsConfiguration;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;

import java.util.List;

public class CdiCommand extends Command
{
    private final SettingsConfiguration settingsConfiguration = CorePlugin.getPlugin().getSettingsConfiguration();


    public CdiCommand() {
        super("cdi", "", "/cdi", "mhCore.cdi", List.of(new String[0]));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        this.settingsConfiguration.setDiamond(!this.settingsConfiguration.isDiamond());
        ChatUtils.sendMessage(commandSender, "&6Kraftowanie diamentowych przedmiotow zostalo: " + (this.settingsConfiguration.isDiamond() ? "&awlaczone" : "&cwylaczone"));
        return false;
    }
}
