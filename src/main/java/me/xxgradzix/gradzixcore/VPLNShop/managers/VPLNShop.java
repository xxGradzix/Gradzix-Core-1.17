package me.xxgradzix.gradzixcore.VPLNShop.managers;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.GlobalItemManager;
import me.xxgradzix.gradzixcore.VPLNShop.data.DataManager;
import me.xxgradzix.gradzixcore.VPLNShop.items.ItemManager;
import me.xxgradzix.gradzixcore.VPLNShop.messages.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.atomic.AtomicInteger;

import static me.xxgradzix.gradzixcore.VPLNShop.messages.Messages.AMOUNT_TO_LOW_ERROR;
import static org.bukkit.Bukkit.getConsoleSender;
import static org.bukkit.Bukkit.getServer;

public class VPLNShop {

    public static final String VIP = "VIP";
    public static final String SVIP = "SVIP";
    public static final String UNI = "UNI";
    public static final String MAGIC_KEY = "MAGIC_KEY";
    public static final String UNI_KEY = "UNI_KEY";
    public static final String SCRATCH_CARD = "SCRATCH_CARD";
    public static final String FRAGMENT = "FRAGMENT";
    public enum Service {
        VIP,
        SVIP,
        UNI,
        MAGIC_KEY,
        UNI_KEY,
        SCRATCH_CARD,
        FRAGMENT
    }

    public static final Double VIP_PRICE = 10.0;
    public static final Double SVIP_PRICE = 20.0;
    public static final Double UNI_PRICE = 50.0;
    public static final Double MAGIC_KEY_PRICE = 1.5;
    public static final Double UNI_KEY_PRICE = 2.0;
    public static final Double SCRATCH_CARD_PRICE = 20.0;
    public static final Double FRAGMENT_PRICE = 0.1;


    private DataManager dataManager;

    public VPLNShop(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void openVPLNShop(Player player) {
        Gui gui = Gui.gui()
                .title(Component.text(ChatColor.AQUA + "" + ChatColor.BOLD + "Sklep VPLN" + ChatColor.RESET + ChatColor.GRAY + " (/potfel)"))
                .rows(5)
                .disableAllInteractions()
                .create();

        GuiItem vipItem = ItemManager.createVipShowcaseGuiItem(dataManager.getPlayerVPLNAmount(player), 1);
        GuiItem svipItem = ItemManager.createSvipShowcaseItem(dataManager.getPlayerVPLNAmount(player), 1);
        GuiItem uniItem = ItemManager.createUniShowcaseItem(dataManager.getPlayerVPLNAmount(player), 1);
        GuiItem magicKeyItem = ItemManager.createMagicKeyShowcaseItem(dataManager.getPlayerVPLNAmount(player), 1);
        GuiItem uniKeyItem = ItemManager.createUniKeyShowcaseItem(dataManager.getPlayerVPLNAmount(player),1 );
        GuiItem fragmentItem = ItemManager.createFragmentShowcaseItem(dataManager.getPlayerVPLNAmount(player), 1);
        GuiItem scratchCardItem = ItemManager.createScratchCardShowcaseItem(dataManager.getPlayerVPLNAmount(player), 1);

        vipItem.setAction(event -> {
            confirmPurchase(player, Service.VIP, 1);
        });
        svipItem.setAction(event -> {
            confirmPurchase(player, Service.SVIP, 1);
        });
        uniItem.setAction(event -> {
            confirmPurchase(player, Service.UNI, 1);
        });
        magicKeyItem.setAction(event -> {
            openKeyAmountSelector(player, Service.MAGIC_KEY);
        });
        uniKeyItem.setAction(event -> {
            openKeyAmountSelector(player, Service.UNI_KEY);
        });
        fragmentItem.setAction(event -> {
            openKeyAmountSelector(player, Service.FRAGMENT);
        });
        scratchCardItem.setAction(event -> {
            confirmPurchase(player, Service.SCRATCH_CARD, 1);
        });

        GuiItem backButton = ItemManager.cancelButtonGuiItem;
        backButton.setAction(event -> {
            gui.close(player);
        });

        gui.setItem(2, 3, vipItem);
        gui.setItem(2, 4, svipItem);
        gui.setItem(2, 6, uniItem);
        gui.setItem(3, 3, magicKeyItem);
        gui.setItem(3, 4, uniKeyItem);
        gui.setItem(3, 6, fragmentItem);
        gui.setItem(3, 7, scratchCardItem);

        gui.setItem(4, 5, backButton);

        gui.getFiller().fillBetweenPoints(1, 3, 1, 4, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
        gui.getFiller().fillBetweenPoints(1, 6, 1, 7, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
        gui.getFiller().fillBetweenPoints(6, 3, 6, 4, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
        gui.getFiller().fillBetweenPoints(6, 6, 6, 7, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);


        gui.setItem(3, 1, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);
        gui.setItem(3, 9, GlobalItemManager.FILLER_GLASS_PANE_GUI_ITEM);

        gui.setItem(1, 2, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);
        gui.setItem(5, 2, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);

        gui.setItem(1, 8, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);
        gui.setItem(5, 8, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);

        gui.setItem(2, 1, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);
        gui.setItem(2, 9, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);

        gui.setItem(4, 1, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);
        gui.setItem(4, 9, GlobalItemManager.DARK_GLASS_PANE_GUI_ITEM);

        gui.setItem(1, 1, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);
        gui.setItem(5, 1, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);

        gui.setItem(1, 9, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);
        gui.setItem(5, 9, GlobalItemManager.LIGHT_GLASS_PANE_GUI_ITEM);

        gui.open(player);
    }


    private void openKeyAmountSelector(Player player, Service keyType) {

        if(!keyType.equals(Service.MAGIC_KEY) && !keyType.equals(Service.UNI_KEY) && !keyType.equals(Service.FRAGMENT)) return;



        Gui gui = Gui.gui()
                .title(Component.text("Wybierz ilość"))
                .rows(3)
                .disableAllInteractions()
                .create();

        double balance = dataManager.getPlayerVPLNAmount(player);

        if(keyType.equals(Service.MAGIC_KEY) || keyType.equals(Service.UNI_KEY)) {
            GuiItem oneKey = ItemManager.getKeyAmountSelectorGuiItem(keyType, balance, 1);
            GuiItem fiveKeys = ItemManager.getKeyAmountSelectorGuiItem(keyType, balance, 5);
            GuiItem twentyKeys = ItemManager.getKeyAmountSelectorGuiItem(keyType, balance, 20);
            GuiItem twentyFiveKeys = ItemManager.getKeyAmountSelectorGuiItem(keyType, balance, 25);
            GuiItem fiftyKeys = ItemManager.getKeyAmountSelectorGuiItem(keyType, balance, 50);
            GuiItem hundredKeys = ItemManager.getKeyAmountSelectorGuiItem(keyType, balance, 100);
            GuiItem twoHundredKeys = ItemManager.getKeyAmountSelectorGuiItem(keyType, balance, 200);
            oneKey.setAction(event -> {
                confirmPurchase(player, keyType, 1);
            });
            fiveKeys.setAction(event -> {
                confirmPurchase(player, keyType, 5);
            });
            twentyKeys.setAction(event -> {
                confirmPurchase(player, keyType, 20);
            });
            twentyFiveKeys.setAction(event -> {
                confirmPurchase(player, keyType, 25);
            });
            fiftyKeys.setAction(event -> {
                confirmPurchase(player, keyType, 50);
            });
            hundredKeys.setAction(event -> {
                confirmPurchase(player, keyType, 100);
            });
            twoHundredKeys.setAction(event -> {
                confirmPurchase(player, keyType, 200);
            });
            gui.setItem(2, 2, oneKey);
            gui.setItem(2, 3, fiveKeys);
            gui.setItem(2, 4, twentyKeys);
            gui.setItem(2, 5, twentyFiveKeys);
            gui.setItem(2, 6, fiftyKeys);
            gui.setItem(2, 7, hundredKeys);
            gui.setItem(2, 8, twoHundredKeys);
        }
        if (keyType.equals(Service.FRAGMENT)) {
            GuiItem fiftyFragments = ItemManager.getKeyAmountSelectorGuiItem(keyType, balance, 50);
            GuiItem hundredFragments = ItemManager.getKeyAmountSelectorGuiItem(keyType, balance, 100);
            GuiItem twoHundredFragments = ItemManager.getKeyAmountSelectorGuiItem(keyType, balance, 200);
            GuiItem threeHundredFragments = ItemManager.getKeyAmountSelectorGuiItem(keyType, balance, 300);
            GuiItem fiveHundredFragments = ItemManager.getKeyAmountSelectorGuiItem(keyType, balance, 500);
            GuiItem sevenHundredFiftyFragments = ItemManager.getKeyAmountSelectorGuiItem(keyType, balance, 750);
            GuiItem thousandFragments = ItemManager.getKeyAmountSelectorGuiItem(keyType, balance, 1000);
            fiftyFragments.setAction(event -> {
                confirmPurchase(player, keyType, 50);
            });
            hundredFragments.setAction(event -> {
                confirmPurchase(player, keyType, 100);
            });
            twoHundredFragments.setAction(event -> {
                confirmPurchase(player, keyType, 200);
            });
            threeHundredFragments.setAction(event -> {
                confirmPurchase(player, keyType, 300);
            });
            fiveHundredFragments.setAction(event -> {
                confirmPurchase(player, keyType, 500);
            });
            sevenHundredFiftyFragments.setAction(event -> {
                confirmPurchase(player, keyType, 750);
            });
            thousandFragments.setAction(event -> {
                confirmPurchase(player, keyType, 1000);
            });
            gui.setItem(2, 2, fiftyFragments);
            gui.setItem(2, 3, hundredFragments);
            gui.setItem(2, 4, twoHundredFragments);
            gui.setItem(2, 5, threeHundredFragments);
            gui.setItem(2, 6, fiveHundredFragments);
            gui.setItem(2, 7, sevenHundredFiftyFragments);
            gui.setItem(2, 8, thousandFragments);
        }

        gui.open(player);



//        {
//
//        Gui gui = Gui.gui()
//                .title(Component.text("Wybierz ilość"))
//                .rows(5)
//                .disableAllInteractions()
//                .create();
//            AtomicInteger currentAmount = new AtomicInteger(1);
//            GuiItem addOne = new GuiItem(new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1));
//            GuiItem addSixteen = new GuiItem(new ItemStack(Material.LIME_STAINED_GLASS_PANE, 16));
//            GuiItem set64 = new GuiItem(new ItemStack(Material.LIME_STAINED_GLASS_PANE, 64));
//
//            GuiItem subtractOne = new GuiItem(new ItemStack(Material.RED_STAINED_GLASS_PANE, 1));
//            GuiItem subtractSixteen = new GuiItem(new ItemStack(Material.RED_STAINED_GLASS_PANE, 16));
//            GuiItem set1 = new GuiItem(new ItemStack(Material.RED_STAINED_GLASS_PANE, 1));
//            ItemStack magicKeyItem;
//            switch (keyType) {
//                case MAGIC_KEY:
//                    magicKeyItem = ItemManager.createMagicKeyShowcaseItem(dataManager.getPlayerVPLNAmount(player), 1).getItemStack();
//                    break;
//                case UNI_KEY:
//                    magicKeyItem = ItemManager.createUniKeyShowcaseItem(dataManager.getPlayerVPLNAmount(player), 1).getItemStack();
//                    break;
//                case FRAGMENT:
//                    magicKeyItem = ItemManager.createFragmentShowcaseItem(dataManager.getPlayerVPLNAmount(player), 1).getItemStack();
//                    break;
//                default:
//                    return;
//            }
//
//
//            gui.setItem(2, 5, ItemManager.getShowcaseItem(magicKeyItem, currentAmount.get(), getTotalPrice(keyType, currentAmount.get())));
//
//
//            addOne.setAction(event -> {
//                if (currentAmount.get() + 1 >= 64) {
//                    player.sendMessage(Messages.AMOUNT_TO_HIGH_ERROR);
//                    return;
//                }
//                currentAmount.getAndIncrement();
//                magicKeyItem.setAmount(currentAmount.get());
//                gui.setItem(2, 5, ItemManager.getShowcaseItem(magicKeyItem, currentAmount.get(), getTotalPrice(keyType, currentAmount.get())));
//
//                gui.update();
//            });
//            addSixteen.setAction(event -> {
//                if (currentAmount.get() + 16 > 64) {
//                    player.sendMessage(Messages.AMOUNT_TO_HIGH_ERROR);
//                    return;
//                }
//                currentAmount.getAndAdd(16);
//                magicKeyItem.setAmount(currentAmount.get());
//                gui.setItem(2, 5, ItemManager.getShowcaseItem(magicKeyItem, currentAmount.get(), getTotalPrice(keyType, currentAmount.get())));
//
//                gui.update();
//            });
//            set64.setAction(event -> {
//                currentAmount.set(64);
//                magicKeyItem.setAmount(currentAmount.get());
//                gui.setItem(2, 5, ItemManager.getShowcaseItem(magicKeyItem, currentAmount.get(), getTotalPrice(keyType, currentAmount.get())));
//
//                gui.update();
//            });
//            subtractOne.setAction(event -> {
//                if (currentAmount.get() - 1 <= 0) {
//                    player.sendMessage(AMOUNT_TO_LOW_ERROR);
//                    return;
//                }
//                currentAmount.getAndDecrement();
//                magicKeyItem.setAmount(currentAmount.get());
//                gui.setItem(2, 5, ItemManager.getShowcaseItem(magicKeyItem, currentAmount.get(), getTotalPrice(keyType, currentAmount.get())));
//
//                gui.update();
//            });
//            subtractSixteen.setAction(event -> {
//                if (currentAmount.get() - 16 <= 0) {
//                    player.sendMessage(AMOUNT_TO_LOW_ERROR);
//                    return;
//                }
//                currentAmount.getAndAdd(-16);
//                magicKeyItem.setAmount(currentAmount.get());
//                gui.setItem(2, 5, ItemManager.getShowcaseItem(magicKeyItem, currentAmount.get(), getTotalPrice(keyType, currentAmount.get())));
//
//                gui.update();
//            });
//            set1.setAction(event -> {
//                currentAmount.set(1);
//                magicKeyItem.setAmount(currentAmount.get());
//                gui.setItem(2, 5, ItemManager.getShowcaseItem(magicKeyItem, currentAmount.get(), getTotalPrice(keyType, currentAmount.get())));
//
//                gui.update();
//            });
//
//
//            gui.setItem(2, 2, subtractSixteen);
//            gui.setItem(2, 3, subtractOne);
//            gui.setItem(2, 1, set1);
//
//            gui.setItem(2, 7, addOne);
//            gui.setItem(2, 8, addSixteen);
//            gui.setItem(2, 9, set64);
//
//            GuiItem buyButtonGuiItem = ItemManager.buyButtonGuiItem;
//
//            buyButtonGuiItem.setAction(event -> {
//                confirmPurchase(player, keyType, currentAmount.get());
//            });
//
//            gui.setItem(4, 5, buyButtonGuiItem);
//
//            gui.open(player);
//        }

    }


    private void confirmPurchase(Player player, Service service, int amount) {

        Gui gui = Gui.gui()
                .title(Component.text("Potwierdź zakup"))
                .rows(5)
                .disableAllInteractions()
                .create();

        final Double balance = dataManager.getPlayerVPLNAmount(player);

        GuiItem showcaseItem;
        switch (service) {
            case VIP:
                showcaseItem = ItemManager.createVipShowcaseGuiItem(balance, amount);
                break;
            case SVIP:
                showcaseItem = ItemManager.createSvipShowcaseItem(balance, amount);
                break;
            case UNI:
                showcaseItem = ItemManager.createUniShowcaseItem(balance, amount);
                break;
            case MAGIC_KEY:
                showcaseItem = ItemManager.createMagicKeyShowcaseItem(balance, amount);
                break;
            case UNI_KEY:
                showcaseItem = ItemManager.createUniKeyShowcaseItem(balance, amount);
                break;
            case FRAGMENT:
                showcaseItem = ItemManager.createFragmentShowcaseItem(balance, amount);
                break;
            case SCRATCH_CARD:
                showcaseItem = ItemManager.createScratchCardShowcaseItem(balance, amount);
                break;
            default:
                return;
        }

        GuiItem showItem = ItemManager.getShowcaseItem(showcaseItem.getItemStack(), amount, balance);

        GuiItem confirm = ItemManager.createConfirmButton(getTotalPrice(service, amount));

        confirm.setAction(event -> {
            switch (service) {
                case VIP:
                    buyVipRank(player);
                    break;
                case SVIP:
                    buySVipRank(player);
                    break;
                case UNI:
                    buyUniRank(player);
                    break;
                case MAGIC_KEY:
                    buyMagicKey(player, amount);
                    break;
                case UNI_KEY:
                    buyUniKey(player, amount);
                    break;
                case FRAGMENT:
                    buyFragment(player, amount);
                    break;
                case SCRATCH_CARD:
                    buyScratchCard(player, amount);
                    break;
                default:
                    player.sendMessage("This service doesn't exist.");
                    break;
            }
            gui.close(player);
        });

        gui.setItem(2, 5, showItem);
        gui.setItem(4, 5, confirm);

        gui.open(player);
    }

    private void buyFragment(Player player, int amount) {
        Double playerVPLNAmount = dataManager.getPlayerVPLNAmount(player);

        if(playerVPLNAmount < FRAGMENT_PRICE * amount) {
            player.sendMessage(Messages.NOT_ENOUGH_VPLN);
            return;
        }
        dataManager.subtractVPLNAmount(player, FRAGMENT_PRICE * amount);
        player.getInventory().addItem(me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment);
        player.sendMessage(Messages.SUCCESSFUL_FRAGMENT_PURCHASE);
    }
    private void buyScratchCard(Player player, int amount) {
        Double playerVPLNAmount = dataManager.getPlayerVPLNAmount(player);

        if(playerVPLNAmount < SCRATCH_CARD_PRICE * amount) {
            player.sendMessage(Messages.NOT_ENOUGH_VPLN);
            return;
        }
        dataManager.subtractVPLNAmount(player, SCRATCH_CARD_PRICE * amount);
        player.getInventory().addItem(me.xxgradzix.gradzixcore.scratchCard.items.ItemManager.zdrapka);
        player.sendMessage(Messages.SUCCESSFUL_SCRATCHCARD_PURCHASE);
    }

    private Double getTotalPrice(Service serviceName, int amount) {

        switch (serviceName) {
            case VIP:
                return VIP_PRICE * amount;
            case SVIP:
                return SVIP_PRICE * amount;
            case UNI:
                return UNI_PRICE * amount;
            case MAGIC_KEY:
                return MAGIC_KEY_PRICE * amount;
            case UNI_KEY:
                return UNI_KEY_PRICE * amount;
            case FRAGMENT:
                return FRAGMENT_PRICE * amount;
            case SCRATCH_CARD:
                return SCRATCH_CARD_PRICE * amount;
            default:
                return 0.0;
        }
    }

    private void buyVipRank(Player player) {
        Double playerVPLNAmount = dataManager.getPlayerVPLNAmount(player);

        if(playerVPLNAmount < VIP_PRICE) {
            player.sendMessage(Messages.NOT_ENOUGH_VPLN);
            return;
        }
    dataManager.subtractVPLNAmount(player, VIP_PRICE);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set vip");

        player.sendMessage(Messages.SUCCESSFUL_VIP_PURCHASE);
    }
    private void buySVipRank(Player player) {
        Double playerVPLNAmount = dataManager.getPlayerVPLNAmount(player);

        if(playerVPLNAmount < SVIP_PRICE) {
            player.sendMessage(Messages.NOT_ENOUGH_VPLN);
            return;
        }
        dataManager.subtractVPLNAmount(player, SVIP_PRICE);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set svip");

        player.sendMessage(Messages.SUCCESSFUL_SVIP_PURCHASE);
    }
    private void buyUniRank(Player player) {
        Double playerVPLNAmount = dataManager.getPlayerVPLNAmount(player);

        if(playerVPLNAmount < UNI_PRICE) {
            player.sendMessage(Messages.NOT_ENOUGH_VPLN);
            return;
        }
        dataManager.subtractVPLNAmount(player, UNI_PRICE);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set uni");

        player.sendMessage(Messages.SUCCESSFUL_UNI_PURCHASE);
    }

    private void buyMagicKey(Player player, int amount) {
        Double playerVPLNAmount = dataManager.getPlayerVPLNAmount(player);

        if(playerVPLNAmount < MAGIC_KEY_PRICE * amount) {
            player.sendMessage(Messages.NOT_ENOUGH_VPLN);
            return;
        }
        dataManager.subtractVPLNAmount(player, MAGIC_KEY_PRICE * amount);
        getServer().dispatchCommand(getConsoleSender(), "excellentcrates key give " +
                player.getName() +
                " magiczna " + amount);

        player.sendMessage(Messages.SUCCESSFUL_MAGIC_KEY_PURCHASE);
    }
    private void buyUniKey(Player player, int amount) {
        Double playerVPLNAmount = dataManager.getPlayerVPLNAmount(player);

        if(playerVPLNAmount < UNI_KEY_PRICE * amount) {
            player.sendMessage(Messages.NOT_ENOUGH_VPLN);
            return;
        }
        dataManager.subtractVPLNAmount(player, UNI_KEY_PRICE * amount);
        getServer().dispatchCommand(getConsoleSender(), "excellentcrates key give " +
                player.getName() +
                " magiczna " + amount);

        player.sendMessage(Messages.SUCCESSFUL_UNI_KEY_PURCHASE);
    }



}
