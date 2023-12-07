package me.xxgradzix.gradzixcore.magicPond.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.magicPond.data.DataManager;
import me.xxgradzix.gradzixcore.magicPond.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MagicPondConfig implements CommandExecutor, TabCompleter {

    private final DataManager dataManager;

    public MagicPondConfig(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) return false;

        Player p = (Player) sender;

        if(args.length < 1) {

            return false;
        }
        String action = args[0].toLowerCase();

        switch (action) {
            case "dodaj":

                if(args.length != 2) {
                    p.sendMessage("Za malo argumentow");
                    return true;
                }

                String chanceString = args[1];
                int chance;
                try {
                    chance = Integer.parseInt(chanceString);
                } catch (Exception e) {
                    p.sendMessage("szansa musi byc liczba");
                    return true;
                }
                addReward(p, chance);
                break;
            case "usun":
                removeReward(p);
                break;
            default:
                p.sendMessage("Nie ma takiej komendy");

        }



        return true;
    }

    private void addReward(Player p, int chance) {
        HashMap<ItemStack, Integer> items = dataManager.getMagicPondEntityRewards();

        ItemStack item = p.getInventory().getItemInMainHand();

        if(item == null || item.getType().equals(Material.AIR)) {
            p.sendMessage("Musisz trzymac przedmiot w rece");
            return;
        }
        items.put(item, chance);
        p.sendMessage("dodales " + item.getType().toString() + " z szansa " + chance + " do nagrod jeziorka");
        dataManager.setMagicPondEntityRewards(items);
    }

    private void removeReward(Player player) {
        Gui gui = Gui.gui()
                .title(Component.text("Nagrody z jeziorka!"))
                .rows(5)
                .disableAllInteractions()
                .create();


        HashMap<ItemStack, Integer> items = dataManager.getMagicPondEntityRewards();

        for (ItemStack item : items.keySet()) {

            GuiItem guiItem = new GuiItem(ItemManager.createItemRewardButton(item.clone(), items.getOrDefault(item, 0)));

            guiItem.setAction((a) -> {
                items.remove(item);
                gui.removeItem(guiItem);
                gui.update();
                dataManager.setMagicPondEntityRewards(items);
            });
            gui.addItem(guiItem);
        }
        gui.open(player);
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("jeziorkoconfig")) {
            if(args.length == 1) {
                List<String> completions = new ArrayList<>();
                completions.add("dodaj");
                completions.add("usun");
                return completions;
            } else if(args.length == 2) {
                ArrayList<String> nums = new ArrayList<>();
                for (int i = 1; i <=100; i++) {
                    nums.add(String.valueOf(i));
                }
                return nums;
            }
        }
        return null;
    }
}
