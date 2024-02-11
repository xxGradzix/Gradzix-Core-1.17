package me.xxgradzix.gradzixcore.generators.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.xxgradzix.gradzixcore.generators.data.database.persisters.MaterialListClassPersister;
import org.bukkit.Material;

import java.util.ArrayList;

@DatabaseTable(tableName = "gradzixcore_generator")
@Data
@NoArgsConstructor
public class GeneratorEntity {
    public GeneratorEntity(String name, Integer coolDownSeconds, ArrayList<Material> materials) {
        this.name = name;
        setCoolDownSeconds(coolDownSeconds);
        this.materials = materials;
    }

//    @DatabaseField(generatedId = true)
//    private Long id;

    @DatabaseField(id = true, unique = true, columnName = "generator_name")
    private String name;

    @DatabaseField
    private Integer coolDownSeconds;

    @DatabaseField(persisterClass = MaterialListClassPersister.class, columnDefinition = "LONGBLOB")
    private ArrayList<Material> materials;

    public void setCoolDownSeconds(Integer coolDownSeconds) {
        if(coolDownSeconds <= 180) {
            this.coolDownSeconds = 180;
        } else {
            this.coolDownSeconds = 300;
        }
    }


}

