// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;
/*
import pl.vertty.core.data.objects.user.UserData;
import net.pietreg.api.utils.ChatUtils;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import pl.vertty.core.CorePlugin;
import pl.vertty.api.commands.interfaces.CommandInfo;
import pl.vertty.api.commands.Command;

@CommandInfo(name = "uprawnienia", usage = "<gracz>", player = true)
public class GuildPermCommand extends Command
{
    private final CorePlugin plugin;
    
    public GuildPermCommand(final CorePlugin plugin) {
        super(command.getName(), "", "", command.getAliases());
        this.plugin = plugin;
    }
    
    @Override
    public void onCommand(final CommandSender sender, final String... args) {
        final User user = User.get((Player)sender);
        if (user.getGuild() == null) {
            ChatUtils.sendMessage(sender, "&4Blad: &7Nie posiadasz gildii!");
            return;
        }
        if (!user.isDeputy() && !user.isOwner()) {
            ChatUtils.sendMessage(sender, "&4Blad: &7Ta komenda jest dostepna tylko dla zalozyciela i zastepcy gildii.");
            return;
        }
        if (args.length != 1) {
            this.sendUsage(sender);
            return;
        }
        final User argUser = User.get(args[0]);
        if (argUser == null) {
            ChatUtils.sendMessage(sender, "&4Blad: &cNie znaleziono gracza.");
            return;
        }
        if (argUser.getGuild() == null) {
            ChatUtils.sendMessage(sender, "&4Blad: &cGracz nie posiada gildii.");
            return;
        }
        if (!argUser.getGuild().equals((Object)user.getGuild())) {
            ChatUtils.sendMessage(sender, "&4Blad: &cGracz nie znajduje sie w twojej gildii.");
            return;
        }
        if (argUser.isDeputy() || argUser.isOwner()) {
            ChatUtils.sendMessage(sender, "&4Blad: &cNie mozesz zarzadzac permisjami tego gracza.");
            return;
        }
        final UserData userData = this.plugin.getUserManager().findUserByName(argUser.getName());
        if (userData != null) {
            user.getPlayer().openInventory(this.plugin.getGuildPermMenu().createInventory(userData));
        }
    }
}

 */
