package me.xxgradzix.gradzixcore.clansCore.data.database.managers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ClanWithThisNameAlreadyExists;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ClanWithThisTagAlreadyExists;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ThisUserAlreadyBelongsToAnotherClan;
import me.xxgradzix.gradzixcore.clansCore.exceptions.ThisUserAlreadyIsALeaderOfAnotherClan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClanEntityManager {

    private Dao<ClanEntity, UUID> entityDao;

    public ClanEntityManager(ConnectionSource connectionSource) {
        try {
            entityDao = DaoManager.createDao(connectionSource, ClanEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createClanEntity(ClanEntity entity) throws ClanWithThisTagAlreadyExists, ClanWithThisNameAlreadyExists, ThisUserAlreadyIsALeaderOfAnotherClan, ThisUserAlreadyBelongsToAnotherClan {

        UserEntity leader = entity.getLeader();
        String tag = entity.getTag();
        String name = entity.getName();

        Optional<ClanEntity> clanEntityByLeader = getClanEntityByLeader(leader);
        Optional<ClanEntity> clanEntityByTag = getClanEntityByTag(tag);
        Optional<ClanEntity> clanEntityByName = getClanEntityByName(name);
        Optional<ClanEntity> clanEntityByMember = getClanEntityByMember(leader);

        if(clanEntityByLeader.isPresent()) throw new ThisUserAlreadyIsALeaderOfAnotherClan();
        if(clanEntityByTag.isPresent()) throw new ClanWithThisTagAlreadyExists();
        if(clanEntityByName.isPresent()) throw new ClanWithThisNameAlreadyExists();
        if(clanEntityByMember.isPresent()) throw new ThisUserAlreadyBelongsToAnotherClan();

        try {
            entityDao.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClanEntity(ClanEntity entity) {
        try {
            entityDao.update(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<ClanEntity> getClanEntityByUUID(UUID uuid) {
        try {
            return Optional.ofNullable(entityDao.queryForId(uuid));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<ClanEntity> getClanEntityByTag(String tag) {
        try {
            List<ClanEntity> clanEntitiesByTag = entityDao.queryForEq("tag", tag);
            if(clanEntitiesByTag.isEmpty()) return Optional.empty();
            if(clanEntitiesByTag.size() == 1) return Optional.ofNullable(clanEntitiesByTag.get(0));
            throw new IllegalStateException("There are more than one clan with the same tag: " + tag);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<ClanEntity> getClanEntityByName(String name) {
        try {
            List<ClanEntity> clanEntitiesByName = entityDao.queryForEq("name", name);

            if(clanEntitiesByName.isEmpty()) return Optional.empty();
            if(clanEntitiesByName.size() == 1) return Optional.ofNullable(clanEntitiesByName.get(0));

            throw new IllegalStateException("There are more than one clan with the same name: " + name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<ClanEntity> getClanEntityByLeader(UserEntity leader) {

        try {
            List<ClanEntity> clanEntitiesByLeader = entityDao.queryForEq("leader", leader);
            if(clanEntitiesByLeader.isEmpty()) return Optional.empty();
            if(clanEntitiesByLeader.size() == 1) return Optional.ofNullable(clanEntitiesByLeader.get(0));
            throw new IllegalStateException("There are more than one clan with the same leader: " + leader.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<ClanEntity> getClanEntityByMember(UserEntity member) {

        List<ClanEntity> clanEntitiesByMember = getAllClanEntities();

        for (ClanEntity clanEntity : clanEntitiesByMember) {

            if(clanEntity.getMembersUUIDs().contains(member.getUuid()) || clanEntity.getLeader().equals(member)) return Optional.of(clanEntity);
        }
        return Optional.empty();

    }

    public List<ClanEntity> getAllClanEntities() {
        try {
            return entityDao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteClanEntityByUUID(UUID id) {
        try {
            entityDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ClanEntity> getAllClans() {
        try {
            return (ArrayList<ClanEntity>) entityDao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}