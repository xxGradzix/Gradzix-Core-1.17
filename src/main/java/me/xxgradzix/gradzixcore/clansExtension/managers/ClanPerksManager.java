package me.xxgradzix.gradzixcore.clansExtension.managers;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerk;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerksEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.ClanPerksEntityManager;
import org.jetbrains.annotations.NotNull;

public class ClanPerksManager {

    private final ClanPerksEntityManager clanPerksEntityManager;

    public ClanPerksManager(ClanPerksEntityManager clanPerksEntityManager) {
        this.clanPerksEntityManager = clanPerksEntityManager;
    }
    public int getClanPerkLevel(ClanPerk perk, @NotNull ClanEntity clanEntity) {

        ClanPerksEntity perksEntity = clanPerksEntityManager.getClanPerksEntityByID(clanEntity.getUuid());

        return perksEntity.getClanPerkLevel(perk);
    }
    public void increaseClanPerkLevel(ClanPerk perk, ClanEntity clanEntity) {

        ClanPerksEntity perksEntity = clanPerksEntityManager.getClanPerksEntityByID(clanEntity.getUuid());
        perksEntity.setClanPerkLevel(perk, perksEntity.getClanPerkLevel(perk) + 1);
        clanPerksEntityManager.createOrUpdateClanPerksEntity(perksEntity);
    }
}
