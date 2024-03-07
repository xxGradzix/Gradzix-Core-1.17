package me.xxgradzix.gradzixcore.clansCore.placeholders;

import me.clip.placeholderapi.PlaceholderHook;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.clip.placeholderapi.expansion.Relational;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PlayerPointsPlaceholder extends PlaceholderExpansion implements Relational {

    private static final JavaPlugin plugin = Gradzix_Core.getInstance();

    public PlayerPointsPlaceholder() {
        super();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "gradzixcore";
    }

    @Override
    public @NotNull String getAuthor() {
        return "xxGradzix";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String identifier) {
        System.out.println("Debug: " + identifier + " ------------------------------");
        if(identifier.equals("player_points")) {
            int points = UserManager.getOrCreateUserEntity(player).getPoints();
            return points + "";
        }
        if(identifier.equals("player_deaths")) {
            int deaths = UserManager.getOrCreateUserEntity(player).getDeaths();
            return deaths + "";
        }
        if(identifier.equals("player_kills")) {
            int kills = UserManager.getOrCreateUserEntity(player).getKills();
            return kills + "";
        }
        if(identifier.startsWith("clan_points_")) {
            String tag = identifier.replace("gradzixcore_clan_points_", "");
            Optional<ClanEntity> clanEntityByTag = ClanManager.getClanEntityByTag(tag);
            if(clanEntityByTag.isPresent()) {
                int points = clanEntityByTag.get().getPoints();
                return points + "";
            }
            return "Nie znaleziono klanu";
        }
        if(identifier.startsWith("player_clan_tag_")) {
            String nick = identifier.replace("gradzixcore_player_clan_tag_", "");
            Optional<UserEntity> userEntityByNick = UserManager.getUserEntityByNick(nick);

            if(userEntityByNick.isPresent()) {
                Optional<ClanEntity> clanEntityByClanMember = ClanManager.getClanEntityByClanMember(userEntityByNick.get());
                if(clanEntityByClanMember.isPresent()) {
                    return clanEntityByClanMember.get().getTag();
                }
                return "Brak klanu";
            }
            return "Nie znaleziono Gracza";
        }
        if (identifier.equals("clan_points")) {
            Optional<ClanEntity> clanEntityByClanMember = ClanManager.getClanEntityByClanMember(UserManager.getOrCreateUserEntity(player));
            if(clanEntityByClanMember.isPresent()) {
                int points = clanEntityByClanMember.get().getPoints();
                return points + "";
            }
            return "0";
        }
        return null;
    }
    @Override
    public String onPlaceholderRequest(Player one, Player two, String identifier) {

        if(identifier.equalsIgnoreCase("player_tag")) {
            return getTestString(one, two);
        }

        return null;
    }
    private static String getTestString(Player player, Player player2) {

        if(player == null || player2 == null) return "test";
        if(player.getGameMode().equals(player2.getGameMode())) return ChatColor.GREEN + "sarazem";
        if(!player.getGameMode().equals(player2.getGameMode())) return ChatColor.RED + "niesa";

        return "chuj";
    }
}
