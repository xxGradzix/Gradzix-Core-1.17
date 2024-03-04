package me.xxgradzix.gradzixcore.clansExtension.listeners.perks.rankPerk;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.events.UserPointsChangeEvent;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerk;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerksEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.PerkModifierEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.ClanPerksEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.PerkModifierEntityManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Optional;

public class PointChangeEvent implements Listener {

    private final ClanPerksEntityManager clanPerksEntityManager;

    public PointChangeEvent(ClanPerksEntityManager clanPerksEntityManager) {
        this.clanPerksEntityManager = clanPerksEntityManager;
    }
    @EventHandler
    public void onPointChange(UserPointsChangeEvent event) {
        Player killer = event.getKiller();

        UserEntity user = UserManager.getOrCreateUserEntity(killer);

        Optional<ClanEntity> clanEntityOptional = ClanManager.getClanEntityOfMember(user);
        if(!clanEntityOptional.isPresent()) return;

        ClanEntity clanEntity = clanEntityOptional.get();
        ClanPerksEntity clanPerksEntity = clanPerksEntityManager.getClanPerksEntityByID(clanEntity.getUuid());

        clanPerksEntity.getClanPerkLevel(ClanPerk.RANK);

        PerkModifierEntity perkModifierEntity;

        try {
            perkModifierEntity = PerkModifierEntityManager.getPerkModifierEntityByID(ClanPerk.RANK);
        } catch (IllegalArgumentException e) {
            return;
        }

        if(perkModifierEntity == null) return;

        int clanPerkLevel = clanPerksEntity.getClanPerkLevel(ClanPerk.RANK);

        if(clanPerkLevel == 0) return;

        try {
            if(event.getKillerPointsToGet() > 0)
                event.setKillerPointsToGet((int) (event.getKillerPointsToGet() + perkModifierEntity.getPerkModifierPerLevel(clanPerksEntity.getClanPerkLevel(ClanPerk.RANK))));
        } catch (IllegalArgumentException ignored) {

        }
    }

}
