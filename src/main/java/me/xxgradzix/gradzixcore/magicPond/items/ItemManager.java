package me.xxgradzix.gradzixcore.magicPond.items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {


    public static void init() {





    }

    public static ItemStack createItemRewardButton(ItemStack reward, int chance) {

        ItemStack item = new ItemStack(reward);

        ItemMeta meta = item.getItemMeta();

        List<String> lore = meta.getLore();

        if(lore == null) lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§7Szansa na wylosowanie tego przedmiotu wynosi: " + chance);
        lore.add(" ");
        lore.add("§7Klikij aby usunąć tą nagrode");

        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }


}
