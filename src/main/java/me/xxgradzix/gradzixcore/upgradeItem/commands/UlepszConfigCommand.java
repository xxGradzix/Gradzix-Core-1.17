package me.xxgradzix.gradzixcore.upgradeItem.commands;

import me.xxgradzix.gradzixcore.upgradeItem.gui.UlepszGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UlepszConfigCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {


        if(sender instanceof Player) {

            Player p = (Player) sender;
//            if(!p.hasPermission("ulepsz.ulepszconfig")) return false;

//            ArrayList<ItemStack[]> itemStacksList = (ArrayList<ItemStack[]>) UlepszConfigFile.getAllItems();

//            List<UpgradeEntity> upgradeEntityList = DataManager.getAllUpgradeEntities();

            UlepszGui ulepszGui = new UlepszGui();


            ulepszGui.open(p);



        }

        return true;

    }
}
