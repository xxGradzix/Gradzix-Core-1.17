package me.xxgradzix.gradzixcore.ustawienia.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WymianaUstawieniaItemsConfigFile {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Gradzix-Core").getDataFolder(), "wymianaUstawieniaItems.yml");
        if(!file.exists()) {
            try{
                file.createNewFile();
            } catch (IOException e) {
                //
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getCustomFile() {
        return customFile;
    }

    public static void save() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            System.out.println("Couldn't save file");
        }
    }
    public static void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }


//    public static Map<ItemStack, ItemStack> getAllItems() {
//    public static ArrayList<ItemStack> getAllItems() {
//
//
////        List<Map<?, ?>> items = getCustomFile().getMapList("items");
////
////        if(items == null || items.isEmpty()) return new HashMap<>();
////
////        Map<ItemStack, ItemStack> map = (Map<ItemStack, ItemStack>) items.get(0);
////
////        if(map == null || map.isEmpty()) {
////            return new HashMap<>();
////        }
////
////        return map;
//
//        return (ArrayList<ItemStack>) getCustomFile().getList("items");
//
//    }

public static ArrayList<ItemStack> getAllItemKeys() {
//public static HashMap<ItemStack, Integer> getAllItemKeys() {


//    List<Map<?, ?>> items = getCustomFile().getMapList("items");
//
//    if(items == null || items.isEmpty()) return new HashMap<>();
//
////        Map<ItemStack, ItemStack> map = (Map<ItemStack, ItemStack>) items.get(0);
//    HashMap<ItemStack, Integer> map = (HashMap<ItemStack, Integer>) items.get(0);
//
//    if(map == null || map.isEmpty()) {
//        return new HashMap<>();
//    }
//
//    return map;
/////////////////////////////////////////////////////
    return (ArrayList<ItemStack>) getCustomFile().getList("items.keys");
////////////////////////////////////////////////////////////

}
    public static ArrayList<ItemStack> getAllItemValues() {
//    public static HashMap<ItemStack, Integer> getAllItemValues() {


//        List<Map<?, ?>> items = getCustomFile().getMapList("items");
//
//        if(items == null || items.isEmpty()) return new HashMap<>();
//
////        Map<ItemStack, ItemStack> map = (Map<ItemStack, ItemStack>) items.get(0);
//        HashMap<ItemStack, Integer> map = (HashMap<ItemStack, Integer>) items.get(1);
//
//        if(map == null || map.isEmpty()) {
//            return new HashMap<>();
//        }
//
//        return map;
////////////////////////////////////////
        ArrayList<ItemStack> items = (ArrayList<ItemStack>) getCustomFile().getList("items.values");
        ArrayList<Integer> amounts = (ArrayList<Integer>) getCustomFile().getIntegerList("items.valuesAmounts");

        if(items == null || items.isEmpty()) return new ArrayList<>();
        for(int i = 0; i < items.size(); i++) {
            ItemStack item = items.get(i);
            item.setAmount(amounts.get(i));
            items.set(i, item);

        }

        return items;

//        return (ArrayList<ItemStack>) getCustomFile().getList("items.values");
////////////////////////////////////////////////////////////

//        return (HashMap<ItemStack, Integer>) getCustomFile().getMapList("items").get(1);
    }




//    public static void setItems(HashMap<ItemStack, ItemStack> items) {
    public static void setItems(ArrayList<ItemStack> itemKeys, ArrayList<ItemStack> itemValues, ArrayList<Integer> itemValuesAmounts) {
//    public static void setItems(HashMap<ItemStack, Integer> itemKeys, HashMap<ItemStack, Integer> itemValues) {

//        ArrayList<HashMap<ItemStack, ItemStack>> list = new ArrayList<>();
//
//        list.add(items);
//
//        getCustomFile().set("items", list);
//        save();
//
//


        getCustomFile().set("items.keys", itemKeys);
        getCustomFile().set("items.values", itemValues);
        getCustomFile().set("items.valuesAmounts", itemValuesAmounts);
        save();
//        ArrayList<HashMap<ItemStack, Integer>> list = new ArrayList<>();
//
//        list.add(itemKeys);
//        list.add(itemValues);
//
//        getCustomFile().set("items", list);
//        save();

    }

    // sprzedaz


    public static Map<ItemStack, Integer> getAllItemsToSell() {


        List<Map<?, ?>> items = getCustomFile().getMapList("itemPrices");

        if(items == null || items.isEmpty()) return new HashMap<>();

        Map<ItemStack, Integer> map = (Map<ItemStack, Integer>) items.get(0);

        if(map == null || map.isEmpty()) {
            return new HashMap<>();
        }

        return map;
    }

    public static void setItemsToSell(HashMap<ItemStack, Integer> itemsToSell) {

        List<Map<?, ?>> list = new ArrayList<>();


        list.add(itemsToSell);

        getCustomFile().set("itemPrices", list);

        save();
    }


    public static int getItemPrice(ItemStack item) {


        List<Map<?, ?>> list = getCustomFile().getMapList("itemPrices");


        if(list == null || list.isEmpty()) return -1;

        HashMap<ItemStack, Integer> map = (HashMap<ItemStack, Integer>) list.get(0);

        int price = map.getOrDefault(item, -1);

        return price;

    }







}
