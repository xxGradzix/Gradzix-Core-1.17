package me.xxgradzix.gradzixcore.events.commands;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.events.Events;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StartEvent implements CommandExecutor , TabCompleter {
    public StartEvent(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    private enum EVENT_NAME {
        GENERATOR, KLUCZ
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

                // TODO rozpoczales event z przedmiotem

                if(args.length > 3) {
                    try {
                        timeMinutes = Integer.parseInt(args[3]);
                    } catch (Exception ignored) {};
                }

                Bukkit.broadcastMessage(ChatColor.GRAY + "Rozpoczął się event " + ChatColor.GREEN +" key drop");
                Bukkit.broadcastMessage(ChatColor.GRAY + "Event zakończy sie za " + ChatColor.DARK_GRAY + timeMinutes + ChatColor.GRAY+ " minut");

                Events.setIsKeyEventEnabled(true);
                Events.setKeyRewardItem(reward);
                Events.setKeyRewardItemAmount(reward.getAmount());

                scheduler.runTaskLaterAsynchronously(plugin, (bukkitTask) -> {

                    Events.setIsKeyEventEnabled(false);
                    Bukkit.broadcastMessage(ChatColor.GRAY + "Event " + ChatColor.GREEN +" key drop " + ChatColor.GRAY + "zakończył się");

                }, 20L * timeMinutes * 60);

                break;
            case GENERATOR:
                Bukkit.broadcastMessage(ChatColor.GRAY + "Rozpoczął się event " + ChatColor.GREEN +" generator boost");
                Bukkit.broadcastMessage(ChatColor.GRAY + "Event zakończy sie za " + ChatColor.DARK_GRAY + timeMinutes + ChatColor.GRAY+ " minut");

                if(args.length > 2) {
                    try {
                        timeMinutes = Integer.parseInt(args[2]);
                    } catch (Exception ignored) {};
                }

                Events.setIsGeneratorEventEnabled(true);

                if(args.length > 2) {
                    try {
                        timeMinutes = Integer.parseInt(args[2]);
                    } catch (Exception ignored) {};
                }

                scheduler.runTaskLaterAsynchronously(plugin, (bukkitTask) -> {
                    Events.setIsGeneratorEventEnabled(false);
                    Bukkit.broadcastMessage(ChatColor.GRAY + "Event " + ChatColor.GREEN +" generator boost " + ChatColor.GRAY + "zakończył się");

                }, 20L * timeMinutes * 60);
                break;
            default:
                sender.sendMessage(ChatColor.RED + "nie ma takiego eventu");
        }

        return true;
    }
    private void cancelTask(EVENT_NAME eventName) {

        switch (eventName) {
            case GENERATOR:
                if(Events.isGeneratorEventEnabled()) {
                    Bukkit.broadcastMessage(ChatColor.GRAY + "Event " + ChatColor.GREEN +" generator boost " + ChatColor.GRAY + "zakończył się");
                }
                Events.setIsGeneratorEventEnabled(false);
                break;
            case KLUCZ:
                if(Events.isKeyEventEnabled()) {
                    Bukkit.broadcastMessage(ChatColor.GRAY + "Event " + ChatColor.GREEN +" key drop " + ChatColor.GRAY + "zakończył się");
                }
                Events.setIsKeyEventEnabled(false);
                break;
        }
    }
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        if(command.getName().equalsIgnoreCase("events") && args.length == 1){
            ArrayList<String> names = new ArrayList<>();
            names.add("START");
            names.add("STOP");
            return names;
        } else if(command.getName().equalsIgnoreCase("events") && args.length == 2) {
            ArrayList<String> names = new ArrayList<>();
            for (EVENT_NAME eventName : EVENT_NAME.values()) {
                names.add(eventName.name());
            }
            return names;
        }
        return null;
    }
}
