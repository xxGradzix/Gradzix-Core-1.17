package me.xxgradzix.gradzixcore.clansExtension.messages;

import org.bukkit.ChatColor;

public class Messages {
    public static final String THIS_CLAN_IS_CURRENTLY_IN_WAR = ChatColor.RED + "Ten klan ma obecnie wojne z innym klanem";
    public static final String YOU_DONT_HAVE_CLAN = ChatColor.RED + "Nie jesteś członkiem żadnego klanu";
    public static final String YOU_MUST_SPECIFY_CLAN_TAG = ChatColor.RED + "Musisz podać tag klanu któremu wypowiadasz wojne";
    public static final String CLAN_DOES_NOT_EXISTS = ChatColor.RED + "Taki klan nie istnieje";
    public static final String YOU_ARE_CURRENTLY_IN_WAR = ChatColor.RED + "Obecnie prowadzisz już wojne";
    public static final String YOU_DECLARED_WAR_TO_CLAN_XXXX = ChatColor.GRAY + "Wypowiedziałeś wojne klanowi " + ChatColor.GREEN;
    public static final String THIS_REWARD_CAN_BE_COLLECTED_ONLY_BY_GUILD_OWNER = ChatColor.RED + "Nagrode może odebrać jedynie właściciel klanu";
    public static final String YOU_COLLECTED_REWARD = ChatColor.GREEN + "Odebrałeś nagrode za wojnę";
    public static final String REWARD_IS_ALREADY_COLLECTED = ChatColor.GREEN + "Nagroda z tej wojny została już odebrana";
    public static final String UPGRADED_CLAN_PERK = ChatColor.GREEN + "Ulepszyłeś perk rankingu klanowego!";
    public static final String UPGRADED_WAR_AMOUNT_PERK = ChatColor.GREEN + "Ulepszyłeś perk ilości wojen klanowych!";
    public static final String NOT_ENOUGH_MONEY = ChatColor.RED + "Nie masz wystarczająco pięniędzy aby ulepszyć ten perk";
    public static final String MAX_PERK_LEVEL = ChatColor.RED + "Ten perk jest już na maksymalnym poziomie";
    public static final String YOU_ALREADY_HAVE_MAX_AMOUNT_OF_WARS = ChatColor.RED + "Posiadasz już maksymalną ilość wojen";

    public static final String CLAN_XXXX_DECLARED_WAR_TO_YOU(String clanTag) {
        return ChatColor.GRAY + "Klan " + ChatColor.GREEN + clanTag + ChatColor.GRAY + " wypowiedział wojne Twojemu klanowi";
    }
    public static final String WARS_ARE_ACTIVE = ChatColor.GREEN + "Zaczęły się wojny";
    public static final String WARS_ENDED = ChatColor.GREEN + "Wojny się zakończyły";
    public static final String YOU_KILLED_MEMBER_OF_ENEMY_GUILD = ChatColor.GOLD + "Udało Ci się zabić członka klanu z którym masz wojne i Twoja gildia zdobyła punkt!";
    public static final String YOU_WERE_KILLED_BY_MEMBER_OF_ENEMY_GUILD = ChatColor.RED + "Zabił Cię członek gildi z którą masz wojnę";
    public static String WAR_ENDED_VIA_GUILD_REMOVAL(String enemyTag) {
        return ChatColor.GREEN + "Wygraliście wojne z gildią " + ChatColor.RED + enemyTag + ChatColor.GREEN + " ponieważ została ona usunięta";
    }
    public static final String YOU_CANT_DECLARE_WAR_TO_YOUR_OWN_GUILD = ChatColor.RED + "Nie możesz wypowiedzieć wojny swojej własnej gildii";
    public static final String YOUR_WAR_WITH_CLAN_XXXX_HAS_STARTED(String enemyTag) {
        return ChatColor.GREEN + "Wojna z klanem " + ChatColor.RED + enemyTag + ChatColor.GREEN + " rozpoczęła się!";
    }
    public static final String YOUR_WAR_WITH_CLAN_XXXX_HAS_ENDED(String enemyTag) {
        return ChatColor.GREEN + "Wojna z klanem " + ChatColor.RED + enemyTag + ChatColor.GREEN + " zakończyła się!";
    }
}
