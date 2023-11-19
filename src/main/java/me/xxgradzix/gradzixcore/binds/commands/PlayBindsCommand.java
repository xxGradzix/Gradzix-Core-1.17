package me.xxgradzix.gradzixcore.binds.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.binds.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayBindsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player) sender;
        Gui bindGui = Gui.gui()
                .title(Component.text(ChatColor.DARK_GRAY + "Bindy!"))
                .rows(3)
                .disableAllInteractions()
                .create();
        GuiItem boxdelKurwa = ItemBuilder.from(
                ItemManager.createBindButton(
                        1,
                        "Boxdel - kurwa",
                        '"' + "Kurwa oczywiscie ze tak" + '"')).asGuiItem((action) -> {
                            playCustomSound(p, 2);
            bindGui.close(p);
        });
        GuiItem dawid = ItemBuilder.from(
                ItemManager.createBindButton(
                        2,
                        "Dawid Jasper",
                        '"' + "Ale ma cycuszki" + '"')).asGuiItem((action) -> {
            bindGui.close(p);
            playCustomSound(p, 1);

        });
        GuiItem major = ItemBuilder.from(
                ItemManager.createBindButton(
                        3,
                        "Major - Prosze nie przeklinać",
                        '"' + "Prosze nie przeklinać, śmieciu pierdolony" + '"')).asGuiItem((action) -> {
            bindGui.close(p);
            playCustomSound(p, 3);
        });
        GuiItem speed = ItemBuilder.from(
                ItemManager.createBindButton(
                        4,
                        "Speed - Giga nigga",
                        '"' + "Who the fuck is the giga nigga" + '"')).asGuiItem((action) -> {
                            bindGui.close(p);
            playCustomSound(p, 4);
        });
        bindGui.addItem(boxdelKurwa, dawid, major, speed);
        bindGui.open(p);
        return true;
    }
    private void playCustomSound(Player player, int soundNum) {
        player.playSound(player.getLocation(), "minecraft:music.sound" + soundNum, 1.0f, 1.0f);
    }
}
