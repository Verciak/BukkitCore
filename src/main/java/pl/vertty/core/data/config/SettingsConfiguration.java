// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.data.config;

import java.io.IOException;

import org.bukkit.configuration.Configuration;
import pl.vertty.core.CorePlugin;
import java.util.List;
import java.io.File;

public class SettingsConfiguration
{
    private long turbodrop$time;
    private long turboexp$time;
    private long mychest$time;
    private boolean tnt;
    private boolean pandore;
    private boolean kits;
    private boolean effects;
    private boolean mychest;
    private boolean diamond;
    private boolean enchants;
    private final List<String> rtp$solo;
    private final List<String> rtp$group;
    
    public SettingsConfiguration(final CorePlugin plugin) {
        Configuration config = plugin.getConfig();
        this.turbodrop$time = config.getLong("turbodrop");
        this.turboexp$time = config.getLong("turboexp");
        this.mychest$time = config.getLong("mychestt");
        this.tnt = config.getBoolean("server.tnt");
        this.pandore = config.getBoolean("server.pandore");
        this.kits = config.getBoolean("server.kits");
        this.effects = config.getBoolean("server.effects");
        this.mychest = config.getBoolean("server.mychest");
        this.diamond = config.getBoolean("server.diamond");
        this.enchants = config.getBoolean("server.enchants");
        this.rtp$solo = (List<String>)config.getStringList("randomtp.solo");
        this.rtp$group = (List<String>)config.getStringList("randomtp.group");
    }
    
    public boolean isTurboDrop() {
        return this.getTurbodrop$time() > System.currentTimeMillis();
    }
    
    public long getTurbodrop$time() {
        return this.turbodrop$time;
    }
    
    public void setTurbodrop$time(final long turbodrop$time) {
        this.turbodrop$time = turbodrop$time;
    }
    
    public boolean isTurboExp() {
        return this.getTurboexp$time() > System.currentTimeMillis();
    }
    
    public long getTurboexp$time() {
        return this.turboexp$time;
    }
    
    public void setTurboexp$time(final long turboexp$time) {
        this.turboexp$time = turboexp$time;
    }
    
    public boolean isTurboMychest() {
        return this.getMychest$time() > System.currentTimeMillis();
    }
    
    public long getMychest$time() {
        return this.mychest$time;
    }
    
    public void setMychest$time(final long mychest$time) {
        this.mychest$time = mychest$time;
    }
    
    public boolean isTnt() {
        return this.tnt;
    }
    
    public void setTnt(final boolean tnt) {
        this.tnt = tnt;
    }
    
    public boolean isPandore() {
        return this.pandore;
    }
    
    public void setPandore(final boolean pandore) {
        this.pandore = pandore;
    }
    
    public boolean isKits() {
        return this.kits;
    }
    
    public void setKits(final boolean kits) {
        this.kits = kits;
    }
    
    public boolean isEffects() {
        return this.effects;
    }
    
    public void setEffects(final boolean effects) {
        this.effects = effects;
    }
    
    public boolean isMychest() {
        return this.mychest;
    }
    
    public void setMychest(final boolean mychest) {
        this.mychest = mychest;
    }
    
    public boolean isDiamond() {
        return this.diamond;
    }
    
    public void setDiamond(final boolean diamond) {
        this.diamond = diamond;
    }
    
    public boolean isEnchants() {
        return this.enchants;
    }
    
    public void setEnchants(final boolean enchants) {
        this.enchants = enchants;
    }
    
    public List<String> getRtp$solo() {
        return this.rtp$solo;
    }
    
    public List<String> getRtp$group() {
        return this.rtp$group;
    }

}
