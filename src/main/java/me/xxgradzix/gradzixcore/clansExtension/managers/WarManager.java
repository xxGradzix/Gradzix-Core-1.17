package me.xxgradzix.gradzixcore.clansExtension.managers;

import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WAR_STATE;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.War;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.WarEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.TheyAlreadyHaveWarException;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.YouAlreadyHaveWarException;
import me.xxgradzix.gradzixcore.clansExtension.listeners.GuildLoseLivesEvent;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.event.FunnyEvent;
import net.dzikoysk.funnyguilds.event.guild.GuildLivesChangeEvent;
import net.dzikoysk.funnyguilds.guild.Guild;
import net.dzikoysk.funnyguilds.guild.GuildManager;
import org.bukkit.Bukkit;
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

    private final FunnyGuilds funnyGuilds;
    public WarManager(WarEntityManager warEntityManager, FunnyGuilds funnyGuilds) {
        this.warEntityManager = warEntityManager;
        this.funnyGuilds = funnyGuilds;
    }

    public void declareWar(Guild invaderGuild, Guild invadedGuild) throws YouAlreadyHaveWarException, TheyAlreadyHaveWarException {

        UUID invaderGuildId = invaderGuild.getUUID();
        UUID invadedGuildId = invadedGuild.getUUID();

        List<War> nonEndedInvaderGuildWars = warEntityManager.getWarsByGuildId(invaderGuildId, WAR_STATE.FUTURE);
        nonEndedInvaderGuildWars.addAll(warEntityManager.getWarsByGuildId(invaderGuildId, WAR_STATE.CURRENT));

        if(!nonEndedInvaderGuildWars.isEmpty()) {
            throw new YouAlreadyHaveWarException("You already have war");
        }

        List<War> nonEndedInvadedGuildWars = warEntityManager.getWarsByGuildId(invadedGuildId, WAR_STATE.FUTURE);
        nonEndedInvadedGuildWars.addAll(warEntityManager.getWarsByGuildId(invadedGuildId, WAR_STATE.CURRENT));

        if(!nonEndedInvadedGuildWars.isEmpty()) {
            throw new TheyAlreadyHaveWarException("This guild already is in war");
        }

        LocalDate now = LocalDate.now();

        LocalDateTime warStart = now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).atTime(12, 00);
        LocalDateTime warEnd = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).atTime(18, 00);

        War war = new War(
                invaderGuild.getUUID(),
                invadedGuild.getUUID(),
                0,
                0,
                warStart,
                warEnd,
                WAR_STATE.FUTURE
        );

        warEntityManager.createWar(war);

    }

    public void startWars() {

        List<War> wars = warEntityManager.getAllWars().stream()
                .filter(war -> war.getWarState().equals(WAR_STATE.FUTURE)
//                        && LocalDateTime.now().isAfter(war.getWarStart()) && LocalDateTime.now().isAfter(war.getWarEnd())
                )
                .collect(Collectors.toList());

        wars.forEach((war) -> {
            war.setWarState(WAR_STATE.CURRENT);
            warEntityManager.createOrUpdateWar(war);
            // TODO notify war participants
        });
    }
    public void endWars() {
        List<War> wars = warEntityManager.getAllWars().stream()
                .filter(war -> war.getWarState().equals(WAR_STATE.CURRENT))
                .collect(Collectors.toList());

        wars.forEach(war -> {

            endWar(war);

            // notify
        });
    }

    public List<War> getAllGuildWars(UUID id) {

        return warEntityManager.getWarsByGuildId(id, null);

    }

    public void addPointForGuild(War war, UUID guildId) {


        if(guildId.equals(war.getInvadedGuildId())) {
            Bukkit.broadcastMessage("Invaded dostaje punkt");
            int previousPoints = war.getInvadedScore();
            previousPoints++;
            war.setInvadedScore(previousPoints);

        }

        if(guildId.equals(war.getInvaderGuildId())) {
            Bukkit.broadcastMessage("Invader dostaje punkt");

            int previousPoints = war.getInvaderScore();
            previousPoints++;
            war.setInvaderScore(previousPoints);
        }

        warEntityManager.createOrUpdateWar(war);
    }

    @Nullable
    public UUID getWinnerGuildUUID(War war) {

        int invaderKills = war.getInvaderScore();
        int invadedKills = war.getInvadedScore();

        if(invaderKills == invadedKills) return null; // draw

        if(invaderKills > invadedKills) return war.getInvaderGuildId();

        if(invaderKills < invadedKills) return war.getInvadedGuildId();

        return null;
    }
    @Nullable
    public UUID getLooserGuildUUID(War war) {

        int invaderKills = war.getInvaderScore();
        int invadedKills = war.getInvadedScore();

        if(invaderKills == invadedKills) return null; // draw

        if(invaderKills < invadedKills) return war.getInvaderGuildId();

        if(invaderKills > invadedKills) return war.getInvadedGuildId();

        return null;
    }

    public Optional<War> getActiveWarOfGuilds(UUID guild1Id, UUID guild2Id) {

        List<War> warByGuildIds = warEntityManager.getWarByGuildIds(guild1Id, guild2Id, WAR_STATE.CURRENT);

        if(!warByGuildIds.isEmpty()) return warByGuildIds.stream().findFirst();

        return Optional.empty();
    }

    public void endWar(War war) { // DODAC SYSTEM REKORDU WOJNY
        // TODO do this method

        UUID winnerGuildUUID = getLooserGuildUUID(war);

        if(winnerGuildUUID != null) {
            GuildManager guildManager = funnyGuilds.getGuildManager();
            Option<Guild> byUuid = guildManager.findByUuid(winnerGuildUUID);

            if(byUuid.isPresent()){
                Guild guild = byUuid.get();
                int oldLives = guild.getLives();
                oldLives--;
                guild.setLives(oldLives);
                Bukkit.getServer().getPluginManager().callEvent(new GuildLivesChangeEvent(FunnyEvent.EventCause.COMBAT, null, guild, oldLives));
            }
        }
        war.setWarState(WAR_STATE.FINISHED);
        warEntityManager.createOrUpdateWar(war);

    }


}
