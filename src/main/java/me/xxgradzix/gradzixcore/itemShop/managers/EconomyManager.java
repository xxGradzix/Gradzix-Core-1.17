package me.xxgradzix.gradzixcore.itemShop.managers;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EconomyManager {

    private Economy economy;

    public EconomyManager() {
        setupEconomy();
    }

    private void setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            economy = rsp.getProvider();
        }
    }

    public boolean depositMoney(Player player, double amount) {
        if (economy != null) {
            economy.depositPlayer(player, amount);
            return true;
        }
        return false;
    }

    public boolean withdrawMoney(Player player, double amount) {
        if (economy != null) {
            if(getBalance(player) >= amount) {
                economy.withdrawPlayer(player, amount);
            }

            return true;
        }
        return false;
    }

    public double getBalance(Player player) {
        if (economy != null) {
            return economy.getBalance(player);
        }
        return 0.0;
    }
}