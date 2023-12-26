package me.xxgradzix.gradzixcore.clansExtension.managers;

import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.*;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.ClanPerksEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.PerkModifierEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.WarEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.WarRecordEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.TheyAlreadyHaveWarException;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.YouAlreadyHaveMaxAmountOfWarsException;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.YouAlreadyHaveWarException;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.event.FunnyEvent;
import net.dzikoysk.funnyguilds.event.guild.GuildLivesChangeEvent;
import net.dzikoysk.funnyguilds.guild.Guild;
import net.dzikoysk.funnyguilds.guild.GuildManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import panda.std.Option;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class WarManager {

    private final WarEntityManager warEntityManager;
    private final WarRecordEntityManager warRecordEntityManager;
    private final ClanPerksEntityManager clanPerksEntityManager;
    private final FunnyGuilds funnyGuilds;

    public WarManager(WarEntityManager warEntityManager, WarRecordEntityManager warRecordEntityManager, FunnyGuilds funnyGuilds, ClanPerksEntityManager clanPerksEntityManager) {
        this.warEntityManager = warEntityManager;
        this.warRecordEntityManager = warRecordEntityManager;
        this.funnyGuilds = funnyGuilds;
        this.clanPerksEntityManager = clanPerksEntityManager;
    }
    public void declareWar(Guild invaderGuild, Guild invadedGuild) throws YouAlreadyHaveMaxAmountOfWarsException {

        UUID invaderGuildId = invaderGuild.getUUID();
        UUID invadedGuildId = invadedGuild.getUUID();

        List<WarEntity> nonEndedInvaderGuildWarEntities = warEntityManager.getWarsByGuildId(invaderGuildId, WAR_STATE.FUTURE);
        nonEndedInvaderGuildWarEntities.addAll(warEntityManager.getWarsByGuildId(invaderGuildId, WAR_STATE.CURRENT));

        int maxInvaderWars = 1;
        ClanPerksEntity clanPerksEntity = clanPerksEntityManager.getClanPerksEntityByID(invaderGuildId);
        int clanPerkLevel = clanPerksEntity.getClanPerkLevel(ClanPerk.WAR_AMOUNT);

        maxInvaderWars = (int) PerkModifierEntityManager.getPerkModifierEntityByID(ClanPerk.WAR_AMOUNT).getPerkModifierPerLevel(clanPerkLevel);

//        if(!nonEndedInvaderGuildWarEntities.isEmpty()) {
//            throw new YouAlreadyHaveWarException("You already have war");
//        }

        if(nonEndedInvaderGuildWarEntities.size() >= maxInvaderWars) {
            throw new YouAlreadyHaveMaxAmountOfWarsException("You already have max amount of wars");
        }

        List<WarEntity> nonEndedInvadedGuildWarEntities = warEntityManager.getWarsByGuildId(invadedGuildId, WAR_STATE.FUTURE);
        nonEndedInvadedGuildWarEntities.addAll(warEntityManager.getWarsByGuildId(invadedGuildId, WAR_STATE.CURRENT));

//        if(!nonEndedInvadedGuildWarEntities.isEmpty()) {
////        int maxGuildWars = 1;
////        clanPerksEntityManager.getClanPerksEntityByID(invaded)
////        TODO pogadac z aftem a propo ilosci wojen (czy oboje gracze musza miec perk na tym samym poziomie?) czy liczy sie to jaki perk ma osoba wypowiadajaca wojne
////        if(nonEndedInvadedGuildWarEntities.size() >= ) {
//            throw new TheyAlreadyHaveWarException("This guild already is in war");
//        }

        LocalDate now = LocalDate.now();

        LocalDateTime warStart = now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).atTime(12, 00);
        LocalDateTime warEnd = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).atTime(18, 00);

        WarEntity warEntity = new WarEntity(
                invaderGuild.getUUID(),
                invadedGuild.getUUID(),
                0,
                0,
                warStart,
                warEnd,
                WAR_STATE.FUTURE
        );
        warEntityManager.createWar(warEntity);
    }

    public void startWars() {
        List<WarEntity> warEntities = warEntityManager.getAllWars().stream()
                .filter(war -> war.getWarState().equals(WAR_STATE.FUTURE)
//                        && LocalDateTime.now().isAfter(war.getWarStart()) && LocalDateTime.now().isAfter(war.getWarEnd())
                )
                .collect(Collectors.toList());


        warEntities.forEach((war) -> {

            war.setWarState(WAR_STATE.CURRENT);
            UUID invaderGuildId = war.getInvaderGuildId();
            UUID invadedGuildId = war.getInvadedGuildId();

            Option<Guild> invaderGuildOption = funnyGuilds.getGuildManager().findByUuid(invaderGuildId);
            Option<Guild> invadedGuildOption = funnyGuilds.getGuildManager().findByUuid(invadedGuildId);

            if(invaderGuildOption.isPresent() && invadedGuildOption.isPresent()) {
                Guild invaderGuild = invaderGuildOption.get();
                Guild invadedGuild = invadedGuildOption.get();

                invaderGuild.getOnlineMembers().forEach(onlinePlayer -> onlinePlayer.sendMessage(Messages.YOUR_WAR_WITH_CLAN_XXXX_HAS_STARTED(invadedGuild.getTag())));
                invadedGuild.getOnlineMembers().forEach(onlinePlayer -> onlinePlayer.sendMessage(Messages.YOUR_WAR_WITH_CLAN_XXXX_HAS_STARTED(invaderGuild.getTag())));
            }

            warEntityManager.createOrUpdateWar(war);
        });
    }
    public void endWars() {
        List<WarEntity> warEntities = warEntityManager.getAllWars().stream()
                .filter(war -> war.getWarState().equals(WAR_STATE.CURRENT))
                .collect(Collectors.toList());

        warEntities.forEach(war -> {
            endWar(war);
        });
    }

    public List<WarEntity> getNonEndedGuildWars(UUID id) {

        return warEntityManager.getActiveWarsByGuildId(id);
    }
    public List<WarRecordEntity> getAllEndedWarsByGuildId(UUID id) {
        return warRecordEntityManager.getWarRecordsByGuildId(id);
    }

    public void addPointForGuild(WarEntity warEntity, UUID guildId) {

        if(guildId.equals(warEntity.getInvadedGuildId())) {
            int previousPoints = warEntity.getInvadedScore();
            previousPoints++;
            warEntity.setInvadedScore(previousPoints);
        }
        if(guildId.equals(warEntity.getInvaderGuildId())) {
            int previousPoints = warEntity.getInvaderScore();
            previousPoints++;
            warEntity.setInvaderScore(previousPoints);
        }
        warEntityManager.createOrUpdateWar(warEntity);
    }

    @Nullable
    public UUID getWinnerGuildUUID(WarEntity warEntity) {

        int invaderKills = warEntity.getInvaderScore();
        int invadedKills = warEntity.getInvadedScore();

        if(invaderKills == invadedKills) return null; // draw

        if(invaderKills > invadedKills) return warEntity.getInvaderGuildId();

        if(invaderKills < invadedKills) return warEntity.getInvadedGuildId();

        return null;
    }
    @Nullable
    public UUID getLooserGuildUUID(WarEntity warEntity) {

        int invaderKills = warEntity.getInvaderScore();
        int invadedKills = warEntity.getInvadedScore();

        if(invaderKills == invadedKills) return null; // draw


        if(invaderKills < invadedKills) return warEntity.getInvaderGuildId();

        if(invaderKills > invadedKills) return warEntity.getInvadedGuildId();

        return null;
    }

    public Optional<WarEntity> getActiveWarOfGuilds(UUID guild1Id, UUID guild2Id) {

        List<WarEntity> warEntityByGuildIds = warEntityManager.getWarByGuildIds(guild1Id, guild2Id, WAR_STATE.CURRENT);

        if(!warEntityByGuildIds.isEmpty()) return warEntityByGuildIds.stream().findFirst();

        return Optional.empty();
    }


    public boolean canCollectReward(WarRecordEntity warRecordEntity) {
        return warRecordEntity.isRewardCollected();
    }

    public void endWar(WarEntity warEntity) {
        UUID looserGuildUUID = getLooserGuildUUID(warEntity);

        String invaderTag = "ERROR";
        String invadedTag = "ERROR";
        GuildManager guildManager = funnyGuilds.getGuildManager();

        Option<Guild> invaderOptionalGuild = guildManager.findByUuid(warEntity.getInvaderGuildId());
        if(invaderOptionalGuild.isPresent()) invaderTag = invaderOptionalGuild.get().getTag();

        Option<Guild> invadedOptionalGuild = guildManager.findByUuid(warEntity.getInvadedGuildId());
        if(invadedOptionalGuild.isPresent()) invadedTag = invadedOptionalGuild.get().getTag();

        if(invaderOptionalGuild.isPresent() && invadedOptionalGuild.isPresent()) {
            Guild invaderGuild = invaderOptionalGuild.get();
            Guild invadedGuild = invadedOptionalGuild.get();

            invaderGuild.getOnlineMembers().forEach(onlinePlayer -> onlinePlayer.sendMessage(Messages.YOUR_WAR_WITH_CLAN_XXXX_HAS_ENDED(invadedGuild.getTag())));
            invadedGuild.getOnlineMembers().forEach(onlinePlayer -> onlinePlayer.sendMessage(Messages.YOUR_WAR_WITH_CLAN_XXXX_HAS_ENDED(invaderGuild.getTag())));
        }

        WarRecordEntity invaderRecord = new WarRecordEntity(warEntity.getInvaderGuildId(),
                invaderTag,
                invadedTag,
                warEntity.getInvaderScore(),
                warEntity.getInvadedScore(),
                warEntity.getWarStart(),
                warEntity.getWarEnd());

        WarRecordEntity invadedRecord = new WarRecordEntity(warEntity.getInvadedGuildId(),
                invadedTag,
                invaderTag,
                warEntity.getInvadedScore(),
                warEntity.getInvaderScore(),
                warEntity.getWarStart(),
                warEntity.getWarEnd());

        warRecordEntityManager.createWarRecordEntity(invaderRecord);
        warRecordEntityManager.createWarRecordEntity(invadedRecord);

        if(looserGuildUUID != null) {

            Option<Guild> byUuid = guildManager.findByUuid(looserGuildUUID);

            if(byUuid.isPresent()){
                Guild guild = byUuid.get();
                int oldLives = guild.getLives();
                oldLives--;
                guild.setLives(oldLives);
                Bukkit.getServer().getPluginManager().callEvent(new GuildLivesChangeEvent(FunnyEvent.EventCause.COMBAT, null, guild, oldLives));
            }
        }
        warEntity.setWarState(WAR_STATE.FINISHED);

        warEntityManager.deleteWarById(warEntity.getId());
    }


    public void collectReward(Player player, WarRecordEntity warRecord) {
        warRecord.setRewardCollected(true);
        warRecordEntityManager.createOrUpdateWarRecordEntity(warRecord);

        player.getInventory().addItem(new ItemStack(Material.FIRE));
    }
}
