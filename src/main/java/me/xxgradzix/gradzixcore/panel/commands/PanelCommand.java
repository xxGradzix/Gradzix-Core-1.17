package me.xxgradzix.gradzixcore.panel.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.panel.data.DataManager;
import me.xxgradzix.gradzixcore.panel.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PanelCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {


        if(sender instanceof Player) {

            Player p = (Player) sender;

//            if(!p.hasPermission("panel.panelPermission")) return false;


            Gui gui = Gui.gui()
                    .title(Component.text(ChatColor.YELLOW + ChatColor.BOLD.toString() + "PANEL " + ChatColor.GRAY + "(/panel)"))
                    .rows(3)
                    .disableAllInteractions()
                    .create();


//            PanelOptionsEntity panelOptionsEntity = Panel.getPanelOptionsEntityManager().getPanelOptionsEntity();

//            if(panelOptionsEntity == null) {
//                Panel.getPanelOptionsEntityManager().createOrUpdatePanelOptionsEntity(new PanelOptionsEntity(true, true, true, true));
//            }

//            PanelOptionsEntity finalPanelOptionsEntity = panelOptionsEntity;

            // CHAT

            GuiItem chatOff = ItemBuilder.from(ItemManager.chatOff).asGuiItem();
            GuiItem chatOn = ItemBuilder.from(ItemManager.chatOn).asGuiItem();

            chatOn.setAction((action) -> {

//                PanelAdminConfigFile.setChatStatus(true);
                DataManager.setChatStatus(true);

                p.sendMessage(ChatColor.RED + "Włączyłeś wiadomości na chat");

                gui.updateItem(action.getSlot(), chatOff);
            });

            chatOff.setAction((action) -> {

//                PanelAdminConfigFile.setChatStatus(false);
                DataManager.setChatStatus(false);

                p.sendMessage(ChatColor.RED + "Wyłączyłeś wiadomości na chat");


                    for (Player player : Bukkit.getOnlinePlayers()) {
                        for (int i = 0; i < 100; i++) {
                            player.sendMessage(" ");
                        }
                    }
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Chat został wyczyszczony.");


                gui.updateItem(action.getSlot(), chatOn);
            });

            if(DataManager.getChatStatus()) {
                gui.setItem(2, 2, chatOff);
            } else {
                gui.setItem(2, 2, chatOn);
            }


            // ZDRAPKA

            GuiItem zdrapkaOff = ItemBuilder.from(ItemManager.zdrapkaOff).asGuiItem();
            GuiItem zdrapkaOn = ItemBuilder.from(ItemManager.zdrapkaOn).asGuiItem();

            // TODO kolor itp wiadomosci

            zdrapkaOn.setAction((action) -> {

//                PanelAdminConfigFile.setZdrapkaStatus(true);
                DataManager.setZdrapkaStatus(true);

                p.sendMessage(ChatColor.RED + "Włączyłeś zdrapki");

                gui.updateItem(action.getSlot(), zdrapkaOff);
            });

            zdrapkaOff.setAction((action) -> {

//                PanelAdminConfigFile.setZdrapkaStatus(false);
                DataManager.setZdrapkaStatus(false);

                p.sendMessage(ChatColor.RED + "Wyłączyłeś zdrapki");


                gui.updateItem(action.getSlot(), zdrapkaOn);
            });

            if(DataManager.getZdrapkaStatus()) {
                gui.setItem(2, 4, zdrapkaOff);
            } else {
                gui.setItem(2, 4, zdrapkaOn);
            }

            // Kity

            GuiItem kityOff = ItemBuilder.from(ItemManager.kityOff).asGuiItem();
            GuiItem kityOn = ItemBuilder.from(ItemManager.kityOn).asGuiItem();

            // TODO kolor itp wiadomosci

            kityOn.setAction((action) -> {

//                PanelAdminConfigFile.setKityStatus(true);
                DataManager.setKitStatus(true);

                p.sendMessage(ChatColor.RED + "Włączyłeś kity");

                gui.updateItem(action.getSlot(), kityOff);
            });

            kityOff.setAction((action) -> {

//                PanelAdminConfigFile.setKityStatus(false);
                DataManager.setKitStatus(false);

                p.sendMessage(ChatColor.RED + "Wyłączyłeś kity");


                gui.updateItem(action.getSlot(), kityOn);
            });

            if(DataManager.getKitStatus()) {
                gui.setItem(2, 6, kityOff);
            } else {
                gui.setItem(2, 6, kityOn);
            }


            // Osiagniecia

            GuiItem osiagnieciaOff = ItemBuilder.from(ItemManager.osiagnieciaOff).asGuiItem();
            GuiItem osiagnieciaOn = ItemBuilder.from(ItemManager.osiagnieciaOn).asGuiItem();

            // TODO kolor itp wiadomosci

            osiagnieciaOn.setAction((action) -> {

//                PanelAdminConfigFile.setOsiagnieciaStatus(true);
                DataManager.setOsiagnieciaStatus(true);

                p.sendMessage(ChatColor.RED + "Włączyłeś osiagniecia");

                gui.updateItem(action.getSlot(), osiagnieciaOff);
            });

            osiagnieciaOff.setAction((action) -> {

//                PanelAdminConfigFile.setOsiagnieciaStatus(false);
                DataManager.setOsiagnieciaStatus(false);

                p.sendMessage(ChatColor.RED + "Wyłączyłeś osiagniecia");


                gui.updateItem(action.getSlot(), osiagnieciaOn);
            });

            if(DataManager.getOsiagnieciaStatus()) {
                gui.setItem(2, 8, osiagnieciaOff);
            } else {
                gui.setItem(2, 8, osiagnieciaOn);
            }


            gui.open(p);


        }

        return true;
    }
}
