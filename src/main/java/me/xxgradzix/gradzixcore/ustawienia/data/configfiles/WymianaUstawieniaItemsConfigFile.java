package me.xxgradzix.gradzixcore.ustawienia.data.configfiles;

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

    public static ArrayList<ItemStack> getAllItemKeys() {
        return (ArrayList<ItemStack>) getCustomFile().getList("items.keys");
    }
    public static ArrayList<ItemStack> getAllItemValues() {

        ArrayList<ItemStack> items = (ArrayList<ItemStack>) getCustomFile().getList("items.values");
        ArrayList<Integer> amounts = (ArrayList<Integer>) getCustomFile().getIntegerList("items.valuesAmounts");

        if(items == null || items.isEmpty()) return new ArrayList<>();
        for(int i = 0; i < items.size(); i++) {
            ItemStack item = items.get(i);
            item.setAmount(amounts.get(i));
            items.set(i, item);

        }

        return items;
    }

    public static void setItems(ArrayList<ItemStack> itemKeys, ArrayList<ItemStack> itemValues, ArrayList<Integer> itemValuesAmounts) {

        getCustomFile().set("items.keys", itemKeys);
        getCustomFile().set("items.values", itemValues);
        getCustomFile().set("items.valuesAmounts", itemValuesAmounts);
        save();

    }

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
