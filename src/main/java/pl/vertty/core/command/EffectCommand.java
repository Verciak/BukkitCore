// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.vertty.core.CorePlugin;

import pl.vertty.core.data.config.SettingsConfiguration;
import pl.vertty.api.commands.Command;
import pl.vertty.core.menu.EffectMenu;
import pl.vertty.core.utils.ChatUtils;

import java.util.List;

public class EffectCommand extends Command
{
    private final SettingsConfiguration settingsConfiguration = CorePlugin.getPlugin().getSettingsConfiguration();

    public EffectCommand() {
        super("efekty", "", "/efekty", "mhCore", List.of(new String[]{"efekt"}));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (!this.settingsConfiguration.isEffects()) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cEfekty sa tymczasowo wylaczone!");
            return false;
        }
       EffectMenu.EffectMenu((Player) commandSender);
        return false;
    }
}
