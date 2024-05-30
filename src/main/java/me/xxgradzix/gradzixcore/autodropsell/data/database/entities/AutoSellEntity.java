package me.xxgradzix.gradzixcore.autodropsell.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import me.xxgradzix.gradzixcore.autodropsell.data.database.persisters.HashMapMaterialDoubleType;
import org.bukkit.Material;

import java.util.HashMap;

@DatabaseTable(tableName = "gradzixcore_autosell")
public class AutoSellEntity {

    @DatabaseField(id = true, unique = true)
    private int id;

    @DatabaseField(persisterClass = HashMapMaterialDoubleType.class, columnDefinition = "LONGBLOB")
    private HashMap<Material, Double> itemsToSell;

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = 1;
    }

    public HashMap<Material, Double> getItemsToSell() {
        return new HashMap<>(itemsToSell);
    }
    public void setItemsToSell(HashMap<Material, Double> itemsToSell) {
        this.itemsToSell = itemsToSell;
    }

    public AutoSellEntity() {
        this.id = 1;
        this.itemsToSell = new HashMap<>();
    }

}
