package me.xxgradzix.gradzixcore.generators.commands;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.SessionOwner;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import me.xxgradzix.gradzixcore.generators.Generators;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorEntity;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorLocationEntity;
import me.xxgradzix.gradzixcore.generators.data.database.managers.GeneratorLocationEntityManager;
import me.xxgradzix.gradzixcore.generators.data.database.managers.GeneratorEntityManager;
import me.xxgradzix.gradzixcore.generators.managers.GeneratorManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

    private final GeneratorManager generatorManager;

    public SetGeneratorCommand(GeneratorManager generatorManager) {
        this.generatorManager = generatorManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        Region selection;
        BukkitPlayer bukkitPlayer = BukkitAdapter.adapt(player);

        try {
            selection = bukkitPlayer.getSelection();
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

        String userInputName = args[0];

        Optional<GeneratorEntity> optionalGenerator = generatorManager.getGeneratorByName(userInputName);

        if(!optionalGenerator.isPresent()) {
            player.sendMessage("Nie ma generatora o nazwie " + userInputName);
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

        try {
            generatorManager.createGeneratorLocation(selection, optionalGenerator.get(), world.getUID(), minLocation, maxLocation);
        } catch (StorageException e) {
            player.sendMessage("Wystąpił błąd podczas zapisywania regionu generatora");
            player.sendMessage("Ustaw mu region ponownie jak plugin worldguard bedzie dzialal");
        }

        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("setGenerator") && args.length >= 0){
            if(sender instanceof Player){

                List<String> nameList = generatorManager.getAllGenerators().stream().map(GeneratorEntity::getName).collect(Collectors.toList());

                return new ArrayList<>(nameList);
            }
        }
        return null;
    }
}
