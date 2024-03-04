package me.xxgradzix.gradzixcore.clansExtension.listeners.addScore;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import me.xxgradzix.gradzixcore.clansExtension.ClansExtension;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarEntity;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Optional;

public class AddWarScoreAfterKill implements Listener {

    private final WarManager warManager;

    public AddWarScoreAfterKill(WarManager warManager) {
        this.warManager = warManager;
    }

    @EventHandler
    public void onPlayerKill(EntityDeathEvent event) {

        if(!ClansExtension.ARE_WARS_ACTIVE) return;

        if(!(event.getEntity() instanceof Player)) return;

        Player killer = event.getEntity().getKiller();
        Player victim = ((Player) event.getEntity());

        UserEntity userEntity = UserManager.getOrCreateUserEntity(killer);
        UserEntity victimEntity = UserManager.getOrCreateUserEntity(victim);

        Optional<ClanEntity> killerClanOptional = ClanManager.getClanEntityOfMember(userEntity);
        Optional<ClanEntity> victimClanOptional = ClanManager.getClanEntityOfMember(victimEntity);

        if(!killerClanOptional.isPresent() || !victimClanOptional.isPresent()) return;

        ClanEntity killerGuild = killerClanOptional.get();
        ClanEntity victimGuild = victimClanOptional.get();

        Optional<WarEntity> optionalWar = warManager.getActiveWarOfGuilds(killerGuild.getUuid(), victimGuild.getUuid());

        if(!optionalWar.isPresent()) return;

        WarEntity warEntity = optionalWar.get();

        warManager.addPointForGuild(warEntity, killerGuild.getUuid());

        killer.sendMessage(Messages.YOU_KILLED_MEMBER_OF_ENEMY_GUILD);

        victim.sendMessage(Messages.YOU_WERE_KILLED_BY_MEMBER_OF_ENEMY_GUILD);

    }


}
