package me.xxgradzix.gradzixcore.playerPerks.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.playerAbilities.AbilitiesGuiManager;
import me.xxgradzix.gradzixcore.playerPerks.PerkGui.PerkGuiManager;
import me.xxgradzix.gradzixcore.playerPerks.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UpgradePerksCommand implements CommandExecutor {

    private final me.xxgradzix.gradzixcore.Gradzix_Core plugin;

    public UpgradePerksCommand(me.xxgradzix.gradzixcore.Gradzix_Core plugin) {
        this.plugin = plugin;
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
            PerkGuiManager.drawPerk(plugin, player);
            for (int i = 0; i < 50; i++) {
                player.getInventory().removeItem(ItemManager.perkFragment);
            }
        });

        gui.setItem(2, 3, abilities);
        gui.setItem(2, 7, perks);

        gui.open(player);
        return true;
    }
}
