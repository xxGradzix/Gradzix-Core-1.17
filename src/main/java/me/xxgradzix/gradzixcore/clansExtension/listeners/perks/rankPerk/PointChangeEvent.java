package me.xxgradzix.gradzixcore.clansExtension.listeners.perks.rankPerk;

import org.bukkit.event.Listener;

public class PointChangeEvent implements Listener {
//
//    private final ClanPerksEntityManager clanPerksEntityManager;
//
//    public PointChangeEvent(ClanPerksEntityManager clanPerksEntityManager) {
//        this.clanPerksEntityManager = clanPerksEntityManager;
//    }
//    @EventHandler
//    public void onPointChange(CombatPointsChangeEvent event) {
//        User user = event.getAttacker();
//
//        Option<Guild> guildOption = user.getGuild();
//        if(guildOption.isEmpty()) return;
//
//        Guild guild = guildOption.get();
//        ClanPerksEntity clanPerksEntity = clanPerksEntityManager.getClanPerksEntityByID(guild.getUUID());
//
//        clanPerksEntity.getClanPerkLevel(ClanPerk.RANK);
//
//        PerkModifierEntity perkModifierEntity;
//
//        try {
//            perkModifierEntity = PerkModifierEntityManager.getPerkModifierEntityByID(ClanPerk.RANK);
//        } catch (IllegalArgumentException e) {
//            return;
//        }
//
//        if(perkModifierEntity == null) return;
//
//        int clanPerkLevel = clanPerksEntity.getClanPerkLevel(ClanPerk.RANK);
//
//        if(clanPerkLevel == 0) return;
//
//        try {
//            if(event.getAttackerPointsChange() > 0)
//                event.setAttackerPointsChange((int) (event.getAttackerPointsChange() + perkModifierEntity.getPerkModifierPerLevel(clanPerksEntity.getClanPerkLevel(ClanPerk.RANK))));
//        } catch (IllegalArgumentException ignored) {
//
//        }
//    }

}
