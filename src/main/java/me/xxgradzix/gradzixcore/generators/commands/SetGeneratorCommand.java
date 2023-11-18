package me.xxgradzix.gradzixcore.generators.commands;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.internal.annotation.Selection;
import com.sk89q.worldedit.regions.Region;
import me.xxgradzix.gradzixcore.generators.data.database.entities.Generator;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorLocation;
import me.xxgradzix.gradzixcore.generators.data.database.managers.GeneratorLocationManager;
import me.xxgradzix.gradzixcore.generators.data.database.managers.GeneratorManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class SetGeneratorCommand implements CommandExecutor, TabCompleter {

    private final WorldEditPlugin worldEdit;

    private final GeneratorManager generatorManager;
    private final GeneratorLocationManager generatorLocationManager;

    public SetGeneratorCommand(WorldEditPlugin worldEdit, GeneratorManager generatorManager, GeneratorLocationManager generatorLocationManager) {
        this.worldEdit = worldEdit;
        this.generatorManager = generatorManager;
        this.generatorLocationManager = generatorLocationManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        Region selection;

        try {
            selection = worldEdit.getSession(player).getSelection();
        } catch (IncompleteRegionException e) {
            player.sendMessage("Musisz najpierw zaznaczyc teren generatora");
            return false;
        }
        if(selection == null) {
            player.sendMessage("Musisz najpierw zaznaczyc teren generatora");
            return false;
        }

        if(args.length != 1) {
            player.sendMessage("Musisz podać id generatora");
            return false;
        }

        Long userInputId;

        try {
            userInputId = Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage("Musisz podać id generatora");
            return false;
        }

        Optional<Generator> optionalGenerator = generatorManager.getGeneratorByID(userInputId);

        if(!optionalGenerator.isPresent()) {
            player.sendMessage("Nie ma generatora o id " + userInputId);
            return false;
        }

        World world = BukkitAdapter.adapt(selection.getWorld());

        int minX = selection.getMinimumPoint().getBlockX();
        int minY = selection.getMinimumPoint().getBlockY();
        int minZ = selection.getMinimumPoint().getBlockZ();

        int maxX = selection.getMaximumPoint().getBlockX();
        int maxY = selection.getMaximumPoint().getBlockY();
        int maxZ = selection.getMaximumPoint().getBlockZ();

        Location minLocation = new Location(world, minX, minY, minZ);
        Location maxLocation = new Location(world, maxX, maxY, maxZ);

        GeneratorLocation generatorLocation = new GeneratorLocation(
                optionalGenerator.get(),
                world.getUID(),
                minLocation,
                maxLocation
        );
        generatorLocationManager.createOrUpdateGeneratorLocation(selection, generatorLocation);
//        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(selection.getWorld());
//        ProtectedCuboidRegion newRegion = new ProtectedCuboidRegion("generator",
//                selection.getMinimumPoint(),
//                selection.getMaximumPoint());
//
//        try {
//
//            regionManager.addRegion(newRegion);
//            regionManager.save();
//
//            player.sendMessage("Region został utworzony na podstawie zaznaczenia!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return true;
    }


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("setGenerator") && args.length >= 0){
            if(sender instanceof Player){
                Player player = (Player) sender;

                List<String> nameList = generatorManager.getAllGenerators().stream().map(Generator::getId).map(Objects::toString).collect(Collectors.toList());

                return new ArrayList<>(nameList);
            }
        }
        return null;
    }
}
