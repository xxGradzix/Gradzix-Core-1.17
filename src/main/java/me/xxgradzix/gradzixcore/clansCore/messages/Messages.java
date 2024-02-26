package me.xxgradzix.gradzixcore.clansCore.messages;

import org.bukkit.ChatColor;

public class Messages {

    public static final String LEAVE_COMMAND_USAGE = ChatColor.GRAY + "Użycie:" + ChatColor.RED + " /opusc";
    public static final String JOIN_COMMAND_USAGE = ChatColor.GRAY + "Użycie:" + ChatColor.RED + " /dolacz <tag>";
    public static final String INVITE_COMMAND_USAGE = ChatColor.GRAY + "Użycie:" + ChatColor.RED + " /zapros <nick>";
    public static final String KICK_COMMAND_USAGE = ChatColor.GRAY + "Użycie:" + ChatColor.RED + " /wyrzuc <nick>";
    public static final String CREATE_CLAN_COMMAND_USAGE = ChatColor.GRAY + "Użycie:" + ChatColor.RED + " /zaloz <nazwa> <tag>";
    public static final String DELETE_CLAN_COMMAND_USAGE = ChatColor.GRAY + "Użycie:" + ChatColor.RED + " /usun";
    public static final String THIS_PLAYER_DOES_NOT_BELONG_TO_THIS_CLAN = ChatColor.RED + "Ten gracz nie należy do tego klanu";
    public static final String CLAN_WITH_THIS_TAG_ALREADY_EXISTS = ChatColor.RED + "Klan z takim tagiem już istnieje";
    public static final String CLAN_WITH_THIS_TAG_DOES_NOT_EXISTS = ChatColor.RED + "Klan z takim tagiem nie istnieje";
    public static final String YOU_DONT_BELONG_TO_ANY_CLAN = ChatColor.RED + "Nie należysz do żadnego klanu";
    public static final String YOU_DONT_HAVE_PERMISSION_TO_DO_THIS = ChatColor.RED + "Nie możesz tego zrobić";
    public static final String YOU_WERE_NOT_INVITED_TO_THIS_CLAN = ChatColor.RED + "Nie zostałeś zaproszony do tego klanu";
    public static final String YOU_BELONG_TO_ANOTHER_CLAN = ChatColor.RED + "Należysz już do innego klanu";
    public static String YOU_BELONG_TO_THIS_CLAN = ChatColor.GREEN + "Należysz już do tego klanu";

    public static String SUCCESFULLY_REMOVED_PLAYER_FROM_CLAN(String nick) { return ChatColor.GRAY + "Usunąłeś gracza " + ChatColor.RED + nick + ChatColor.GRAY + " z klanu";}
    public static final String YOU_LEAVED_CLAN_XXXX = ChatColor.GRAY + "Już nie jesteś członkiem klanu " + ChatColor.RED;
    public static String CREATED_GUILD_MESSAGE_LEADER(String name, String tag) {
        return ChatColor.GRAY + "Pomyślnie utworzyłeś klan " + ChatColor.GREEN + name + ChatColor.GRAY + " z tagiem " + ChatColor.GREEN + tag;
    }
    public static String CREATED_CLAN_GLOBAL_MESSAGE(String playerName, String clanName, String tag) {return ChatColor.GRAY + "Gracz " + ChatColor.GREEN + playerName + ChatColor.GRAY + " utworzył klan " + ChatColor.GREEN + clanName + ChatColor.GRAY + " z tagiem " + ChatColor.GREEN + tag;}
    public static final String YOU_CANT_LEAVE_CLAN_BECAUSE_YOU_ARE_LEADER = ChatColor.RED + "Nie możesz opuścić klanu bo jesteś jego liderem";

    public static final String PLAYER_DOES_NOT_EXISTS(String name) {return ChatColor.GRAY + "Gracz " + ChatColor.GREEN + name + ChatColor.GRAY + " nie istnieje";}
    public static final String PLAYER_IS_NOT_ONLINE(String name) {return ChatColor.GRAY + "Gracz " + ChatColor.GREEN + name + ChatColor.GRAY + " nie jest online";}
    public static final String PLAYER_DOES_BELONG_TO_YOUR_CLAN(String name) {return ChatColor.GRAY + "Gracz " + ChatColor.GREEN + name + ChatColor.GRAY + " nie należy do Twojego klanu";}
    public static final String YOU_KICKED_PLAYER(String name) {return ChatColor.GRAY + "Wyrzuciłeś gracza " + ChatColor.GREEN + name + ChatColor.GRAY + " ze swojego klanu";}


}
