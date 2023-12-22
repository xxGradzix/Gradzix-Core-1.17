package me.xxgradzix.gradzixcore.clansExtension.managers;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerk;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerksEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.ClanPerksEntityManager;
import net.dzikoysk.funnyguilds.guild.Guild;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class ClanPerksManager {

    private final ClanPerksEntityManager clanPerksEntityManager;

    public ClanPerksManager(ClanPerksEntityManager clanPerksEntityManager) {
        this.clanPerksEntityManager = clanPerksEntityManager;
    }
    public int getClanPerkLevel(ClanPerk perk, @NotNull Guild guild) {
        ClanPerksEntity perksEntity;
        Optional<ClanPerksEntity> clanPerksEntityByID = clanPerksEntityManager.getClanPerksEntityByID(guild.getUUID());
        if (clanPerksEntityByID.isPresent()) {
            perksEntity = clanPerksEntityByID.get();
        } else {
            perksEntity = new ClanPerksEntity(guild.getUUID());
            clanPerksEntityManager.createOrUpdateClanPerksEntity(perksEntity);
        }
        return perksEntity.getClanPerkLevel(perk);
    }
    public void increaseClanPerkLevel(ClanPerk perk, Guild guild) {
        ClanPerksEntity perksEntity;
        Optional<ClanPerksEntity> clanPerksEntityByID = clanPerksEntityManager.getClanPerksEntityByID(guild.getUUID());
        if (clanPerksEntityByID.isPresent()) {
            perksEntity = clanPerksEntityByID.get();
        } else {
            perksEntity = new ClanPerksEntity(guild.getUUID());
            clanPerksEntityManager.createOrUpdateClanPerksEntity(perksEntity);
        }
        perksEntity.setClanPerkLevel(perk, perksEntity.getClanPerkLevel(perk) + 1);
        clanPerksEntityManager.createOrUpdateClanPerksEntity(perksEntity);
    }
}
