package me.xxgradzix.gradzixcore.clansCore.managers;

import me.xxgradzix.gradzixcore.clansCore.Clans;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.managers.ClanEntityManager;
import me.xxgradzix.gradzixcore.clansCore.data.database.managers.UserEntityManager;
import me.xxgradzix.gradzixcore.clansCore.exceptions.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class UserManager {

    private static final UserEntityManager userEntityManager = Clans.getUserEntityManager();


    public static UserEntity getOrCreateUserEntity(Player player) {

        Optional<UserEntity> userEntityOptional = userEntityManager.geUserEntityByUUID(player.getUniqueId());

        if(!userEntityOptional.isPresent()) {
            return createUser(player);
        }
        return userEntityOptional.get();
    }

    public static Player parsePlayer(UserEntity userEntity) {
        return Bukkit.getPlayer(userEntity.getUuid());
    }

    public static Optional<UserEntity> getUserEntityByNick(String nick) {
        Optional<UserEntity> userEntityOptional = userEntityManager.geUserEntityByName(nick);

        if(userEntityOptional.isPresent()) {
            Player player;
            try {
                player = Bukkit.getPlayer(nick);
            } catch (Exception e) {
                return Optional.empty();
            }
            if(player != null) return Optional.of(getOrCreateUserEntity(player));
        }

        return userEntityOptional;
    }
    public static Optional<UserEntity> getUserEntityByUUID(UUID uuid) {
        Optional<UserEntity> userEntityOptional = userEntityManager.geUserEntityByUUID(uuid);

        if(userEntityOptional.isPresent()) {
            Player player;
            try {
                player = Bukkit.getPlayer(uuid);
            } catch (Exception e) {
                return Optional.empty();
            }
            if(player != null) return Optional.of(getOrCreateUserEntity(player));
        }

        return userEntityOptional;
    }

    public static void updateUserEntity(UserEntity userEntity) {
        userEntityManager.createOrUpdateUserEntity(userEntity);
    }
    private static UserEntity createUser(Player player) {

        UserEntity userEntity = new UserEntity(player.getUniqueId(), player.getName());
        userEntityManager.createOrUpdateUserEntity(userEntity);

        return userEntity;
    }

}
