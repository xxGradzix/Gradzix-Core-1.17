package me.xxgradzix.gradzixcore.clansExtension.commands;

import me.xxgradzix.gradzixcore.clansCore.commands.ClanCommand;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarScheduleEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.WarScheduleEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarTimeManager;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WarConfigCommand implements CommandExecutor, TabCompleter {

    private final WarTimeManager warTimeManager;
    private final WarScheduleEntityManager warScheduleEntityManager;

    public WarConfigCommand(WarTimeManager warTimeManager, WarScheduleEntityManager warScheduleEntityManager) {
        this.warTimeManager = warTimeManager;
        this.warScheduleEntityManager = warScheduleEntityManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length != 5) {
            sender.sendMessage(Messages.WRONG_COMMAND_USAGE);
            return false;
        }

        LocalDateTime warStart;
        LocalDateTime warEnd;
        try {
            warStart = parseDate(args[2].split(","));
            warEnd = parseDate(args[4].split(","));

        } catch (IllegalArgumentException e) {
            sender.sendMessage(Messages.WRONG_COMMAND_USAGE);
            return false;

        }
        if(warStart.isBefore(LocalDateTime.now())) {
            sender.sendMessage(Messages.WRONG_DATE);
            return false;
        }

        if(warStart.isAfter(warEnd)) {
            sender.sendMessage(Messages.WRONG_DATE);
            return false;
        }
        WarScheduleEntity warScheduleEntity = new WarScheduleEntity(warStart, warEnd);

        warScheduleEntityManager.createWarScheduleEntity(warScheduleEntity);

        warTimeManager.scheduleWarStart(warStart, warEnd);

        sender.sendMessage(Messages.WAR_SCHEDULED(warStart, warEnd));

        return true;
    }

    private static LocalDateTime parseDate(String[] args) throws IllegalArgumentException {
        if(args.length != 5) {
            throw new IllegalArgumentException();
        }
        try {
            int year = Integer.parseInt(args[0]);
            int month = Integer.parseInt(args[1]);
            int day = Integer.parseInt(args[2]);
            int hour = Integer.parseInt(args[3]);
            int minute = Integer.parseInt(args[4]);
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        List<String> completions = new ArrayList<>();
        if(args.length == 1) {
            completions.add("schedule");
        }
        if(args.length == 2) {
            completions.add("start");
        }
        if(args.length == 4) {
            completions.add("end");
        }
        return completions;
    }
}
