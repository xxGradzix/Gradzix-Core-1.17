package me.xxgradzix.gradzixcore.dailyQuests;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.achievements.database.DataManager;
import me.xxgradzix.gradzixcore.achievements.database.entities.PlayerAchievementsProgressEntity;
import me.xxgradzix.gradzixcore.achievements.database.managers.PlayerAchievementProgressEntityManager;

import java.sql.SQLException;

public class DailyQuests {

    private final Gradzix_Core plugin;
    private final ConnectionSource connectionSource;


    public DailyQuests(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    private static DataManager dataManager;

    public void onEnable() {



    }

}
