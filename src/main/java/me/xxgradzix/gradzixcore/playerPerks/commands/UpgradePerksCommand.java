package me.xxgradzix.gradzixcore.playerPerks.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.GlobalItemManager;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.playerAbilities.AbilitiesGuiManager;
import me.xxgradzix.gradzixcore.playerPerks.PerkGui.PerkGuiManager;
import me.xxgradzix.gradzixcore.playerPerks.data.database.managers.PlayerPerkEntityManager;
import me.xxgradzix.gradzixcore.playerPerks.items.ItemManager;
import me.xxgradzix.gradzixcore.playerPerks.messages.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UpgradePerksCommand implements CommandExecutor {

    private final me.xxgradzix.gradzixcore.Gradzix_Core plugin;

    private final PlayerPerkEntityManager playerPerkEntityManager;

    private final PerkGuiManager perkGuiManager;

    public UpgradePerksCommand(Gradzix_Core plugin, PlayerPerkEntityManager playerPerkEntityManager, PerkGuiManager perkGuiManager) {
        this.plugin = plugin;
        this.playerPerkEntityManager = playerPerkEntityManager;
        this.perkGuiManager = perkGuiManager;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Komenda tylko dla graczy");
            return true;
        }

        Player player = (Player) sender;

        Gui gui = Gui.gui()
                .title(Component.text(ChatColor.GREEN + ChatColor.BOLD.toString() + "PERKI " + ChatColor.GRAY + "(/ulepszPerki)"))
                .disableAllInteractions()
                .rows(3)
                .create();

        GuiItem blackGlass = new GuiItem(GlobalItemManager.FILLER_GLASS_PANE);

        gui.getFiller().fillBetweenPoints(1, 2, 1, 8, blackGlass);
        gui.getFiller().fillBetweenPoints(3, 2, 3, 8, blackGlass);

        gui.setItem(2, 1, blackGlass);
        gui.setItem(2, 9, blackGlass);

        GuiItem limeGlass = new GuiItem(GlobalItemManager.LIGHT_GLASS_PANE);

        gui.setItem(1, 1, limeGlass);
        gui.setItem(1, 9, limeGlass);
        gui.setItem(3, 1, limeGlass);
        gui.setItem(3, 9, limeGlass);


        GuiItem abilities = ItemBuilder.from(ItemManager.abilityItemButton).asGuiItem();

        abilities.setAction(event -> {
            AbilitiesGuiManager.openAbilitiesGui(player);
        });

        GuiItem perks = ItemBuilder.from(ItemManager.perksItemButton).asGuiItem();
        perks.setAction(event -> {

            if(!player.getInventory().containsAtLeast(ItemManager.perkFragment, 50)) {
                player.sendMessage(ChatColor.RED + "Nie posiadasz wystarczającej ilości fragmentów perku");
                return;
            }
            perkGuiManager.drawPerk(plugin, player);

            for (int i = 0; i < 100; i++) {
                player.getInventory().removeItem(ItemManager.perkFragment);
            }
        });

        GuiItem yourPerks = ItemBuilder.from(ItemManager.yourPerksItemButton).asGuiItem();
        yourPerks.setAction(event -> {

            gui.close(player);

            Messages.sendPerksLevelsToPlayer(player, playerPerkEntityManager.getPlayerPerksEntityById(player.getUniqueId()));

        });

        gui.setItem(2, 3, abilities);
        gui.setItem(2, 7, perks);
        gui.setItem(2, 5, yourPerks);

        gui.open(player);
        return true;
    }
}
