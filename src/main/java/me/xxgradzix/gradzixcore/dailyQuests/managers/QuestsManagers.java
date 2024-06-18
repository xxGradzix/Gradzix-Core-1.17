package me.xxgradzix.gradzixcore.dailyQuests.managers;

import java.util.HashMap;
import java.util.UUID;

public class QuestsManagers {

    private enum CollectStatus {
        COLLECTED,
        READY_TO_COLLECT
    }

    private static HashMap<UUID, CollectStatus> collectStatusForBlockBroken = new HashMap<>();
    private static HashMap<UUID, CollectStatus> collectStatusForBlockPlaced = new HashMap<>();
    private static HashMap<UUID, CollectStatus> collectStatusForTotemOfUndyingUsed = new HashMap<>();
    private static HashMap<UUID, CollectStatus> collectStatusForPlayerKills = new HashMap<>();


    public static final int BLOCKS_BREAKED_QUEST_GOAL = 10000;
    public static final int BLOCKS_PLACED_QUEST_GOAL = 5000;
    public static final int TOTEM_OF_UNDYING_QUEST_GOAL = 16;
    public static final int PLAYERS_KILLED_QUEST_GOAL = 8;

    private static HashMap<UUID, Integer> blocksBreaked = new HashMap<>();
    private static HashMap<UUID, Integer> blocksPlaced = new HashMap<>();
    private static HashMap<UUID, Integer> totemsOfUndyingUsed = new HashMap<>();
    private static HashMap<UUID, Integer> playerKills = new HashMap<>();

    public static void onBlockBreak(UUID playerUUID) {
        int blocks = blocksBreaked.getOrDefault(playerUUID, 0);
        blocks++;

        if(CollectStatus.COLLECTED.equals(collectStatusForBlockBroken.get(playerUUID)) || CollectStatus.READY_TO_COLLECT.equals(collectStatusForBlockBroken.get(playerUUID))) {
            return;
        }

        if(blocks >= BLOCKS_BREAKED_QUEST_GOAL) {
            collectStatusForBlockBroken.put(playerUUID, CollectStatus.READY_TO_COLLECT);
            return;
        }
        blocksBreaked.put(playerUUID, blocks);
    }
    public static void onBlockPlace(UUID playerUUID) {
        int blocks = blocksPlaced.getOrDefault(playerUUID, 0);
        blocks++;

        if(CollectStatus.COLLECTED.equals(collectStatusForBlockPlaced.get(playerUUID)) || CollectStatus.READY_TO_COLLECT.equals(collectStatusForBlockPlaced.get(playerUUID))) {
            return;
        }

        if(blocks >= BLOCKS_PLACED_QUEST_GOAL) {
            collectStatusForBlockPlaced.put(playerUUID, CollectStatus.READY_TO_COLLECT);
            return;
        }
        blocksPlaced.put(playerUUID, blocks);
    }
    public static void onTotemOfUndyingUse(UUID playerUUID) {
        int totems = totemsOfUndyingUsed.getOrDefault(playerUUID, 0);
        totems++;

        if(CollectStatus.COLLECTED.equals(collectStatusForTotemOfUndyingUsed.get(playerUUID)) || CollectStatus.READY_TO_COLLECT.equals(collectStatusForTotemOfUndyingUsed.get(playerUUID))) {
            return;
        }

        if(totems >= TOTEM_OF_UNDYING_QUEST_GOAL) {
            collectStatusForTotemOfUndyingUsed.put(playerUUID, CollectStatus.READY_TO_COLLECT);
            return;
        }
        totemsOfUndyingUsed.put(playerUUID, totems);
    }
    public static void onPlayerKill(UUID playerUUID) {
        int kills = playerKills.getOrDefault(playerUUID, 0);
        kills++;

        if(CollectStatus.COLLECTED.equals(collectStatusForPlayerKills.get(playerUUID)) || CollectStatus.READY_TO_COLLECT.equals(collectStatusForPlayerKills.get(playerUUID))) {
            return;
        }

        if(kills >= PLAYERS_KILLED_QUEST_GOAL) {
            collectStatusForPlayerKills.put(playerUUID, CollectStatus.READY_TO_COLLECT);
            return;
        }
        playerKills.put(playerUUID, kills);
    }

    private static void collectBlocksBroken(UUID playerUUID) {
        if(CollectStatus.READY_TO_COLLECT.equals(collectStatusForBlockBroken.get(playerUUID))) {
            collectStatusForBlockBroken.put(playerUUID, CollectStatus.COLLECTED);
        }
    }
    private static void collectBlocksPlaced(UUID playerUUID) {
        if(CollectStatus.READY_TO_COLLECT.equals(collectStatusForBlockPlaced.get(playerUUID))) {
            collectStatusForBlockPlaced.put(playerUUID, CollectStatus.COLLECTED);
        }
    }
    private static void collectTotemsOfUndyingUsed(UUID playerUUID) {
        if(CollectStatus.READY_TO_COLLECT.equals(collectStatusForTotemOfUndyingUsed.get(playerUUID))) {
            collectStatusForTotemOfUndyingUsed.put(playerUUID, CollectStatus.COLLECTED);
        }
    }
    private static void collectPlayerKills(UUID playerUUID) {
        if(CollectStatus.READY_TO_COLLECT.equals(collectStatusForPlayerKills.get(playerUUID))) {
            collectStatusForPlayerKills.put(playerUUID, CollectStatus.COLLECTED);
        }
    }


}
