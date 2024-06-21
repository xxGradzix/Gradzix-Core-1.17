package me.xxgradzix.gradzixcore.achievements.items;

import me.xxgradzix.gradzixcore.achievements.AchievementType;
import me.xxgradzix.gradzixcore.achievements.Achievements;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static me.xxgradzix.gradzixcore.dailyQuests.QuestType.BLOCK_PLACED;

public class ItemManager {

    public static void init() {

    }

    public static ItemStack createProgressQuestItem(AchievementType achievementType, int questProgress, boolean isClaimed) {

        ItemStack item = null;
        ItemMeta itemMeta = null;
        int questGoal = 0;
        switch (achievementType) {
            case BLOCKS_BROKEN:
                item = new ItemStack(Material.GRASS_BLOCK, 1);
                itemMeta = item.getItemMeta();
                itemMeta.setDisplayName("§2§lPostawione Bloki");
                questGoal = Achievements.BLOCKS_BROKEN_GOAL;
                break;
            case BLOCKS_PLACED:
                item = new ItemStack(Material.IRON_PICKAXE, 1);
                itemMeta = item.getItemMeta();
                itemMeta.setDisplayName("§1§lWykopane Bloki");
                questGoal = Achievements.BLOCKS_PLACED_GOAL;
                break;
            case PLAYERS_KILLED:
                item = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
                itemMeta = item.getItemMeta();
                itemMeta.setDisplayName("§6§lUżyte totemy");
                questGoal = Achievements.PLAYERS_KILLED_GOAL;
                break;
            case TOTEM_OF_UNDYING:
                item = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
                itemMeta = item.getItemMeta();
                itemMeta.setDisplayName("§4§lZabici gracze");
                questGoal = Achievements.TOTEM_OF_UNDYING_GOAL;
                break;
        }

        if (questProgress > questGoal) {
            questProgress = questGoal;
        }
        boolean isCompleted = questProgress >= questGoal;



        double progress = (double) questProgress / questGoal;

        progress = Math.round(progress * 100.0) / 100.0;

        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add("§7Progres: §8[" + showProgressWithBars(questProgress, questGoal) + "§8] §7" + progress + "% §7(" + questProgress + "/" + questGoal + ")");
        lore.add("§7Status: " + (isClaimed ? "§aOdebrałeś już nagrode za to zadanie" : (isCompleted ? "§aZakończone" : "§cNieukończone")));
        lore.add("§7Nagroda: §e1000 monet");
        if (!isClaimed && isCompleted) {
            lore.add(" ");
            lore.add("§aKliknij lewy przycisk, aby odebrać nagrodę");
        }
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return item;
    }
//    public static ItemStack createBlockBreakQuestItem(int questProgress, boolean isCompleted, boolean isClaimed) {
//
//        if(questProgress > DailyQuests.BLOCKS_BREAKED_QUEST_GOAL) {
//            questProgress = DailyQuests.BLOCKS_BREAKED_QUEST_GOAL;
//        }
//
//        int questGoal = DailyQuests.BLOCKS_BREAKED_QUEST_GOAL;
//
//        double progress = (double) questProgress / questGoal;
//
//        progress = Math.round(progress * 100.0) / 100.0;
//
//        ItemStack item = new ItemStack(Material.IRON_PICKAXE, 1);
//
//        ItemMeta itemMeta = item.getItemMeta();
//
//        itemMeta.setDisplayName("§1§lWykopane Bloki");
//
//        ArrayList<String> lore = new ArrayList<>();
//
//        lore.add(" ");
//        lore.add("§7Progres: §8[" + showProgressWithBars(questProgress, questGoal) + "§8] §7" + progress + "% §7(" + questProgress + "/" + questGoal + ")");
//        lore.add("§7Status: " + (isClaimed ? "§aOdebrałeś już nagrode za to zadanie" : (isCompleted ? "§aZakończone" : "§cNieukończone")));
//        lore.add("§7Nagroda: §e1000 monet");
//        if(!isClaimed && isCompleted) {
//            lore.add(" ");
//            lore.add("§aKliknij lewy przycisk, aby odebrać nagrodę");
//        }
//        itemMeta.setLore(lore);
//        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(itemMeta);
//
//        return item;
//    }
//    public static ItemStack createBlockPlaceQuestItem(int questProgress, boolean isCompleted, boolean isClaimed) {
//
//        int questGoal = DailyQuests.BLOCKS_PLACED_QUEST_GOAL;
//
//        if(questProgress > questGoal) {
//            questProgress = questGoal;
//        }
//
//        double progress = (double) questProgress / questGoal;
//
//        progress = Math.round(progress * 100.0) / 100.0;
//
//        ItemStack item = new ItemStack(Material.GRASS_BLOCK, 1);
//
//        ItemMeta itemMeta = item.getItemMeta();
//
//        itemMeta.setDisplayName("§2§lPostawione Bloki");
//
//        ArrayList<String> lore = new ArrayList<>();
//
//        lore.add(" ");
//        lore.add("§7Progres: §8[" + showProgressWithBars(questProgress, questGoal) + "§8] §7" + progress + "% §7(" + questProgress + "/" + questGoal + ")");
//        lore.add("§7Status: " + (isClaimed ? "§aOdebrałeś już nagrode za to zadanie" : (isCompleted ? "§aZakończone" : "§cNieukończone")));
//        lore.add("§7Nagroda: §e1000 monet");
//        if(!isClaimed && isCompleted) {
//            lore.add(" ");
//            lore.add("§aKliknij lewy przycisk, aby odebrać nagrodę");
//        }
//        itemMeta.setLore(lore);
//        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(itemMeta);
//
//        return item;
//    }
//    public static ItemStack createTotemUseQuestItem(int questProgress, boolean isCompleted, boolean isClaimed) {
//
//        int questGoal = DailyQuests.TOTEM_OF_UNDYING_QUEST_GOAL;
//
//        if (questProgress > questGoal) {
//            questProgress = questGoal;
//        }
//
//        double progress = (double) questProgress / questGoal;
//
//        progress = Math.round(progress * 100.0) / 100.0;
//
//        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
//
//        ItemMeta itemMeta = item.getItemMeta();
//
//        itemMeta.setDisplayName("§6§lUżyte totemy");
//
//        ArrayList<String> lore = new ArrayList<>();
//
//        lore.add(" ");
//        lore.add("§7Progres: §8[" + showProgressWithBars(questProgress, questGoal) + "§8] §7" + progress + "% §7(" + questProgress + "/" + questGoal + ")");
//        lore.add("§7Status: " + (isClaimed ? "§aOdebrałeś już nagrode za to zadanie" : (isCompleted ? "§aZakończone" : "§cNieukończone")));
//        lore.add("§7Nagroda: §e1000 monet");
//        if (!isClaimed && isCompleted) {
//            lore.add(" ");
//            lore.add("§aKliknij lewy przycisk, aby odebrać nagrodę");
//        }
//        itemMeta.setLore(lore);
//        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(itemMeta);
//        return item;
//    }
//
//    public static ItemStack createKillPlayersQuestItem(int questProgress, boolean isCompleted, boolean isClaimed) {
//
//        int questGoal = DailyQuests.PLAYERS_KILLED_QUEST_GOAL;
//
//        if (questProgress > questGoal) {
//            questProgress = questGoal;
//        }
//
//        double progress = (double) questProgress / questGoal;
//
//        progress = Math.round(progress * 100.0) / 100.0;
//
//        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
//
//        ItemMeta itemMeta = item.getItemMeta();
//
//        itemMeta.setDisplayName("§4§lZabici gracze");
//
//        ArrayList<String> lore = new ArrayList<>();
//
//        lore.add(" ");
//        lore.add("§7Progres: §8[" + showProgressWithBars(questProgress, questGoal) + "§8] §7" + progress + "% §7(" + questProgress + "/" + questGoal + ")");
//        lore.add("§7Status: " + (isClaimed ? "§aOdebrałeś już nagrode za to zadanie" : (isCompleted ? "§aZakończone" : "§cNieukończone")));
//        lore.add("§7Nagroda: §e1000 monet");
//        if (!isClaimed && isCompleted) {
//            lore.add(" ");
//            lore.add("§aKliknij lewy przycisk, aby odebrać nagrodę");
//        }
//        itemMeta.setLore(lore);
//        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(itemMeta);
//        return item;
//    }


    private static String showProgressWithBars(int progress, int maxProgress) {
        StringBuilder stringBuilder = new StringBuilder();
        int bars = (int) Math.round((double) progress / maxProgress * 10);
        for (int i = 0; i < bars; i++) {
            stringBuilder.append("§a█");
        }
        for (int i = 0; i < 10 - bars; i++) {
            stringBuilder.append("§7█");
        }
        return stringBuilder.toString();
    }

}
