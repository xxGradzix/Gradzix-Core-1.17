//package me.xxgradzix.gradzixcore.clansCore.placeholders;
//
//import me.clip.placeholderapi.expansion.PlaceholderExpansion;
//import me.clip.placeholderapi.expansion.Relational;
//import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
//import org.bukkit.entity.Player;
//import org.jetbrains.annotations.NotNull;
//
//public class PlayerTagPlaceholder extends PlaceholderExpansion implements Relational {
//    @Override
//    public @NotNull String getIdentifier() {
//        return "gradzixcore";
//    }
//
//    @Override
//    public @NotNull String getAuthor() {
//        return "xxGradzix";
//    }
//
//    @Override
//    public @NotNull String getVersion() {
//        return "1.0.0";
//    }
//
//    @Override
//    public String onPlaceholderRequest(Player one, Player two, String identifier) {
//
//        Bukkit.broadcastMessage("testrt identifier = " + identifier);
//        if(identifier.equalsIgnoreCase("player_tag")) {
//            return getTestString(one, two);
//        }
//
//        return null;
//    }
//    private static String getTestString(Player player, Player player2) {
//
//        if(player == null || player2 == null) return "test";
//        if(player.getGameMode().equals(player2.getGameMode())) return ChatColor.GREEN + "sarazem";
//        if(!player.getGameMode().equals(player2.getGameMode())) return ChatColor.RED + "niesa";
//
//        return "chuj";
//    }
//}
