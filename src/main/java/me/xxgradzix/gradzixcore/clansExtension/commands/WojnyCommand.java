package me.xxgradzix.gradzixcore.clansExtension.commands;

import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.War;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.guild.Guild;
import net.dzikoysk.funnyguilds.user.User;
import net.dzikoysk.funnyguilds.user.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import panda.std.Option;

import java.util.List;

public class WojnyCommand implements CommandExecutor {
    private final FunnyGuilds funnyGuilds;
    private final WarManager warManager;

    public WojnyCommand(FunnyGuilds funnyGuilds, WarManager warManager) {
        this.funnyGuilds = funnyGuilds;
        this.warManager = warManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        UserManager userManager = funnyGuilds.getUserManager();

        Option<User> userOption = userManager.findByPlayer(player);

        if(userOption.isEmpty()) return false;

        User user = userOption.get();

        Option<Guild> guildOption = user.getGuild();

        if(guildOption.isEmpty()) {
            // TODO wiadomsoc
            player.sendMessage("Nie masz zadnej gildi");
            return true;
        }

        Guild guild = guildOption.get();

        List<War> allGuildWars = warManager.getAllGuildWars(guild.getUUID());

        allGuildWars.forEach(war -> {
            player.sendMessage(war.toString());
        });

        return false;
    }
}
