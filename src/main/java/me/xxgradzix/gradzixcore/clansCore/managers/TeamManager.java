package me.xxgradzix.gradzixcore.clansCore.managers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.InternalStructure;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import me.clip.placeholderapi.PlaceholderAPI;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.clansCore.Clans;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class TeamManager {

    private static final Scoreboard scoreboard = Clans.SCOREBOARD;
    private static JavaPlugin plugin = Gradzix_Core.getInstance();

    public static void updateEntities(ClanEntity entity) {
        removeEntities(entity);
        addEntities(entity);
    }

    public static void changePrefixes() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(plugin, PacketType.Play.Server.SCOREBOARD_TEAM) {
            @Override
            public void onPacketSending(PacketEvent event) {

                PacketContainer packet = event.getPacket();

                String teamName = packet.getStrings().read(0);
                if(teamName == null) return;
                Team team = scoreboard.getTeam(teamName);
                if(team == null) return;

                Player observer = event.getPlayer();

                for (String entry : team.getEntries()) {

                    Player player = Bukkit.getPlayer(entry);
                    if(player == null) continue;

                    if(player == null) continue;
                    if(player.equals(observer)) continue;

                    String newSuffix = " %rel_gradzixcore_player_tag%" + team.getName();

                    Optional<InternalStructure> optStruct = packet.getOptionalStructures().read(0); //Team Data

                    if (optStruct.isPresent()) {

                        newSuffix = PlaceholderAPI.setRelationalPlaceholders(player, observer, newSuffix);
                        InternalStructure struct = optStruct.get();
                        struct.getChatComponents().write(2, WrappedChatComponent.fromText(newSuffix));//Team Suffix
                        packet.getOptionalStructures().write(0, Optional.of(struct));
                    }
                }

                event.setPacket(packet);
            }

        });
    }
    public static void refreshAllTeams() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (ClanEntity entity : Clans.getClanEntityManager().getAllClans()) {
                refreshTeam(entity);
            }
            for (Team team : scoreboard.getTeams()) {
                String name = team.getName();
                Optional<ClanEntity> entity = Clans.getClanEntityManager().getClanEntityByTag(name);
                if (!entity.isPresent()) {
                    try {
                        team.unregister();
                    } catch (IllegalStateException ignored) {
                    }
                }
            }
        }, 0, 20 * 15);

    }

    private static void addEntities(ClanEntity entity) {

        Optional<Team> optionalTeam = getTeam(entity);
        if (!optionalTeam.isPresent()) return;

        Team team = optionalTeam.get();

        for (UUID memberUUID : entity.getMembersUUIDs()) {
            Optional<UserEntity> member = UserManager.getUserEntityByUUID(memberUUID);
            if (member.isPresent() && !team.hasEntry(member.get().getName())) team.addEntry(member.get().getName());
        }

    }

    private static void removeEntities(ClanEntity entity) {
        Optional<Team> optionalTeam = getTeam(entity);
        if (!optionalTeam.isPresent()) return;
        Team team = optionalTeam.get();
        for (String entry : team.getEntries()) team.removeEntry(entry);
    }

    public static void removeTeam(ClanEntity entity) {
        Team entryTeam = scoreboard.getTeam(entity.getTag());

        if (entryTeam != null) {
            try {
                entryTeam.unregister();
            } catch (IllegalStateException ignored) {
            }
        }
    }

    public static void refreshTeam(ClanEntity entity) {
        createOrUpdateTeam(entity);
    }

    private static Team createOrUpdateTeam(ClanEntity entity) {

        Optional<Team> optionalTeam = getTeam(entity);

        Team team;
        if(!optionalTeam.isPresent()) {
            team = scoreboard.registerNewTeam(entity.getTag());
        } else {
            team = optionalTeam.get();
        }

        team.setSuffix(ChatColor.DARK_GRAY + " [" + ChatColor.GRAY + entity.getTag() + ChatColor.DARK_GRAY + "]");
        team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        team.setAllowFriendlyFire(entity.isPvp());

        updateEntities(entity);

        return team;
    }

    private static Optional<Team> getTeam(ClanEntity entity) {
        return Optional.ofNullable(scoreboard.getTeam(entity.getTag()));
    }

}
