package me.xxgradzix.gradzixcore.serverconfig.commands;

import com.google.common.collect.Multimap;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SetArmorAttributeOnItem implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Komenda tylko dla graczy");
            return true;
        }

        Player player = (Player) sender;

        ItemStack item = player.getInventory().getItemInMainHand();

        if(item == null || item.getType().isAir()) {
            player.sendMessage("Musisz trzymać przedmiot w ręce");
            return true;
        }

        ItemMeta itemMeta = item.getItemMeta();
        if(itemMeta == null) return true;

        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "genericarmor", 3.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "armortoughtness", 3.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
        itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "knockbackresistance", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
        player.sendMessage("Dodano atrybuty do przedmiotu");
        item.setItemMeta(itemMeta);


        return false;
    }
}
