package me.xxgradzix.gradzixcore.dailyQuests.managers;

import me.xxgradzix.gradzixcore.dailyQuests.DailyQuests;
import me.xxgradzix.gradzixcore.dailyQuests.QuestType;

import java.util.HashMap;
import java.util.UUID;

public class QuestsManagers {


    public static void resetDailyQuests() {
        for(QuestType questType : QuestType.values()) {
            collectStatus.get(questType).clear();
            questProgress.get(questType).clear();
        }
    }

    private enum CollectStatus {
        COLLECTED,
        READY_TO_COLLECT,
        NOT_COLLECTED
    }

    private static HashMap<QuestType, HashMap<UUID, CollectStatus>> collectStatus = new HashMap<>();

//    private static HashMap<UUID, CollectStatus> collectStatusForBlockBroken = new HashMap<>();
//    private static HashMap<UUID, CollectStatus> collectStatusForBlockPlaced = new HashMap<>();
//    private static HashMap<UUID, CollectStatus> collectStatusForTotemOfUndyingUsed = new HashMap<>();
//    private static HashMap<UUID, CollectStatus> collectStatusForPlayerKills = new HashMap<>();

    public static boolean canCollect(UUID uniqueId, QuestType questType) {
        return CollectStatus.READY_TO_COLLECT.equals(collectStatus.get(questType).get(uniqueId));
    }
    public static boolean isClaimed(UUID uniqueId, QuestType questType) {
        collectStatus.get(questType).getOrDefault(uniqueId, CollectStatus.NOT_COLLECTED);
        return CollectStatus.COLLECTED.equals(collectStatus.get(questType).get(uniqueId));
    }

    private static HashMap<QuestType, HashMap<UUID, Integer>> questProgress = new HashMap<>();

    static {
        for(QuestType questType : QuestType.values()) {
            collectStatus.put(questType, new HashMap<>());
            questProgress.put(questType, new HashMap<>());
        }
    }
//    private static HashMap<UUID, Integer> blocksBreaked = new HashMap<>();
//    private static HashMap<UUID, Integer> blocksPlaced = new HashMap<>();
//    private static HashMap<UUID, Integer> totemsOfUndyingUsed = new HashMap<>();
//    private static HashMap<UUID, Integer> playerKills = new HashMap<>();

    public static int getQuestProgress(UUID playerUUID, QuestType questType) {
        return questProgress.get(questType).getOrDefault(playerUUID, 0);
    }

    public static void onQuestProgress(UUID playerUUID, QuestType questType) {

        int questGoal = 0;
        switch (questType) {
            case BLOCK_BREAKED:
                questGoal = DailyQuests.BLOCKS_BREAKED_QUEST_GOAL;
                break;
            case BLOCK_PLACED:
                questGoal = DailyQuests.BLOCKS_PLACED_QUEST_GOAL;
                break;
            case TOTEM_OF_UNDYING_USED:
                questGoal = DailyQuests.TOTEM_OF_UNDYING_QUEST_GOAL;
                break;
            case PLAYER_KILLS:
                questGoal = DailyQuests.PLAYERS_KILLED_QUEST_GOAL;
                break;
        }

        int progress = questProgress.get(questType).getOrDefault(playerUUID, 0);

        progress++;

        if(CollectStatus.COLLECTED.equals(collectStatus.get(questType).get(playerUUID)) || CollectStatus.READY_TO_COLLECT.equals(collectStatus.get(questType).get(playerUUID))) {
            return;
        }

        if(progress > DailyQuests.BLOCKS_BREAKED_QUEST_GOAL) {
            collectStatus.get(questType).put(playerUUID, CollectStatus.READY_TO_COLLECT);
            return;
        }
        questProgress.get(questType).put(playerUUID, progress);
    }

//    public static void onBlockBreak(UUID playerUUID) {
//        int blocks = blocksBreaked.getOrDefault(playerUUID, 0);
//        blocks++;
//
//        if(CollectStatus.COLLECTED.equals(collectStatusForBlockBroken.get(playerUUID)) || CollectStatus.READY_TO_COLLECT.equals(collectStatusForBlockBroken.get(playerUUID))) {
//            return;
//        }
//
//        if(blocks >= DailyQuests.BLOCKS_BREAKED_QUEST_GOAL) {
//            collectStatusForBlockBroken.put(playerUUID, CollectStatus.READY_TO_COLLECT);
//            return;
//        }
//        blocksBreaked.put(playerUUID, blocks);
//    }
//    public static void onBlockPlace(UUID playerUUID) {
//        int blocks = blocksPlaced.getOrDefault(playerUUID, 0);
//        blocks++;
//
//        if(CollectStatus.COLLECTED.equals(collectStatusForBlockPlaced.get(playerUUID)) || CollectStatus.READY_TO_COLLECT.equals(collectStatusForBlockPlaced.get(playerUUID))) {
//            return;
//        }
//
//        if(blocks >= DailyQuests.BLOCKS_PLACED_QUEST_GOAL) {
//            collectStatusForBlockPlaced.put(playerUUID, CollectStatus.READY_TO_COLLECT);
//            return;
//        }
//        blocksPlaced.put(playerUUID, blocks);
//    }
//    public static void onTotemOfUndyingUse(UUID playerUUID) {
//        int totems = totemsOfUndyingUsed.getOrDefault(playerUUID, 0);
//        totems++;
//
//        if(CollectStatus.COLLECTED.equals(collectStatusForTotemOfUndyingUsed.get(playerUUID)) || CollectStatus.READY_TO_COLLECT.equals(collectStatusForTotemOfUndyingUsed.get(playerUUID))) {
//            return;
//        }
//
//        if(totems >= DailyQuests.TOTEM_OF_UNDYING_QUEST_GOAL) {
//            collectStatusForTotemOfUndyingUsed.put(playerUUID, CollectStatus.READY_TO_COLLECT);
//            return;
//        }
//        totemsOfUndyingUsed.put(playerUUID, totems);
//    }
//    public static void onPlayerKill(UUID playerUUID) {
//        int kills = playerKills.getOrDefault(playerUUID, 0);
//        kills++;
//
//        if(CollectStatus.COLLECTED.equals(collectStatusForPlayerKills.get(playerUUID)) || CollectStatus.READY_TO_COLLECT.equals(collectStatusForPlayerKills.get(playerUUID))) {
//            return;
//        }
//
//        if(kills >= DailyQuests.PLAYERS_KILLED_QUEST_GOAL) {
//            collectStatusForPlayerKills.put(playerUUID, CollectStatus.READY_TO_COLLECT);
//            return;
//        }
//        playerKills.put(playerUUID, kills);
//    }

    public static void setCollectStatus(UUID playerUUID, QuestType questType) {
        if(CollectStatus.READY_TO_COLLECT.equals(collectStatus.get(questType).get(playerUUID))) {
            collectStatus.get(questType).put(playerUUID, CollectStatus.COLLECTED);
        }
    }
//
//    private static void collectBlocksBroken(UUID playerUUID) {
//        if(CollectStatus.READY_TO_COLLECT.equals(collectStatusForBlockBroken.get(playerUUID))) {
//            collectStatusForBlockBroken.put(playerUUID, CollectStatus.COLLECTED);
//        }
//    }
//    private static void collectBlocksPlaced(UUID playerUUID) {
//        if(CollectStatus.READY_TO_COLLECT.equals(collectStatusForBlockPlaced.get(playerUUID))) {
//            collectStatusForBlockPlaced.put(playerUUID, CollectStatus.COLLECTED);
//        }
//    }
//    private static void collectTotemsOfUndyingUsed(UUID playerUUID) {
//        if(CollectStatus.READY_TO_COLLECT.equals(collectStatusForTotemOfUndyingUsed.get(playerUUID))) {
//            collectStatusForTotemOfUndyingUsed.put(playerUUID, CollectStatus.COLLECTED);
//        }
//    }
//    private static void collectPlayerKills(UUID playerUUID) {
//        if(CollectStatus.READY_TO_COLLECT.equals(collectStatusForPlayerKills.get(playerUUID))) {
//            collectStatusForPlayerKills.put(playerUUID, CollectStatus.COLLECTED);
//        }
//    }


}
