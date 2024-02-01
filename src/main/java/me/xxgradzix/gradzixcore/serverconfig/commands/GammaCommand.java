package me.xxgradzix.gradzixcore.serverconfig.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GammaCommand implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("&cNie mozesz uzyc tej komendy w konsoli!");
      return false;
    } 
    Player player = (Player)sender;
    if (!player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
      player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 10000000, 1, false, false));
      player.sendMessage("&9&lAGE&f&lPLAY &8&7Gamma została włączona.");
      return false;
    } 
    if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
      player.removePotionEffect(PotionEffectType.NIGHT_VISION);
      player.sendMessage("&9&lAGE&f&lPLAY &8&7Gamma została wyłączona.");
      return false;
    } 
    return false;
  }
}