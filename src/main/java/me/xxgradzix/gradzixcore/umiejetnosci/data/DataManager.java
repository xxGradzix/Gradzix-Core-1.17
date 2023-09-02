package me.xxgradzix.gradzixcore.umiejetnosci.data;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.umiejetnosci.Umiejetnosci;
import me.xxgradzix.gradzixcore.umiejetnosci.data.database.entities.AbilityModifierEntity;
import me.xxgradzix.gradzixcore.umiejetnosci.data.database.entities.PlayerAbilitiesEntity;
import me.xxgradzix.gradzixcore.umiejetnosci.data.database.entities.enums.Ability;
import me.xxgradzix.gradzixcore.umiejetnosci.data.database.managers.AbilitiesModifiersEntityManager;
import me.xxgradzix.gradzixcore.umiejetnosci.data.database.managers.PlayerAbilitiesEntityManager;
import me.xxgradzix.gradzixcore.umiejetnosci.data.configfiles.UmiejetnosciConfigFile;
import org.bukkit.entity.Player;

public class DataManager {

    private static boolean useDB = Gradzix_Core.USEDB;

    public static void incrementAbilityLevel(Ability ability, Player player) {
        if(useDB) {
            PlayerAbilitiesEntityManager manager = Umiejetnosci.getPlayerAbilitiesEntityManager();
            PlayerAbilitiesEntity entity = manager.getPlayerAbilitiesEntityById(player.getUniqueId());
            if(entity == null) {
                entity = new PlayerAbilitiesEntity(player.getUniqueId(), entity.getPlayerName(), 0, 0, 0);
            }
            entity.incrementAbilityLevel(ability);
            manager.createOrUpdatePlayerAbilitiesEntity(entity);
        } else {
//            UmiejetnosciConfigFile.incrementDropLevel(player);
        }
    }

    public static int getPlayerAbilityLevel(Ability ability, Player player) {
        if(useDB) {
            PlayerAbilitiesEntityManager manager = Umiejetnosci.getPlayerAbilitiesEntityManager();
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
            PlayerAbilitiesEntityManager manager = Umiejetnosci.getPlayerAbilitiesEntityManager();
            for(PlayerAbilitiesEntity playerAbilitiesEntity : manager.getPlayerAbilitiesEntities()) {
                manager.deletePlayerAbilitiesEntity(playerAbilitiesEntity);
            }

        } else {
            UmiejetnosciConfigFile.resetLevels();
        }
    }

    //////////////////////////


    public static double getAbilityModifier(Ability ability, int level) {

        if(useDB) {
            AbilitiesModifiersEntityManager manager = Umiejetnosci.getAbilitiesModifiersEntityManager();
            AbilityModifierEntity entity = manager.getAbilityModifierEntityByAbilityType(ability);
            return entity.getAbilityModifier(level);

        } else {
            throw new RuntimeException("Nie ma obsulgi pliku konfiguracyjnego");
//            UmiejetnosciConfigFile.resetLevels();
        }
    }
    public static void setAbilityModifier(Ability ability, int level, double modifier) {

        if(useDB) {
            AbilitiesModifiersEntityManager manager = Umiejetnosci.getAbilitiesModifiersEntityManager();
            AbilityModifierEntity entity = manager.getAbilityModifierEntityByAbilityType(ability);
            entity.setAbilityModifier(level, modifier);

        } else {
            throw new RuntimeException("Nie ma obsulgi pliku konfiguracyjnego");
//            UmiejetnosciConfigFile.resetLevels();
        }
    }


}
