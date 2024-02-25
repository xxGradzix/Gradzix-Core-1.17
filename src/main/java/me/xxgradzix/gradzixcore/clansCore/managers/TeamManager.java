package me.xxgradzix.gradzixcore.clansCore.managers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.InternalStructure;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
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

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class TeamManager {

    private static final Scoreboard scoreboard = Clans.SCOREBOARD;
    private static JavaPlugin plugin = Gradzix_Core.getInstance();

    private static void updateEntities(ClanEntity entity) {
        addEntities(entity);
        removeEntities(entity);
    }

//    public static void changePrefixes() {
//        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(plugin, PacketType.Play.Server.SCOREBOARD_TEAM) {
//            @Override
//            public void onPacketSending(PacketEvent event) {
//
//                PacketContainer packet = event.getPacket();
//                String teamName = packet.getStrings().read(0);
//                if(teamName == null) return;
//                Team team = scoreboard.getTeam(teamName);
//                if(team == null) return;
//
//
//
//                Bukkit.broadcastMessage("Opt str: " + packet.getOptionalStructures().size() + " bool: " + (packet.getOptionalStructures().size() == 1 ? packet.getOptionalStructures().read(0).isPresent() : "null"));
//
//                Player observer = event.getPlayer();
//
//                for (String entry : team.getEntries()) {
//
//                    Player player = Bukkit.getPlayer(entry);
//
//                    if(player == null) continue;
//                    if(player.equals(observer)) continue;
//
//                    ChatColor suffixColor = getSuffixColor(observer, player);
//                    String newPrefix = suffixColor + " newSuffix";
//
//                    Optional<InternalStructure> optStruct = packet.getOptionalStructures().read(0); //Team Data
//
//                    if (optStruct.isPresent()) {
//
//                        InternalStructure struct = optStruct.get();
//                        struct.getChatComponents().write(2, WrappedChatComponent.fromText(newPrefix));//Team Suffix
//                        packet.getOptionalStructures().write(0, Optional.of(struct));
//
//                        Bukkit.broadcastMessage("Gracz " + player.getName() + " ma nowy prefix: " + newPrefix + " ktory powinien byc widoczny dla " + observer.getName());
//                    } else Bukkit.broadcastMessage("cos sie jebnelo dla widoku " + observer.getName());
//
//                }
//            }
//
//        });
//    }

//    private static ChatColor getSuffixColor(Player observer, Player player) {
//        Team observerTeam = observer.getScoreboard().getEntryTeam(observer.getName());
//
//        if(observerTeam == null) {
//            Bukkit.broadcastMessage("observerTeam == null");
//            return ChatColor.GRAY;
//        }
//
//        Team playerTeam = player.getScoreboard().getEntryTeam(player.getName());
//
//        if(observerTeam == null) {
//            Bukkit.broadcastMessage("playerTeam == null");
//            return ChatColor.GRAY;
//        }
//
//        if(observerTeam.equals(playerTeam)) {
//            Bukkit.broadcastMessage("equals");
//            return ChatColor.GREEN;
//        }
//        Bukkit.broadcastMessage("not equals");
//        return ChatColor.RED;
//
//    }

    private static Team addEntities(ClanEntity entity) {

        Team team = getRefreshTeam(entity);

        for (UserEntity member : entity.getMembers()) {
            if (team.hasEntry(member.getName()))
                continue;
            team.addEntry(member.getName());
        }


        return team;

    }

    private static void removeEntities(ClanEntity entity) {

        Team entryTeam = getRefreshTeam(entity);

        for (String entry : entryTeam.getEntries()) {
            if (entity.getMembers().stream().noneMatch(member -> member.getName().equals(entry))) {
                entryTeam.removeEntry(entry);
            }
        }

    }

//    public static void refreshTeams() {
//        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
//            for (ClanEntity entity : Clans.getClanEntityManager().getAllClanEntities()) {
//                Team team = getTeam(entity);
//
//                team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
//            }
//        }, 0, 20 * 10L);
//      }

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
        getRefreshTeam(entity);
    }

    private static Team getRefreshTeam(ClanEntity entity) {

        Team entryTeam = scoreboard.getTeam(entity.getTag());

        if(entryTeam != null) {
            try {
                entryTeam.unregister();
            } catch (IllegalStateException ignored) {
            }
        }

        entryTeam = scoreboard.registerNewTeam(entity.getTag());

        entryTeam.setSuffix(ChatColor.DARK_GRAY + " [" + ChatColor.GRAY + entity.getTag() + ChatColor.DARK_GRAY + "]");

        entryTeam.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

        entryTeam.setAllowFriendlyFire(entity.isPvp());

        updateEntities(entity);

        return entryTeam;
    }

}
