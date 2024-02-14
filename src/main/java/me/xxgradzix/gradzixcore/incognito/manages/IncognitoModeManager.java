package me.xxgradzix.gradzixcore.incognito.manages;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.InternalStructure;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.*;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import panda.std.Option;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.util.*;

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

//    public static void SChangeSkin(Player player, boolean restore) {
//        //  Init the player's connection
//        GameProfile profile = ((CraftPlayer)player).getHandle().getProfile();
//
//
//        PlayerConnection connection = ((CraftPlayer)player).getHandle().b;
//
//        //  Send the packets
//        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.b, ((CraftPlayer)player).getHandle()));
//        profile.getProperties().removeAll("textures");
//        profile.getProperties().put("textures", getSkin(restore));
//        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, ((CraftPlayer)player).getHandle()));
//        for (Player online : Bukkit.getOnlinePlayers()) {
//            online.hidePlayer(plugin, player);
//            online.showPlayer(plugin, player);
//        }
//
//    }

    private static Property getSkin(boolean restore) {
        //  Choose a random skin from the config
        if(restore) return new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTcwNzY2NTM1MjA4NiwKICAicHJvZmlsZUlkIiA6ICIzODAzYjdhZTMzZDk0MTBiYjE3ZjQwN2Y5NWMzODkwNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJ4eEdyYWR6aXgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmE3OGQxNGMyZjIyODhkZjI2NzNjNmRkMDdlZmJiZmE2NThkMDBjY2Q0Njg3OTNkYjdjOWE2ODEyMWRmYTg4IgogICAgfSwKICAgICJDQVBFIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMzQwYzBlMDNkZDI0YTExYjE1YThiMzNjMmE3ZTllMzJhYmIyMDUxYjI0ODFkMGJhN2RlZmQ2MzVjYTdhOTMzIgogICAgfQogIH0KfQ==", "Aw0+z4o4l3GbNXJrUrE3jnjxt0bK4ATGNHJOEVq0XPNBdfDDh+tif8iwlcEmkF6h+j9xrzQcsClNCAQ8cdGMrejm2nhOpc3kAyDhevuHPPt86enINJWcuX5dW9n8JuuGz4Gt9Fnpu2qDs3nSb5F61qSQ8xppYmwtGSMSOTbFDSJr9UzWm2Cq6jWQQERijGk/jKKh05JOlwfIVB0BmWryaOLdsL5hs4C88qUPDzKrCVDJi4ZvyJ1u3nw0bxsCtzdE9K99DS8eoRFgw9FCW0pq7Lf8hN6crhrqEUC7w/+XDZX2/uNTO2rXt17l/HMw3Fl/o/Ftot8m1STnoepB9+knOSzC1AvPS8iRwa74yFudnS4no/PZHigXg2iaEAwpVBI7GO8He2cIZzyXuk6RbrCfw6vk5xa9XQEJ9EtCjdxux34o05vz6mWj9fqLljekBR6OLFv3/NQx1Xn2vVWd5zYlxp20LyVsYd4pVnFCDRccfIW6PyphHTwCUit9B54E9iHM6w71EYo+/whoXExpAB6ZP2PXGn1AqK9hjbjgIAUCFENBcBTRJVQ2bYcfTqHfrNpVia2so4GnTqw4auN2wcs4MNSIJgtJUSxGTfNmM2TvLv/F3VOZyviQY33Mgu4ibyfbEOzd2JuwDAmVfmQEMwmBJGJX+G43QUrDDB4Evy2QQfk=");
        return new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTcwNzc0NjM2ODIxMywKICAicHJvZmlsZUlkIiA6ICJiN2ZjZTM2MzZjY2U0MGRhYTExYjM5NTcyMTVkYTkyZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJSaWNreWxhY2lvIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzgxNzUxZWE2ZGQzYTM4ODY0MmJjYjNiOTUzMmVhZDE2Y2IyYmMzZDNkZDBhZTgzNjFhYWExYzQ1YWYyMTk4NzEiCiAgICB9CiAgfQp9", "MjGXWi/3ftRi8TWnNB1zqDLv6eTQcIsgCVsUXw1tEu27aPuxyUgaZ21BcmMuworq7g9yA66B+yoe5LPqrMbcXv+WjpdWtMl7bNq0Z0vvwfFjakIN0kVbMr49pOYn1/2aPIvr+Bt7L6ES7lrI8/GhGIDc6KxikrKnslkdhS4YWI3fhDI2fZ6g5eBB7Usg61Fls3Y8MBtgGZ4LgoiuHiCEY0cSHfZ6CaWY+Ui8chAYXYH3++9mFbNX5omz0ngplPOwghbHCQuF9IJUAlaOppD3nuF27smVdX1T58BeGKOM8k8JKADQob6YHmvo8kJIupkVdgGW3F09HoKG8oDwjzD45VagT0qcSOsSxbb38Y/BxNjt6ccYNz8u6SM23bdlu9aZIz1MkuiHrBadaaBs/Xghd9kQviL2zS6yQ8+CSrXbjsPksRiLQC2YtnTgrCC+XBl11iX4tQlp5VoGZmiAtYeDxlMv6WSTIhYCkfCoadzgX/VNc3mOuERl+3Y5kLNFtYoN4cub/LJU4JlZVhYL5JO/3lzudNiUZE2jQ9Xf840mWa3edUGEsW75NJmxxb61sqiar1kiUY+zA8OMqeakXsDB2vovpy6FLJwgtydBoHfVkL1W78/PquOVl4XJbfcVkg+5kpBXIemy0o0R7Hc1Qk1lKuotyiIqjVlj1id7NEhJx/Y=");
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
            Player observer = Bukkit.getPlayer(incognitoAdminEntity.getUuid());

            if(observer == null) continue;
            if(observer.equals(player)) continue;
            PlayerConnection connection = ((CraftPlayer) observer).getHandle().b;
            PlayerConnection connection2 = ((CraftPlayer) player).getHandle().b;
            if(connection == null) continue;
            if(connection2 == null) continue;

            protocolManager.sendServerPacket(Bukkit.getPlayer(incognitoAdminEntity.getUuid()), packet); // Send the packet to the player = osoba oglądająca
        }
    }

    public static String getResponse(String _url){
        try {
            URL url = new URL(_url);
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            String body = IOUtils.toString(in, encoding);
            return body;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

                    profile.getProperties().removeAll("textures");
//                    profile.getProperties().put("textures", getSkin(incognitoModeEntity.isIncognitoModeEnabled() ? false : true));


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
