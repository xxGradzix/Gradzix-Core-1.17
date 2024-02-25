package me.xxgradzix.gradzixcore.clansCore.data.database.entities;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;
import me.xxgradzix.gradzixcore.clansCore.Clans;
import me.xxgradzix.gradzixcore.clansCore.data.database.MembersSetPersister;
import me.xxgradzix.gradzixcore.generators.data.database.persisters.LocationClassPersister;
import org.checkerframework.checker.units.qual.A;

import java.util.*;

@DatabaseTable(tableName = "gradzixcore_clans")
public class ClanEntity {

    public ClanEntity(String name, String tag, UserEntity leader) {

        this.name = name;
        this.tag = tag;
        this.leader = leader;
        this.members = new HashSet<>();
        this.points = Clans.DEFAULT_POINTS;
        this.lives = Clans.DEFAULT_LIVES;
        this.born = System.currentTimeMillis();
        this.pvp = false;

    }

    public ClanEntity() {
    }

    @DatabaseField(generatedId = true)
    @Getter
    private UUID uuid;

    @DatabaseField(unique = true, columnName = "name")
    @Getter
    private String name;

    @DatabaseField(unique = true, columnName = "tag")
    @Getter
    private String tag;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "leader")
    @Getter
    @Setter
    private UserEntity leader;

    @DatabaseField(persisterClass = MembersSetPersister.class, columnDefinition = "TEXT")
    @Getter
    @Setter
    private Set<UserEntity> members;

    @DatabaseField
    @Getter
    @Setter
    private Integer points;

    @DatabaseField
    @Getter
    @Setter
    private Integer lives;

    @DatabaseField
    @Getter
    private Long born;

    @DatabaseField
    @Getter
    @Setter
    private boolean pvp;


}

