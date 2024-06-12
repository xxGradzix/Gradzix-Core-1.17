package me.xxgradzix.gradzixcore.kits.managers;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.GlobalItemManager;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.globalStatic.TextInputFromChat;
import me.xxgradzix.gradzixcore.kits.data.DataManager;
import me.xxgradzix.gradzixcore.kits.data.database.entities.ItemInKitEntity;
import me.xxgradzix.gradzixcore.kits.data.database.entities.KitEntity;
import me.xxgradzix.gradzixcore.kits.messages.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class KitManager {

    private static final int KIT_PREVIEW_ROWS = 5;
    private static DataManager dataManager;

    private static HashMap<Player, HashMap<String, Long>> playerCooldowns = new HashMap<>();

    public KitManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public static void openKitSelector(Player player) {

        Gui gui = Gui.gui()
                .title(Component.text("§b§lKity"))
                .rows(3)
                .disableAllInteractions()
                .create();

        gui.getFiller().fillBorder(GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
        gui.setItem(1, 1, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);
        gui.setItem(1, 9, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);
        gui.setItem(3, 1, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);
        gui.setItem(3, 9, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);

        List<KitEntity> allKits = dataManager.getAllKits().stream().sorted(Comparator.comparingInt(KitEntity::getSlot).reversed()).collect(Collectors.toList());;

        for (KitEntity kit : allKits) {
            boolean hasPermission = player.hasPermission(kit.getPermission()) || player.isOp();

            GuiItem kitGuiItem = hasPermission ? new GuiItem(kit.getPermissionItem()) : new GuiItem(kit.getNoPermissionItem());

            kitGuiItem.setAction(event -> {
                if (event.isRightClick()) {
                    openKitViever(player, kit);
                    return;
                }
                if (hasPermission && event.isLeftClick()) {
                    receiveKit(player, kit);
                    return;
                }
                player.closeInventory();
                player.sendMessage(Messages.NO_PERMISSION);
            });


            if(kit.getSlot() < 0) {
                gui.addItem(kitGuiItem);
            } else {
                gui.setItem(kit.getSlot(), kitGuiItem);
            }
        }

        gui.open(player);
    }

    private static void openKitViever(Player player, KitEntity kit) {
        Gui gui = Gui.gui()
                .title(Component.text("§b§lKit: " + kit.getKitName()))
                .rows(5)
                .disableAllInteractions()
                .create();

        for (ItemInKitEntity itemInKitEntity : kit.getKitItems()) {
            if(itemInKitEntity.getItemStack() == null) continue;
            if(itemInKitEntity.getSlot() == -1) {
                gui.addItem(new GuiItem(itemInKitEntity.getItemStack()));
            } else {
                gui.setItem(itemInKitEntity.getSlot(), new GuiItem(itemInKitEntity.getItemStack()));
            }
        }

        gui.open(player);
    }

    private static void receiveKit(Player player, KitEntity kit) {
        List<ItemStack> itemsToCollect = kit.getKitItems().stream().map(ItemInKitEntity::getItemStack).collect(Collectors.toList());

        // get empty slots number in player inventory
        int size = Arrays.stream(player.getInventory().getStorageContents()).filter((item) -> {
            return item == null || item.getType().isAir();
        }).collect(Collectors.toList()).size();

        player.sendMessage(size + "");
//        if (itemsToCollect.size() > 36 - player.getInventory().getContents().length) {
//            player.sendMessage(Messages.INVENTORY_FULL);
//            return;
//        }

        // check if player has cooldown, if not collect and add cooldown to player

        HashMap<String, Long> playerCooldown = playerCooldowns.getOrDefault(player, new HashMap<>());

        Long orDefault = playerCooldown.getOrDefault(kit.getKitName(), 0L);

        if(orDefault < System.currentTimeMillis() || player.isOp()) {
            playerCooldown.put(kit.getKitName(), System.currentTimeMillis() + kit.getCooldownSeconds() * 1000);
            playerCooldowns.put(player, playerCooldown);

            for (ItemStack item : itemsToCollect) {
                player.getInventory().addItem(item);
            }
            player.sendMessage(Messages.KIT_RECEIVED);
        } else {
            int hours = (int) ((orDefault - System.currentTimeMillis()) / 1000 / 60 / 60);
            int minutes = (int) ((orDefault - System.currentTimeMillis()) / 1000 / 60 % 60);
            int seconds = (int) ((orDefault - System.currentTimeMillis()) / 1000 % 60);
            player.sendMessage(Messages.KIT_COOLDOWN.replace("{hours}", String.valueOf(hours)).replace("{minutes}", String.valueOf(minutes)).replace("{seconds}", String.valueOf(seconds)));
        }

        playerCooldowns.put(player, playerCooldown);
    }
    public static void createKit(Player player, String kitName) {
        dataManager.createKit(kitName, Arrays.asList(player.getInventory().getContents()));
        player.sendMessage(Messages.KIT_CREATED);
    }

    public static void openKitsEditor(Player player) {

        Gui gui = Gui.gui()
                .title(Component.text("§b§lEdytor kitów"))
                .rows(3)
                .create();

        dataManager.getAllKits().forEach(kitEntity -> {
            GuiItem guiItem = new GuiItem(kitEntity.getPermissionItem());

            if(kitEntity.getSlot() < 0) {
                gui.addItem(guiItem);
            } else gui.setItem(kitEntity.getSlot(), guiItem);

            guiItem.setAction(event -> {

                event.setCancelled(true);

                boolean shiftClick = event.isShiftClick();
                boolean rightClick = event.isRightClick();
                boolean leftClick = event.isLeftClick();
//                boolean isItemOnCursor = player.getItemOnCursor() != null && !player.getItemOnCursor().getType().isAir();
                boolean isItemOnCursor = event.getCursor() != null && event.getCursor().getType() != Material.AIR;

                if(isItemOnCursor && leftClick) {
                    ItemStack itemOnCursor = event.getCursor();
                    if(itemOnCursor == null || itemOnCursor.getType().isAir()) return;

                    kitEntity.setPermissionItem(itemOnCursor);
                    dataManager.updateKit(kitEntity);
                    openKitsEditor(player);
                    return;
                }
                if(isItemOnCursor && rightClick) {
                    ItemStack itemOnCursor = event.getCursor();
                    if(itemOnCursor == null || itemOnCursor.getType().isAir()) return;
                    kitEntity.setNoPermissionItem(itemOnCursor);
                    dataManager.updateKit(kitEntity);
                    openKitsEditor(player);

                    return;
                }

                if(!shiftClick && rightClick) {
                    player.closeInventory();
                    TextInputFromChat.getPlayerInput(player.getUniqueId()).thenAccept(input -> {
                        kitEntity.setSlot(Integer.parseInt(input));
                        dataManager.updateKit(kitEntity);
                        Bukkit.getScheduler().runTaskLater(Gradzix_Core.getInstance(), () -> {
                            openKitsEditor(player);
                        }, 1);
                    });
                    return;
                }

                    openKitEditor(player, kitEntity);

            });
        });
        gui.open(player);
    }

    private static void chooseSlot(Player player, KitEntity kitEntity, ItemInKitEntity itemInKitEntity) {
        player.sendMessage(Messages.CHOOSE_SLOT);

        AtomicInteger returnSlot = new AtomicInteger(-1);

        Gui gui = Gui.gui()
                .title(Component.text("§b§lWybierz slot"))
                .rows(KIT_PREVIEW_ROWS)
                .disableAllInteractions()
                .create();

        List<ItemInKitEntity> kitItems = kitEntity.getKitItems().stream().sorted(Comparator.comparingInt(ItemInKitEntity::getSlot).reversed()).collect(Collectors.toList());

        for (ItemInKitEntity item : kitItems) {
            if(item.getSlot() < 0) {
                gui.addItem(new GuiItem(item.getItemStack()));
            } else {
                gui.setItem(item.getSlot(), new GuiItem(item.getItemStack()));
            }
        }

        gui.setDefaultClickAction(event -> {

            if(event.getCurrentItem() != null && !event.getCurrentItem().getType().isAir()) return;

            event.setCancelled(true);
            returnSlot.set(event.getSlot());

            player.closeInventory();
            itemInKitEntity.setSlot(returnSlot.get());
            dataManager.updateItemInKitEntity(itemInKitEntity);
            Bukkit.getScheduler().runTaskLater(Gradzix_Core.getInstance(), () -> openKitEditor(player, kitEntity), 1);

        });

        gui.open(player);


    }

    private static void openKitEditor(Player player, KitEntity kitEntity) {


        Gui gui = Gui.gui()
                .title(Component.text("§b§lEdytor kitu: " + kitEntity.getKitName()))
                .rows(KIT_PREVIEW_ROWS)
                .disableAllInteractions()
                .create();

        // get list of ItemInKitEntity from kitEntity sorted by slot number from highest to lowest
         List<ItemInKitEntity> kitItems = kitEntity.getKitItems().stream().sorted(Comparator.comparingInt(ItemInKitEntity::getSlot).reversed()).collect(Collectors.toList());

        kitItems.forEach(itemInKitEntity -> {

            GuiItem guiItem = new GuiItem(itemInKitEntity.getItemStack());

            if(itemInKitEntity.getSlot() == -1) {
                gui.addItem(guiItem);
            } else {

                GuiItem guiItemOnSlot = gui.getGuiItem(itemInKitEntity.getSlot());

                gui.setItem(itemInKitEntity.getSlot(), guiItem);

                if(guiItemOnSlot != null) {
                    itemInKitEntity.setSlot(-1);
                    dataManager.updateItemInKitEntity(itemInKitEntity);
                    openKitEditor(player, kitEntity);
                    return;
                }
                gui.setItem(itemInKitEntity.getSlot(), guiItem);
            }

            guiItem.setAction(event -> {
                chooseSlot(player, kitEntity, itemInKitEntity);
            });
        });
        gui.open(player);
    }



}
