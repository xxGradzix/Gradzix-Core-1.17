package me.xxgradzix.gradzixcore.clansExtension.listeners.addScore;

import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.War;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.guild.Guild;
import net.dzikoysk.funnyguilds.user.User;
import net.dzikoysk.funnyguilds.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import panda.std.Option;

import java.util.Optional;

public class AddWarScoreAfterKill implements Listener {

    private final FunnyGuilds funnyGuilds;
    private final WarManager warManager;

    public AddWarScoreAfterKill(FunnyGuilds funnyGuilds, WarManager warManager) {
        this.funnyGuilds = funnyGuilds;
        this.warManager = warManager;
    }

    @EventHandler
    public void onPlayerKill(EntityDeathEvent event) {

        if(!(event.getEntity() instanceof Player)) return;
        Bukkit.broadcastMessage("Test 1");

        Player killer = event.getEntity().getKiller();

        Player victim = ((Player) event.getEntity());

        UserManager userManager = funnyGuilds.getUserManager();

        Option<User> optionalKiller = userManager.findByPlayer(killer);
        Option<User> optionalVictim = userManager.findByPlayer(victim);

        if(!optionalKiller.isPresent() || !optionalVictim.isPresent()) return;
        Bukkit.broadcastMessage("Test 2");


        User killerUser = optionalKiller.get();
        User victimUser = optionalVictim.get();

        Option<Guild> killerGuildOption = killerUser.getGuild();
        Option<Guild> victimGuildOption = victimUser.getGuild();

        if(!killerGuildOption.isPresent() || !victimGuildOption.isPresent()) return;
        Bukkit.broadcastMessage("Test 3");


        Guild killerGuild = killerGuildOption.get();
        Guild victinGuild = killerGuildOption.get();

        Optional<War> optionalWar = warManager.getActiveWarOfGuilds(killerGuild.getUUID(), victinGuild.getUUID());

        if(!optionalWar.isPresent()) return;
        Bukkit.broadcastMessage("Test 4");

        War war = optionalWar.get();

        warManager.addPointForGuild(war, killerGuild.getUUID());

        killer.sendMessage("Zdobyłeś punkt dla swojej gildi w wojnie");

        victim.sendMessage("Zabil Cie czlonek gildi z ktora masz wojne");

    }


}
