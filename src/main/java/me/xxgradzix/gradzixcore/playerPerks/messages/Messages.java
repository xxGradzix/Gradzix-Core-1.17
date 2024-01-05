package me.xxgradzix.gradzixcore.playerPerks.messages;

import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.data.database.entities.PlayerPerksEntity;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {
    public static final String PERK_MAX_LEVEL = ChatColor.GRAY + "Perk jest już na maksymalnym poziomie!";
    public static final String UPGRADED_STRENGTH_PERK = ChatColor.GRAY + "Twój perk siły został ulepszony do poziomu:";
    public static final String UPGRADED_POISON_PERK = ChatColor.GRAY + "Twój perk trucizny został ulepszony do poziomu:";
    public static final String UPGRADED_RESISTANCE_PERK = ChatColor.GRAY + "Twój perk odporności został ulepszony do poziomu:";
    public static final String UPGRADED_LIFE_STEAL_PERK = ChatColor.GRAY + "Twój perk kradzieży zdrowia został ulepszony do poziomu:";
    public static final String UPGRADED_SICKNESS_PERK = ChatColor.GRAY + "Twój perk osłabienia został ulepszony do poziomu:";
    public static final String UPGRADED_ADDITIONAL_HEARTS = ChatColor.GRAY + "Twój perk dodatkowych serc został ulepszony do poziomu:";
    public static final String UPGRADED_SLOWNESS_PERK = ChatColor.GRAY + "Twój perk spowolnienia został ulepszony do poziomu:";
    public static final String AMOUNT_MUST_BE_A_NUMBER = ChatColor.GRAY + "Ilość musi być liczbą!";
    public static final String PLAYER_IS_NOT_ONLINE = ChatColor.GRAY + "Gracz nie jest online!";
    public static final String THERE_IS_NO_SUCH_PERK = ChatColor.GRAY + "Nie ma takiego perk'u!";
    public static final String YOU_MUST_SPECIFY_PERK_TYPE = ChatColor.GRAY + "Musisz podać perk!";

    public static void sendPerksLevelsToPlayer(Player player, PlayerPerksEntity playerPerksEntity) {
        player.sendMessage(ChatColor.GRAY + "*----------------------------*");
        player.sendMessage(ChatColor.GRAY + "Twoje Perki: ");
        player.sendMessage(ChatColor.DARK_RED + "Perk Siły: +" + playerPerksEntity.getPerkTypeLevel(PerkType.STRENGTH) + "% obrażeń");
        player.sendMessage(ChatColor.GREEN + "Perk Trucizny: +" + playerPerksEntity.getPerkTypeLevel(PerkType.POISON) + "% szansy na zatrucie");
        player.sendMessage(ChatColor.GOLD + "Perk Odporności: +" + playerPerksEntity.getPerkTypeLevel(PerkType.RESISTANCE) + "% odporności na uzyskanie odporności");
        player.sendMessage(ChatColor.DARK_PURPLE + "Perk Kradzieży Zdrowia: +" + playerPerksEntity.getPerkTypeLevel(PerkType.LIFE_STEAL) + "% szansy na kradzież zdrowia");
        player.sendMessage(ChatColor.DARK_GRAY + "Perk Osłąbienia: +" + playerPerksEntity.getPerkTypeLevel(PerkType.SICKNESS) + "% szansy na osłabienie wroga");
//        player.sendMessage(ChatColor.AQUA + "Perk Dodatkowych Serc: +" + playerPerksEntity.getPerkTypeLevel(PerkType.ADDITIONAL_HEARTS) + " serc");
        player.sendMessage(ChatColor.BLUE + "Perk Spowolnienia: +" + playerPerksEntity.getPerkTypeLevel(PerkType.SLOWNESS) + "% szansy na spowolnienie wroga");
        player.sendMessage(ChatColor.GRAY + "*----------------------------*");
    }
}
