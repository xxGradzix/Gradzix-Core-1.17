package me.xxgradzix.gradzixcore.serverconfig.commands;

import me.xxgradzix.gradzixcore.rewardSystem.RewardSystem;
import me.xxgradzix.gradzixcore.rewardSystem.database.entities.PlayerRewardsEntity;
import me.xxgradzix.gradzixcore.rewardSystem.managers.RewardManager;
import me.xxgradzix.gradzixcore.scratchCard.items.ItemManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getConsoleSender;
import static org.bukkit.Bukkit.getServer;

public class AgePlayItemShopCommand implements CommandExecutor, TabCompleter {

    private final RewardManager rewardManager;

    public AgePlayItemShopCommand() {
        this.rewardManager = RewardSystem.rewardManager;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        if(args.length == 1) {
            completions.add("nadaj");
            return completions;
        }
        if (args.length == 3) {
            for (Service s : Service.values()) {
                completions.add(s.name());
            }
            return completions;
        }
        return null;
    }

    private enum Service {
        VIP,
        SVIP,
        AGE,
        ZESTAW_BOGACZ,
        ZESTAW_JASKINIOWCA,
        ZDRAPKA,
        KLUCZ_MAGICZNA,
        KLUCZ_JASKINIOWCA
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!sender.isOp()) {
            System.out.println("Tylko OP może użyć tej komendy");
            return true;
        }

        if(args.length < 3) {
            sender.sendMessage("Poprawne użycie to");
            sender.sendMessage("/ais nadaj <NICK> [usługa] <ILOSC>");
            return true;
        }

        String targetPlayerName = args[1];
        String serviceName = args[2];


        Player targetPlayer;
        Service service;


        try {
            targetPlayer = Bukkit.getServer().getPlayer(targetPlayerName);
        } catch (Exception e) {
            throw new RuntimeException("Nie znaleziono gracza o podanej nazwie.");
        }

        try {
            service = Service.valueOf(serviceName);
        } catch (Exception e) {
            sender.sendMessage("Podane usługi to: ");
            for (Service s : Service.values()) {
                sender.sendMessage(s.name());
            }
            throw new RuntimeException("Podana usługa nie istnieje.");
        }

        int amount = 1;

        if(args.length == 4) {
            String amountString = args[3];
            try {
                amount = Integer.parseInt(amountString);
            } catch (Exception e) {
                throw new RuntimeException("Podana ilość nie jest liczbą.");
            }
        }

        switch (service) {
            case VIP:
                Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §eRanga VIP");
                Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + targetPlayer.getName() + " parent set vip");
                break;
            case SVIP:
                Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §6Ranga SVIP");
                Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + targetPlayer.getName() + " parent set svip");
                break;
            case AGE:
                Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §9Ranga AGE");
                Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + targetPlayer.getName() + " parent set age");
                break;
            case ZESTAW_BOGACZ:
                Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §4Zestaw Bogacz");
                Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + targetPlayer.getName() + " parent set age");
                rewardManager.addRewardToPlayer(targetPlayer.getUniqueId(), PlayerRewardsEntity.Reward.caveman_key, 10);
                break;
            case ZESTAW_JASKINIOWCA:
                Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §5Zestaw Jaskiniowca");
                Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + targetPlayer.getName() + " parent set age");
                rewardManager.addRewardToPlayer(targetPlayer.getUniqueId(), PlayerRewardsEntity.Reward.caveman_key, 32);
                break;
            case ZDRAPKA:
                Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §e§lZ§c§ld§b§lr§2§la§5§lp§6§lk§9§la");
                Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                rewardManager.addRewardToPlayer(targetPlayer.getUniqueId(), PlayerRewardsEntity.Reward.scratchcard, amount);
                break;
            case KLUCZ_MAGICZNA:
                Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §dMagiczny Klucz");
                Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                rewardManager.addRewardToPlayer(targetPlayer.getUniqueId(), PlayerRewardsEntity.Reward.magic_key, amount);
                break;
            case KLUCZ_JASKINIOWCA:
                Bukkit.broadcastMessage("§7Gracz §2" + targetPlayer.getName() + " §7zakupił §cJaskiniowy Klucz");
                Bukkit.broadcastMessage("§aZakupy zrobisz na stronie §2www.ageplay.pl");
                rewardManager.addRewardToPlayer(targetPlayer.getUniqueId(), PlayerRewardsEntity.Reward.caveman_key, amount);
                break;
        }

        return false;
    }

}
