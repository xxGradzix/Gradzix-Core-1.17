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

        ClanPerksEntity perksEntity = clanPerksEntityManager.getClanPerksEntityByID(guild.getUUID());

        return perksEntity.getClanPerkLevel(perk);
    }
    public void increaseClanPerkLevel(ClanPerk perk, Guild guild) {

        ClanPerksEntity perksEntity = clanPerksEntityManager.getClanPerksEntityByID(guild.getUUID());

        perksEntity.setClanPerkLevel(perk, perksEntity.getClanPerkLevel(perk) + 1);
        clanPerksEntityManager.createOrUpdateClanPerksEntity(perksEntity);
    }
}
