package me.xxgradzix.gradzixcore.panel.listeners;

import me.xxgradzix.gradzixcore.panel.files.PanelAdminConfigFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class KityBlock implements Listener {

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().toLowerCase();

        Player p = event.getPlayer();

        if(PanelAdminConfigFile.getKityStatus()) return;

        if(!PanelAdminConfigFile.getKityStatus()) {


            if (message.startsWith("/kit")) {

                    event.setCancelled(true);

                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");


            }
            if (message.startsWith("/essentials:kits")) {

                event.setCancelled(true);

                p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");


            }
            if (message.startsWith("/essentials:ekits")) {

                event.setCancelled(true);

                p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");


            }
            if (message.startsWith("/essentials:kit")) {

                event.setCancelled(true);

                p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");


            }
            if (message.startsWith("/essentials:ekit")) {

                event.setCancelled(true);

                p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");


            }
//            if (message.startsWith("/Kit")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//            }
//            if (message.startsWith("/KIt")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//            }
//            if (message.startsWith("/kIt")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//            }
//            if (message.startsWith("/kIT")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//            }
//            if (message.startsWith("/kiT")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//            }
//            if (message.startsWith("/KIT")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//            }
            /////////////////////////////
            if (message.startsWith("/ekit")) {

                if(!message.equals("/ekit gracz")) {
                    event.setCancelled(true);

                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
                }

            }
//            if (message.startsWith("/Ekit")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//
//            }
//            if (message.startsWith("/EKit")) {
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//
//
//            }
//            if (message.startsWith("/EKIt")) {
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//
//
//            }
//            if (message.startsWith("/eKit")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//
//            }
//            if (message.startsWith("/eKIt")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//
//            }
//            if (message.startsWith("/eKIT")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//
//            }
//            if (message.startsWith("/ekIt")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//            }
//            if (message.startsWith("/ekIT")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//
//            }
//            if (message.startsWith("/ekiT")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//
//
//            }
//            if (message.startsWith("/eKiT")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//
//            }
//            if (message.startsWith("/EkIt")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//
//            }
//
//            if (message.startsWith("/EKIT")) {
//
//                if(!message.equals("/ekit gracz")) {
//                    event.setCancelled(true);
//
//                    p.sendMessage(ChatColor.RED + "Kity są aktualnie wyłączone");
//                }
//            }
            ////////////////////////////////////


        }

    }

}
