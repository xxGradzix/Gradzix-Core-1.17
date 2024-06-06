package me.xxgradzix.gradzixcore.globalStatic;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EconomyManager {

    private static Economy economy;

    public EconomyManager() {
        setupEconomy();
    }

    public static void setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            economy = rsp.getProvider();
        }
    }

    public static boolean depositMoney(Player player, double amount) {
        if (economy != null) {
            economy.depositPlayer(player, amount);
            return true;
        }
        return false;
    }

    public static boolean withdrawMoney(Player player, double amount) {
        if (economy != null) {
            economy.withdrawPlayer(player, amount);
            return true;
        }
        return false;
    }

    public static double getBalance(Player player) {
        if (economy != null) {
            return economy.getBalance(player);
        }
        return 0.0;
    }
}