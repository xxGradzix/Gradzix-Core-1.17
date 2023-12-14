package me.xxgradzix.gradzixcore.clansExtension.commands;

import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.War;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.TheyAlreadyHaveWarException;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.YouAlreadyHaveWarException;
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

public class WarCommand implements CommandExecutor {

    private final FunnyGuilds funnyGuilds;

    private final WarManager warManager;

    public WarCommand(FunnyGuilds funnyGuilds, WarManager warManager) {
        this.funnyGuilds = funnyGuilds;
        this.warManager = warManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        UserManager userManager = funnyGuilds.getUserManager();

        Option<User> userOption = userManager.findByPlayer(player);

        if(userOption.isEmpty()) return false;

        User user = userOption.get();

        Option<Guild> guildOption = user.getGuild();

        if(guildOption.isEmpty()) {
            player.sendMessage("nie masz gildii");
            return false;
        }

        Guild guild = guildOption.get();

        if(args.length != 1) {
            //TODO musisz podac tag gildi
            return false;
        }
        String guildTag = args[0];

        Option<Guild> invadedGuildOption = funnyGuilds.getGuildManager().findByTag(guildTag);

        if(invadedGuildOption.isEmpty()) {
            player.sendMessage("Taka gildia nie istnieje");
            return false;
        }
        Guild invadedGuild = invadedGuildOption.get();

        try {
            warManager.declareWar(guild, invadedGuild);
        } catch (YouAlreadyHaveWarException e) {
            player.sendMessage("Masz juz wojne");
            return false;
        } catch (TheyAlreadyHaveWarException e) {
            player.sendMessage("Oni maja juz wojne"); //TODO
            return false;
        }

        player.sendMessage("Wypowiedziales wojne gildi " + invadedGuild.getUUID());
        // TODO notify enemy


        return false;
    }
}
