package me.xxgradzix.gradzixcore.clansCore.messages;

import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import org.bukkit.ChatColor;

public class Messages {

    public static final String LEAVE_COMMAND_USAGE = ChatColor.DARK_GRAY +"- " + ChatColor.GRAY + "/klan opusc";
    public static final String JOIN_COMMAND_USAGE = ChatColor.DARK_GRAY +"- " + ChatColor.GRAY + "/klan dolacz" + ChatColor.DARK_GRAY + " <" + ChatColor.GREEN + "tag" + ChatColor.DARK_GRAY + ">";
    public static final String INVITE_COMMAND_USAGE = ChatColor.DARK_GRAY +"- " + ChatColor.GRAY + "/klan zapros" + ChatColor.DARK_GRAY + " <" + ChatColor.GREEN + "nick" + ChatColor.DARK_GRAY + ">";
    public static final String KICK_COMMAND_USAGE = ChatColor.DARK_GRAY +"- " + ChatColor.GRAY + "/klan wyrzuc" + ChatColor.DARK_GRAY + " <" + ChatColor.GREEN + "nick" + ChatColor.DARK_GRAY + ">";
    public static final String CREATE_CLAN_COMMAND_USAGE = ChatColor.DARK_GRAY +"- " + ChatColor.GRAY + "/klan zaloz" + ChatColor.DARK_GRAY + " <" + ChatColor.GREEN + "tag" + ChatColor.DARK_GRAY + ">" + ChatColor.DARK_GRAY + " <" + ChatColor.GREEN + "nazwa" + ChatColor.DARK_GRAY + ">";
    public static final String DELETE_CLAN_COMMAND_USAGE = ChatColor.DARK_GRAY +"- " + ChatColor.GRAY + "/klan usun";
    public static final String THIS_PLAYER_DOES_NOT_BELONG_TO_THIS_CLAN = ChatColor.RED + "Ten gracz nie należy do tego klanu";
    public static final String CLAN_WITH_THIS_TAG_ALREADY_EXISTS = ChatColor.RED + "Klan z takim tagiem już istnieje";
    public static final String CLAN_WITH_THIS_TAG_DOES_NOT_EXISTS = ChatColor.RED + "Klan z takim tagiem nie istnieje";
    public static final String YOU_DONT_BELONG_TO_ANY_CLAN = ChatColor.RED + "Nie należysz do żadnego klanu";
    public static final String YOU_DONT_HAVE_PERMISSION_TO_DO_THIS = ChatColor.RED + "Nie możesz tego zrobić";
    public static final String YOU_WERE_NOT_INVITED_TO_THIS_CLAN = ChatColor.RED + "Nie zostałeś zaproszony do tego klanu";
    public static final String YOU_BELONG_TO_ANOTHER_CLAN = ChatColor.RED + "Należysz już do innego klanu";
    public static final String PLAYER_INFO_COMMAND_USAGE = ChatColor.DARK_GRAY +"- " + ChatColor.GRAY + "/gracz " + ChatColor.DARK_GRAY + " <" + ChatColor.GREEN + "nick" + ChatColor.DARK_GRAY + ">";
    public static final String CLAN_INFO_COMMAND_USAGE = ChatColor.DARK_GRAY +"- " + ChatColor.GRAY + "/klan info" + ChatColor.DARK_GRAY + " <" + ChatColor.GREEN + "tag" + ChatColor.DARK_GRAY + ">";
    public static final String ONLY_LEADER_CANT_DELETE_CLAN = ChatColor.RED + "Tylko lider klanu może usunąć klan";
    public static final String DELETE_CLAN_SUCCESSFUL = ChatColor.GREEN + "Pomyślnie usunąłeś swój klan";
    public static final String CLAN_WITH_THIS_NAME_ALREADY_EXISTS = ChatColor.RED + "Klan z taką nazwą już istnieje";
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


    public static String PLAYER_INFO(UserEntity user, ClanEntity targetClan) {
        StringBuilder sb = new StringBuilder();

        sb.append(ChatColor.GRAY).append("Informacje o graczu ").append(ChatColor.GREEN).append(user.getName()).append("\n");

        sb.append(ChatColor.GRAY).append("Punkty: ").append(ChatColor.GREEN).append(user.getPoints()).append("\n");
        sb.append(ChatColor.GRAY).append("Zabójstwa: ").append(ChatColor.GREEN).append(user.getKills()).append("\n");
        sb.append(ChatColor.GRAY).append("Śmierci: ").append(ChatColor.GREEN).append(user.getDeaths()).append("\n");

        if (targetClan == null) {
            sb.append(ChatColor.GRAY).append("Klan: ").append(ChatColor.RED).append("Brak").append("\n");
        } else {
            sb.append(ChatColor.GRAY).append("Klan: ").append(ChatColor.GREEN).append(targetClan.getTag());
        }

        return sb.toString();
    }

    public static String CLAN_INFO(ClanEntity targetClan) {
        StringBuilder sb = new StringBuilder();

        sb.append(ChatColor.GRAY).append("Informacje o klanie ").append("\n");

        sb.append(ChatColor.GRAY).append("Tag: ").append(ChatColor.GREEN).append(targetClan.getTag()).append("\n");
        sb.append(ChatColor.GRAY).append("Nazwa: ").append(ChatColor.GREEN).append(targetClan.getName()).append("\n");
        sb.append(ChatColor.GRAY).append("Lider: ").append(ChatColor.GREEN).append(targetClan.getLeader().getName()).append("\n");

        sb.append(ChatColor.GRAY).append("Punkty: ").append(ChatColor.GREEN).append(targetClan.getPoints()).append("\n");
        sb.append(ChatColor.GRAY).append("Życia: ").append(ChatColor.GREEN).append(targetClan.getLives()).append("\n");
        sb.append(ChatColor.GRAY).append("Ilość członków: ").append(ChatColor.GREEN).append(targetClan.getMembersUUIDs().size()).append("\n");


        return sb.toString();
    }
}
