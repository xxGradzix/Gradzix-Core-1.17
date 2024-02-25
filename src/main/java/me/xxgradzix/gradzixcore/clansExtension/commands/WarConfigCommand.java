package me.xxgradzix.gradzixcore.clansExtension.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WarConfigCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }

//    private final WarTimeManager warTimeManager;
//    private final WarScheduleEntityManager warScheduleEntityManager;
//
//    public WarConfigCommand(WarTimeManager warTimeManager, WarScheduleEntityManager warScheduleEntityManager) {
//        this.warTimeManager = warTimeManager;
//        this.warScheduleEntityManager = warScheduleEntityManager;
//    }
//
//    @Override
//    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//
//        if(args.length != 5) {
//            sender.sendMessage(Messages.WRONG_COMMAND_USAGE);
//            return false;
//        }
//
//        LocalDateTime warStart;
//        LocalDateTime warEnd;
//        try {
//            warStart = parseDate(args[2].split(","));
//            warEnd = parseDate(args[4].split(","));
//
//        } catch (IllegalArgumentException e) {
//            sender.sendMessage(Messages.WRONG_COMMAND_USAGE);
//            return false;
//
//        }
//        if(warStart.isBefore(LocalDateTime.now())) {
//            sender.sendMessage(Messages.WRONG_DATE);
//            return false;
//        }
//
//        if(warStart.isAfter(warEnd)) {
//            sender.sendMessage(Messages.WRONG_DATE);
//            return false;
//        }
//        WarScheduleEntity warScheduleEntity = new WarScheduleEntity(warStart, warEnd);
//
//        warScheduleEntityManager.createWarScheduleEntity(warScheduleEntity);
//
//        warTimeManager.scheduleWarStart(warStart, warEnd);
//        return true;
//    }
//
//    private static LocalDateTime parseDate(String[] args) throws IllegalArgumentException {
//        if(args.length != 5) {
//            Bukkit.broadcastMessage("args length: " + args.length);
//            throw new IllegalArgumentException();
//        }
//        try {
//            int year = Integer.parseInt(args[0]);
//            int month = Integer.parseInt(args[1]);
//            int day = Integer.parseInt(args[2]);
//            int hour = Integer.parseInt(args[3]);
//            int minute = Integer.parseInt(args[4]);
//            return LocalDateTime.of(year, month, day, hour, minute);
//        } catch (NumberFormatException e) {
//            throw new IllegalArgumentException();
//        }
//    }
//
//    @Nullable
//    @Override
//    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
//        if(!command.getName().equalsIgnoreCase("wojnyconfig")) {
//            return Collections.emptyList();
//        }
//
//        List<String> completions = Collections.emptyList();
//        if(args.length == 0) {
//            completions.add("schedule");
//        }
//        if(args.length == 1) {
//            completions.add("start");
//        }
//        if(args.length == 3) {
//            completions.add("end");
//        }
//        return completions;
//    }
}
