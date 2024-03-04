package me.xxgradzix.gradzixcore.clansCore.managers;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.clansCore.Clans;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.exceptions.*;
import me.xxgradzix.gradzixcore.clansCore.data.database.managers.ClanEntityManager;
import me.xxgradzix.gradzixcore.clansCore.data.database.managers.UserEntityManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class ClanManager {

    private static final JavaPlugin plugin = Gradzix_Core.getInstance();
    // PlayerUUID, ClanUUIDSet
    private static final HashMap<UUID, Set<UUID>> clanInvitations = new HashMap<>();

    public static void addClanInvitation(Player player, UUID clanUUID) {
        if(clanInvitations.containsKey(player.getUniqueId())) {
            Set<UUID> clanUUIDSet = clanInvitations.get(player.getUniqueId());
            clanUUIDSet.add(clanUUID);
            clanInvitations.put(player.getUniqueId(), clanUUIDSet);
        } else {
            Set<UUID> clanUUIDSet = new HashSet<>();
            clanUUIDSet.add(clanUUID);
            clanInvitations.put(player.getUniqueId(), clanUUIDSet);
        }
        scheduleClanInvitationRemoval(player, clanUUID);
    }
    public static boolean isPlayerInvitedToClan(Player player, UUID clanUUID) {
        if(clanInvitations.containsKey(player.getUniqueId())) {
            Set<UUID> clanUUIDSet = clanInvitations.get(player.getUniqueId());
            return clanUUIDSet.contains(clanUUID);
        }
        return false;
    }
    public static void removeClanInvitation(Player player, UUID clanUUID) {
        if(clanInvitations.containsKey(player.getUniqueId())) {
            Set<UUID> clanUUIDSet = clanInvitations.get(player.getUniqueId());
            clanUUIDSet.remove(clanUUID);
            if(clanUUIDSet.isEmpty()) {
                clanInvitations.remove(player.getUniqueId());
            } else {
                clanInvitations.put(player.getUniqueId(), clanUUIDSet);
            }
        }
    }
    private static void scheduleClanInvitationRemoval(Player player, UUID clanUUID) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            removeClanInvitation(player, clanUUID);
        }, 20 * 60 * 15);
    }

    private static final ClanEntityManager clanEntityManager = Clans.getClanEntityManager();
    private static final UserEntityManager userEntityManager = Clans.getUserEntityManager();

    public static void createClan(String name, String tag, Player leader) throws ClanWithThisTagAlreadyExists, ClanWithThisNameAlreadyExists, ThisUserAlreadyIsALeaderOfAnotherClan, ThisUserAlreadyBelongsToAnotherClan {

        UserEntity leaderEntity = UserManager.getOrCreateUserEntity(leader);

        ClanEntity clanEntity = new ClanEntity(name, tag, leaderEntity);

        try {
            clanEntityManager.createClanEntity(clanEntity);
        } catch (ClanWithThisTagAlreadyExists | ClanWithThisNameAlreadyExists | ThisUserAlreadyIsALeaderOfAnotherClan | ThisUserAlreadyBelongsToAnotherClan e) {
            throw e;
        }

        TeamManager.refreshTeam(clanEntity);

    }
    public static void removeClanOfPlayerByPlayer(Player player) throws ThisUserIsNotALeader {

        UserEntity userEntity = UserManager.getOrCreateUserEntity(player);

        Optional<ClanEntity> clanEntityByLeader = getClanEntityByLeader(userEntity);

        if(!clanEntityByLeader.isPresent()) throw new ThisUserIsNotALeader();

        ClanEntity clanEntity = clanEntityByLeader.get();

        clanEntityManager.deleteClanEntityByUUID(clanEntity.getUuid());

        TeamManager.removeTeam(clanEntity);
    }

    public static void removeClan(ClanEntity clanEntity) {
        clanEntityManager.deleteClanEntityByUUID(clanEntity.getUuid());
        TeamManager.removeTeam(clanEntity);
    }

    public static void addMemberToClan(UUID clanUUID, Player player) throws ThisUserAlreadyBelongsToAnotherClan, ClanWithThisUUIDDoesNotExists, ThisUserAlreadyBelongsToThisClan {

        UserEntity userEntity = UserManager.getOrCreateUserEntity(player);

        Optional<ClanEntity> clanEntityOptional = clanEntityManager.getClanEntityByUUID(clanUUID);

        if(!clanEntityOptional.isPresent()) throw new ClanWithThisUUIDDoesNotExists();

        ClanEntity clanEntity = clanEntityOptional.get();

        Optional<ClanEntity> clanEntityByMember = clanEntityManager.getClanEntityByMember(userEntity);
        Optional<ClanEntity> clanEntityByLeader = clanEntityManager.getClanEntityByLeader(userEntity);
        if(clanEntityByMember.isPresent() || clanEntityByLeader.isPresent()) throw new ThisUserAlreadyBelongsToAnotherClan();

        Set<UUID> members = clanEntity.getMembersUUIDs();

        if(members.contains(userEntity.getUuid())) throw new ThisUserAlreadyBelongsToThisClan();

        members.add(userEntity.getUuid());
        clanEntity.setMembersUUIDs(members);


        clanEntityManager.updateClanEntity(clanEntity);

        TeamManager.refreshTeam(clanEntity);
    }

    public static void removeMemberFromClan(UUID clanUUID, Player player) throws ThisClanDoesNotExists, ThisUserDoesNotBelongToThisClan {

        UserEntity userEntity = UserManager.getOrCreateUserEntity(player);

        Optional<ClanEntity> clanEntityOptional = clanEntityManager.getClanEntityByUUID(clanUUID);

        if(!clanEntityOptional.isPresent()) throw new ThisClanDoesNotExists();

        ClanEntity clanEntity = clanEntityOptional.get();

        UserEntity userToKick = UserManager.getOrCreateUserEntity(player);

        Set<UUID> members = clanEntity.getMembersUUIDs();

        if(!members.contains(userToKick.getUuid())) throw new ThisUserDoesNotBelongToThisClan();

        members.remove(userEntity.getUuid());
        clanEntity.setMembersUUIDs(members);

        clanEntityManager.updateClanEntity(clanEntity);

        TeamManager.refreshTeam(clanEntity);
    }
    public static void removeMemberFromClan(Player player) {

        UserEntity userEntity = UserManager.getOrCreateUserEntity(player);

        Optional<ClanEntity> clanEntityOptional = clanEntityManager.getClanEntityByMember(userEntity);

        if(!clanEntityOptional.isPresent()) return;

        ClanEntity clanEntity = clanEntityOptional.get();

        Set<UUID> members = clanEntity.getMembersUUIDs();
        members.remove(userEntity.getUuid());
        clanEntity.setMembersUUIDs(members);

        clanEntityManager.updateClanEntity(clanEntity);

        TeamManager.refreshTeam(clanEntity);
    }


    public static void updateClan(ClanEntity clanEntity) {
        clanEntityManager.updateClanEntity(clanEntity);
        TeamManager.refreshTeam(clanEntity);
    }

    public static Optional<ClanEntity> getClanEntityByUUID(UUID uuid) {
        return clanEntityManager.getClanEntityByUUID(uuid);
    }
    public static Optional<ClanEntity> getClanEntityByTag(String tag) {
        return clanEntityManager.getClanEntityByTag(tag);
    }
    public static Optional<ClanEntity> getClanEntityByLeader(UserEntity leader) {
        return clanEntityManager.getClanEntityByLeader(leader);
    }
    public static Optional<ClanEntity> getClanEntityByClanMember(UserEntity member) {
        return clanEntityManager.getClanEntityByMember(member);
    }
    public static Optional<ClanEntity> getClanEntityOfMember(UserEntity member) {
        return clanEntityManager.getClanEntityByMember(member);
    }


    public static ArrayList<ClanEntity> getAllClans() {
        return clanEntityManager.getAllClans();
    }
}
