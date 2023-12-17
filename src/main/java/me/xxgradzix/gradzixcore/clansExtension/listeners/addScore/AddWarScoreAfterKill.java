package me.xxgradzix.gradzixcore.clansExtension.listeners.addScore;

import me.xxgradzix.gradzixcore.clansExtension.ClansExtension;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarEntity;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
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

        if(!ClansExtension.ARE_WARS_ACTIVE) return;

        if(!(event.getEntity() instanceof Player)) return;

        Player killer = event.getEntity().getKiller();

        Player victim = ((Player) event.getEntity());

        UserManager userManager = funnyGuilds.getUserManager();

        Option<User> optionalKiller = userManager.findByPlayer(killer);
        Option<User> optionalVictim = userManager.findByPlayer(victim);

        if(!optionalKiller.isPresent() || !optionalVictim.isPresent()) return;

        User killerUser = optionalKiller.get();
        User victimUser = optionalVictim.get();

        Option<Guild> killerGuildOption = killerUser.getGuild();
        Option<Guild> victimGuildOption = victimUser.getGuild();

        if(!killerGuildOption.isPresent() || !victimGuildOption.isPresent()) return;

        Guild killerGuild = killerGuildOption.get();
        Guild victinGuild = killerGuildOption.get();

        Optional<WarEntity> optionalWar = warManager.getActiveWarOfGuilds(killerGuild.getUUID(), victinGuild.getUUID());

        if(!optionalWar.isPresent()) return;

        WarEntity warEntity = optionalWar.get();

        warManager.addPointForGuild(warEntity, killerGuild.getUUID());

        killer.sendMessage(Messages.YOU_KILLED_MEMBER_OF_ENEMY_GUILD);

        victim.sendMessage(Messages.YOU_WERE_KILLED_BY_MEMBER_OF_ENEMY_GUILD);

    }


}
