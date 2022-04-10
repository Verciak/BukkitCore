// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.manager;

import org.bukkit.Location;
import pl.vertty.core.utils.LocationUtils;
import pl.vertty.core.data.config.SettingsConfiguration;

public class ButtonManager
{
    private final SettingsConfiguration settingsConfiguration;
    
    public ButtonManager(final SettingsConfiguration settingsConfiguration) {
        this.settingsConfiguration = settingsConfiguration;
    }
    
    public boolean isSoloButton(final Location location) {
        return this.settingsConfiguration.getRtp$solo().contains(LocationUtils.locToString(location));
    }
    
    public void removeSoloButton(final Location location) {
        this.settingsConfiguration.getRtp$solo().remove(LocationUtils.locToString(location));
    }
    
    public void addSoloButton(final Location location) {
        this.settingsConfiguration.getRtp$solo().add(LocationUtils.locToString(location));
    }
    
    public boolean isGroupButton(final Location location) {
        return this.settingsConfiguration.getRtp$group().contains(LocationUtils.locToString(location));
    }
    
    public void removeGroupButton(final Location location) {
        this.settingsConfiguration.getRtp$group().remove(LocationUtils.locToString(location));
    }
    
    public void addGroupButton(final Location location) {
        this.settingsConfiguration.getRtp$group().add(LocationUtils.locToString(location));
    }
}
