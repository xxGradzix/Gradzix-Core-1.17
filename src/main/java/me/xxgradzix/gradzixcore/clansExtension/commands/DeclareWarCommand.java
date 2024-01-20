package me.xxgradzix.gradzixcore.clansExtension.commands;

import me.xxgradzix.gradzixcore.clansExtension.exceptions.TheyAlreadyHaveWarException;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.YouAlreadyHaveMaxAmountOfWarsException;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.YouAlreadyHaveWarException;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
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

public class DeclareWarCommand implements CommandExecutor {

    private final FunnyGuilds funnyGuilds;

    private final WarManager warManager;

    public DeclareWarCommand(FunnyGuilds funnyGuilds, WarManager warManager) {
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
            player.sendMessage(Messages.YOU_DONT_HAVE_CLAN);
            return false;
        }

        Guild guild = guildOption.get();

        if(args.length != 1) {
            player.sendMessage(Messages.YOU_MUST_SPECIFY_CLAN_TAG);
            return false;
        }
        String guildTag = args[0];

        Option<Guild> invadedGuildOption = funnyGuilds.getGuildManager().findByTag(guildTag);

        if(invadedGuildOption.isEmpty()) {
            player.sendMessage(Messages.CLAN_DOES_NOT_EXISTS);
            return false;
        }
        if (guild.equals(invadedGuildOption.get())) {
            player.sendMessage(Messages.YOU_CANT_DECLARE_WAR_TO_YOUR_OWN_GUILD);
            return false;
        }
        Guild invadedGuild = invadedGuildOption.get();

        try {
            warManager.declareWar(guild, invadedGuild);
        } catch (YouAlreadyHaveMaxAmountOfWarsException e) {
            player.sendMessage(Messages.YOU_ALREADY_HAVE_MAX_AMOUNT_OF_WARS);
            return false;
        }
//        catch (TheyAlreadyHaveWarException e) {
//            player.sendMessage(Messages.THIS_CLAN_IS_CURRENTLY_IN_WAR);
//            return false;
//        }

        player.sendMessage(Messages.YOU_DECLARED_WAR_TO_CLAN_XXXX + invadedGuild.getUUID());

        invadedGuild.getOnlineMembers().forEach(onlinePlayer -> onlinePlayer.sendMessage(Messages.CLAN_XXXX_DECLARED_WAR_TO_YOU(guild.getTag())));

        return false;
    }
}
