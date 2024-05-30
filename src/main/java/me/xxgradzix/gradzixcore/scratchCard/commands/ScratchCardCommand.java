package me.xxgradzix.gradzixcore.scratchCard.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.GlobalItemManager;
import me.xxgradzix.gradzixcore.scratchCard.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScratchCardCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(sender instanceof Player) {

            Player p = (Player) sender;

            Gui gui = Gui.gui()
                    .title(Component.text(ItemManager.zdrapka.getItemMeta().getDisplayName()))
                    .rows(5)
                    .disableAllInteractions()
                    .create();


            ItemStack[] itemStacks = Arrays.asList(me.xxgradzix.gradzixcore.scratchCard.data.DataManager.getScratchCardItems()).toArray(new ItemStack[0]);

            List<ItemStack> list = new ArrayList<>();

            for (ItemStack item : itemStacks) {
                if (item == null) continue;
                list.add(item);
            }

            // glass

            ArrayList<Integer> black = new ArrayList<>();


            black.add(2);
            black.add(3);
            black.add(4);
            black.add(5);
            black.add(6);

            black.add(18);
            black.add(26);

            black.add(38);
            black.add(39);
            black.add(40);
            black.add(41);
            black.add(42);

            GuiItem blackGlass = new GuiItem(GlobalItemManager.FILLER_GLASS_PANE);

            gui.setItem(black, blackGlass);


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

            GuiItem limeGlass = new GuiItem(GlobalItemManager.LIGHT_GLASS_PANE);

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
            gui.open(p);

        }

        return true;
    }
}
