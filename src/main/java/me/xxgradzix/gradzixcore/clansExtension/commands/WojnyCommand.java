package me.xxgradzix.gradzixcore.clansExtension.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.War;
import me.xxgradzix.gradzixcore.clansExtension.items.ItemManager;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.guild.Guild;
import net.dzikoysk.funnyguilds.guild.GuildManager;
import net.dzikoysk.funnyguilds.user.User;
import net.dzikoysk.funnyguilds.user.UserManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import panda.std.Option;

import java.util.List;
import java.util.UUID;

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

        PaginatedGui gui = Gui.paginated()
                .title(Component.text("Wojny gildi "))
                .rows(2)
                .disableAllInteractions()
//                .pageSize(45) // Set the size you want, or leave it to be automatic.
                .create();

        gui.setItem(2, 1, ItemBuilder.from(Material.PAPER).setName("Previous").asGuiItem(event -> gui.previous()));
        gui.getFiller().fillBetweenPoints(2, 2, 2, 8, new GuiItem(Material.GREEN_STAINED_GLASS_PANE));
        gui.setItem(2, 9, ItemBuilder.from(Material.PAPER).setName("Next").asGuiItem(event -> gui.next()));

        UUID userGuildId = guild.getUUID();

        List<War> allGuildWars = warManager.getAllGuildWars(userGuildId);

        GuildManager guildManager = funnyGuilds.getGuildManager();

        allGuildWars.forEach(war -> {
            UUID enemyGuildID;
            if(userGuildId.equals(war.getInvadedGuildId())) {
                enemyGuildID = war.getInvaderGuildId();
            } else enemyGuildID = war.getInvadedGuildId();

            Option<Guild> enemyGuildOpt = guildManager.findByUuid(enemyGuildID);

            if(enemyGuildOpt.isEmpty()) return;

            gui.addItem(new GuiItem(ItemManager.warResult(guild, enemyGuildOpt.get(), war.getWarState(), 1, 1)));


        });
        gui.open(player);

        return false;
    }
}
