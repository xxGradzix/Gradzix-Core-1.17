package me.xxgradzix.gradzixcore.warps.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.warps.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        openGui(player);

        return false;
    }
    private void openGui(Player player) {

        Gui gui = Gui.gui()
                .title(Component.text(ChatColor.AQUA + "" + ChatColor.BOLD + "Warp " + ChatColor.RESET + ChatColor.GRAY + "(/warp)"))
                .disableAllInteractions()
                .rows(3)
                .create();

        GuiItem blackGlass = new GuiItem(me.xxgradzix.gradzixcore.chatOptions.items.ItemManager.blackGlass);

        gui.getFiller().fillBetweenPoints(1, 2, 1, 8, blackGlass);
        gui.getFiller().fillBetweenPoints(3, 2, 3, 8, blackGlass);

        gui.setItem(2, 1, blackGlass);
        gui.setItem(2, 9, blackGlass);

        GuiItem limeGlass = new GuiItem(me.xxgradzix.gradzixcore.chatOptions.items.ItemManager.limeGlass);

        gui.setItem(1, 1, limeGlass);
        gui.setItem(1, 9, limeGlass);
        gui.setItem(3, 1, limeGlass);
        gui.setItem(3, 9, limeGlass);

        GuiItem playerSafeRegion = new GuiItem(ItemManager.playerSafeRegion);
        playerSafeRegion.setAction(event -> {
            player.performCommand("essentials:warp bezpieczna_gracz");
        });

        gui.setItem(2, 2, playerSafeRegion);

        GuiItem vipSVipSafeRegion = new GuiItem(ItemManager.vipSVipSafeRegion);
        vipSVipSafeRegion.setAction(event -> {
            player.performCommand("essentials:warp bezpieczna_vip_svip");
        });

        gui.setItem(2, 3, vipSVipSafeRegion);

        GuiItem ageSafeRegion = new GuiItem(ItemManager.ageSafeRegion);
        ageSafeRegion.setAction(event -> {
            player.performCommand("essentials:warp bezpieczna_age");
        });

        gui.setItem(2, 4, ageSafeRegion);

        GuiItem pvpArena = new GuiItem(ItemManager.pvpArena);
        pvpArena.setAction(event -> {
            player.performCommand("essentials:warp pvp");
        });

        gui.setItem(2, 5, pvpArena);

        GuiItem afk = new GuiItem(ItemManager.afkArea);
        afk.setAction(event -> {
            player.performCommand("essentials:warp afk");
        });

        gui.setItem(2, 6, afk);

        GuiItem chests = new GuiItem(ItemManager.chestArea);
        chests.setAction(event -> {
            player.performCommand("essentials:warp skrzynie");
        });

        gui.setItem(2, 7, chests);

        GuiItem armor = new GuiItem(ItemManager.armorArea);
        armor.setAction(event -> {
            player.performCommand("essentials:warp zbroje");
        });

        gui.setItem(2, 8, armor);

        gui.open(player);

    }
}
