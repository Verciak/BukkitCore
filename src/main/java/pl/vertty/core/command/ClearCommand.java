// 
// Decompiled by Procyon v0.5.36
// 

package pl.vertty.core.command;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.vertty.api.commands.Command;
import pl.vertty.core.utils.ChatUtils;
import pl.vertty.core.utils.TitleUtils;

import java.util.List;

public class ClearCommand extends Command
{
    public ClearCommand() {
        super("clear", "", "/clear <gracz>", "mhCore.clear", List.of(new String[0]));
    }

    @Override
    public boolean onExecute(CommandSender commandSender, String[] args) {
        if (args.length == 0) {
            final Player player = (Player)commandSender;
            this.clear(player);
            TitleUtils.sendTitle(player, "", "&aWyczyszczono ekwipunek!", 20, 60, 20);
            return false;
        }
        final Player player = Bukkit.getPlayer(args[0]);
        final Player admin = (Player)commandSender;
        if (player == null) {
            ChatUtils.sendMessage(commandSender, "&4Blad: &cGracz jest offline!");
            return false;
        }
        this.clear(player);
        TitleUtils.sendTitle(admin, "", "&aWyczyszczono ekwipunek gracza " + player.getName(), 20, 60, 20);
        TitleUtils.sendTitle(player, "", "&aWyczyszczono ekwipunek", 20, 60, 20);
        return false;
    }
    
    private void clear(final Player player) {
        player.getInventory().setHelmet(new ItemStack(Material.AIR,0));
        player.getInventory().setChestplate(new ItemStack(Material.AIR,0));
        player.getInventory().setLeggings(new ItemStack(Material.AIR,0));
        player.getInventory().setBoots(new ItemStack(Material.AIR,0));
        player.getInventory().clear();
    }
}
