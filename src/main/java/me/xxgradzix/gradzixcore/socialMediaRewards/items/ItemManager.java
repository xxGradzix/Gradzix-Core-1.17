package me.xxgradzix.gradzixcore.socialMediaRewards.items;

import me.xxgradzix.gradzixcore.socialMediaRewards.data.database.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {



    public static ItemStack createRewardButton(DataManager.Reward reward, boolean collected) {

        ItemStack item;
        switch (reward) {
            case TIKTOK: item = new ItemStack(Material.AMETHYST_SHARD, 1);
                break;
            case YOUTUBE: item = new ItemStack(Material.REDSTONE, 1);
                break;
            case FACEBOOK: item = new ItemStack(Material.LAPIS_LAZULI, 1);
                break;
            case DISCORD: item = new ItemStack(Material.LIGHT_BLUE_DYE, 1);
                break;
            default: item = new ItemStack(Material.BARRIER, 1);

        }
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        switch (reward) {
            case TIKTOK:
                meta.setDisplayName(ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "TikTok ");
                lore.add(" ");
                lore.add(ChatColor.GRAY + "Wejdź na naszego " + ChatColor.LIGHT_PURPLE + "TikToka " + ChatColor.GRAY + "i zostaw obserwacje");
                lore.add(ChatColor.GRAY + "Aby otrzymać nagrode");
                break;
            case YOUTUBE:
                meta.setDisplayName(ChatColor.BOLD + "" + ChatColor.DARK_RED + "YouTube");
                lore.add(" ");
                lore.add(ChatColor.GRAY + "Wejdź na naszego " + ChatColor.RED + "YouTuba " + ChatColor.GRAY + "i zostaw subskrybcje");
                lore.add(ChatColor.GRAY + "Aby otrzymać nagrode");
                break;
            case FACEBOOK:
                meta.setDisplayName(ChatColor.BOLD + "" + ChatColor.DARK_BLUE + "FaceBook");
                lore.add(" ");
                lore.add(ChatColor.GRAY + "Wejdź na naszego " + ChatColor.BLUE + "FaceBooka " + ChatColor.GRAY + "i zostaw obserwacje");
                lore.add(ChatColor.GRAY + "Aby otrzymać nagrode");
                break;
            case DISCORD:
                meta.setDisplayName(ChatColor.BOLD + "" + ChatColor.BLUE + "Discord");
                lore.add(" ");
                lore.add(ChatColor.GRAY + "Dołącz na nasz serwer " + ChatColor.BLUE + "Discord " + ChatColor.GRAY + "i zweryfikuj się");
                lore.add(ChatColor.GRAY + "Aby otrzymać nagrode");
                break;
            default:
                meta.setDisplayName(ChatColor.BOLD + "" + ChatColor.MAGIC + "NULL NULL NULL");
        }
        lore.add(" ");
        if(!collected) {
            lore.add(ChatColor.GRAY + "Odebrano nagrode: "  + ChatColor.RED + "nie");
            lore.add(" ");
            lore.add(ChatColor.GRAY + "Kliknij aby odebrac");
        } else {
            lore.add(ChatColor.GRAY + "Odebrano nagrode: "  + ChatColor.GREEN + "tak");
        }

        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }


}
