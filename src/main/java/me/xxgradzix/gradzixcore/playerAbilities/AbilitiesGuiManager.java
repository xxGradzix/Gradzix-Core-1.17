package me.xxgradzix.gradzixcore.playerAbilities;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.GlobalItemManager;
import me.xxgradzix.gradzixcore.playerAbilities.data.DataManager;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.enums.Ability;
import me.xxgradzix.gradzixcore.playerAbilities.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;

public class AbilitiesGuiManager {


    public static void openAbilitiesGui(Player p) {


        Gui gui = Gui.gui()
                .title(Component.text(ChatColor.GREEN + ChatColor.BOLD.toString() + "UMIEJĘTNOŚCI " + ChatColor.GRAY + "(/umiejetnosci)"))
                .disableAllInteractions()
                .rows(3)
                .create();


        // szklo

        GuiItem blackGlass = GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM;

        gui.getFiller().fillBetweenPoints(1, 2, 1, 8, blackGlass);
        gui.getFiller().fillBetweenPoints(3, 2, 3, 8, blackGlass);

        gui.setItem(2, 1, blackGlass);
        gui.setItem(2, 9, blackGlass);

        GuiItem limeGlass = GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM;

        gui.setItem(1, 1, limeGlass);
        gui.setItem(1, 9, limeGlass);
        gui.setItem(3, 1, limeGlass);
        gui.setItem(3, 9, limeGlass);




        // sila

        /** Strength ability is currently off, increase damage event will also be turned off */
//
//            GuiItem sila0 = ItemBuilder.from(ItemManager.sila0).asGuiItem();
//            GuiItem sila1 = ItemBuilder.from(ItemManager.sila1).asGuiItem();
//            GuiItem sila2 = ItemBuilder.from(ItemManager.sila2).asGuiItem();
//            GuiItem sila3 = ItemBuilder.from(ItemManager.sila3).asGuiItem();
//            GuiItem sila4 = ItemBuilder.from(ItemManager.sila4).asGuiItem();
//
//            sila0.setAction((action) -> {
//                int requiredAmount = 64;
//                if(p.getInventory().containsAtLeast(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount)) {
//
//                    removeItems(p, me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount);
//
//                    gui.updateItem(action.getSlot(), sila1);
//
//                    DataManager.incrementAbilityLevel(Ability.STRENGTH, p);
//
//                } else {
//                    p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
//                }
//            });
//            sila1.setAction((action) -> {
//                int requiredAmount = 128;
//                if(p.getInventory().containsAtLeast(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount)) {
//
//                    removeItems(p, me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount);
//
//                    gui.updateItem(action.getSlot(), sila2);
//
//                    DataManager.incrementAbilityLevel(Ability.STRENGTH, p);
//                } else {
//                    p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
//                }
//            });
//            sila2.setAction((action) -> {
//                int requiredAmount = 192;
//                if(p.getInventory().containsAtLeast(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount)) {
//
//                    removeItems(p, me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount);
//
//                    gui.updateItem(action.getSlot(), sila3);
//                    DataManager.incrementAbilityLevel(Ability.STRENGTH, p);
//
//                } else {
//                    p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
//                }
//            });
//            sila3.setAction((action) -> {
//                int requiredAmount = 256;
//                if(p.getInventory().containsAtLeast(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount)) {
//
//                    removeItems(p, me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount);
//
//                    gui.updateItem(action.getSlot(), sila4);
//                    DataManager.incrementAbilityLevel(Ability.STRENGTH, p);
//
//                } else {
//                    p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
//                }
//            });
//            sila4.setAction((action) -> {
//                p.sendMessage(ChatColor.RED + "Masz już najwyższy poziom tej umiejętności");
//            });
//
//            int playerStrengthLevel = DataManager.getPlayerAbilityLevel(Ability.STRENGTH, p);;
//
//            switch (playerStrengthLevel) {
//                case 0:
//                    gui.setItem(3, 5, sila0);
//                    break;
//                case 1:
//                    gui.setItem(3, 5, sila1);
//                    break;
//                case 2:
//                    gui.setItem(3, 5, sila2);
//                    break;
//                case 3:
//                    gui.setItem(3, 5, sila3);
//                    break;
//                case 4:
//                    gui.setItem(3, 5, sila4);
//                    break;
//                default:
//                    gui.setItem(3, 5, new GuiItem(Material.BARRIER));
//                    break;
//            }

        // drop

        GuiItem drop0 = ItemBuilder.from(ItemManager.createDropByLevel(0)).asGuiItem();
        GuiItem drop1 = ItemBuilder.from(ItemManager.createDropByLevel(1)).asGuiItem();
        GuiItem drop2 = ItemBuilder.from(ItemManager.createDropByLevel(2)).asGuiItem();
        GuiItem drop3 = ItemBuilder.from(ItemManager.createDropByLevel(3)).asGuiItem();
        GuiItem drop4 = ItemBuilder.from(ItemManager.createDropByLevel(4)).asGuiItem();

        drop0.setAction((action) -> {
            int requiredAmount = 64;
            if(p.getInventory().containsAtLeast(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount)) {

                removeItems(p, me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount);

                gui.updateItem(action.getSlot(), drop1);

                DataManager.incrementAbilityLevel(Ability.DROP, p);

            } else {
                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
            }
        });
        drop1.setAction((action) -> {
            int requiredAmount = 128;
            if(p.getInventory().containsAtLeast(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount)) {

                removeItems(p, me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount);

                gui.updateItem(action.getSlot(), drop2);

                DataManager.incrementAbilityLevel(Ability.DROP, p);

            } else {
                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
            }
        });
        drop2.setAction((action) -> {
            int requiredAmount = 192;
            if(p.getInventory().containsAtLeast(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount)) {

                removeItems(p, me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount);

                gui.updateItem(action.getSlot(), drop3);
                DataManager.incrementAbilityLevel(Ability.DROP, p);

            } else {
                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
            }
        });
        drop3.setAction((action) -> {
            int requiredAmount = 256;
            if(p.getInventory().containsAtLeast(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount)) {

                removeItems(p, me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount);

                gui.updateItem(action.getSlot(), drop4);
//                    UmiejetnosciConfigFile.incrementDropLevel(p);
                DataManager.incrementAbilityLevel(Ability.DROP, p);


            } else {
                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
            }
        });
        drop4.setAction((action) -> {
            p.sendMessage(ChatColor.RED + "Masz już najwyższy poziom tej umiejętności");
        });

        int playerDropLevel = DataManager.getPlayerAbilityLevel(Ability.DROP, p);

        switch (playerDropLevel) {
            case 0:
                gui.setItem(2, 4, drop0);
                break;
            case 1:
                gui.setItem(2, 4, drop1);
                break;
            case 2:
                gui.setItem(2, 4, drop2);
                break;
            case 3:
                gui.setItem(2, 4, drop3);
                break;
            case 4:
                gui.setItem(2, 4, drop4);
                break;
            default:
                gui.setItem(2, 4, new GuiItem(Material.BARRIER));
                break;
        }


        // rank


        GuiItem rank0 = ItemBuilder.from(ItemManager.createRankByLevel(0)).asGuiItem();
        GuiItem rank1 = ItemBuilder.from(ItemManager.createRankByLevel(1)).asGuiItem();
        GuiItem rank2 = ItemBuilder.from(ItemManager.createRankByLevel(2)).asGuiItem();
        GuiItem rank3 = ItemBuilder.from(ItemManager.createRankByLevel(3)).asGuiItem();
        GuiItem rank4 = ItemBuilder.from(ItemManager.createRankByLevel(4)).asGuiItem();

        rank0.setAction((action) -> {
            int requiredAmount = 64;
            if(p.getInventory().containsAtLeast(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount)) {

                removeItems(p, me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount);

                gui.updateItem(action.getSlot(), rank1);


                DataManager.incrementAbilityLevel(Ability.RANK, p);


            } else {
                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
            }
        });
        rank1.setAction((action) -> {
            int requiredAmount = 128;
            if(p.getInventory().containsAtLeast(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount)) {

                removeItems(p, me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount);

                gui.updateItem(action.getSlot(), rank2);

                DataManager.incrementAbilityLevel(Ability.RANK, p);
            } else {
                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
            }
        });
        rank2.setAction((action) -> {
            int requiredAmount = 192;
            if(p.getInventory().containsAtLeast(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount)) {

                removeItems(p, me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount);

                gui.updateItem(action.getSlot(), rank3);
                DataManager.incrementAbilityLevel(Ability.RANK, p);

            } else {
                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
            }
        });
        rank3.setAction((action) -> {
            int requiredAmount = 256;
            if(p.getInventory().containsAtLeast(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount)) {

                removeItems(p, me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment, requiredAmount);

                gui.updateItem(action.getSlot(), rank4);
                DataManager.incrementAbilityLevel(Ability.RANK, p);

            } else {
                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
            }
        });
        rank4.setAction((action) -> {
            p.sendMessage(ChatColor.RED + "Masz już najwyższy poziom tej umiejętności");
        });


        int playerRankLevel = DataManager.getPlayerAbilityLevel(Ability.RANK, p);

        switch (playerRankLevel) {
            case 0:
                gui.setItem(2, 6, rank0);
                break;
            case 1:
                gui.setItem(2, 6, rank1);
                break;
            case 2:
                gui.setItem(2, 6, rank2);
                break;
            case 3:
                gui.setItem(2, 6, rank3);
                break;
            case 4:
                gui.setItem(2, 6, rank4);
                break;
            default:
                gui.setItem(2, 6, new GuiItem(Material.BARRIER));
                break;
        }
        DataManager.refreshAbilities(p);
        gui.open(p);

    }
    public static void removeItems(Player player, ItemStack itemStack, int amount) {
        PlayerInventory inventory = player.getInventory();
        int remainingAmount = amount;

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);

            if (item != null && item.isSimilar(itemStack)) {
                int itemAmount = item.getAmount();

                if (itemAmount <= remainingAmount) {
                    remainingAmount -= itemAmount;
                    inventory.setItem(i, null);
                } else {
                    item.setAmount(itemAmount - remainingAmount);
                    break;
                }
            }
        }
    }





}
