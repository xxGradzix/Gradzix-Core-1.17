package me.xxgradzix.gradzixcore.VPLNShop.managers;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.VPLNShop.data.DataManager;
import me.xxgradzix.gradzixcore.VPLNShop.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.atomic.AtomicInteger;

import static org.bukkit.Bukkit.getConsoleSender;
import static org.bukkit.Bukkit.getServer;

public class VPLNShop {

    private static final String VIP = "VIP";
    private static final String SVIP = "SVIP";
    private static final String UNI = "UNI";
    private static final String MAGIC_KEY = "MAGIC_KEY";
    private static final String UNI_KEY = "UNI_KEY";

    private static final Double VIP_PRICE = 10.0;
    private static final Double SVIP_PRICE = 30.0;
    private static final Double UNI_PRICE = 50.0;
    private static final Double MAGIC_KEY_PRICE = 0.5;
    private static final Double UNI_KEY_PRICE = 2.5;


    private DataManager dataManager;

    public VPLNShop(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void openVPLNShop(Player player) {
        Gui gui = Gui.gui()
                .title(Component.text("VPLN Shop"))
                .rows(5)
                .disableAllInteractions()
                .create();

        GuiItem vipItem = new GuiItem(ItemManager.vipShowcaseItem);
        GuiItem svipItem = new GuiItem(ItemManager.svipShowcaseItem);
        GuiItem uniItem = new GuiItem(ItemManager.uniShowcaseItem);
        GuiItem magicKeyItem = new GuiItem(ItemManager.magicKeyShowcaseItem);
        GuiItem uniKeyItem = new GuiItem(ItemManager.uniKeyShowcaseItem);

        vipItem.setAction(event -> {
            confirmPurchase(player, VIP, 1);
        });
        svipItem.setAction(event -> {
            confirmPurchase(player, SVIP, 1);
        });
        uniItem.setAction(event -> {
            confirmPurchase(player, UNI, 1);
        });
        magicKeyItem.setAction(event -> {
            openKeyAmountSelector(player, MAGIC_KEY);
        });
        uniKeyItem.setAction(event -> {
            openKeyAmountSelector(player, UNI_KEY);
        });

        GuiItem backButton = ItemManager.cancelButtonGuiItem;
        backButton.setAction(event -> {
            gui.close(player);
        });

        gui.setItem(2, 2, vipItem);
        gui.setItem(2, 4, svipItem);
        gui.setItem(2, 6, uniItem);
        gui.setItem(3, 3, magicKeyItem);
        gui.setItem(3, 7, uniKeyItem);
        gui.setItem(4, 5, backButton);

        gui.open(player);
    }


    private void openKeyAmountSelector(Player player, String keyType) {
        if(!keyType.equals(MAGIC_KEY) && !keyType.equals(UNI_KEY)) return;

        AtomicInteger currentAmount = new AtomicInteger(1);

        Gui gui = Gui.gui()
                .title(Component.text("Wybierz ilość"))
                .rows(5)
                .disableAllInteractions()
                .create();

        GuiItem addOne = new GuiItem(new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1));
        GuiItem addSixteen = new GuiItem(new ItemStack(Material.LIME_STAINED_GLASS_PANE, 16));
        GuiItem set64 = new GuiItem(new ItemStack(Material.LIME_STAINED_GLASS_PANE, 64));

        GuiItem subtractOne = new GuiItem(new ItemStack(Material.RED_STAINED_GLASS_PANE, 1));
        GuiItem subtractSixteen = new GuiItem(new ItemStack(Material.RED_STAINED_GLASS_PANE, 16));
        GuiItem set1 = new GuiItem(new ItemStack(Material.RED_STAINED_GLASS_PANE, 1));

        addOne.setAction(event -> {
            if(currentAmount.get() + 1 >= 64){
                player.sendMessage("You can't buy more than 64 keys at once.");
                return;
            }
            currentAmount.getAndIncrement();
            gui.update();
        });
        addSixteen.setAction(event -> {
            if(currentAmount.get() + 16 > 64){
                player.sendMessage("You can't buy more than 64 keys at once.");
                return;
            }
            currentAmount.getAndAdd(16);
            gui.update();
        });
        set64.setAction(event -> {
            currentAmount.set(64);
            gui.update();
        });
        subtractOne.setAction(event -> {
            if(currentAmount.get() - 1 <= 0){
                player.sendMessage("You can't buy less than 1 key.");
                return;
            }
            currentAmount.getAndDecrement();
            gui.update();
        });
        subtractSixteen.setAction(event -> {
            if(currentAmount.get() - 16 <= 0){
                player.sendMessage("You can't buy less than 1 key.");
                return;
            }
            currentAmount.getAndAdd(-16);
            gui.update();
        });
        set1.setAction(event -> {
            currentAmount.set(1);
            gui.update();
        });

        gui.setItem(2, 2, subtractSixteen);
        gui.setItem(2, 3, subtractOne);
        gui.setItem(2, 1, set1);

        gui.setItem(2, 7, addOne);
        gui.setItem(2, 8, addSixteen);
        gui.setItem(2, 9, set64);

        GuiItem buyButtonGuiItem = ItemManager.buyButtonGuiItem;

        buyButtonGuiItem.setAction(event -> {
            confirmPurchase(player, keyType, currentAmount.get());
        });

        gui.setItem(4, 5, buyButtonGuiItem);

        gui.open(player);

    }

    private void confirmPurchase(Player player, String serviceName, int amount) {

        Gui gui = Gui.gui()
                .title(Component.text("Potwierdź zakup"))
                .rows(5)
                .disableAllInteractions()
                .create();

        final Double totalPrice = getTotalPrice(serviceName, amount);

        GuiItem showItem = ItemManager.getShowcaseItem(new ItemStack(Material.DIAMOND), amount, totalPrice);

        GuiItem confirm = ItemManager.createConfirmButton(getTotalPrice(serviceName, amount));

        confirm.setAction(event -> {
            switch (serviceName) {
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

    private Double getTotalPrice(String serviceName, int amount) {

        switch (serviceName.toUpperCase()) {
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
            default:
                return 0.0;
        }
    }

    private void buyVipRank(Player player) {
        Double playerVPLNAmount = dataManager.getPlayerVPLNAmount(player);

        if(playerVPLNAmount < VIP_PRICE) {
            player.sendMessage("You don't have enough VPLN to buy this product.");
            return;
        }
    dataManager.subtractVPLNAmount(player, VIP_PRICE);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set vip");

        player.sendMessage("You have successfully bought VIP rank.");
    }
    private void buySVipRank(Player player) {
        Double playerVPLNAmount = dataManager.getPlayerVPLNAmount(player);

        if(playerVPLNAmount < SVIP_PRICE) {
            player.sendMessage("You don't have enough VPLN to buy this product.");
            return;
        }
        dataManager.subtractVPLNAmount(player, SVIP_PRICE);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set svip");

        player.sendMessage("You have successfully bought SVIP rank.");
    }
    private void buyUniRank(Player player) {
        Double playerVPLNAmount = dataManager.getPlayerVPLNAmount(player);

        if(playerVPLNAmount < UNI_PRICE) {
            player.sendMessage("You don't have enough VPLN to buy this product.");
            return;
        }
        dataManager.subtractVPLNAmount(player, UNI_PRICE);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent set uni");

        player.sendMessage("You have successfully bought UNI rank.");
    }

    private void buyMagicKey(Player player, int amount) {
        Double playerVPLNAmount = dataManager.getPlayerVPLNAmount(player);

        if(playerVPLNAmount < MAGIC_KEY_PRICE * amount) {
            player.sendMessage("You don't have enough VPLN to buy this product.");
            return;
        }
        dataManager.subtractVPLNAmount(player, MAGIC_KEY_PRICE * amount);
        getServer().dispatchCommand(getConsoleSender(), "excellentcrates key give " +
                player.getName() +
                " magiczna " + amount);

        player.sendMessage("You have successfully bought Magic Key.");
    }
    private void buyUniKey(Player player, int amount) {
        Double playerVPLNAmount = dataManager.getPlayerVPLNAmount(player);

        if(playerVPLNAmount < UNI_KEY_PRICE * amount) {
            player.sendMessage("You don't have enough VPLN to buy this product.");
            return;
        }
        dataManager.subtractVPLNAmount(player, UNI_KEY_PRICE * amount);
        getServer().dispatchCommand(getConsoleSender(), "excellentcrates key give " +
                player.getName() +
                " magiczna " + amount);

        player.sendMessage("You have successfully bought Magic Key.");
    }



}
