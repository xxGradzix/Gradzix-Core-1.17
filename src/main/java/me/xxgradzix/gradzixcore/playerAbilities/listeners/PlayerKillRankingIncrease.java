package me.xxgradzix.gradzixcore.playerAbilities.listeners;

import org.bukkit.event.Listener;

public class PlayerKillRankingIncrease implements Listener {

//    @EventHandler
//    public void onPlayerKill(CombatPointsChangeEvent event) {
//
//        int killerPoints = event.getAttackerPointsChange();
//        int preyPoints = event.getAttackerPointsChange();
//
//        Player p = Bukkit.getPlayer(event.getAttacker().getUUID());
//
//        double multiplier = DataManager.getRankAbilityModifier(p);
//
//        if(multiplier > 1) {
//            killerPoints *= multiplier;
//        }
//
//        event.setAttackerPointsChange(killerPoints);
//
//        List<ChatOptionsEntity> chatOptionsEntityList = ChatOptions.getChatOptionsEntityManager().getChatOptionsEntitiesWhereShowDeathMessageIs(false);
//
//        List<UUID> blockedMessagePlayerUUIDs = chatOptionsEntityList.stream().map((ChatOptionsEntity::getUuid)).collect(Collectors.toList());
//
//
//        for(Player player : Bukkit.getOnlinePlayers()) {
//            if(!blockedMessagePlayerUUIDs.contains(player.getUniqueId())) {
//                player.sendMessage(ChatColor.DARK_RED + "⚔ " + ChatColor.GRAY + "| " + ChatColor.RED +  event.getAttacker().getName() + ChatColor.GRAY + " (" + ChatColor.GREEN +"+" + ChatColor.GRAY + killerPoints +  ") zabił " + ChatColor.RED + event.getVictim().getName() + ChatColor.GRAY +" (" + ChatColor.RED + "-" + ChatColor.GRAY + preyPoints + ")");
//            }
//        }
//
//    }


}