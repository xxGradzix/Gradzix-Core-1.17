package me.xxgradzix.gradzixcore.itemShop.command;

import me.xxgradzix.gradzixcore.itemShop.data.DataManager;
import me.xxgradzix.gradzixcore.itemShop.data.database.entities.ItemShopPlayerBalanceEntity;
import me.xxgradzix.gradzixcore.itemShop.data.database.enums.ShopType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ItemShopBalanceCommand implements CommandExecutor {

    private final DataManager dataManager;

    public ItemShopBalanceCommand(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        ItemShopPlayerBalanceEntity playerBalanceEntity = dataManager.getPlayerBalanceEntity(player);

        player.sendMessage("Twoj stan konta:");
        player.sendMessage("Monety czasu: " + dataManager.getPlayerBalance(player, ShopType.TIME));
        player.sendMessage("Monety zabojstw: " + dataManager.getPlayerBalance(player, ShopType.KILLS));
        player.sendMessage("Pieniądze: " + dataManager.getPlayerBalance(player, ShopType.MONEY));

        return true;
    }
}
