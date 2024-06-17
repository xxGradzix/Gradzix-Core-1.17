package me.xxgradzix.gradzixcore.events.managers;

import lombok.Getter;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.events.Messages;
import me.xxgradzix.gradzixcore.events.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BossManager {

    @Getter
    private static PiglinBrute boss;

    private static BossBar bossBar;

    public static void spawnBoss(Location location) {
        removeBoss();

        boss = location.getWorld().spawn(location, PiglinBrute.class);

        boss.setCustomName(ChatColor.RED + "BOSS");
        boss.setCustomNameVisible(true);
        boss.setImmuneToZombification(true);
        setAttributes();
        try {
            createBossBar();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Boss entity is null");
        }

    }

    public static void removeBossBar() {
        if(bossBar == null) return;
        bossBar.removeAll();
        bossBar.setVisible(false);
        bossBar = null;
    }
    public static void updateBossBar() {
        if(bossBar == null) return;
        if(boss == null) return;
        if(boss.isDead()) return;

        double maxHealth = boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        bossBar.setProgress(boss.getHealth() / maxHealth);
    }
    private static void createBossBar() throws NullPointerException {
        removeBossBar();
        if(boss == null) throw new NullPointerException("Boss entity is null");
        bossBar = Bukkit.createBossBar(ChatColor.DARK_PURPLE + "BOSS", BarColor.PURPLE, BarStyle.SEGMENTED_10);

        bossBar.setVisible(true);

        for(Player player : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(player);
        }

        double maxHealth = boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        bossBar.setProgress(boss.getHealth() / maxHealth);
    }

    private static void setAttributes() {
        AttributeInstance healthAttribute = boss.getAttribute(Attribute.GENERIC_MAX_HEALTH);

        if (healthAttribute != null) {
            double customHealth = 5000.0;
            healthAttribute.setBaseValue(customHealth);
        }
        AttributeInstance attackAttribute = boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        if (attackAttribute != null) {
            double customAttack = 80;
            attackAttribute.setBaseValue(customAttack);
        }
        AttributeInstance speedAttribute = boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        if (speedAttribute != null) {
            double customSpeed = 0.42;
            speedAttribute.setBaseValue(customSpeed);
        }
        boss.setHealth(boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    }
    public static void removeBoss() {
        removeBossBar();
        if(boss == null) return;
        boss.remove();
        boss = null;
    }

    public static boolean isEntityBoss(Entity entity) {
        if(boss == null) return false;
        return boss.equals(entity);
    }


    public static void dropSmallReward() {
        if(boss == null) return;
        Location location = boss.getLocation();

        boss.setAI(false);
        Bukkit.getScheduler().runTaskLater(Gradzix_Core.getInstance(), () -> boss.setAI(true), 30L);


        for(int i = 0; i < 10; i++) {
            double angle = i * Math.PI / 5;
            double x = Math.cos(angle) * 3;
            double z = Math.sin(angle) * 3;

            location.add(x, 0, z);

            if(i%2 == 0) {
                location.getWorld().dropItem(location, me.xxgradzix.gradzixcore.playerPerks.items.ItemManager.perkFragment);
            } else {
                location.getWorld().dropItem(location, ItemManager.mainReward);
            }

            location.subtract(x, 0, z);
        }
    }
    public static void giveFullReward(Player player) {
        player.sendMessage(Messages.YOU_KILLED_BOSS);
//        Bukkit.broadcastMessage(Messages.PLAYER_X_TOOK_BOSS_SHARDS);
        ItemStack reward = new ItemStack(ItemManager.mainReward);
        reward.setAmount(16);
        if(player.getInventory().firstEmpty() != -1) {
            player.getInventory().addItem(reward);
        } else {
            player.getWorld().dropItem(player.getLocation(), reward);
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * 20, 1));
    }

    private static void explosion(Location location) {
        location.getWorld().createExplosion(location, 15.0f);
    }

    public static void spawnMinions() {
//        for (int i = 0; i < 5; i++) {
            Location location = boss.getLocation();
            location.add(Math.random() * 10 - 5, 0, Math.random() * 10 - 5);
            Piglin minion = location.getWorld().spawn(location, Piglin.class);
            minion.setBaby();
            minion.setImmuneToZombification(true);
            minion.setCustomName(ChatColor.RED + "MINION");
            minion.setCustomNameVisible(true);

            AttributeInstance healthAttribute = minion.getAttribute(Attribute.GENERIC_MAX_HEALTH);

            if (healthAttribute != null) {
                double customHealth = 400.0;
                healthAttribute.setBaseValue(customHealth);
            }

            AttributeInstance attackAttribute = minion.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);

            if (attackAttribute != null) {
                double customAttribute = 30.0;
                attackAttribute.setBaseValue(customAttribute);
            }

            AttributeInstance speedAttribute = boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
            if (speedAttribute != null) {
                double customSpeed = 1.0;
                speedAttribute.setBaseValue(customSpeed);
            }

            minion.setHealth(boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
//        }
    }
}
