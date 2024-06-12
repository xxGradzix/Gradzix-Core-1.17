package me.xxgradzix.gradzixcore.VPLNShop.items;

import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.VPLNShop.managers.VPLNShop;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.xxgradzix.gradzixcore.magicFirework.items.ItemManager.convertColorText;

public class ItemManager {

    public static GuiItem cancelButtonGuiItem;
    public static GuiItem buyButtonGuiItem;

    public static void init() {
        createCancelButton();
        createBuyButton();
    }

    public static GuiItem createVipShowcaseGuiItem(double balance, int amount) {
        ItemStack vipItem = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta vipItemMeta = vipItem.getItemMeta();
        vipItemMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.YELLOW + "VIP");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "ZOBACZ PRZYWILEJE TEJ RANGI,");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "WPISUJĄC " + ChatColor.YELLOW + "" + ChatColor.BOLD + "/VIP");
        lore.add(" ");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Cena: " + ChatColor.YELLOW + VPLNShop.VIP_PRICE +  " VPLN");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Twój stan konta: " + ChatColor.YELLOW + balance + " VPLN");
        lore.add(" ");
        lore.add(ChatColor.AQUA + "vpln doładujesz na: www.unimc.pl");
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Kliknij aby zakupić rangę!");
        vipItemMeta.setLore(lore);
        vipItemMeta.addEnchant(Enchantment.LUCK, 1, true);
        vipItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        vipItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        vipItem.setItemMeta(vipItemMeta);
        return new GuiItem(vipItem);
    }
    public static GuiItem createSvipShowcaseItem(double balance, int amount) {
        ItemStack svipItem = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta svipItemMeta = svipItem.getItemMeta();
        svipItemMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.GOLD + "SVIP");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "ZOBACZ PRZYWILEJE TEJ RANGI,");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "WPISUJĄC " + ChatColor.GOLD + "" + ChatColor.BOLD + "/SVIP");
        lore.add(" ");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Cena: " + ChatColor.YELLOW + VPLNShop.SVIP_PRICE +  " VPLN");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Twój stan konta: " + ChatColor.YELLOW + balance + " VPLN");
        lore.add(" ");
        lore.add(ChatColor.AQUA + "vpln doładujesz na: www.unimc.pl");
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Kliknij aby zakupić rangę!");
        svipItemMeta.setLore(lore);
        svipItemMeta.addEnchant(Enchantment.LUCK, 1, true);
        svipItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        svipItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        svipItem.setItemMeta(svipItemMeta);
        return  new GuiItem(svipItem);
    }
    public static GuiItem createUniShowcaseItem(double balance, int amount) {
        ItemStack uniItem = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta uniItemMeta = uniItem.getItemMeta();
        uniItemMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.AQUA + "UNI");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "ZOBACZ PRZYWILEJE TEJ RANGI,");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "WPISUJĄC " + ChatColor.AQUA + "" + ChatColor.BOLD + "/UNI");
        lore.add(" ");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Cena: " + ChatColor.YELLOW + VPLNShop.UNI_PRICE +  " VPLN");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Twój stan konta: " + ChatColor.YELLOW + balance + " VPLN");
        lore.add(" ");
        lore.add(ChatColor.AQUA + "vpln doładujesz na: www.unimc.pl");
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Kliknij aby zakupić rangę!");
        uniItemMeta.setLore(lore);
        uniItemMeta.addEnchant(Enchantment.LUCK, 1, true);
        uniItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        uniItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        uniItem.setItemMeta(uniItemMeta);
        return new GuiItem(uniItem);
    }
    public static GuiItem createMagicKeyShowcaseItem(double balance, int amount) {
        ItemStack magicKeyItem = new ItemStack(Material.NAME_TAG);
        ItemMeta magicKeyItemMeta = magicKeyItem.getItemMeta();
        magicKeyItemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "MAGICZNY KLUCZ");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Ilość: " + ChatColor.YELLOW + amount);
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Cena: " + ChatColor.YELLOW + VPLNShop.MAGIC_KEY_PRICE * amount +  " VPLN");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Twój stan konta: " + ChatColor.YELLOW + balance + " VPLN");
        lore.add(" ");
        lore.add(ChatColor.AQUA + "vpln doładujesz na: www.unimc.pl");
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Kliknij tutaj, aby przejść do");
        lore.add(ChatColor.YELLOW + "wyboru wariantu usługi");
        magicKeyItemMeta.addEnchant(Enchantment.LUCK, 1, true);
        magicKeyItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        magicKeyItemMeta.setLore(lore);
        magicKeyItem.setItemMeta(magicKeyItemMeta);

        return new GuiItem(magicKeyItem);
    }
    public static GuiItem createUniKeyShowcaseItem(double balance, int amount) {
        ItemStack uniKeyItem = new ItemStack(Material.NAME_TAG);
        ItemMeta uniKeyItemMeta = uniKeyItem.getItemMeta();
        uniKeyItemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "UNIBOX KLUCZ");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Ilość: " + ChatColor.YELLOW + amount);
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Cena: " + ChatColor.YELLOW + VPLNShop.UNI_KEY_PRICE * amount +  " VPLN");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Twój stan konta: " + ChatColor.YELLOW + balance + " VPLN");
        lore.add(" ");
        lore.add(ChatColor.AQUA + "vpln doładujesz na: www.unimc.pl");
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Kliknij tutaj, aby przejść do");
        lore.add(ChatColor.YELLOW + "wyboru wariantu usługi");
        uniKeyItemMeta.addEnchant(Enchantment.LUCK, 1, true);
        uniKeyItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        uniKeyItemMeta.setLore(lore);
        uniKeyItem.setItemMeta(uniKeyItemMeta);
        return new GuiItem(uniKeyItem);
    }


    public static GuiItem getShowcaseItem(ItemStack itemStack, int amount, double price) {


        ItemStack item = itemStack.clone();

        item.setAmount(1);
        if(amount <= 64) item.setAmount(amount);

        ItemMeta itemMeta = item.getItemMeta();

        List<String> lore = new ArrayList<>();


        lore.add(" ");
        lore.add("§7Ilość: §3" + amount);
        lore.add("§7Cena: §6" + price + " VPLN");

        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);

        return new GuiItem(item);

    }

    private static void createCancelButton() {
        ItemStack cancelButton = new ItemStack(Material.RED_DYE);
        ItemMeta cancelButtonMeta = cancelButton.getItemMeta();
        cancelButtonMeta.setDisplayName("§cAnuluj zakup");
        cancelButton.setItemMeta(cancelButtonMeta);
        cancelButtonGuiItem = new GuiItem(cancelButton);
    }
    private static void createBuyButton() {
        ItemStack addOneButton = new ItemStack(Material.LIME_DYE);
        ItemMeta addOneButtonMeta = addOneButton.getItemMeta();
        addOneButtonMeta.setDisplayName("§aKup");
        addOneButton.setItemMeta(addOneButtonMeta);
        buyButtonGuiItem = new GuiItem(addOneButton);
    }

    public static GuiItem createConfirmButton(double price) {
        ItemStack confirmButton = new ItemStack(Material.LIME_DYE);
        ItemMeta confirmButtonMeta = confirmButton.getItemMeta();
        confirmButtonMeta.setDisplayName("§aPotwierdź zakup");

        List<String> lore = new ArrayList<>();
        lore.add("§7Cena: §6" + price + " VPLN");
        lore.add("§7Kliknij aby potwierdzić zakup");
        confirmButtonMeta.setLore(lore);
        confirmButton.setItemMeta(confirmButtonMeta);
        return new GuiItem(confirmButton);
    }

    public static GuiItem createFragmentShowcaseItem(double balance, int amount) {

        ItemStack uniKeyItem = new ItemStack(Material.PAPER);

        ItemMeta meta = uniKeyItem.getItemMeta();

        meta.setDisplayName(ChatColor.BOLD + "" + convertColorText("&#04aafbF&#0dadfbR&#16b0fbA&#1fb3fbG&#28b6fcM&#31b9fcE&#3abcfcN&#43bffcT &#4cc2fcK&#55c5fcS&#5ec8fdI&#67cbfdĘ&#70cefdG&#79d1fdI"));

        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Ilość: " + ChatColor.YELLOW + amount);
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Cena: " + ChatColor.YELLOW + VPLNShop.FRAGMENT_PRICE * amount +  " VPLN");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Twój stan konta: " + ChatColor.YELLOW + balance + " VPLN");
        lore.add(" ");
        lore.add(ChatColor.AQUA + "vpln doładujesz na: www.unimc.pl");
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Kliknij tutaj, aby przejść do");
        lore.add(ChatColor.YELLOW + "wyboru wariantu usługi");

        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);
        uniKeyItem.setItemMeta(meta);
        return new GuiItem(uniKeyItem);
    }

    public static GuiItem createScratchCardShowcaseItem(double balance, int amount) {
        ItemStack uniKeyItem = new ItemStack(Material.PAPER);
        ItemMeta meta = uniKeyItem.getItemMeta();

        meta.setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Z" +
                ChatColor.RED + ChatColor.BOLD.toString() + "d" +
                ChatColor.AQUA + ChatColor.BOLD.toString() + "r" +
                ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "a" +
                ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "p" +
                ChatColor.GOLD + ChatColor.BOLD.toString() + "k" +
                ChatColor.BLUE + ChatColor.BOLD.toString() + "a");

        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Cena: " + ChatColor.YELLOW + VPLNShop.SCRATCH_CARD_PRICE * amount +  " VPLN");
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "Twój stan konta: " + ChatColor.YELLOW + balance + " VPLN");
        lore.add(" ");
        lore.add(ChatColor.AQUA + "vpln doładujesz na: www.unimc.pl");
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Kliknij tutaj, aby przejść do");
        lore.add(ChatColor.YELLOW + "wyboru wariantu usługi");

        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);

        meta.setLore(lore);
        uniKeyItem.setItemMeta(meta);
        return new GuiItem(uniKeyItem);
    }

    public static GuiItem getKeyAmountSelectorGuiItem(VPLNShop.Service keyType, double balance, int amount) {

        if(amount > 64) amount = 64;
        if(keyType.equals(VPLNShop.Service.MAGIC_KEY)) {
            ItemStack itemStack = createMagicKeyShowcaseItem(balance, amount).getItemStack();

            itemStack.setAmount(amount);
            return new GuiItem(itemStack);
        }
        if (keyType.equals(VPLNShop.Service.UNI_KEY)) {
            ItemStack itemStack = createUniKeyShowcaseItem(balance, amount).getItemStack();
            itemStack.setAmount(amount);
            return new GuiItem(itemStack);
        }
        if(keyType.equals(VPLNShop.Service.FRAGMENT)) {
            ItemStack itemStack = createFragmentShowcaseItem(balance, amount).getItemStack();
            itemStack.setAmount(amount);
            return new GuiItem(itemStack);
        }
        throw new IllegalArgumentException("Invalid key type");
    }
}
