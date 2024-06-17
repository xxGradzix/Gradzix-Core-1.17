package me.xxgradzix.gradzixcore.serverconfig.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class NewHelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        sender.sendMessage(convertColorText("&#69E9FF§l✪ Przydatne komendy:"));
        sender.sendMessage(convertColorText("&#555555〉&#42C8EF§l/klan &#555555-&#C9E4DE wszelkie informacje dotyczące klanów."));
        sender.sendMessage(convertColorText("&#555555〉&#42C8EF§l/gracz <nick> &#555555-&#C9E4DE informacje o podanym graczu."));
        sender.sendMessage(convertColorText("&#555555〉&#42C8EF§l/wymiana <nick> &#555555-&#C9E4DE wysyła zaproszenie do gracza o wymianę."));
        sender.sendMessage(convertColorText("&#555555〉&#42C8EF§l/rangi &#555555-&#C9E4DE lista dostępnych rang premium."));
        sender.sendMessage(convertColorText("&#555555〉&#42C8EF§l/kit &#555555-&#C9E4DE lista dostępnych zestawów."));
        sender.sendMessage(convertColorText("&#555555〉&#42C8EF§l/warp &#555555-&#C9E4DE lista dostępnych warpów."));
        sender.sendMessage(convertColorText("&#555555〉&#42C8EF§l/kosz &#555555-&#C9E4DE śmietnik na zbędne przedmioty."));
        sender.sendMessage(convertColorText("&#555555〉&#42C8EF§l/rynek &#555555-&#C9E4DE rynek serwerowy."));
        sender.sendMessage(convertColorText("&#555555〉&#42C8EF§l/gamma &#555555-&#C9E4DE możliwość widzenia w ciemności."));
        sender.sendMessage(convertColorText("&#555555〉&#42C8EF§l/sklep &#555555-&#C9E4DE sklep za $ oraz monety czasu."));
        sender.sendMessage(convertColorText("&#555555〉&#42C8EF§l/gornik &#555555-&#C9E4DE cena bloków do sprzedaży."));
        sender.sendMessage(convertColorText("&#555555〉&#42C8EF§l/portfel &#555555-&#C9E4DE otwiera sklep za VPLN."));

        // paragragh sign §



        return false;
    }
    public static String convertColorText(String text) {
        StringBuilder convertedText = new StringBuilder();
        String[] parts = text.split("&#");

        for (String part : parts) {
            if (!part.isEmpty()) {
                String colorCode = part.substring(0, 6);
                String letter = part.substring(6);

                ChatColor color = ChatColor.of("#" + colorCode);

                convertedText.append(color).append(letter);
            }
        }

        return convertedText.toString();
    }
}
