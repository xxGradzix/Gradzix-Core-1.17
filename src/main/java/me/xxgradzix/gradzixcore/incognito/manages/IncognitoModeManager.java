package me.xxgradzix.gradzixcore.incognito.manages;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.InternalStructure;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.incognito.data.database.entities.IncognitoAdminEntity;
import me.xxgradzix.gradzixcore.incognito.data.database.entities.IncognitoModeEntity;
import me.xxgradzix.gradzixcore.incognito.data.database.managers.IncognitoAdminEntityManager;
import me.xxgradzix.gradzixcore.incognito.data.database.managers.IncognitoModeEntityManager;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.guild.Guild;
import net.dzikoysk.funnyguilds.user.User;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.network.PlayerConnection;
import org.apache.logging.log4j.core.util.WatchManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import panda.std.Option;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

public class IncognitoModeManager {


    private static final Gradzix_Core plugin = Gradzix_Core.getInstance();

    private static FunnyGuilds funnyGuilds = FunnyGuilds.getInstance();
    private static IncognitoModeEntityManager incognitoModeEntityManager;
    private static IncognitoAdminEntityManager incognitoAdminEntityManager;

    public IncognitoModeManager(IncognitoModeEntityManager incognitoModeEntityManager, IncognitoAdminEntityManager incognitoAdminEntityManager) {
        this.incognitoModeEntityManager = incognitoModeEntityManager;
        this.incognitoAdminEntityManager = incognitoAdminEntityManager;
        changeNicks();
    }

    public static void toggleIncognitoMode(Player player) {

        String incognitoNick = generateRandomString(11);
        IncognitoModeEntity incognitoModeEntity = incognitoModeEntityManager.getIncognitoModeEntityById(player.getUniqueId());

        if(incognitoModeEntity == null) {
            incognitoModeEntity = new IncognitoModeEntity(player.getUniqueId(), incognitoNick, false);
            incognitoModeEntityManager.createIncognitoModeEntity(incognitoModeEntity);
        }
        incognitoModeEntity.setIncognitoNick(incognitoNick);
        if(incognitoModeEntity.isIncognitoModeEnabled()) {
            player.sendMessage("§aWyłaczono tryb incognito!");
        } else {
            player.sendMessage("§aWłączono tryb incognito!");
        }
        incognitoModeEntity.setIncognitoModeEnabled(!incognitoModeEntity.isIncognitoModeEnabled());
        incognitoModeEntityManager.createOrIncognitoModeEntity(incognitoModeEntity);

        refreshNick(player);

        setPrefix(player);
    }

    private static void refreshNick(Player player) { // refreshes nick of given player for all players
        EntityPlayer handle = ((CraftPlayer) player).getHandle();

        for (Player online : Bukkit.getOnlinePlayers()) {

            PlayerConnection connection = ((CraftPlayer) online).getHandle().b;

            connection.sendPacket(new PacketPlayOutPlayerInfo(
                    PacketPlayOutPlayerInfo.EnumPlayerInfoAction.b, handle));

            connection.sendPacket(new PacketPlayOutPlayerInfo(
                    PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, handle));

            if(!online.equals(player)) {
                connection.sendPacket(new PacketPlayOutEntityDestroy(handle.getId()));
                connection.sendPacket(new PacketPlayOutNamedEntitySpawn(handle));
            }
        }
    }
    public static void setPrefix(Player player) {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

        IncognitoModeEntity incognitoModeEntityById = incognitoModeEntityManager.getIncognitoModeEntityById(player.getUniqueId());

        if(incognitoModeEntityById == null) return;
        if(!incognitoModeEntityById.isIncognitoModeEnabled()) return;

        PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.SCOREBOARD_TEAM); // Create a new scoreboard team packet

        packet.getIntegers().write(0,0); //Mode -create team

        packet.getStrings().write(0, incognitoModeEntityById.getIncognitoNick()); // Give the team a name

        Optional<InternalStructure> optStruct = packet.getOptionalStructures().read(0); //Team Data

        if (optStruct.isPresent()) {

            InternalStructure struct = optStruct.get();

            struct.getChatComponents().write(0, WrappedChatComponent.fromText(incognitoModeEntityById.getIncognitoNick()));//TeamName

            struct.getChatComponents().write(2, WrappedChatComponent.fromText(" §8[§7" + player.getName() + "§8]"));//Team Suffix
            struct.getIntegers().write(0, 1); // Bit mask. 0x01: Allow friendly fire, 0x02: can see invisible players on same team.
            struct.getEnumModifier(ChatColor.class, MinecraftReflection.getMinecraftClass("EnumChatFormat")).write(0, ChatColor.AQUA);
            packet.getOptionalStructures().write(0, Optional.of(struct));

        }

        String incognitoNick = incognitoModeEntityById.getIncognitoNick();

        packet.getModifier().write(2, Collections.singletonList(incognitoNick));

        for(IncognitoAdminEntity incognitoAdminEntity: incognitoAdminEntityManager.getAllIncognitoAdminEntities()) {
            protocolManager.sendServerPacket(Bukkit.getPlayer(incognitoAdminEntity.getUuid()), packet); // Send the packet to the player = osoba oglądająca
        }
    }

    private static void changeNicks() {

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(plugin, PacketType.Play.Server.PLAYER_INFO) {

            @Override
            public void onPacketSending(PacketEvent event) {

                if (event.getPacket().getPlayerInfoAction().read(0) != EnumWrappers.PlayerInfoAction.ADD_PLAYER) return;

                List<PlayerInfoData> newPlayerInfoDataList = new ArrayList<>();

                for (PlayerInfoData playerInfoData : event.getPacket().getPlayerInfoDataLists().read(0)) {

                    if (playerInfoData == null || playerInfoData.getProfile() == null || Bukkit.getPlayer(playerInfoData.getProfile().getUUID()) == null) { //Unknown Player
                        newPlayerInfoDataList.add(playerInfoData);
                        continue;
                    }

                    WrappedGameProfile profile = playerInfoData.getProfile();

                    Player player = Bukkit.getPlayer(profile.getUUID());

                    if (player == null) return;

                    IncognitoModeEntity incognitoModeEntity = incognitoModeEntityManager.getIncognitoModeEntityById(player.getUniqueId());

                    if (incognitoModeEntity == null) return;
                    if(!incognitoModeEntity.isIncognitoModeEnabled()) return;

                    String displayName;

                    Player observer = event.getPlayer();

                    displayName = getIncognitoNickColor(observer, player) + incognitoModeEntity.getIncognitoNick();

                    profile = profile.withName(displayName);

                    PlayerInfoData newPlayerInfoData = new PlayerInfoData(profile, playerInfoData.getPing(), playerInfoData.getGameMode(), playerInfoData.getDisplayName());

                    newPlayerInfoDataList.add(newPlayerInfoData);

                }

                event.getPacket().getPlayerInfoDataLists().write(0, newPlayerInfoDataList);
            }
        });
    }

    private static String getIncognitoNickColor(Player observer, Player player) {

        boolean isObserverAdmin = incognitoAdminEntityManager.getAllIncognitoAdminEntities().stream()
                .map(IncognitoAdminEntity::getUuid)
                .anyMatch(uuid -> uuid.equals(observer.getUniqueId()));

        if(isObserverAdmin) return "";

        Option<User> playerUserOption = funnyGuilds.getUserManager().findByPlayer(player);
        Option<User> observerUserOption = funnyGuilds.getUserManager().findByPlayer(observer);

        if(playerUserOption.isEmpty()) return "";
        if(observerUserOption.isEmpty()) return "";

        User playerUser = playerUserOption.get();
        User observerUser = observerUserOption.get();

        if(playerUser.getGuild().isEmpty()) return "§f"; // gracz nie ma gildii

        if(observerUser.getGuild().isEmpty()) return "§7"; // gracz ma gildie ale obserwator nie ma = szary

        Guild guild = playerUser.getGuild().get();
        Guild observerGuild = observerUser.getGuild().get();

        if(guild.equals(observerGuild)) return "§a"; // gracz i obserwator maja ta sama gildie = zielony

        if(guild.getAllies().contains(observerGuild)) return "§9"; // gracz i obserwator maja sojusznice = niebieski

        if(guild.getEnemies().contains(observerGuild)) return "§c"; // gracz i obserwator maja wrogow = czerwony

        return "";
    }

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static String generateRandomString(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
}
