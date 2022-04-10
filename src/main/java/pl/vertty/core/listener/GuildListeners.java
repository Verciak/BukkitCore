package pl.vertty.core.listener;
/*
import pl.vertty.core.data.objects.user.UserData;
import net.dzikoysk.funnyguilds.basic.guild.Region;
import net.pietreg.api.utils.ChatUtils;
import net.dzikoysk.funnyguilds.basic.guild.RegionUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import net.dzikoysk.funnyguilds.event.guild.member.GuildMemberJoinEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.EventHandler;
import pl.vertty.core.enums.GuildPermission;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import org.bukkit.plugin.Plugin;
import pl.vertty.core.CorePlugin;


public class GuildListeners implements Listener
{
    private final PluginConfiguration.Commands commands;
    private final CorePlugin plugin;
    
    public GuildListeners(final CorePlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
        this.commands = FunnyGuilds.getInstance().getPluginConfiguration().commands;
    }
    
    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        final Material eventMaterial = event.getBlock().getType();
        if (eventMaterial == Material.BEACON && this.isAllowed(event.getPlayer(), event.getBlock().getLocation(), GuildPermission.BEACON)) {
            event.setCancelled(true);
        }
        else if (this.isAllowed(event.getPlayer(), event.getBlock().getLocation(), GuildPermission.BLOCK_BREAK)) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        final Material eventMaterial = event.getBlockPlaced().getType();
        if (eventMaterial == Material.TNT && this.isAllowed(event.getPlayer(), event.getBlockPlaced().getLocation(), GuildPermission.TNT_PLACEMENT)) {
            event.setCancelled(true);
        }
        else if (eventMaterial == Material.BEACON && this.isAllowed(event.getPlayer(), event.getBlock().getLocation(), GuildPermission.BEACON)) {
            event.setCancelled(true);
        }
        else if (this.isAllowed(event.getPlayer(), event.getBlockPlaced().getLocation(), GuildPermission.BLOCK_PLACE)) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onFluidPlace(final PlayerBucketEmptyEvent event) {
        if (this.isAllowed(event.getPlayer(), event.getBlockClicked().getLocation(), GuildPermission.FLUID_PLACEMENT)) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onFluidBreak(final PlayerBucketFillEvent event) {
        if (this.isAllowed(event.getPlayer(), event.getBlockClicked().getLocation(), GuildPermission.FLUID_PLACEMENT)) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onCommand(final PlayerCommandPreprocessEvent e) {
        final String commandName = e.getMessage().split(" ")[0].substring(1);
        final User sender = User.get(e.getPlayer());
        if ((this.isCommand(commandName, this.commands.kick) && this.isAllowed(e.getPlayer(), GuildPermission.MEMBER_KICK)) || (this.isCommand(commandName, this.commands.invite) && this.isAllowed(e.getPlayer(), GuildPermission.MEMBER_INVITE)) || (this.isCommand(commandName, this.commands.validity) && this.isAllowed(e.getPlayer(), GuildPermission.GUILD_PROLONG))) {
            e.setCancelled(true);
            final User previousOwner = sender.getGuild().getOwner();
            try {
                sender.getGuild().setOwner(sender);
                Bukkit.dispatchCommand((CommandSender)e.getPlayer(), e.getMessage().substring(1));
            }
            finally {
                sender.getGuild().setOwner(previousOwner);
            }
            return;
        }
        if (commandName.equalsIgnoreCase("tpaccept") && this.isAllowed(e.getPlayer(), e.getPlayer().getLocation(), GuildPermission.TELEPORTATION_USE)) {
            e.setCancelled(true);
        }
    }
    
    private boolean isCommand(final String commandName, final PluginConfiguration.Commands.FunnyCommand command) {
        return commandName.equalsIgnoreCase(command.name) || command.aliases.stream().anyMatch(str -> str.equalsIgnoreCase(commandName));
    }
    
    @EventHandler
    public void onGuildJoin(final GuildMemberJoinEvent e) {
        this.plugin.getUserManager().getUser(e.getMember().getPlayer()).resetPermissions();
    }
    
    private boolean isAllowed(final Player player, final GuildPermission permission) {
        final User user = User.get(player);
        return user.getGuild() != null && !user.isDeputy() && !user.isOwner() && !this.plugin.getUserManager().getUser(player).getPermissions().get(permission);
    }
    
    private boolean isAllowed(final Player player, final Location location, final GuildPermission permission) {
        final User user = User.get(player);
        if (user.isOwner() || user.isDeputy()) {
            return false;
        }
        final Region region = RegionUtils.getAt(location);
        if (region == null || !region.getGuild().equals((Object)user.getGuild())) {
            return false;
        }
        final UserData userData = this.plugin.getUserManager().getUser(player);
        if (userData.getPermissions().get(permission)) {
            ChatUtils.sendMessage(player, "&4Blad: &cPopros lidera gildii o to uprawnienie!");
            return true;
        }
        return false;
    }
}

 */
