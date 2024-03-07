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

//                    ChatColor suffixColor = getSuffixColor(observer, player);
                    String newPrefix = " bu by %rel_gradzixcore_player_tag%";

                    Optional<InternalStructure> optStruct = packet.getOptionalStructures().read(0); //Team Data

                    if (optStruct.isPresent()) {

                        Bukkit.broadcastMessage("Updating prefix");
                        newPrefix = PlaceholderAPI.setRelationalPlaceholders(player, observer, newPrefix);
                        InternalStructure struct = optStruct.get();
                        struct.getChatComponents().write(2, WrappedChatComponent.fromText(newPrefix));//Team Suffix
                        packet.getOptionalStructures().write(0, Optional.of(struct));
                    }
                }

                Bukkit.broadcastMessage("Now packet is sent to " + observer.getName() + " with new prefix");
                event.setPacket(packet);
            }

        });
    }
//    public static void sendCustomPacket(Player player, ClanEntity entity) {
//        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.SCOREBOARD_TEAM);
//
//        // Set the team name
//        packet.getStrings().write(0, entity.getTag());
//
//        // Create an Optional<InternalStructure> and write the new prefix to it
//        Optional<InternalStructure> optStruct = packet.getOptionalStructures().read(0); //Team Data
//
//        if (optStruct.isPresent()) {
//
//            UserEntity leader = entity.getLeader();
//            Player leaderPlayer = UserManager.parsePlayer(leader);
//
//            String newPrefix = " bu by %rel_gradzixcore_player_tag%";
//
//            newPrefix = PlaceholderAPI.setRelationalPlaceholders(leaderPlayer, player, newPrefix);
//            InternalStructure struct = optStruct.get();
//            struct.getChatComponents().write(1, WrappedChatComponent.fromText(newPrefix)); //Team Prefix
//            packet.getOptionalStructures().write(0, Optional.of(struct));
//        }
//
//        ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
//    }
//    private static ChatColor getSuffixColor(Player observer, Player player) {
//        Team observerTeam = observer.getScoreboard().getEntryTeam(observer.getName());
//
//        if(observerTeam == null) {
//            return ChatColor.GRAY;
//        }
//
//        Team playerTeam = player.getScoreboard().getEntryTeam(player.getName());
//
//        if(observerTeam == null) {
//            return ChatColor.GRAY;
//        }
//        if(observerTeam.equals(playerTeam)) {
//            return ChatColor.GREEN;
//        }
//        return ChatColor.RED;
//    }
//
    public static void refreshAllTeams() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
//            Bukkit.broadcastMessage("Refreshing teams");
            for (ClanEntity entity : Clans.getClanEntityManager().getAllClans()) {
                refreshTeam(entity);
            }
//            for (Player player : Bukkit.getOnlinePlayers()) {
//
//                UserEntity userEntity = UserManager.getOrCreateUserEntity(player);
//                Optional<ClanEntity> clanEntityByMember = ClanManager.getClanEntityOfMember(userEntity);
//                if(clanEntityByMember.isPresent()) {
//                    ClanEntity clanEntity = clanEntityByMember.get();
////                    sendScoreboardTeamPacket(player, clanEntity);
//                    sendCustomPacket(player, clanEntity);
//                }
//
//            }

        }, 0, 20 * 5);

    }
//    public  static void sendScoreboardTeamPacket(Player player, ClanEntity entity) {
////
////        UserEntity leader = entity.getLeader();
////        Player leaderPlayer = UserManager.parsePlayer(leader);
////
//        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.SCOREBOARD_TEAM);
////
////        // Set the team name
//        packet.getStrings().write(0, entity.getTag());
////        // suffix
////        String newPrefix = " bu by %rel_gradzixcore_player_tag%";
////
////        newPrefix = PlaceholderAPI.setRelationalPlaceholders(leaderPlayer, player, newPrefix);
////        // create internal structure
////
////        Optional<InternalStructure> optStruct = packet.getOptionalStructures().read(0); //Team Data
////
////        if (optStruct.isPresent()) {
////            Bukkit.broadcastMessage("opt jest tutaj");
////            InternalStructure struct = optStruct.get();
////            struct.getChatComponents().write(0, WrappedChatComponent.fromText(entity.getTag()));
////            struct.getChatComponents().write(1, WrappedChatComponent.fromText(newPrefix));//Team Prefix
////            struct.getChatComponents().write(2, WrappedChatComponent.fromText(newPrefix));//Team Suffix
////
////            packet.getOptionalStructures().write(0, Optional.of(struct));
////        }
//
//        ProtocolLibrary.getProtocolManager().broadcastServerPacket(packet);
////        ProtocolLibrary.getProtocolManager().sendServerPacket(leaderPlayer, packet);
//    }
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
