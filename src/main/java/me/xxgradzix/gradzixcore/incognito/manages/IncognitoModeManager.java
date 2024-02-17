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

    private static WrappedSignedProperty getSkin() {
        Random r = new Random();
        int num = r.nextInt(4);
        switch (num) {
            case 0: return new WrappedSignedProperty("textures", "eyJ0aW1lc3RhbXAiOjE0ODI1MjE4MTU4MTQsInByb2ZpbGVJZCI6IjQzYTgzNzNkNjQyOTQ1MTBhOWFhYjMwZjViM2NlYmIzIiwicHJvZmlsZU5hbWUiOiJTa3VsbENsaWVudFNraW42Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82ODQ1NzU2ODI5YjZiY2E1MTZiNWJmOTI1MWFlMzFjNzljZDZkZGJjM2M1N2YxMTkzNzBiMGNjZDhkNmY1YTEifX19", "SKrjFrc/FJfl+xSG1/gsjcPMkEWhHkme527T3dTZIXtofzunAQ8VqZcPu9NJmwOCOlvqRL8T0STwSGaNZctHkh5+5xVKsS6w8/oe3rbdz+7g854C/p5Op7xCH0H/HhEB1HgVqQx5ZOWFuaGn2EaPIdx1v/9z//TG/SyCeNeYsiafJcETDFTeFLxl+L+RpyMxjBlTwPD1vQPrt2VZ8PLfpMlndbQUuquPhCeoYRNil9fqjYjNJHSnc9URjGfpBVZk/XCb+F6i3ljkbv9OChSgPUhli9ktckVnyFQmkImOq1eviyThh2pjg9qV7peaU9dxNyNazpf40B82X4Wztor62Y14DJXaGzUZcQN6oMbr/L8xjEwXRXuWBt9Szemi7ZZIpGXR00GSqeEW20+C5ZiwbsjzmuLxDw876FG/w76U2T9Z1joEf4ef+c13Byc+9KVXBX3ybhTerrXkW+oXbx2XBRZ5K5cOHmlcFT7rmR9iiXTcA2smB4eFxyHqgITLK/28aWSGyQFjZJMLxSr0EP0I0yrMil8tXggTZFq7kFeO32Ehr1unqGQet3kjCnk6z3vNUAnW6PmzmATwEaGEvOeIpBufq7EnylBK/UzpqoGTkn47zEXBHXY7m+BT2PwFSEF0D0X63h8SHx+G5hiSbcH6BRG1i1OLi7GofuadMHEPddI=");
            case 1: return new WrappedSignedProperty("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYwODI1NDU1NDk0OSwKICAicHJvZmlsZUlkIiA6ICIzM2ViZDMyYmIzMzk0YWQ5YWM2NzBjOTZjNTQ5YmE3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJEYW5ub0JhbmFubm9YRCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kYWQzM2FiNjRjODFiNDNiZjRiZjUyNjFkZmRkODRiODEyOTE1MmRkN2IxMjI2MWY1ZGFjYjc3MzYyOTIyYTVkIgogICAgfQogIH0KfQ==", "NWkfC4LjPAHCY5mvfjZ/QmtnvAJ8GOQnlg8U6k10si6e545TSNi5XpNIXS1cTuRdSoZ3mzWm4a00Cob6bOxb7G5o115UcX2rFzAT6nRNnAIkDkdYi3NxFcdip+pNDdOV+y+QR4J4VpZAwoMaGCsKyIMoT8CYeyexIO+ifYMpwZsEsLRlWaz+GNOes7Ql6+3aONsZuU9qTiRCRXyqBlDAWV3DyNToJDoudQ0Hl0L5KGbR4hUB913JHTlEuBfcRT3mHNmOrzMp9Zdr08q5SA2Fn1IZ9gMr+Dj1K+NEjkzyyRbZkjHR+CSzXajBvIztgGsdb0ZOPDckuPabVBvIQrHVj9Wq4A7u1z8H2cUroVOaH0ePIwi5YSCkeXW3sw8dyS4XSd7x6upjhAJaJ4hkrw8FFTeKYo96LbqROkO57A5fr8d/f+l0pfR4uSJtKtSMeeSkqPmJQsPWYx9tWeqSDEsr3HGekxO/hcuBrlSmyXjhmzdIO1zTUmWTxqaCfFxDx6JFan8QcbVCBxFm9Ro8KnTIOfcc+np82N4oFo67wZwZYOo27oV8NzKbwvRukdQGSNfT4xHwWMLR8wNsBUcOvoTco+lZDNL1Er1a4nKTigURPiokOVsnwvGR1B8nuE5xAil5F22644Yt8NQkzO06CcLFShw9FrLfbb3Znexp1VC2ztg=");
            case 2: return new WrappedSignedProperty("textures", "eyJ0aW1lc3RhbXAiOjE1NzUzMzg1MjYxMjUsInByb2ZpbGVJZCI6IjJjY2I1NDYxYmNhMDQ4YzE4ZGU4MjIwYWMzMjQ4ZWY3IiwicHJvZmlsZU5hbWUiOiJSZWluZGVlciIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjNiYjBjMzQ5M2M4YzdkOWE3NDAxNTRmOTMyMjhmM2JjMjkzZTA1ZWZlZTkyMzQwMTk2YzI0MTMzMGUxYTJkMiJ9fX0=", "LB3VTkXygcg9/8RrWZLjtz0vf+KeDHGiJIyX47KDScVNSvvOOykFy18ncKioGV1yXdXyPlyIa5b9vu/Mfhn+21ykDaMtzbwfgQ4K0VSyXYyH7aLpHGB3izE26ghYULfqUKMEAH6uz3SGrFW9UbsOv6YdNI4tSxvBMetgsRU46vf2QcSRRgw+7ap3ZBNRNG8ca62u+a4HiC+5bMVgRwOM6YOSrw7MXtJjNlz3bsS0we8okUhCMq+my2mcl1XTF34UIogCYFk2R7LgDP2Fzkh/f1srt2TMNjMtFoKlmXy3/GVMW1sM3plKGVcfARMJYTpVI+Tsq4Wrze+yzK89dvm/x0N71MpkDgdZxTnTFu7EsFfml1nyM4RwE2h0LU2K6BNm8UBShb5plD8PnuBNVuQJzG0IvVoRJWKqRjGAJL4qgLxSjgVWjsAbXBEgqsx8b2MIFWHSXkiycIW7ZZBhP/LAtIAsLaxFbnidpPBR7rdBQ7ERyqrGTDc1DLBRUpGTtQb4Q4kF0y0wh5gZA05wvn85fh+qAu8HxEo0vE5I+Wa+v5b8Uo0Udsby0yqEZXxGQERNXEYK6BPmu1CKu5OZJJ8DOe0JWZs8Lu0NYWnvyQkSRlhLZqePW8B9nXCHyWFTaSRoO3FB5VisTpzayMBP4v86fLI3CUbyb0hEPdZe0TV77Zs=");
            default: return new WrappedSignedProperty("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYwNjc5MDYyMTExOCwKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICI0MTQxNDE0MWgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODlhNDFmMGRiYzcyOTYyZWFlNzExODcwZTkyYTc3YmNkMDFjZGNjNmE2ZDQwZTExZTk2NDYzOWMyMWIyYjVmOSIKICAgIH0KICB9Cn0=", "pegOuK3kV049MM5A0yKTwMxUu2f9jOB14FMkWkXs9GG7f7KsIssOj1eRt/39j7aW9U+M4pULYM2CG6rlpyih6Qofw26IHubG4P594ybhIKxtMbSEbOBIl7xkZI86hul/3odPvwkghgngsp3aY1jfgTNVjfROF9rOVGfugkWdXvYX/+CJs+pKkwdkKZpeUmSSwlB7p0MGvKCY8LuBms4/g0k8ipTOfEK4upOLHD/vBA01GHX+Epspd8tKvucIJLD+VANu12DmT0d2JZKrIyga4zuH4phFqnlg3JoNBurgJhAsDSXWuOnQRtcDqv8KOg/W8KRuvmAP4TZB1amAyPND72nmteF9YinN0KH1QkBKb6d4gvkP/0RKz562Z9+srZnlxBBrJ5V7JpT7kshQHYHP4qy6zE8De/vomJa23GB0T1lQai2a/p0uOCMk+oXu7aKuf0MrxaFptEN+q2VYJBz+9l6VRxRyJJZhFA0Yc0o2eD9zeqEQNTulbY17VP1bIU06Fxuo+S2AMjdV3a+IMaYo8FGx6I5H5rGXrL3Uqnu5IJFXAFN1eHW2RulOpvWO1SUvUYQUTCIestFB87quWCINUFk/DjUGysEZ+7E6q5kNM75SaTCFsMmDI0wFCCRPD6Hv6kcHNu+xx7Bezse75kUsdSiNVyNC4B3mVNMBuBFn/Nw=");
        }
    }

//    private static Property getSkin(boolean restore) {
//        //  Choose a random skin from the config
//        if(restore) return new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTcwNzY2NTM1MjA4NiwKICAicHJvZmlsZUlkIiA6ICIzODAzYjdhZTMzZDk0MTBiYjE3ZjQwN2Y5NWMzODkwNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJ4eEdyYWR6aXgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmE3OGQxNGMyZjIyODhkZjI2NzNjNmRkMDdlZmJiZmE2NThkMDBjY2Q0Njg3OTNkYjdjOWE2ODEyMWRmYTg4IgogICAgfSwKICAgICJDQVBFIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMzQwYzBlMDNkZDI0YTExYjE1YThiMzNjMmE3ZTllMzJhYmIyMDUxYjI0ODFkMGJhN2RlZmQ2MzVjYTdhOTMzIgogICAgfQogIH0KfQ==", "Aw0+z4o4l3GbNXJrUrE3jnjxt0bK4ATGNHJOEVq0XPNBdfDDh+tif8iwlcEmkF6h+j9xrzQcsClNCAQ8cdGMrejm2nhOpc3kAyDhevuHPPt86enINJWcuX5dW9n8JuuGz4Gt9Fnpu2qDs3nSb5F61qSQ8xppYmwtGSMSOTbFDSJr9UzWm2Cq6jWQQERijGk/jKKh05JOlwfIVB0BmWryaOLdsL5hs4C88qUPDzKrCVDJi4ZvyJ1u3nw0bxsCtzdE9K99DS8eoRFgw9FCW0pq7Lf8hN6crhrqEUC7w/+XDZX2/uNTO2rXt17l/HMw3Fl/o/Ftot8m1STnoepB9+knOSzC1AvPS8iRwa74yFudnS4no/PZHigXg2iaEAwpVBI7GO8He2cIZzyXuk6RbrCfw6vk5xa9XQEJ9EtCjdxux34o05vz6mWj9fqLljekBR6OLFv3/NQx1Xn2vVWd5zYlxp20LyVsYd4pVnFCDRccfIW6PyphHTwCUit9B54E9iHM6w71EYo+/whoXExpAB6ZP2PXGn1AqK9hjbjgIAUCFENBcBTRJVQ2bYcfTqHfrNpVia2so4GnTqw4auN2wcs4MNSIJgtJUSxGTfNmM2TvLv/F3VOZyviQY33Mgu4ibyfbEOzd2JuwDAmVfmQEMwmBJGJX+G43QUrDDB4Evy2QQfk=");
//        return new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTcwNzc0NjM2ODIxMywKICAicHJvZmlsZUlkIiA6ICJiN2ZjZTM2MzZjY2U0MGRhYTExYjM5NTcyMTVkYTkyZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJSaWNreWxhY2lvIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzgxNzUxZWE2ZGQzYTM4ODY0MmJjYjNiOTUzMmVhZDE2Y2IyYmMzZDNkZDBhZTgzNjFhYWExYzQ1YWYyMTk4NzEiCiAgICB9CiAgfQp9", "MjGXWi/3ftRi8TWnNB1zqDLv6eTQcIsgCVsUXw1tEu27aPuxyUgaZ21BcmMuworq7g9yA66B+yoe5LPqrMbcXv+WjpdWtMl7bNq0Z0vvwfFjakIN0kVbMr49pOYn1/2aPIvr+Bt7L6ES7lrI8/GhGIDc6KxikrKnslkdhS4YWI3fhDI2fZ6g5eBB7Usg61Fls3Y8MBtgGZ4LgoiuHiCEY0cSHfZ6CaWY+Ui8chAYXYH3++9mFbNX5omz0ngplPOwghbHCQuF9IJUAlaOppD3nuF27smVdX1T58BeGKOM8k8JKADQob6YHmvo8kJIupkVdgGW3F09HoKG8oDwjzD45VagT0qcSOsSxbb38Y/BxNjt6ccYNz8u6SM23bdlu9aZIz1MkuiHrBadaaBs/Xghd9kQviL2zS6yQ8+CSrXbjsPksRiLQC2YtnTgrCC+XBl11iX4tQlp5VoGZmiAtYeDxlMv6WSTIhYCkfCoadzgX/VNc3mOuERl+3Y5kLNFtYoN4cub/LJU4JlZVhYL5JO/3lzudNiUZE2jQ9Xf840mWa3edUGEsW75NJmxxb61sqiar1kiUY+zA8OMqeakXsDB2vovpy6FLJwgtydBoHfVkL1W78/PquOVl4XJbfcVkg+5kpBXIemy0o0R7Hc1Qk1lKuotyiIqjVlj1id7NEhJx/Y=");
//    }

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

                    if (incognitoModeEntity == null) return;
                    if(!incognitoModeEntity.isIncognitoModeEnabled()) return;

                    String displayName;


                    Player observer = event.getPlayer();

                    displayName = getIncognitoNickColor(observer, player) + incognitoModeEntity.getIncognitoNick();

                    profile = profile.withName(displayName);

                    PlayerInfoData newPlayerInfoData = new PlayerInfoData(profile, playerInfoData.getPing(), playerInfoData.getGameMode(), WrappedChatComponent.fromText(displayName));

                    newPlayerInfoData.getProfile().getProperties().removeAll("textures");
                    newPlayerInfoData.getProfile().getProperties().put("textures", getSkin());

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
