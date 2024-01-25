package me.xxgradzix.gradzixcore.clansExtension.commands;

import me.xxgradzix.gradzixcore.clansExtension.ClansExtension;
import me.xxgradzix.gradzixcore.clansExtension.exceptions.*;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.guild.Guild;
import net.dzikoysk.funnyguilds.user.User;
import net.dzikoysk.funnyguilds.user.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import panda.std.Option;

import java.util.ArrayList;
import java.util.List;

public class DeclareWarCommand implements CommandExecutor, TabCompleter {

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

        if(ClansExtension.ARE_WARS_ACTIVE) {
            player.sendMessage(Messages.YOU_CANNOT_DECLARE_WAR_WHILE_WARS_ARE_ACTIVE);
            return false;
        }

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

        Option<Guild> invadedGuildOption = funnyGuilds.getGuildManager().findByTag(guildTag, true);

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
        } catch (CantDeclareWarDuringWarTimeException e) {
            player.sendMessage(Messages.YOU_CANNOT_DECLARE_WAR_WHILE_WARS_ARE_ACTIVE);
            return false;
        } catch (YouAlreadyHaveWarWithThisGuildException e) {
            player.sendMessage(Messages.You_ARE_ALREADY_IN_WAR_WITH_THIS_CLAN);
            return false;
        }


        player.sendMessage(Messages.YOU_DECLARED_WAR_TO_CLAN_XXXX + invadedGuild.getTag());

        invadedGuild.getOnlineMembers().forEach(
                onlinePlayer -> onlinePlayer.sendMessage(Messages.CLAN_XXXX_DECLARED_WAR_TO_YOU(guild.getTag()))
        );

        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ArrayList<String> completions = new ArrayList<>();

        if(command.getName().equalsIgnoreCase("wypowiedzwojne")) {
            if(strings.length == 1) {
                funnyGuilds.getGuildManager().getGuilds().forEach(guild -> completions.add(guild.getTag()));
                return completions;
            }
        }
        return null;
    }
}
