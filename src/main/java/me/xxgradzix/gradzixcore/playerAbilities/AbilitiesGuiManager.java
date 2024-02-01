package me.xxgradzix.gradzixcore.playerAbilities;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
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
                .rows(5)
                .create();


        // szklo

        ArrayList<Integer> czarne = new ArrayList<>();


        czarne.add(2);
        czarne.add(3);
        czarne.add(4);
        czarne.add(5);
        czarne.add(6);

        czarne.add(18);
        czarne.add(26);

        czarne.add(38);
        czarne.add(39);
        czarne.add(40);
        czarne.add(41);
        czarne.add(42);

        GuiItem blackGlass = new GuiItem(me.xxgradzix.gradzixcore.chatOptions.items.ItemManager.blackGlass);

        gui.setItem(czarne, blackGlass);


//            ArrayList<Integer> zielone = new ArrayList<>();
//
//            zielone.add(3);
//            zielone.add(5);
//            zielone.add(9);
//            zielone.add(17);
//            zielone.add(27);
//            zielone.add(35);
//            zielone.add(39);
//            zielone.add(41);
//
//            GuiItem greenGlass = new GuiItem(me.xxgradzix.gradzixcore.chatopcje.items.ItemManager.greenGlass);
//
//            gui.setItem(zielone, greenGlass);

        ArrayList<Integer> lime = new ArrayList<>();

        lime.add(0);
        lime.add(1);
        lime.add(7);
        lime.add(8);
        lime.add(9);
        lime.add(17);

        lime.add(27);
        lime.add(35);
        lime.add(36);
        lime.add(37);
        lime.add(43);
        lime.add(44);

        GuiItem limeGlass = new GuiItem(me.xxgradzix.gradzixcore.chatOptions.items.ItemManager.limeGlass);

        gui.setItem(lime, limeGlass);


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
//                if(p.getInventory().containsAtLeast(ItemManager.fragment, requiredAmount)) {
//
//                    removeItems(p, ItemManager.fragment, requiredAmount);
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
//                if(p.getInventory().containsAtLeast(ItemManager.fragment, requiredAmount)) {
//
//                    removeItems(p, ItemManager.fragment, requiredAmount);
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
//                if(p.getInventory().containsAtLeast(ItemManager.fragment, requiredAmount)) {
//
//                    removeItems(p, ItemManager.fragment, requiredAmount);
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
//                if(p.getInventory().containsAtLeast(ItemManager.fragment, requiredAmount)) {
//
//                    removeItems(p, ItemManager.fragment, requiredAmount);
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

        GuiItem drop0 = ItemBuilder.from(ItemManager.drop0).asGuiItem();
        GuiItem drop1 = ItemBuilder.from(ItemManager.drop1).asGuiItem();
        GuiItem drop2 = ItemBuilder.from(ItemManager.drop2).asGuiItem();
        GuiItem drop3 = ItemBuilder.from(ItemManager.drop3).asGuiItem();
        GuiItem drop4 = ItemBuilder.from(ItemManager.drop4).asGuiItem();

        drop0.setAction((action) -> {
            int requiredAmount = 64;
            if(p.getInventory().containsAtLeast(ItemManager.fragment, requiredAmount)) {

                removeItems(p, ItemManager.fragment, requiredAmount);

                gui.updateItem(action.getSlot(), drop1);

                DataManager.incrementAbilityLevel(Ability.DROP, p);

            } else {
                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
            }
        });
        drop1.setAction((action) -> {
            int requiredAmount = 128;
            if(p.getInventory().containsAtLeast(ItemManager.fragment, requiredAmount)) {

                removeItems(p, ItemManager.fragment, requiredAmount);

                gui.updateItem(action.getSlot(), drop2);

                DataManager.incrementAbilityLevel(Ability.DROP, p);

            } else {
                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
            }
        });
        drop2.setAction((action) -> {
            int requiredAmount = 192;
            if(p.getInventory().containsAtLeast(ItemManager.fragment, requiredAmount)) {

                removeItems(p, ItemManager.fragment, requiredAmount);

                gui.updateItem(action.getSlot(), drop3);
                DataManager.incrementAbilityLevel(Ability.DROP, p);

            } else {
                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
            }
        });
        drop3.setAction((action) -> {
            int requiredAmount = 256;
            if(p.getInventory().containsAtLeast(ItemManager.fragment, requiredAmount)) {

                removeItems(p, ItemManager.fragment, requiredAmount);

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
                gui.setItem(3, 4, drop0);
                break;
            case 1:
                gui.setItem(3, 4, drop1);
                break;
            case 2:
                gui.setItem(3, 4, drop2);
                break;
            case 3:
                gui.setItem(3, 4, drop3);
                break;
            case 4:
                gui.setItem(3, 4, drop4);
                break;
            default:
                gui.setItem(3, 4, new GuiItem(Material.BARRIER));
                break;
        }


        // rank


        GuiItem rank0 = ItemBuilder.from(ItemManager.rank0).asGuiItem();
        GuiItem rank1 = ItemBuilder.from(ItemManager.rank1).asGuiItem();
        GuiItem rank2 = ItemBuilder.from(ItemManager.rank2).asGuiItem();
        GuiItem rank3 = ItemBuilder.from(ItemManager.rank3).asGuiItem();
        GuiItem rank4 = ItemBuilder.from(ItemManager.rank4).asGuiItem();

        rank0.setAction((action) -> {
            int requiredAmount = 64;
            if(p.getInventory().containsAtLeast(ItemManager.fragment, requiredAmount)) {

                removeItems(p, ItemManager.fragment, requiredAmount);

                gui.updateItem(action.getSlot(), rank1);


                DataManager.incrementAbilityLevel(Ability.RANK, p);


            } else {
                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
            }
        });
        rank1.setAction((action) -> {
            int requiredAmount = 128;
            if(p.getInventory().containsAtLeast(ItemManager.fragment, requiredAmount)) {

                removeItems(p, ItemManager.fragment, requiredAmount);

                gui.updateItem(action.getSlot(), rank2);

                DataManager.incrementAbilityLevel(Ability.RANK, p);
            } else {
                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
            }
        });
        rank2.setAction((action) -> {
            int requiredAmount = 192;
            if(p.getInventory().containsAtLeast(ItemManager.fragment, requiredAmount)) {

                removeItems(p, ItemManager.fragment, requiredAmount);

                gui.updateItem(action.getSlot(), rank3);
                DataManager.incrementAbilityLevel(Ability.RANK, p);

            } else {
                p.sendMessage(ChatColor.RED + "Nie masz wystarczającej ilości odłamków");
            }
        });
        rank3.setAction((action) -> {
            int requiredAmount = 256;
            if(p.getInventory().containsAtLeast(ItemManager.fragment, requiredAmount)) {

                removeItems(p, ItemManager.fragment, requiredAmount);

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
                gui.setItem(3, 6, rank0);
                break;
            case 1:
                gui.setItem(3, 6, rank1);
                break;
            case 2:
                gui.setItem(3, 6, rank2);
                break;
            case 3:
                gui.setItem(3, 6, rank3);
                break;
            case 4:
                gui.setItem(3, 6, rank4);
                break;
            default:
                gui.setItem(3, 6, new GuiItem(Material.BARRIER));
                break;
        }

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
