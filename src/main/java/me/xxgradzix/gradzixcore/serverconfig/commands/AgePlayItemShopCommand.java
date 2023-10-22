package me.xxgradzix.gradzixcore.serverconfig.commands;

import me.xxgradzix.gradzixcore.scratchCard.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getConsoleSender;
import static org.bukkit.Bukkit.getServer;

public class AgePlayItemShopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if (sender instanceof Player) {
//            Player player = (Player) sender;
        if(!sender.isOp()) {
            System.out.println("Tylko uzytkowniz z opem moze uzyc");
            return true;
        }
        sender.sendMessage(String.valueOf(args.length));

                if(args.length < 2) {
                    sender.sendMessage("Poprawne uzycie to");
                    sender.sendMessage("/ais nadaj {NICK} klucz/ranga (opcjonalne){ILOSC}");
                    return true;
                }

//                if(args[0] != "nadaj") {
//                    sender.sendMessage(args[1]);
//
//                    sender.sendMessage("BLAD NADAJ");
//                    sender.sendMessage("Poprawne uzycie to");
//                    sender.sendMessage("/ais nadaj {NICK} klucz/ranga (opcjonalne){ILOSC}");
//
//                    return true;
//                }

                String targetPlayerName = args[1];
                Player targetPlayer = null;



                if (targetPlayerName != null) {

                    targetPlayer = Bukkit.getServer().getPlayer(targetPlayerName);

                    if (targetPlayer == null) {
                        throw new RuntimeException("Nie znaleziono gracza o podanej nazwie.");
                    }
                } else throw new RuntimeException("Nie podano nicku");
                String ranga = args[2];
                ranga.toLowerCase();

                if(args.length == 3) {
//                    if(args[2] == "bogacz") {
//                        getServer().dispatchCommand(getConsoleSender(), "givezdrapka " +
//                                targetPlayer.getName() +
//                                " 3");
//                        getServer().dispatchCommand(getConsoleSender(), "excellentcrates key give " +
//                                targetPlayer.getName() +
//                                " jaskiniowca 10");
//                    }
//                    if(args[2] == "jaskiniowca") {
//                        getServer().dispatchCommand(getConsoleSender(), "givezdrapka " +
//                                targetPlayer.getName());
//                        getServer().dispatchCommand(getConsoleSender(), "excellentcrates key give " +
//                                targetPlayer.getName() +
//                                " magiczna 32");
//                    }
//                    getServer().dispatchCommand(getConsoleSender(), "lp user " +
//                            targetPlayer.getName() +
//                            " parent addtemp " + args[2] + " 30d" );
                    switch (args[2]) {
                        case "vip":
                            getServer().dispatchCommand(getConsoleSender(), "lp user " + targetPlayer.getName() + " parent addtemp vip 30d");
                            Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §eRanga VIP");
                            Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                            break;

                        case "svip":
                            getServer().dispatchCommand(getConsoleSender(), "lp user " + targetPlayer.getName() + " parent addtemp svip 30d");
                            Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §6Ranga SVIP");
                            Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                            break;
                        case "age":
                            getServer().dispatchCommand(getConsoleSender(), "lp user " + targetPlayer.getName() + " parent addtemp age 30d");
                            Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §9Ranga AGE");
                            Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                            break;
                        case "bogacz":
                            getServer().dispatchCommand(getConsoleSender(), "lp user " + targetPlayer.getName() + " parent addtemp age 30d");
                            Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §4Zestaw Bogacz");
                            Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
//                            getServer().dispatchCommand(getConsoleSender(), "givezdrapka " +
//                                    targetPlayer.getName() +
//                                    " 3");
                            ItemStack itemToGive = new ItemStack(ItemManager.zdrapka);
                            itemToGive.setAmount(3);
                            targetPlayer.getInventory().addItem(itemToGive);
                            getServer().dispatchCommand(getConsoleSender(), "excellentcrates key give " +
                                    targetPlayer.getName() +
                                    " jaskiniowca 10");
                            break;
                        case "jaskiniowca":
                            getServer().dispatchCommand(getConsoleSender(), "lp user " + targetPlayer.getName() + " parent addtemp svip 30d");
                            Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §5Zestaw Jaskiniowca");
                            Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");

//                            getServer().dispatchCommand(getConsoleSender(), "givezdrapka " +
//                                    targetPlayer.getName());
                            targetPlayer.getInventory().addItem(ItemManager.zdrapka);
                            getServer().dispatchCommand(getConsoleSender(), "excellentcrates key give " +
                                    targetPlayer.getName() +
                                    " magiczna 32");
                            break;
                        default:
                            sender.sendMessage("Mozliwe rangi to: ");
                            sender.sendMessage("vip");
                            sender.sendMessage("svip");
                            sender.sendMessage("age");
                            sender.sendMessage("bogacz");
                            sender.sendMessage("jaskiniowca");
                            break;

                    }

                }
                if(args.length == 4) {
                    int amount = Integer.parseInt(args[3]);

                    switch (args[2]) {
                        case "zdrapka":
//                            getServer().dispatchCommand(getConsoleSender(), "givezdrapka " + targetPlayer.getName() + " " + amount);
                            ItemStack itemToGive = new ItemStack(ItemManager.zdrapka);
                            itemToGive.setAmount(amount);
                            targetPlayer.getInventory().addItem(itemToGive);
                            Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §e§lZ§c§ld§b§lr§2§la§5§lp§6§lk§9§la");
                            Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                            break;

                        case "jaskiniowca":
                            getServer().dispatchCommand(getConsoleSender(), "excellentcrates key give " + targetPlayer.getName() + " jaskiniowca " + amount);
                            Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §cJaskiniowy Klucz");
                            Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                            break;
                        case "kluczmagiczna":
                            getServer().dispatchCommand(getConsoleSender(), "excellentcrates key give " + targetPlayer.getName() + " magiczna " + amount);
                            Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §dMagiczny Klucz");
                            Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                            break;
                        default:
                            sender.sendMessage("Mozliwe klucze to: ");
                            sender.sendMessage("zdrapka");
                            sender.sendMessage("jaskiniowca");
                            sender.sendMessage("kluczmagiczna");

                            break;


                    }







                return true;
            }
//        }
        return false;
    }

}
