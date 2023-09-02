package me.xxgradzix.gradzixcore.ustawienia.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;
import me.xxgradzix.gradzixcore.ustawienia.data.database.persisters.HashMapItemStackIntegerType;
import me.xxgradzix.gradzixcore.ustawienia.data.database.persisters.HashMapItemStackItemStackType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

@DatabaseTable(tableName = "gradzixcore_settings_items")
@Getter
@Setter
public class SettingsItemsEntity {


    @DatabaseField(id = true, unique = true)
    private int id;

    @DatabaseField(persisterClass = HashMapItemStackItemStackType.class, columnDefinition = "LONGBLOB")
    private HashMap<ItemStack, ItemStack> itemsToExchange;

    @DatabaseField(persisterClass = HashMapItemStackIntegerType.class, columnDefinition = "LONGBLOB")
    private HashMap<ItemStack, Integer> itemsToSell;

    public HashMap<ItemStack, Integer> getItemsToSell() {
        return new HashMap<>(itemsToSell);
    }

    public void setItemsToSell(HashMap<ItemStack, Integer> itemsToSell) {
        this.itemsToSell = itemsToSell;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = 1;
    }

    public HashMap<ItemStack, ItemStack> getItemsToExchange() {
        return new HashMap<>(itemsToExchange);
    }

    public void setItemsToExchange(HashMap<ItemStack, ItemStack> itemsToExchange) {
        this.itemsToExchange = itemsToExchange;
    }

    public SettingsItemsEntity(HashMap<ItemStack, ItemStack> itemsToExchange, HashMap<ItemStack, Integer> itemsToSell) {
        this.id = 1;
        this.itemsToExchange = itemsToExchange;
        this.itemsToSell = itemsToSell;
    }

    public SettingsItemsEntity() {
        this.id = 1;
    }
}
