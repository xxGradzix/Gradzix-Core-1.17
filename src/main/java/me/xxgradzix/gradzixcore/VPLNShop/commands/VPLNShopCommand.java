package me.xxgradzix.gradzixcore.VPLNShop.commands;

import me.xxgradzix.gradzixcore.GlobalMessagesManager;
import me.xxgradzix.gradzixcore.VPLNShop.managers.VPLNShop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VPLNShopCommand implements CommandExecutor {

    private final VPLNShop vplnShop;

    public VPLNShopCommand(VPLNShop vplnShop) {
        this.vplnShop = vplnShop;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(GlobalMessagesManager.PLAYER_ONLY);
            return true;
        }

        vplnShop.openVPLNShop((Player) sender);

        return true;
    }
}
