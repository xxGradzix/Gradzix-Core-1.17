package me.xxgradzix.gradzixcore.events.commands;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.events.Events;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class StartEvent implements CommandExecutor, TabCompleter {
    public StartEvent(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    private enum EVENT_NAME {
        GENERATOR, KLUCZ, JEZIORKO
    }

    private static final HashMap<EVENT_NAME, Integer> eventsIds = new HashMap<>();

    private final Gradzix_Core plugin;

    BukkitScheduler scheduler = Bukkit.getScheduler();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length < 2) {
            sender.sendMessage("Musisz sprecyzować nazwe eventu");
            sender.sendMessage("Poprawne uzycie to /events [start/stop] [EVENT_TYPE]");
            return true;
        }

        String choice = args[0];
        String chosenEventName = args[1];


        EVENT_NAME eventChoice;

        try {
            eventChoice = EVENT_NAME.valueOf(chosenEventName);
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "nie ma takiego eventu");
            return false;
        }

        if(choice.equalsIgnoreCase("STOP")) {
            cancelTask(eventChoice);
            return true;
        }

        if(!choice.equalsIgnoreCase("START")) return false;

        int timeMinutes = 2;

        switch (eventChoice) {
            case KLUCZ:
            {
                if(!(sender instanceof Player)) {
                    sender.sendMessage("tylko gracz moze rozpoczac ten event");
                    return false;
                }

                ItemStack reward = ((Player) sender).getInventory().getItemInMainHand();

                if(reward == null || reward.getType().equals(Material.AIR)) {
                    sender.sendMessage("Musisz trzymac przedmiot w rece");
                    return false;
                }

                double chance = 0.01;

                if(args.length > 2) {

                    try {
                        chance = Double.parseDouble(args[2]);
                        if(chance < 1) {
                            Events.setKeyDropChance(chance);
                        }
                    } catch (Exception ignored) {
                        //todo correct command message
                    };
                } else {
                    sender.sendMessage("Poprawne uzycie to /events START KLUCZ [szansa] [czas]");
                }

                if(args.length > 3) {
                    try {
                        timeMinutes = Integer.parseInt(args[3]);
                    } catch (Exception ignored) {};
                }

                startKeyEventTask(timeMinutes, reward, chance);
            }
            break;
            case GENERATOR: {
                if(args.length > 2) {
                    try {
                        timeMinutes = Integer.parseInt(args[2]);
                    } catch (Exception ignored) {};
                }
                startGeneratorEventTask(timeMinutes);
            }
            break;
            case JEZIORKO: {
                if(args.length > 2) {
                    try {
                        timeMinutes = Integer.parseInt(args[2]);
                    } catch (Exception ignored) {};
                }
                startMagicPondEventTask(timeMinutes);
            }
            break;
            default:
                sender.sendMessage(ChatColor.RED + "nie ma takiego eventu");
        }

        return true;
    }
    private void startGeneratorEventTask(int timeMinutes){
        Bukkit.broadcastMessage(ChatColor.GRAY + "Rozpoczął się event " + ChatColor.GREEN +" generator boost");
        Bukkit.broadcastMessage(ChatColor.GRAY + "Event zakończy sie za " + ChatColor.DARK_GRAY + timeMinutes + ChatColor.GRAY+ " minut");

        Events.setIsGeneratorEventEnabled(true);

        startTask(EVENT_NAME.GENERATOR, timeMinutes);
    }
    private void startKeyEventTask(int timeMinutes, ItemStack reward, double chance){

        if(reward == null || reward.getType().equals(Material.AIR)) {
            throw new NullPointerException("Reward cannot be null");
        }
        // TODO rozpoczales event z przedmiotem

        Bukkit.broadcastMessage(ChatColor.GRAY + "Rozpoczął się event " + ChatColor.GREEN +" key drop");
        Bukkit.broadcastMessage(ChatColor.GRAY + "Event zakończy sie za " + ChatColor.DARK_GRAY + timeMinutes + ChatColor.GRAY+ " minut");

        Events.setIsKeyEventEnabled(true);
        Events.setKeyRewardItem(reward);
        Events.setKeyRewardItemAmount(reward.getAmount());

        startTask(EVENT_NAME.KLUCZ, timeMinutes);
    }
    private void startMagicPondEventTask(int timeMinutes){

        Bukkit.broadcastMessage(ChatColor.GRAY + "Rozpoczął się event " + ChatColor.AQUA +" magiczne jeziorko");
        Bukkit.broadcastMessage(ChatColor.GRAY + "Event zakończy sie za " + ChatColor.DARK_GRAY + timeMinutes + ChatColor.GRAY+ " minut");

        Events.setIsMagicPondEventEnabled(true);

        startTask(EVENT_NAME.JEZIORKO, timeMinutes);
    }
    private void startTask(EVENT_NAME eventName, int timeMinutes) throws NoSuchElementException { // todo save id to map
        int id;
        cancelTask(eventName);
        switch (eventName){
            case KLUCZ:{

                id = scheduler.runTaskLaterAsynchronously(plugin, () -> {

                    Events.setIsKeyEventEnabled(false);
                    Bukkit.broadcastMessage(ChatColor.GRAY + "Event " + ChatColor.GREEN +" key drop " + ChatColor.GRAY + "zakończył się");

                }, 20L * timeMinutes * 60).getTaskId();

            }
            break;
            case GENERATOR: {
                id = scheduler.runTaskLaterAsynchronously(plugin, () -> {
                    Events.setIsGeneratorEventEnabled(false);
                    Bukkit.broadcastMessage(ChatColor.GRAY + "Event " + ChatColor.GREEN +" generator boost " + ChatColor.GRAY + "zakończył się");

                }, 20L * timeMinutes * 60).getTaskId();
            }
            break;
            case JEZIORKO: {
                id = scheduler.runTaskLater(plugin, () -> {
                    Events.setIsMagicPondEventEnabled(false);
                    Bukkit.broadcastMessage(ChatColor.GRAY + "Event " + ChatColor.AQUA +" magiczne jeziorko " + ChatColor.GRAY + "zakończył się");

                }, 20L * timeMinutes * 60).getTaskId();
            }
            break;
            default: {
                throw new NoSuchElementException();
            }
        }
        eventsIds.put(eventName, id);

    }
    private void cancelTask(EVENT_NAME eventName) {
        if(!eventsIds.containsKey(eventName)) {
            return;
        }
        scheduler.cancelTask(eventsIds.get(eventName));
        eventsIds.remove(eventName);
        startTask(eventName, 0);
    }
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(!command.getName().equalsIgnoreCase("events")) return new ArrayList<>();

        if(args.length == 1){
            ArrayList<String> names = new ArrayList<>();
            names.add("START");
            names.add("STOP");
            return names;
        } else if(args.length == 2) {
            ArrayList<String> names = new ArrayList<>();
            for (EVENT_NAME eventName : EVENT_NAME.values()) {
                names.add(eventName.name());
            }
            return names;
        } else if(args.length == 3) {
            ArrayList<String> nums = new ArrayList<>();
            for (int i = 1; i <=60; i++) {
                nums.add(String.valueOf(i));
            }
            return nums;
        }

        return new ArrayList<>();
    }

}
