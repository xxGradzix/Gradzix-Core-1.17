package me.xxgradzix.gradzixcore.clansExtension.listeners.perks.rankPerk;

import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerk;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerksEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.PerkModifierEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.ClanPerksEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.PerkModifierEntityManager;
import net.dzikoysk.funnyguilds.event.rank.CombatPointsChangeEvent;
import net.dzikoysk.funnyguilds.guild.Guild;
import net.dzikoysk.funnyguilds.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import panda.std.Option;

public class PointChangeEvent implements Listener {

    private final ClanPerksEntityManager clanPerksEntityManager;

    public PointChangeEvent(ClanPerksEntityManager clanPerksEntityManager) {
        this.clanPerksEntityManager = clanPerksEntityManager;
    }
    @EventHandler
    public void onPointChange(CombatPointsChangeEvent event) {
        User user = event.getAttacker();

        Option<Guild> guildOption = user.getGuild();
        if(guildOption.isEmpty()) return;

        Guild guild = guildOption.get();
        ClanPerksEntity clanPerksEntity = clanPerksEntityManager.getClanPerksEntityByID(guild.getUUID());

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
            if(event.getAttackerPointsChange() > 0)
                event.setAttackerPointsChange((int) (event.getAttackerPointsChange() + perkModifierEntity.getPerkModifierPerLevel(clanPerksEntity.getClanPerkLevel(ClanPerk.RANK))));
        } catch (IllegalArgumentException ignored) {

        }
    }

}
