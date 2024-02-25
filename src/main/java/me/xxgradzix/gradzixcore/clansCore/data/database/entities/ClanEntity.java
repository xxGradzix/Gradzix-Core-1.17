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

        this.name = name.toUpperCase();
        this.tag = tag.toUpperCase();
        this.leader = leader;
        HashSet<UUID> startingMembers = new HashSet<>();
        startingMembers.add(leader.getUuid());
        this.membersUUIDs = startingMembers;

        HashSet<UUID> startingEnemies = new HashSet<>();
        this.enemiesUUIDs = startingEnemies;
        HashSet<UUID> startingAllies = new HashSet<>();
        this.alliesUUIDs = startingAllies;

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

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "deputy")
    @Getter
    @Setter
    private UserEntity deputy;

//    @DatabaseField(persisterClass = MembersSetPersister.class, columnDefinition = "TEXT")
//    @Getter
//    @Setter
//    private Set<UserEntity> members;
    @DatabaseField(persisterClass = UUIDSetPersister.class, columnDefinition = "TEXT")
    @Getter
    @Setter
    private Set<UUID> membersUUIDs;

    public void setMembersUUIDs(Set<UUID> membersUUIDs) {
        this.membersUUIDs = membersUUIDs;
    }

    @DatabaseField(persisterClass = UUIDSetPersister.class, columnDefinition = "TEXT")
    @Getter
    @Setter
    private Set<UUID> enemiesUUIDs;

    public void setEnemiesUUIDs(Set<UUID> enemiesUUIDs) {
        this.enemiesUUIDs = enemiesUUIDs;
    }

    @DatabaseField(persisterClass = UUIDSetPersister.class, columnDefinition = "TEXT")
    @Getter
    @Setter
    private Set<UUID> alliesUUIDs;

    public void setAlliesUUIDs(Set<UUID> alliesUUIDs) {
        this.alliesUUIDs = alliesUUIDs;
    }

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

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof ClanEntity)) return false;
        ClanEntity clanEntity = (ClanEntity) obj;
        return clanEntity.getUuid().equals(this.getUuid());
    }
}

