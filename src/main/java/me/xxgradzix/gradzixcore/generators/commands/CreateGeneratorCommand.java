package me.xxgradzix.gradzixcore.generators.commands;


import me.xxgradzix.gradzixcore.generators.managers.GeneratorManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CreateGeneratorCommand implements CommandExecutor {
    private final GeneratorManager generatorManager;

    public CreateGeneratorCommand( GeneratorManager generatorManager) {
        this.generatorManager = generatorManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(args.length < 3) {
            player.sendMessage("Musisz podać nazwe generatora, cooldown oraz materialy z jakiego ma sie skladac");
            return false;
        }

        String name = args[0];
        int coolDownSeconds;
        try {
            coolDownSeconds = Integer.parseInt(args[1]);
        } catch (Exception e) {
            player.sendMessage("Cooldown musi być liczbą");
            return false;
        }
        ArrayList<Material> materials = new ArrayList<>();

        for(int i = 2; i < args.length; i++) {
            String materialName = args[i].toUpperCase();
            Material material = Material.getMaterial(materialName);

            if(material != null) {
                if(material.isBlock()) {
                    materials.add(material);
                } else {
                    player.sendMessage("Materiał " + materialName + " nie jest blokiem");
                    return false;
                }
            } else {
                player.sendMessage("Materiał " + materialName + " nie istnieje");
                return false;
            }
        }

        if(materials.isEmpty()) {
            player.sendMessage("Musisz sprecyzować bloki ktore beda w generatorze");
            return false;
        }
        generatorManager.createGenerator(name, coolDownSeconds, materials);

        player.sendMessage("Utworzyles generator "
                + name
                + " z cooldownem "
                + coolDownSeconds
                + " sekund oraz materialami");

        for (Material material : materials) {
            player.sendMessage(material.name());
        }


        return true;
    }



}
