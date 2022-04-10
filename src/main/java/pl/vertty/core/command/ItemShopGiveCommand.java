// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;


import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.MessageUtils;
import pl.vertty.core.data.objects.ItemShop;
import pl.vertty.core.CorePlugin;
import pl.vertty.core.manager.ItemShopManager;
import pl.vertty.api.commands.Command;

import java.util.List;

public class ItemShopGiveCommand extends Command
{
    private final Configuration itemShopConfiguration = CorePlugin.getPlugin().getConfig();
    private final ItemShopManager itemShopManager = CorePlugin.getPlugin().getItemShopManager();

    public ItemShopGiveCommand() {
        super("itemshopgive", "", "/itemshopgive <give/list> [name] [gracz]", "mhCore.itemshopgive", List.of(new String[0]));
    }
    
    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length < 1) {
            ChatUtils.sendMessage(commandSender, "/itemshopgive <give/list> [name] [gracz]");
            return false;
        }
        final Configuration fc = CorePlugin.getPlugin().getConfig();
        final String s = args[0];
        switch (s) {
            case "lista":
            case "list": {
                ChatUtils.sendMessage(commandSender, fc.getString("list-of-services"));
                for (final ItemShop itemShop : this.itemShopManager.getServiceMap().values()) {
                    ChatUtils.sendMessage(commandSender, StringUtils.replace(fc.getString("list-line-service"), "{SERVICE}", itemShop.getName()));
                }
            }
            case "daj":
            case "give": {
                if (args.length < 3) {
                    ChatUtils.sendMessage(commandSender, "/itemshopgive <give/list> [name] [gracz]");
                    return false;
                }
                final String player = args[2];
                final ItemShop itemShop = this.itemShopManager.getItemShop(args[1]);
                if (itemShop == null) {
                    ChatUtils.sendMessage(commandSender, fc.getString("no-found-service"));
                    return false;
                }
                itemShop.getChatMessage().forEach(message -> MessageUtils.sendBroadcast(StringUtils.replace(message, "{PLAYER}", player)));
                itemShop.getCommands().forEach(command -> Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), StringUtils.replace(command, "{PLAYER}", player)));
            }
            default: {
                ChatUtils.sendMessage(commandSender, "/itemshopgive <give/list> [name] [gracz]");
            }
        }
        return false;
    }
}
