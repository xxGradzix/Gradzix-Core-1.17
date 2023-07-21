package me.xxgradzix.gradzixcore.zdrapka.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.zdrapka.files.ZdrapkaConfigFile;
import me.xxgradzix.gradzixcore.zdrapka.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ZdrapkaCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(sender instanceof Player) {

            Player p = (Player) sender;

            Gui gui = Gui.gui()
                    .title(Component.text(ItemManager.zdrapka.getItemMeta().getDisplayName()))
                    .rows(5)
                    .disableAllInteractions()
                    .create();


            List<ItemStack> list = (List<ItemStack>) ZdrapkaConfigFile.getCustomFile().get("items");
            // szklo

            ArrayList<Integer> czarne = new ArrayList<>();


            czarne.add(2);
            czarne.add(3);
            czarne.add(4);
            czarne.add(5);
            czarne.add(6);

            czarne.add(18);
            czarne.add(26);

            czarne.add(38);
            czarne.add(39);
            czarne.add(40);
            czarne.add(41);
            czarne.add(42);

            GuiItem blackGlass = new GuiItem(me.xxgradzix.gradzixcore.chatopcje.items.ItemManager.blackGlass);

            gui.setItem(czarne, blackGlass);


//            ArrayList<Integer> zielone = new ArrayList<>();
//
//            zielone.add(3);
//            zielone.add(5);
//            zielone.add(9);
//            zielone.add(17);
//            zielone.add(27);
//            zielone.add(35);
//            zielone.add(39);
//            zielone.add(41);
//
//            GuiItem greenGlass = new GuiItem(me.xxgradzix.gradzixcore.chatopcje.items.ItemManager.greenGlass);
//
//            gui.setItem(zielone, greenGlass);

            ArrayList<Integer> lime = new ArrayList<>();

            lime.add(0);
            lime.add(1);
            lime.add(7);
            lime.add(8);
            lime.add(9);
            lime.add(17);

            lime.add(27);
            lime.add(35);
            lime.add(36);
            lime.add(37);
            lime.add(43);
            lime.add(44);

            GuiItem limeGlass = new GuiItem(me.xxgradzix.gradzixcore.chatopcje.items.ItemManager.limeGlass);

            gui.setItem(lime, limeGlass);



            int itemNum =0;
            for(int i = 10; i <= 35; i++) {
                if(i % 9 == 0 || (i+1)%9 == 0) continue;
                GuiItem guiItem = ItemBuilder.from(list.get(itemNum).clone()).asGuiItem();

                gui.setItem(i, guiItem);
                if(itemNum + 1 < list.size()) {
                    itemNum++;
                } else break;

            }
//            for(ItemStack itemStack : list) {
//
//                ItemStack copy = itemStack.clone();
//
//                GuiItem guiItem = ItemBuilder.from(copy).asGuiItem();
//
//                gui.addItem(guiItem);
//
//            }
            gui.open(p);

        }



        return true;
    }
}
