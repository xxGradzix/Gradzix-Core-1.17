package me.xxgradzix.gradzixcore.generators.commands;


import com.sk89q.worldedit.WorldEdit;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorEntity;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorLocationEntity;
import me.xxgradzix.gradzixcore.generators.items.ItemManager;
import me.xxgradzix.gradzixcore.generators.managers.GeneratorManager;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShowGeneratorsCommand implements CommandExecutor {

    private final GeneratorManager generatorManager;

    public ShowGeneratorsCommand(GeneratorManager generatorManager) {
        this.generatorManager = generatorManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        Gui initialChoiceGui = Gui.gui()
                .title(Component.text("Generatory"))
                .rows(1)
                .disableAllInteractions()
                .create();

        GuiItem generatorTypesButton = ItemBuilder.from(ItemManager.generatorTypeButton).asGuiItem();
        GuiItem generatorLocationsButton = ItemBuilder.from(ItemManager.generatorLocationsButton).asGuiItem();

        generatorTypesButton.setAction(event -> {

            Gui generatorTypesGui = Gui.gui()
                    .title(Component.text("Typy generatorow"))
                    .rows(6)
                    .disableAllInteractions()
                    .create();

            List<GeneratorEntity> generatorsList = generatorManager.getAllGenerators();

            for(GeneratorEntity generator : generatorsList) {

                GuiItem generatorButton = ItemBuilder.from(ItemManager.createGeneratorTypeButton(generator)).asGuiItem();

                generatorButton.setAction(buttonEvent -> {

                    if(buttonEvent.isRightClick()) {
                        generatorManager.deleteGeneratorByName(generator.getName());
                        generatorTypesGui.removeItem(generatorButton);
                    }

                    generatorTypesGui.update();
                });

                generatorTypesGui.addItem(generatorButton);
            }

            generatorTypesGui.open(player);
        });

        generatorLocationsButton.setAction(event -> {

            Gui generatorLocationsGui = Gui.gui()
                    .title(Component.text("Lokacje generatorow"))
                    .rows(6)
                    .disableAllInteractions()
                    .create();

            List<GeneratorLocationEntity> generatorsList = generatorManager.getAllGeneratorLocationsByWorldUUID(player.getWorld().getUID());

            for(GeneratorLocationEntity generator : generatorsList) {
                GuiItem generatorButton = ItemBuilder.from(ItemManager.createGeneratorLocationButton(generator)).asGuiItem();

                generatorButton.setAction(buttonEvent -> {
                    if(buttonEvent.isRightClick()) {
                        generatorManager.deleteGeneratorLocationById(generator.getId());

                        generatorLocationsGui.removeItem(generatorButton);
                    }
                    generatorLocationsGui.update();
                });
                generatorLocationsGui.addItem(generatorButton);
            }
            generatorLocationsGui.open(player);
        });

        initialChoiceGui.setItem(2, generatorTypesButton);
        initialChoiceGui.setItem(6, generatorLocationsButton);
        initialChoiceGui.open(player);

        return true;
    }



}
