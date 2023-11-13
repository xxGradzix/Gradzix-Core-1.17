package me.xxgradzix.gradzixcore.generators.commands;


import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.generators.data.database.entities.Generator;
import me.xxgradzix.gradzixcore.generators.data.database.entities.GeneratorLocation;
import me.xxgradzix.gradzixcore.generators.data.database.managers.GeneratorLocationManager;
import me.xxgradzix.gradzixcore.generators.data.database.managers.GeneratorManager;
import me.xxgradzix.gradzixcore.generators.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShowGeneratorsCommand implements CommandExecutor {
    private final GeneratorManager generatorManager;
    private final GeneratorLocationManager generatorLocationManager;

    public ShowGeneratorsCommand(GeneratorManager generatorManager, GeneratorLocationManager generatorLocationManager) {
        this.generatorLocationManager = generatorLocationManager;
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

            List<Generator> generatorsList = generatorManager.getAllGenerators();

            for(Generator generator : generatorsList) {

                GuiItem generatorButton = ItemBuilder.from(ItemManager.createGeneratorTypeButton(generator)).asGuiItem();

                generatorButton.setAction(buttonEvent -> {

                    if(buttonEvent.isRightClick()) {
                        generatorManager.deleteGeneratorById(generator.getId());
                        generatorLocationManager.deleteAllGeneratorLocationsByGenerator(generator);
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

            List<GeneratorLocation> generatorsList = generatorLocationManager.getAllGeneratorLocationsByWorldUUID(player.getWorld().getUID());

            for(GeneratorLocation generator : generatorsList) {
                GuiItem generatorButton = ItemBuilder.from(ItemManager.createGeneratorLocationButton(generator)).asGuiItem();
                generatorButton.setAction(buttonEvent -> {
                    if(buttonEvent.isRightClick()) {
                        generatorLocationManager.deleteGeneratorLocationById(generator.getId());
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
