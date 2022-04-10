package pl.vertty.core.data.config;

import com.google.common.collect.Maps;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.List;

public class PluginConfiguration
{
    private static PluginConfiguration instance;
    private final String db_host;
    private final int db_port;
    private final String db_user;
    private final String db_pass;
    private final String db_base;
    private final List<String> command_pomoc;
    private final List<String> command_premium;
    private final List<String> command_premiumplus;
    private final List<String> command_sponsor;
    private final List<String> command_youtube;
    private final List<String> command_vip;
    private final int deposit_notch;
    private final int deposit_golden;
    private final int deposit_pearls;
    private final int deposit_arrow;
    private final String cx_name;
    private final List<String> cx_lore;
    private final String cx_message;
    private final String pandora_name;
    private final List<String> pandora_lore;
    private final String pandora_message;
    private final String pandora_get_message;
    private final String pandora_all;
    private final String mychest_name;
    private final List<String> mychest_lore;
    private final String mychest_message;
    private final double mychest_chance;
    private final List<String> antilogout_commands;
    private final String drop_message;
    private final int randomtp_min;
    private final int randomtp_max;
    private final int spawn_size;
    private final String normal_format;
    private final String admin_format;
    private final int home_default;
    private final Map<String, Integer> homes;
    private final List<String> automessage_messages;
    private final int automessage_time;
    private final List<String> blocked_commands;
    private final String ban_perm_message;
    private final String ban_temp_message;
    private final String unban__message;
    
    public PluginConfiguration(final JavaPlugin plugin) {

        plugin.saveDefaultConfig();
        PluginConfiguration.instance = this;
        final Configuration fc = plugin.getConfig();
        this.db_host = fc.getString("database.host");
        this.db_port = fc.getInt("database.port");
        this.db_user = fc.getString("database.user");
        this.db_pass = fc.getString("database.pass");
        this.db_base = fc.getString("database.base");
        this.ban_perm_message = fc.getString("punishment.ban");
        this.ban_temp_message = fc.getString("punishment.permban");
        this.unban__message = fc.getString("punishment.unban");
        this.command_pomoc = (List<String>)fc.getStringList("command.pomoc");
        this.command_premium = (List<String>)fc.getStringList("command.premium");
        this.command_premiumplus = (List<String>)fc.getStringList("command.premiumplus");
        this.command_sponsor = (List<String>)fc.getStringList("command.sponsor");
        this.command_youtube = (List<String>)fc.getStringList("command.youtube");
        this.command_vip = (List<String>)fc.getStringList("command.vip");
        this.automessage_messages = (List<String>)fc.getStringList("automessage.list");
        this.automessage_time = fc.getInt("automessage.time");
        this.blocked_commands = (List<String>)fc.getStringList("blockedcommands");
        this.randomtp_min = fc.getInt("randomtp.min");
        this.randomtp_max = fc.getInt("randomtp.max");
        this.deposit_notch = fc.getInt("deposit.notch");
        this.deposit_golden = fc.getInt("deposit.golden");
        this.deposit_pearls = fc.getInt("deposit.pearls");
        this.deposit_arrow = fc.getInt("deposit.arrows");
        this.normal_format = fc.getString("chat.format.normal");
        this.admin_format = fc.getString("chat.format.admin");
        this.cx_name = fc.getString("cobblexx.name");
        this.cx_lore = (List<String>)fc.getStringList("cobblexx.lore");
        this.cx_message = fc.getString("cobblexx.message");
        this.pandora_name = fc.getString("pandoree.name");
        this.pandora_lore = (List<String>)fc.getStringList("pandoree.lore");
        this.pandora_message = fc.getString("pandoree.message");
        this.pandora_get_message = fc.getString("pandoree.get");
        this.pandora_all = fc.getString("pandoree.all");
        this.mychest_name = fc.getString("mychesttt.name");
        this.mychest_lore = (List<String>)fc.getStringList("mychesttt.lore");
        this.mychest_message = fc.getString("mychesttt.message");
        this.mychest_chance = fc.getDouble("mychesttt.chance");
        this.antilogout_commands = (List<String>)fc.getStringList("antilogout-commands");
        this.drop_message = fc.getString("drop-message");
        this.home_default = fc.getInt("home-default");
        this.spawn_size = fc.getInt("spawn-size");
        (this.homes = Maps.newLinkedHashMap()).put("vip", 1);
        this.homes.put("svip", 2);
        this.homes.put("sponsor", 3);
    }
    
    public static PluginConfiguration getInstance() {
        return PluginConfiguration.instance;
    }
    
    public String getDb_host() {
        return this.db_host;
    }
    
    public int getDb_port() {
        return this.db_port;
    }
    
    public String getDb_user() {
        return this.db_user;
    }
    
    public String getDb_pass() {
        return this.db_pass;
    }
    
    public String getDb_base() {
        return this.db_base;
    }
    
    public int getAutomessage_time() {
        return this.automessage_time;
    }
    
    public List<String> getAutomessage_messages() {
        return this.automessage_messages;
    }
    
    public List<String> getBlocked_commands() {
        return this.blocked_commands;
    }
    
    public List<String> getCommand_pomoc() {
        return this.command_pomoc;
    }
    
    public List<String> getCommand_premium() {
        return this.command_premium;
    }
    
    public List<String> getCommand_premiumplus() {
        return this.command_premiumplus;
    }
    
    public List<String> getCommand_sponsor() {
        return this.command_sponsor;
    }
    
    public List<String> getCommand_youtube() {
        return this.command_youtube;
    }
    
    public List<String> getCommand_vip() {
        return this.command_vip;
    }
    
    public String getAdmin_format() {
        return this.admin_format;
    }
    
    public String getNormal_format() {
        return this.normal_format;
    }
    
    public int getDeposit_notch() {
        return this.deposit_notch;
    }
    
    public int getDeposit_golden() {
        return this.deposit_golden;
    }
    
    public int getDeposit_pearls() {
        return this.deposit_pearls;
    }
    
    public int getDeposit_arrow() {
        return this.deposit_arrow;
    }
    
    public int getRandomtp_min() {
        return this.randomtp_min;
    }
    
    public int getRandomtp_max() {
        return this.randomtp_max;
    }
    
    public String getCx_name() {
        return this.cx_name;
    }
    
    public List<String> getCx_lore() {
        return this.cx_lore;
    }
    
    public String getCx_message() {
        return this.cx_message;
    }
    
    public String getMychest_name() {
        return this.mychest_name;
    }
    
    public List<String> getMychest_lore() {
        return this.mychest_lore;
    }
    
    public String getMychest_message() {
        return this.mychest_message;
    }
    
    public double getMychest_chance() {
        return this.mychest_chance;
    }
    
    public List<String> getAntilogout_commands() {
        return this.antilogout_commands;
    }
    
    public String getPandora_name() {
        return this.pandora_name;
    }
    
    public List<String> getPandora_lore() {
        return this.pandora_lore;
    }
    
    public String getPandora_message() {
        return this.pandora_message;
    }
    
    public String getPandora_all() {
        return this.pandora_all;
    }
    
    public String getPandora_get_message() {
        return this.pandora_get_message;
    }
    
    public String getDrop_message() {
        return this.drop_message;
    }
    
    public int getHome_default() {
        return this.home_default;
    }
    
    public Map<String, Integer> getHomes() {
        return this.homes;
    }
    
    public int getSpawn_size() {
        return this.spawn_size;
    }
    
    public String getBan_perm_message() {
        return this.ban_perm_message;
    }
    
    public String getBan_temp_message() {
        return this.ban_temp_message;
    }
    
    public String getUnban__message() {
        return this.unban__message;
    }
}
