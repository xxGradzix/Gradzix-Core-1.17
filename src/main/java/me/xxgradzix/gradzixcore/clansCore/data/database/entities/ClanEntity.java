package me.xxgradzix.gradzixcore.clansCore.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;
import me.xxgradzix.gradzixcore.clansCore.Clans;
import me.xxgradzix.gradzixcore.clansCore.data.database.utills.ClanSetPersister;
import me.xxgradzix.gradzixcore.clansCore.data.database.utills.MembersSetPersister;
import me.xxgradzix.gradzixcore.clansCore.data.database.utills.UUIDSetPersister;

import java.io.Serializable;
import java.util.*;

@DatabaseTable(tableName = "gradzixcore_clans")
public class ClanEntity implements Serializable {

    public ClanEntity(String name, String tag, UserEntity leader) {

        this.name = name;
        this.tag = tag;
        this.leader = leader;
        HashSet<UUID> startingMembers = new HashSet<>();
        startingMembers.add(leader.getUuid());
        this.membersUUIDs = startingMembers;
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

//    @DatabaseField(persisterClass = MembersSetPersister.class, columnDefinition = "TEXT")
//    @Getter
//    @Setter
//    private Set<UserEntity> members;
    @DatabaseField(persisterClass = UUIDSetPersister.class, columnDefinition = "TEXT")
    @Getter
    @Setter
    private Set<UUID> membersUUIDs;

    @DatabaseField(persisterClass = ClanSetPersister.class, columnDefinition = "TEXT")
    @Getter
    @Setter
    private Set<ClanEntity> enemies;

    @DatabaseField(persisterClass = ClanSetPersister.class, columnDefinition = "TEXT")
    @Getter
    @Setter
    private Set<ClanEntity> allies;

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

