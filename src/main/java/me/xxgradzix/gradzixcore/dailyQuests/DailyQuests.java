package me.xxgradzix.gradzixcore.dailyQuests;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.dailyQuests.commands.DailyQuestsCommand;

public class DailyQuests {

    public static final int BLOCKS_BREAKED_QUEST_GOAL = 10;
    public static final int BLOCKS_PLACED_QUEST_GOAL = 10;
    public static final int TOTEM_OF_UNDYING_QUEST_GOAL = 2;
    public static final int PLAYERS_KILLED_QUEST_GOAL = 1;

    private final Gradzix_Core plugin;


    public DailyQuests(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {

        plugin.getServer().getPluginManager().registerEvents(new QuestListener(), plugin);

        plugin.getCommand("codziennewyzwanie").setExecutor(new DailyQuestsCommand());

    }

}
