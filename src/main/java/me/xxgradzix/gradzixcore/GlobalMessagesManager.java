package me.xxgradzix.gradzixcore;

public class GlobalMessagesManager {
    public static final String INVALID_ARGUMENTS = "§cInvalid arguments!";
    public static String PREFIX = "§b§lUNI§3§lMC §8» §r§7";
    public static String NO_PERMISSION = PREFIX + "§cNie posiadasz uprawnień do tej komendy!";
    public static String PLAYER_ONLY = PREFIX + "§cTa komenda jest dostępna tylko dla graczy!";
    public static String INVALID_USAGE = PREFIX + "§cUżycie: %s";
    public static String INVALID_NUMBER = PREFIX + "§cPodana wartość musi być liczbą!";
    public static String INVALID_ITEM = PREFIX + "§cMusisz trzymać przedmiot w ręce!";
    public static String INVALID_BLOCK = PREFIX + "§cMusisz trzymać blok w ręce!";
    public static String INVALID_PRICE = PREFIX + "§cPodana cena jest nieprawidłowa!";


    public static String secondsToTimeFormat(int seconds) {
        int minutes = seconds / 60;
        int sec = seconds % 60;
        int hours = minutes / 60;
        minutes %= 60;
        StringBuilder sb = new StringBuilder();
        if(hours > 0) {
            sb.append(hours).append(" godzin ");
        }
        if(minutes > 0) {
            sb.append(minutes).append(" minut ");
        }
        if(sec > 0) {
            sb.append(sec).append(" sekund");
        }

        return sb.toString();
    }

}
