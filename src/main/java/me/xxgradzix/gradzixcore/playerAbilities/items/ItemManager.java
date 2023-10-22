package me.xxgradzix.gradzixcore.playerAbilities.items;

import me.xxgradzix.gradzixcore.playerAbilities.data.DataManager;
import me.xxgradzix.gradzixcore.playerAbilities.data.database.entities.enums.Ability;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {

    public static ItemStack fragment;

    // strength
    public static ItemStack sila0;
    public static ItemStack sila1;
    public static ItemStack sila2;
    public static ItemStack sila3;
    public static ItemStack sila4;

    // drop

    public static ItemStack drop0;
    public static ItemStack drop1;
    public static ItemStack drop2;
    public static ItemStack drop3;
    public static ItemStack drop4;

    // rank

    public static ItemStack rank0;
    public static ItemStack rank1;
    public static ItemStack rank2;
    public static ItemStack rank3;
    public static ItemStack rank4;


    private static int sila1Percent;
    private static int sila2Percent;
    private static int sila3Percent;
    private static int sila4Percent;

    private static int rank1Mod;
    private static int rank2Mod;
    private static int rank3Mod;
    private static int rank4Mod;


    private static double drop1Mod;
    private static double drop2Mod;
    private static double drop3Mod;
    private static double drop4Mod;


    public static void init() {

        createFragment();

        drop1Mod = DataManager.getAbilityModifier(Ability.DROP, 1);
        drop2Mod = DataManager.getAbilityModifier(Ability.DROP, 2);
        drop3Mod = DataManager.getAbilityModifier(Ability.DROP, 3);
        drop4Mod = DataManager.getAbilityModifier(Ability.DROP, 4);



        sila1Percent = (int) ((DataManager.getAbilityModifier(Ability.STRENGTH, 1) * 100) - 100);
        sila2Percent = (int) ((DataManager.getAbilityModifier(Ability.STRENGTH, 2) * 100) - 100);
        sila3Percent = (int) ((DataManager.getAbilityModifier(Ability.STRENGTH, 3) * 100) - 100);
        sila4Percent = (int) ((DataManager.getAbilityModifier(Ability.STRENGTH, 4) * 100) - 100);

        rank1Mod = (int) ((DataManager.getAbilityModifier(Ability.RANK, 1) * 100) - 100);
        rank2Mod = (int) ((DataManager.getAbilityModifier(Ability.RANK, 2) * 100) - 100);
        rank3Mod = (int) ((DataManager.getAbilityModifier(Ability.RANK, 3) * 100) - 100);
        rank4Mod = (int) ((DataManager.getAbilityModifier(Ability.RANK, 4) * 100) - 100);


        // strength
        createSila0();
        createSila1();
        createSila2();
        createSila3();
        createSila4();




        // drop
        createDrop0();
        createDrop1();
        createDrop2();
        createDrop3();
        createDrop4();

        createRank0();
        createRank1();
        createRank2();
        createRank3();
        createRank4();



    }

    // strength

    private static void createFragment() {

        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "§cOdłamek Jaskiniowca");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Służy do ulepszenia umiejętności");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        fragment = item;
    }

    // strength
    private static final Material STRENGTH_MATERIAL = Material.REDSTONE;

    private static void createSila0() {

        ItemStack item = new ItemStack(STRENGTH_MATERIAL, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Poziom Siły");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "0");
        lore.add(" ");
        lore.add(ChatColor.RED + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa obrażenia o " + ChatColor.RED + sila1Percent + "%");
        lore.add(ChatColor.RED + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa obrażenia o " + ChatColor.RED + sila2Percent+ "%");
        lore.add(ChatColor.RED + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa obrażenia o " + ChatColor.RED + sila3Percent+ "%");
        lore.add(ChatColor.RED + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa obrażenia o " + ChatColor.RED  + sila4Percent+ "%");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " + ChatColor.RED + "64x odłamki jaskiniowca");

        meta.setLore(lore);
        item.setItemMeta(meta);

        sila0 = item;
    }

    private static void createSila1() {

        ItemStack item = new ItemStack(STRENGTH_MATERIAL, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Poziom Siły");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "1");
        lore.add(" ");
        lore.add(ChatColor.GREEN + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa obrażenia o " + ChatColor.GREEN + sila1Percent + "%");
        lore.add(ChatColor.RED + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa obrażenia o " + ChatColor.RED + sila2Percent+ "%");
        lore.add(ChatColor.RED + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa obrażenia o " + ChatColor.RED + sila3Percent+ "%");
        lore.add(ChatColor.RED + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa obrażenia o " + ChatColor.RED + sila4Percent+ "%");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " + ChatColor.RED + "128x odłamki jaskiniowca");

        meta.setLore(lore);
        item.setItemMeta(meta);

        sila1 = item;
    }
    private static void createSila2() {

        ItemStack item = new ItemStack(STRENGTH_MATERIAL, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Poziom Siły");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "2");
        lore.add(" ");
        lore.add(ChatColor.GREEN + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa obrażenia o " + ChatColor.GREEN + sila1Percent + "%");
        lore.add(ChatColor.GREEN + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa obrażenia o " + ChatColor.GREEN + sila2Percent+ "%");
        lore.add(ChatColor.RED + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa obrażenia o " + ChatColor.RED + sila3Percent+ "%");
        lore.add(ChatColor.RED + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa obrażenia o " + ChatColor.RED + sila4Percent+ "%");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " + ChatColor.RED + "192x odłamki jaskiniowca");

        meta.setLore(lore);
        item.setItemMeta(meta);

        sila2 = item;
    }
    private static void createSila3() {

        ItemStack item = new ItemStack(STRENGTH_MATERIAL, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Poziom Siły");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "3");
        lore.add(" ");
        lore.add(ChatColor.GREEN + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa obrażenia o " + ChatColor.GREEN + sila1Percent + "%");
        lore.add(ChatColor.GREEN + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa obrażenia o " + ChatColor.GREEN + sila2Percent+ "%");
        lore.add(ChatColor.GREEN + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa obrażenia o " + ChatColor.GREEN + sila3Percent+ "%");
        lore.add(ChatColor.RED + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa obrażenia o " + ChatColor.RED + sila4Percent+ "%");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " + ChatColor.RED + "256x odłamki jaskiniowca");

        meta.setLore(lore);
        item.setItemMeta(meta);

        sila3 = item;
    }
    private static void createSila4() {

        ItemStack item = new ItemStack(STRENGTH_MATERIAL, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Poziom Siły");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "4");
        lore.add(" ");
        lore.add(ChatColor.GREEN + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa obrażenia o " + ChatColor.GREEN + sila1Percent + "%");
        lore.add(ChatColor.GREEN + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa obrażenia o " + ChatColor.GREEN + sila2Percent+ "%");
        lore.add(ChatColor.GREEN + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa obrażenia o " + ChatColor.GREEN + sila3Percent+ "%");
        lore.add(ChatColor.GREEN + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa obrażenia o " + ChatColor.GREEN + sila4Percent+ "%");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " + ChatColor.RED + "Masz już najwyższy poziom tej umiejętności");

        meta.setLore(lore);
        item.setItemMeta(meta);

        sila4 = item;
    }


    // drop
    private static final Material DROP_MATERIAL = Material.NETHERITE_PICKAXE;

    private static void createDrop0() {



        ItemStack item = new ItemStack(DROP_MATERIAL, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Dodatkowy drop");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "0");
        lore.add(" ");
        lore.add(ChatColor.RED + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa drop " + ChatColor.RED + "x" + drop1Mod);
        lore.add(ChatColor.RED + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa drop " + ChatColor.RED + "x" + drop2Mod);
        lore.add(ChatColor.RED + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa drop " + ChatColor.RED + "x" + drop3Mod);
        lore.add(ChatColor.RED + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa drop " + ChatColor.RED  + "x" + drop4Mod);
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " +  ChatColor.DARK_GRAY + "64x odłamki jaskiniowca");

        meta.setLore(lore);
        item.setItemMeta(meta);

        drop0 = item;
    }

    private static void createDrop1() {

        ItemStack item = new ItemStack(DROP_MATERIAL, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Dodatkowy drop");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "1");
        lore.add(" ");
        lore.add(ChatColor.GREEN + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa drop " + ChatColor.GREEN + "x" + drop1Mod);
        lore.add(ChatColor.RED + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa drop " + ChatColor.RED + "x" + drop2Mod);
        lore.add(ChatColor.RED + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa drop " + ChatColor.RED + "x" + drop3Mod);
        lore.add(ChatColor.RED + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa drop " + ChatColor.RED  + "x" + drop4Mod);
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " +  ChatColor.DARK_GRAY + "128x odłamki jaskiniowca");

        meta.setLore(lore);
        item.setItemMeta(meta);

        drop1 = item;
    }
    private static void createDrop2() {

        ItemStack item = new ItemStack(DROP_MATERIAL, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Dodatkowy drop");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "2");
        lore.add(" ");
        lore.add(ChatColor.GREEN + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa drop " + ChatColor.GREEN + "x" + drop1Mod);
        lore.add(ChatColor.GREEN + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa drop " + ChatColor.GREEN + "x" + drop2Mod);
        lore.add(ChatColor.RED + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa drop " + ChatColor.RED + "x" + drop3Mod);
        lore.add(ChatColor.RED + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa drop " + ChatColor.RED  + "x" + drop4Mod);
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " +  ChatColor.DARK_GRAY + "192x odłamki jaskiniowca");

        meta.setLore(lore);
        item.setItemMeta(meta);

        drop2 = item;
    }
    private static void createDrop3() {

        ItemStack item = new ItemStack(DROP_MATERIAL, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Dodatkowy drop");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "3");
        lore.add(" ");
        lore.add(ChatColor.GREEN + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa drop " + ChatColor.GREEN + "x" + drop1Mod);
        lore.add(ChatColor.GREEN + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa drop " + ChatColor.GREEN + "x" + drop2Mod);
        lore.add(ChatColor.GREEN + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa drop " + ChatColor.GREEN + "x" + drop3Mod);
        lore.add(ChatColor.RED + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa drop " + ChatColor.RED  + "x" + drop4Mod);
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " +  ChatColor.DARK_GRAY + "256x odłamki jaskiniowca");

        meta.setLore(lore);
        item.setItemMeta(meta);

        drop3 = item;
    }
    private static void createDrop4() {

        ItemStack item = new ItemStack(DROP_MATERIAL, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Dodatkowy drop");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "4");
        lore.add(" ");
        lore.add(ChatColor.GREEN + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa drop " + ChatColor.GREEN + "x" + drop1Mod);
        lore.add(ChatColor.GREEN + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa drop " + ChatColor.GREEN + "x" + drop2Mod);
        lore.add(ChatColor.GREEN + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa drop " + ChatColor.GREEN + "x" + drop3Mod);
        lore.add(ChatColor.GREEN + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa drop " + ChatColor.GREEN  + "x" + drop4Mod);
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " + ChatColor.DARK_GRAY + "Masz już najwyższy poziom tej umiejętności");

        meta.setLore(lore);
        item.setItemMeta(meta);

        drop4 = item;
    }

    // rank


    private static final Material rankMat = Material.EXPERIENCE_BOTTLE;

    private static void createRank0() {



        ItemStack item = new ItemStack(rankMat, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Dodatkowy ranking");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "0");
        lore.add(" ");
        lore.add(ChatColor.RED + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa ranking o " + ChatColor.RED + rank1Mod + "%");
        lore.add(ChatColor.RED + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa ranking o " + ChatColor.RED + rank2Mod+ "%");
        lore.add(ChatColor.RED + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa ranking o " + ChatColor.RED + rank3Mod+ "%");
        lore.add(ChatColor.RED + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa ranking o " + ChatColor.RED  + rank4Mod+ "%");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " +  ChatColor.DARK_GRAY + "64x odłamki jaskiniowca");

        meta.setLore(lore);
        item.setItemMeta(meta);

        rank0 = item;
    }

    private static void createRank1() {

        ItemStack item = new ItemStack(rankMat, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Dodatkowy ranking");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "1");
        lore.add(" ");
        lore.add(ChatColor.GREEN + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa ranking o " + ChatColor.GREEN + rank1Mod + "%");
        lore.add(ChatColor.RED + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa ranking o " + ChatColor.RED + rank2Mod+ "%");
        lore.add(ChatColor.RED + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa ranking o " + ChatColor.RED + rank3Mod+ "%");
        lore.add(ChatColor.RED + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa ranking o " + ChatColor.RED  + rank4Mod+ "%");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " +  ChatColor.DARK_GRAY + "128x odłamki jaskiniowca");

        meta.setLore(lore);
        item.setItemMeta(meta);

        rank1 = item;
    }
    private static void createRank2() {

        ItemStack item = new ItemStack(rankMat, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Dodatkowy ranking");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "2");
        lore.add(" ");
        lore.add(ChatColor.GREEN + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa ranking o " + ChatColor.GREEN + rank1Mod + "%");
        lore.add(ChatColor.GREEN + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa ranking o " + ChatColor.GREEN + rank2Mod+ "%");
        lore.add(ChatColor.RED + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa ranking o " + ChatColor.RED + rank3Mod+ "%");
        lore.add(ChatColor.RED + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa ranking o " + ChatColor.RED  + rank4Mod+ "%");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " +  ChatColor.DARK_GRAY + "192x odłamki jaskiniowca");

        meta.setLore(lore);
        item.setItemMeta(meta);

        rank2 = item;
    }
    private static void createRank3() {

        ItemStack item = new ItemStack(rankMat, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Dodatkowy ranking");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "3");
        lore.add(" ");
        lore.add(ChatColor.GREEN + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa ranking o " + ChatColor.GREEN + rank1Mod + "%");
        lore.add(ChatColor.GREEN + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa ranking o " + ChatColor.GREEN + rank2Mod+ "%");
        lore.add(ChatColor.GREEN + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa ranking o " + ChatColor.GREEN + rank3Mod+ "%");
        lore.add(ChatColor.RED + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_RED + "Zwiększa ranking o " + ChatColor.RED  + rank4Mod+ "%");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " +  ChatColor.DARK_GRAY + "256x odłamki jaskiniowca");

        meta.setLore(lore);
        item.setItemMeta(meta);

        rank3 = item;
    }
    private static void createRank4() {

        ItemStack item = new ItemStack(rankMat, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "Dodatkowy ranking");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Twój poziom: " + ChatColor.RED + "4");
        lore.add(" ");
        lore.add(ChatColor.GREEN + "Poziom I" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa ranking o " + ChatColor.GREEN + rank1Mod + "%");
        lore.add(ChatColor.GREEN + "Poziom II" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa ranking o " + ChatColor.GREEN + rank2Mod+ "%");
        lore.add(ChatColor.GREEN + "Poziom III" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa ranking o " + ChatColor.GREEN + rank3Mod+ "%");
        lore.add(ChatColor.GREEN + "Poziom IV" + ChatColor.GRAY + ": " + ChatColor.DARK_GREEN + "Zwiększa ranking o " + ChatColor.GREEN  + rank4Mod+ "%");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Cena: " + ChatColor.DARK_GRAY + "Masz już najwyższy poziom tej umiejętności");

        meta.setLore(lore);
        item.setItemMeta(meta);

        rank4 = item;
    }


}
