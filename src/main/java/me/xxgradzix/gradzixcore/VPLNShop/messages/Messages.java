package me.xxgradzix.gradzixcore.VPLNShop.messages;

import org.bukkit.ChatColor;

public class Messages {

    public static final String SUCCESSFUL_UNI_KEY_PURCHASE = ChatColor.GREEN + "Pomyślnie zakupiono klucz §b§lUNI!";
    public static final String NOT_ENOUGH_VPLN = ChatColor.RED + "Nie posiadasz wystarczającej ilości VPLN!";
    public static final String SUCCESSFUL_MAGIC_KEY_PURCHASE = ChatColor.GREEN + "Pomyślnie zakupiono §5§lMagiczny klucz!";
    public static final String SUCCESSFUL_UNI_PURCHASE = ChatColor.GREEN + "Pomyślnie zakupiono rangę §b§lUNI!";
    public static final String SUCCESSFUL_SVIP_PURCHASE = ChatColor.GREEN + "Pomyślnie zakupiono rangę §6§lSVIP!";
    public static final String SUCCESSFUL_VIP_PURCHASE = ChatColor.GREEN + "Pomyślnie zakupiono rangę §e§lVIP!";
    public static final String AMOUNT_TO_LOW_ERROR = ChatColor.RED + "Podana kwota jest za niska!";
    public static final String AMOUNT_TO_HIGH_ERROR = ChatColor.RED + "Podana kwota jest za wysoka!";
    public static final String SUCCESSFUL_SCRATCHCARD_PURCHASE = ChatColor.GREEN + "Pomyślnie zakupiono §6§lKartę zdrapkę!";
    public static final String SUCCESSFUL_FRAGMENT_PURCHASE = ChatColor.GREEN + "Pomyślnie zakupiono §6§lFragment perku!";

    public static String playerBoughtVpln(String nickname, Double vplnAmount) {
        return ChatColor.GRAY + "Gracz " +ChatColor.AQUA + nickname + ChatColor.GRAY + " zakupił doładowanie " + ChatColor.GREEN + vplnAmount + " VPLN!\n" +
                ChatColor.YELLOW + "Doładujesz swój portfel na naszej stronie: \n" +
                ChatColor.GOLD + "www.unimc.pl";
    }

}
