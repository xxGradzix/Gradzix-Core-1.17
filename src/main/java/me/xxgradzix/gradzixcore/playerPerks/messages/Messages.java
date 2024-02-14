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
    public static final String UPGRADED_WEAKNESS_PERK = ChatColor.GRAY + "Twój perk osłabienia został ulepszony do poziomu:";
    public static final String UPGRADED_FRAGMENT_DROP_PERK = ChatColor.GRAY + "Twój perk dropu fragmentów został ulepszony do poziomu:"; 

    public static void sendPerksLevelsToPlayer(Player player, PlayerPerksEntity playerPerksEntity) {
        player.sendMessage(ChatColor.GRAY + "*----------------------------*");
        player.sendMessage("§8» §7Twoje Perki§8: ");
        player.sendMessage("§8» §ePerk Siły§8: §2+" + playerPerksEntity.getPerkTypeLevel(PerkType.STRENGTH) + "§f% §7obrażeń");
        player.sendMessage("§8» §2Perk Trucizny§8: §2+" + playerPerksEntity.getPerkTypeLevel(PerkType.POISON) + "§f% §7szansy na zatrucie");
        player.sendMessage("§8» §6Perk Odporności§8: §2+" + playerPerksEntity.getPerkTypeLevel(PerkType.RESISTANCE) + "§f% §7odporności na uzyskanie odporności");
        player.sendMessage("§8» §cPerk Kradzieży Zdrowia§8: §2+" + playerPerksEntity.getPerkTypeLevel(PerkType.LIFE_STEAL) + "§f% §7szansy na kradzież zdrowia");
        player.sendMessage("§8» §dPerk Osłabienia§8: §2+" + playerPerksEntity.getPerkTypeLevel(PerkType.WEAKNESS) + "§f% §7szansy na osłabienie wroga");
        player.sendMessage("§8» §4Perk Życia§8: §2+" + playerPerksEntity.getPerkTypeLevel(PerkType.ADDITIONAL_HEARTS) + " §7serc");
        player.sendMessage("§8» §5Perk Spowolnienia§8: §2+" + playerPerksEntity.getPerkTypeLevel(PerkType.SLOWNESS) + "§f% §7szansy na spowolnienie wroga");
        player.sendMessage("§8» §bPerk Fragmentów§8: §2+" + playerPerksEntity.getPerkTypeLevel(PerkType.PERK_FRAGMENT_DROP) + "§f% §7szansy na spowolnienie wroga");
        player.sendMessage("*----------------------------*");
    }
}
