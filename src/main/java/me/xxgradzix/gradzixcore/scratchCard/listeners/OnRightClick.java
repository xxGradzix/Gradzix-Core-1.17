package me.xxgradzix.gradzixcore.scratchCard.listeners;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.chatOptions.Chatopcje;
import me.xxgradzix.gradzixcore.chatOptions.data.database.entities.ChatOptionsEntity;
import me.xxgradzix.gradzixcore.adminPanel.data.DataManager;
import me.xxgradzix.gradzixcore.scratchCard.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class OnRightClick implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {

        Player p = e.getPlayer();


        ItemStack itemInMainHand = p.getInventory().getItemInMainHand();
        ItemStack itemInOffHand = p.getInventory().getItemInOffHand();

        Action action = e.getAction();

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {

            if((p.getOpenInventory().getType() == InventoryType.CRAFTING) || (p.getOpenInventory().getType() == InventoryType.CREATIVE)) {



            if ((itemInMainHand != null && itemInMainHand.isSimilar(ItemManager.zdrapka)) || (itemInOffHand != null && itemInOffHand.isSimilar(ItemManager.zdrapka))) {

                if(!DataManager.getScratchCardStatus()) {
                    p.sendMessage(ChatColor.RED + "Zdrapki są chwilowo wyłączone");
                    return;
                }

//                List<ItemStack> items = (List<ItemStack>) ZdrapkaConfigFile.getCustomFile().get("items");
                ItemStack[] itemStacks = Arrays.asList(me.xxgradzix.gradzixcore.scratchCard.data.DataManager.getScratchCardItems()).toArray(new ItemStack[0]);
                List<ItemStack> items = new ArrayList<>();
                for (ItemStack item : itemStacks) {
                    if(item == null) continue;
                    items.add(item);
                }




                if(items.isEmpty() || items == null) {
                    p.sendMessage(ChatColor.RED + "Chwilowo zdrapka jest nieczynna");
                    return;
                }

                if( (itemInOffHand != null && itemInOffHand.isSimilar(ItemManager.zdrapka) ) && (itemInMainHand != null && itemInMainHand.isSimilar(ItemManager.zdrapka))) {
                    itemInMainHand.setAmount(itemInMainHand.getAmount() - 1);

                } else if( (itemInMainHand != null && itemInMainHand.isSimilar(ItemManager.zdrapka)) ) {
                    itemInMainHand.setAmount(itemInMainHand.getAmount() - 1);

                } else if( (itemInOffHand != null && itemInOffHand.isSimilar(ItemManager.zdrapka)) ) {
                    itemInOffHand.setAmount(itemInOffHand.getAmount() - 1);

                }

                Gui gui = Gui.gui()
                        .title(Component.text(ItemManager.zdrapka.getItemMeta().getDisplayName()))
                        .rows(3)
                        .disableAllInteractions()
                        .create();



                Random random = new Random();
                ItemStack losowyPrzedmiot = items.get(random.nextInt(items.size()));

                GuiItem yellowGlass = ItemBuilder.from(Material.YELLOW_STAINED_GLASS_PANE).setName(ChatColor.YELLOW+ "WYBIERZ").asGuiItem();
                GuiItem redGlass = ItemBuilder.from(Material.RED_STAINED_GLASS_PANE).setName(ChatColor.RED+ "WYBIERZ").asGuiItem();
                GuiItem aquaGlass = ItemBuilder.from(Material.LIGHT_BLUE_STAINED_GLASS_PANE).setName(ChatColor.AQUA+ "WYBIERZ").asGuiItem();
                GuiItem darkGreenGlass = ItemBuilder.from(Material.GREEN_STAINED_GLASS_PANE).setName(ChatColor.DARK_GREEN+ "WYBIERZ").asGuiItem();
                GuiItem darkPurpleGlass = ItemBuilder.from(Material.MAGENTA_STAINED_GLASS_PANE).setName(ChatColor.DARK_PURPLE+ "WYBIERZ").asGuiItem();
                GuiItem goldGlass = ItemBuilder.from(Material.ORANGE_STAINED_GLASS_PANE).setName(ChatColor.GOLD+ "WYBIERZ").asGuiItem();
                GuiItem blueGlass = ItemBuilder.from(Material.BLUE_STAINED_GLASS_PANE).setName(ChatColor.BLUE+ "WYBIERZ").asGuiItem();
                GuiItem lightGreen = ItemBuilder.from(Material.LIME_STAINED_GLASS_PANE).setName(ChatColor.GREEN+ "WYBIERZ").asGuiItem();


                AtomicBoolean swapped = new AtomicBoolean(false);



                gui.setDefaultClickAction((defaultAction) -> {

                    int clickedSlot = defaultAction.getSlot();

                    if(!gui.getInventory().contains(losowyPrzedmiot)) {
                        gui.disableAllInteractions();
                    }


                    if(gui.getInventory().getItem(clickedSlot) != null && gui.getInventory().getItem(clickedSlot).equals(losowyPrzedmiot)) {
                        gui.enableItemTake();
                        gui.setDefaultClickAction((newDefaultAction) -> {
                            gui.disableAllInteractions();
                        });
                    }



                    if(!swapped.get() && defaultAction.getClickedInventory().equals(gui.getInventory())) {

                        gui.getInventory().setItem(clickedSlot, losowyPrzedmiot);
                        swapped.set(true);
                    }


                });

                gui.setCloseGuiAction(event -> {
                    if(gui.getInventory().contains(losowyPrzedmiot) || (!containsEmptySlot(gui.getInventory()) && !gui.getInventory().contains(losowyPrzedmiot))) {
                        p.getInventory().addItem(losowyPrzedmiot);

                    }
                    List<ChatOptionsEntity> chatOptionsEntityList = Chatopcje.getChatOptionsEntityManager().getChatOptionsEntitiesWhereShowScratchCardMessageIs(false);
                    List<UUID> list = chatOptionsEntityList.stream().map(ChatOptionsEntity::getUuid).collect(Collectors.toList());

                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        if (!onlinePlayer.equals(p)) {

                            if (!list.contains(onlinePlayer.getUniqueId())) {

                                onlinePlayer.sendMessage(ChatColor.GOLD + p.getDisplayName() + " wylosował " + losowyPrzedmiot.getItemMeta().getDisplayName() + ChatColor.GOLD + " ze " + ChatColor.YELLOW + "Z" +
                                        ChatColor.RED + "d" +
                                        ChatColor.AQUA + "r" +
                                        ChatColor.DARK_GREEN + "a" +
                                        ChatColor.DARK_PURPLE + "p" +
                                        ChatColor.GOLD + "k" +
                                        ChatColor.BLUE + "i!");
                            }


                        } else {
                            p.sendMessage(ChatColor.GOLD + "Wylosowales " + losowyPrzedmiot.getItemMeta().getDisplayName() + ChatColor.GOLD + "!");
                        }
                    }
                });

                gui.addItem(blueGlass);
                gui.addItem(darkGreenGlass);
                gui.addItem(redGlass);
                gui.addItem(aquaGlass);
                gui.addItem(yellowGlass);
                gui.addItem(darkPurpleGlass);
                gui.addItem(lightGreen);
                gui.addItem(goldGlass);

                gui.addItem(blueGlass);
                gui.addItem(darkGreenGlass);
                gui.addItem(redGlass);
                gui.addItem(aquaGlass);
                gui.addItem(yellowGlass);
                gui.addItem(darkPurpleGlass);
                gui.addItem(lightGreen);
                gui.addItem(goldGlass);
                gui.addItem(blueGlass);
                gui.addItem(darkGreenGlass);
                gui.addItem(redGlass);
                gui.addItem(aquaGlass);
                gui.addItem(yellowGlass);
                gui.addItem(darkPurpleGlass);
                gui.addItem(lightGreen);
                gui.addItem(goldGlass);
                gui.addItem(blueGlass);
                gui.addItem(darkGreenGlass);
                gui.addItem(redGlass);
                gui.addItem(aquaGlass);
                gui.addItem(yellowGlass);
                gui.addItem(darkPurpleGlass);
                gui.addItem(lightGreen);
                gui.addItem(goldGlass);



                gui.open(p);


            }
        }
    }

    }
    public static boolean containsEmptySlot(Inventory inventory) {
        for (ItemStack item : inventory.getContents()) {
            if (item == null || item.getType() == Material.AIR) {
                return true;
            }
        }
        return false;
    }

}
