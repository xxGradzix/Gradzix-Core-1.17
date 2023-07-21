package me.xxgradzix.gradzixcore.chatopcje.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathMessage implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

//        event.getDefaultCancelMessage();
//
//        event.setCancelMessage("Dupa dupa");
//
//        System.out.println("aktywacja");
//        event.getAffected().sendMessage("hej");
//        event.getDoer().get().sendMessage("dup");
//        event.getDeathsChange();
//
//        event.setCancelled(true);

        event.setDeathMessage(null);
//
//        Player player = event.getEntity();
//
//
//        List<String> list = ChatOpcjeConfigFile.getShowDeathMessageStatusUUIDsList(true);
//
//            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
//                if (!list.contains(onlinePlayer.getUniqueId().toString())) {
//
//                    if(player.getKiller() instanceof Player) {
//                        Player killer = player.getKiller();
//
////                        onlinePlayer.sendMessage("Gracz (" + rank.getPoints()+ ") " + player.getDisplayName() + " został zabity przez " + killer.getDisplayName());
//                        onlinePlayer.sendMessage("Gracz " + player.getDisplayName() + " został zabity przez " + killer.getDisplayName());
//                    }
//
//                }
//            }
    }
}
