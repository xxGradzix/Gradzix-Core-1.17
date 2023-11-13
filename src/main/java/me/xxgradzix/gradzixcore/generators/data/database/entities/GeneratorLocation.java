package me.xxgradzix.gradzixcore.generators.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.xxgradzix.gradzixcore.generators.data.database.persisters.LocationClassPersister;
import org.bukkit.Location;

import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_generator_location")
@Data
@NoArgsConstructor
public class GeneratorLocation {


    public GeneratorLocation(Generator generator, UUID worldUUID, Location minLocation, Location maxLocation) {
        this.generator = generator;
        this.worldUUID = worldUUID;
        this.minLocation = minLocation;
        this.maxLocation = maxLocation;
    }

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "generator_id")
    private Generator generator;

    @DatabaseField
    private UUID worldUUID;

    @DatabaseField(persisterClass = LocationClassPersister.class, columnDefinition = "TEXT")
    private Location minLocation;

    @DatabaseField(persisterClass = LocationClassPersister.class, columnDefinition = "TEXT")
    private Location maxLocation;


}
