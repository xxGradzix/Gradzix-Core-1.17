package me.xxgradzix.gradzixcore.clansExtension.managers;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.events.ClanLivesChangeEvent;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import me.xxgradzix.gradzixcore.clansExtension.ClansExtension;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerk;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerksEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarRecordEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.ClanPerksEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.PerkModifierEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.WarEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.WarRecordEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.CantDeclareWarDuringWarTimeException;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.YouAlreadyHaveMaxAmountOfWarsException;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.YouAlreadyHaveWarWithThisGuildException;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class WarManager {

    private final WarEntityManager warEntityManager;
    private final WarRecordEntityManager warRecordEntityManager;
    private final ClanPerksEntityManager clanPerksEntityManager;

    public WarManager(WarEntityManager warEntityManager, WarRecordEntityManager warRecordEntityManager, ClanPerksEntityManager clanPerksEntityManager) {
        this.warEntityManager = warEntityManager;
        this.warRecordEntityManager = warRecordEntityManager;
        this.clanPerksEntityManager = clanPerksEntityManager;
    }
    public void declareWar(ClanEntity invaderGuild, ClanEntity invadedGuild) throws YouAlreadyHaveMaxAmountOfWarsException, CantDeclareWarDuringWarTimeException, YouAlreadyHaveWarWithThisGuildException {

        if (ClansExtension.ARE_WARS_ACTIVE) {
            throw new CantDeclareWarDuringWarTimeException("You already have war");
        }

        UUID invaderGuildId = invaderGuild.getUuid();
        UUID invadedGuildId = invadedGuild.getUuid();

        /*** WOJNY ZAPLANOWANE WYPOWIEDZIANE PRZEZ ATAKUJACEGO */
        Set<WarEntity> nonEndedInvaderGuildWarEntities = warEntityManager.getInvaderWarsByGuildId(invaderGuildId, false, false);

        int maxInvaderWars = 1;

        {
            ClanPerksEntity invaderClanPerksEntity = clanPerksEntityManager.getClanPerksEntityByID(invaderGuildId);
            int invaderWarAmountPerkLevel = invaderClanPerksEntity.getClanPerkLevel(ClanPerk.WAR_AMOUNT);
            if(invaderWarAmountPerkLevel > 0) maxInvaderWars = (int) PerkModifierEntityManager.getPerkModifierEntityByID(ClanPerk.WAR_AMOUNT).getPerkModifierPerLevel(invaderWarAmountPerkLevel);
        }


        if(nonEndedInvaderGuildWarEntities.size() >= maxInvaderWars) {
            throw new YouAlreadyHaveMaxAmountOfWarsException("You already have max amount of wars");
        }

        Set<WarEntity> commonWars = warEntityManager.getWarByGuildIds(invaderGuildId, invadedGuildId, false, false);
        if(!commonWars.isEmpty()) {
            throw new YouAlreadyHaveWarWithThisGuildException("You already have war with this guild");
        }

        LocalDate now = LocalDate.now();
        LocalDateTime warStart = now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).atTime(12, 00);
        LocalDateTime warEnd = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).atTime(18, 00);

        WarEntity warEntity = new WarEntity(
                invaderGuild.getUuid(),
                invadedGuild.getUuid(),
                0,
                0,
                warStart,
                warEnd
        );

        warEntityManager.createWar(warEntity);
    }

    public void startWars() {


        List<WarEntity> warEntities = warEntityManager.getAllWars();
        Bukkit.broadcastMessage("Startowanie wojen " + warEntities.size());

        for (WarEntity war : warEntities) {

            UUID invaderGuildId = war.getInvaderGuildId();
            UUID invadedGuildId = war.getInvadedGuildId();

            Optional<ClanEntity> invaderClanOptional = ClanManager.getClanEntityByUUID(invaderGuildId);
            Optional<ClanEntity> invadedClanOptional = ClanManager.getClanEntityByUUID(invadedGuildId);

            if (invaderClanOptional.isPresent() && invadedClanOptional.isPresent()) {
                ClanEntity invaderClan = invaderClanOptional.get();
                ClanEntity invadedClan = invadedClanOptional.get();


                Set<UUID> invaderEnemies = invaderClan.getEnemiesUUIDs();
                invaderEnemies.add(invadedClan.getUuid());
                invaderClan.setEnemiesUUIDs(invaderEnemies);

                Set<UUID> invadedClanEnemies = invadedClan.getEnemiesUUIDs();
                invadedClanEnemies.add(invaderClan.getUuid());
                invadedClan.setEnemiesUUIDs(invadedClanEnemies);

                invaderClan.getMembersUUIDs().forEach(uuid -> {
                    Player player = Bukkit.getPlayer(uuid);
                    if(player != null) {
                        player.sendMessage(Messages.YOUR_WAR_WITH_CLAN_XXXX_HAS_STARTED(invadedClan.getTag()));
                    }
                });
                invadedClan.getMembersUUIDs().forEach(uuid -> {
                    Player player = Bukkit.getPlayer(uuid);
                    if(player != null) {
                        player.sendMessage(Messages.YOUR_WAR_WITH_CLAN_XXXX_HAS_STARTED(invaderClan.getTag()));
                    }
                });
                ClanManager.updateClan(invaderClan);
                ClanManager.updateClan(invadedClan);
                war.setActive(true);
                warEntityManager.createOrUpdateWar(war);


            }

        }
    }
    public void endWars() {

        List<WarEntity> warEntities = warEntityManager.getAllWars();

        warEntities.forEach(war -> {
            endWar(war);
        });
    }

    public Set<WarEntity> getNonEndedGuildWars(UUID clanId) {

        Set<WarEntity> activeWars = warEntityManager.getWarsByGuildId(clanId, true, false);
        Set<WarEntity> scheduledWars = warEntityManager.getWarsByGuildId(clanId, false, false);

        activeWars.addAll(scheduledWars);
        return activeWars;
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

        Set<WarEntity> warEntityByGuildIds = warEntityManager.getWarByGuildIds(guild1Id, guild2Id, true, false);

        if(!warEntityByGuildIds.isEmpty()) return warEntityByGuildIds.stream().findFirst();

        return Optional.empty();
    }


    public boolean canCollectReward(WarRecordEntity warRecordEntity) {
        return !warRecordEntity.isRewardCollected();
    }

    public void endWar(WarEntity warEntity) {

        UUID looserGuildUUID = getLooserGuildUUID(warEntity);

        String invaderTag = "ERROR";
        String invadedTag = "ERROR";

        Optional<ClanEntity> invaderOptionalClan = ClanManager.getClanEntityByUUID(warEntity.getInvaderGuildId());
        if(invaderOptionalClan.isPresent()) invaderTag = invaderOptionalClan.get().getTag();

        Optional<ClanEntity> invadedOptionalClan = ClanManager.getClanEntityByUUID(warEntity.getInvadedGuildId());
        if(invadedOptionalClan.isPresent()) invadedTag = invadedOptionalClan.get().getTag();

        if(invaderOptionalClan.isPresent() && invadedOptionalClan.isPresent()) {
            ClanEntity invaderGuild = invaderOptionalClan.get();
            ClanEntity invadedGuild = invadedOptionalClan.get();

            {
                Set<UUID> enemies = invaderGuild.getEnemiesUUIDs();
                enemies.remove(invadedGuild.getUuid());
                invaderGuild.setEnemiesUUIDs(enemies);
            }
            {
                Set<UUID> enemies = invadedGuild.getEnemiesUUIDs();
                enemies.remove(invaderGuild.getUuid());
                invadedGuild.setEnemiesUUIDs(enemies);
            }
            invaderGuild.getMembersUUIDs().forEach(uuid -> {
                Player player = Bukkit.getPlayer(uuid);
                if(player != null) {
                    player.sendMessage(Messages.YOUR_WAR_WITH_CLAN_XXXX_HAS_ENDED(invaderGuild.getTag()));
                }
            });
            invaderGuild.getMembersUUIDs().forEach(uuid -> {
                Player player = Bukkit.getPlayer(uuid);
                if(player != null) {
                    player.sendMessage(Messages.YOUR_WAR_WITH_CLAN_XXXX_HAS_ENDED(invadedGuild.getTag()));
                }
            });
            ClanManager.updateClan(invaderGuild);
            ClanManager.updateClan(invadedGuild);
        }

        WarRecordEntity invaderRecord = new WarRecordEntity(
                warEntity.getInvaderGuildId(),
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

            Optional<ClanEntity> byUuid = ClanManager.getClanEntityByUUID(looserGuildUUID);

            if(byUuid.isPresent()){
                ClanEntity guild = byUuid.get();
                int newLives = guild.getLives();
                newLives--;
                guild.setLives(newLives);
                Bukkit.getServer().getPluginManager().callEvent(new ClanLivesChangeEvent(guild, newLives));
            }
        }
        warEntity.setActive(false);
        warEntity.setFinished(true);

        warEntityManager.deleteWarById(warEntity.getId());

    }


    // Nagroda za wojne
    public void collectReward(Player player, WarRecordEntity warRecord) {
        warRecord.setRewardCollected(true);
        warRecordEntityManager.createOrUpdateWarRecordEntity(warRecord);

        player.getInventory().addItem(new ItemStack(Material.BARRIER));
    }
}
