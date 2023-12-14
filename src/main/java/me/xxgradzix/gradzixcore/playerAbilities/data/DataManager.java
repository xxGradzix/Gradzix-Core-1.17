package me.xxgradzix.gradzixcore.playerAbilities.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.playerAbilities.PlayerAbilities;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.AbilityModifierEntity;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.PlayerAbilitiesEntity;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.enums.Ability;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.managers.AbilitiesModifiersEntityManager;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.managers.PlayerAbilitiesEntityManager;
import me.xxgradzix.gradzixcore.playerAbilities.data.configfiles.UmiejetnosciConfigFile;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataManager {

    private static boolean useDB = Gradzix_Core.USEDB;

    public static void incrementAbilityLevel(Ability ability, Player player) {
        if(useDB) {
            PlayerAbilitiesEntityManager manager = PlayerAbilities.getPlayerAbilitiesEntityManager();
            PlayerAbilitiesEntity entity = manager.getPlayerAbilitiesEntityById(player.getUniqueId());
            if(entity == null) {
                entity = new PlayerAbilitiesEntity(player.getUniqueId(), entity.getPlayerName(), 0, 0, 0);
            }
            entity.incrementAbilityLevel(ability);
            manager.createOrUpdatePlayerAbilitiesEntity(entity);
        } else {

        }
        refreshAbilities(player);
    }

    private static final Map<UUID, Double> dropAbilityModifierCache = new HashMap<>();
    public static double getDropAbilityModifier(Player player) {
        if (!dropAbilityModifierCache.containsKey(player.getUniqueId())) {
            refreshDropAbilityModifier(player);
        }
        return dropAbilityModifierCache.get(player.getUniqueId());
    }
    private static void refreshDropAbilityModifier(Player player) {
        dropAbilityModifierCache.put(player.getUniqueId(), getAbilityModifier(Ability.DROP, getPlayerAbilityLevel(Ability.DROP, player)));
    }

    private static final Map<UUID, Double> rankAbilityModifierCache = new HashMap<>();
    public static double getRankAbilityModifier(Player player) {
        if (!rankAbilityModifierCache.containsKey(player.getUniqueId())) {
            refreshRankAbilityModifier(player);
        }
        return rankAbilityModifierCache.get(player.getUniqueId());
    }

    private static void refreshRankAbilityModifier(Player player) {
        rankAbilityModifierCache.put(player.getUniqueId(), getAbilityModifier(Ability.RANK, getPlayerAbilityLevel(Ability.RANK, player)));
    }

    public static void refreshAbilities(Player player) {
        refreshDropAbilityModifier(player);
        refreshRankAbilityModifier(player);
    }

    public static int getPlayerAbilityLevel(Ability ability, Player player) {
        if(useDB) {
            PlayerAbilitiesEntityManager manager = PlayerAbilities.getPlayerAbilitiesEntityManager();
            PlayerAbilitiesEntity entity = manager.getPlayerAbilitiesEntityById(player.getUniqueId());

            if(entity == null) {
                entity = new PlayerAbilitiesEntity(player.getUniqueId(), player.getName(), 0, 0, 0);
                manager.createOrUpdatePlayerAbilitiesEntity(entity);
            }

            return entity.getAbilityLevel(ability);
        } else {
            return UmiejetnosciConfigFile.getDropLevel(player);
        }
    }

    public static void resetAllAbilities() {
        if(useDB) {
            PlayerAbilitiesEntityManager manager = PlayerAbilities.getPlayerAbilitiesEntityManager();
            for(PlayerAbilitiesEntity playerAbilitiesEntity : manager.getPlayerAbilitiesEntities()) {
                manager.deletePlayerAbilitiesEntity(playerAbilitiesEntity);
            }

        } else {
            UmiejetnosciConfigFile.resetLevels();
        }
    }


    public static double getAbilityModifier(Ability ability, int level) {

        if(useDB) {
            AbilitiesModifiersEntityManager manager = PlayerAbilities.getAbilitiesModifiersEntityManager();
            AbilityModifierEntity entity = manager.getAbilityModifierEntityByAbilityType(ability);
            return entity.getAbilityModifier(level);

        } else {
            throw new RuntimeException("Nie ma obsługi pliku konfiguracyjnego");
        }
    }
    public static void setAbilityModifier(Ability ability, int level, double modifier) {

        if(useDB) {
            AbilitiesModifiersEntityManager manager = PlayerAbilities.getAbilitiesModifiersEntityManager();
            AbilityModifierEntity entity = manager.getAbilityModifierEntityByAbilityType(ability);
            entity.setAbilityModifier(level, modifier);
            manager.updateAbilityModifierEntity(entity);

        } else {
            throw new RuntimeException("Nie ma obsługi pliku konfiguracyjnego");
        }
    }


}
