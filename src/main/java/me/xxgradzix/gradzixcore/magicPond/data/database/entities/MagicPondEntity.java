package me.xxgradzix.gradzixcore.magicPond.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import me.xxgradzix.gradzixcore.magicPond.data.database.persisters.HashMapItemStackIntegerType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

@DatabaseTable(tableName = "gradzixcore_magic_pond_rewards")
@Getter
public class MagicPondEntity {

    public MagicPondEntity() {
        this.id = 1;
        this.rewards = new HashMap<>();
    }

    public MagicPondEntity(HashMap<ItemStack, Integer> rewards) {
        this.id = 1;
        this.rewards = rewards;
    }

    @DatabaseField(id = true, unique = true)
    private int id;


    @DatabaseField(persisterClass = HashMapItemStackIntegerType.class, columnDefinition = "LONGBLOB")
    private HashMap<ItemStack, Integer> rewards;

    public void setRewards(HashMap<ItemStack, Integer> rewards) {
        this.rewards = rewards;
    }

    public void setId() {
        this.id = 1;
    }
}
