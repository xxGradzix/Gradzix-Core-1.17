package me.xxgradzix.gradzixcore.afkRegion.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;
import me.xxgradzix.gradzixcore.upgradeItem.data.database.persisters.ItemStackClassPersister;
import org.bukkit.inventory.ItemStack;

@DatabaseTable(tableName = "gradzixcore_afk_rewards")
@Getter
@Setter
public class RewardsEntity {


    @DatabaseField(id = true, unique = true)
    private int id;

    @DatabaseField(persisterClass = ItemStackClassPersister.class, columnDefinition = "TEXT")
    private ItemStack bigReward;

    @DatabaseField(persisterClass = ItemStackClassPersister.class, columnDefinition = "TEXT")
    private ItemStack smallReward;

    @DatabaseField
    private Double smallRewardMoney;

    public RewardsEntity(ItemStack bigReward, ItemStack smallReward) {
        this.id = 1;
        this.bigReward = bigReward;
        this.smallReward = smallReward;
        this.smallRewardMoney = 1.0;
    }

    public RewardsEntity() {
        id = 1;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = 1;
    }

    public ItemStack getBigReward() {
        return bigReward;
    }

    public void setBigReward(ItemStack bigReward) {
        this.bigReward = bigReward;
    }

    public ItemStack getSmallReward() {
        return smallReward;
    }
    public Double getSmallRewardMoney() {
        return smallRewardMoney;
    }
    public void setSmallRewardMoney(Double smallRewardMoney) {
        this.smallRewardMoney = smallRewardMoney;
    }


    public void setSmallReward(ItemStack smallReward) {
        this.smallReward = smallReward;
    }
}
