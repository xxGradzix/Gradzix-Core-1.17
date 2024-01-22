package me.xxgradzix.gradzixcore.clansExtension.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarRecordEntity;
import me.xxgradzix.gradzixcore.clansExtension.items.ItemManager;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.guild.Guild;
import net.dzikoysk.funnyguilds.guild.GuildManager;
import net.dzikoysk.funnyguilds.user.User;
import net.dzikoysk.funnyguilds.user.UserManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import panda.std.Option;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class WarsCommand implements CommandExecutor {
    private final FunnyGuilds funnyGuilds;
    private final WarManager warManager;

    public WarsCommand(FunnyGuilds funnyGuilds, WarManager warManager) {
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
            player.sendMessage(Messages.YOU_DONT_HAVE_CLAN);
            return true;
        }
        Guild playerGuild = guildOption.get();

        Gui chooseWarsStatus = Gui.gui()
                .title(Component.text("Wybierz status wojny"))
                .rows(1)
                .disableAllInteractions()
                .create();

        GuiItem endedWarsButton = new GuiItem(Material.END_CRYSTAL);
        GuiItem activeWarsButton = new GuiItem(Material.END_PORTAL_FRAME);

        endedWarsButton.setAction(event -> {
            openEndedWarsGui(player, playerGuild);
        });

        activeWarsButton.setAction(event -> {
            openActiveWarsGui(player, playerGuild);
        });
        chooseWarsStatus.setItem(2, activeWarsButton);
        chooseWarsStatus.setItem(6, endedWarsButton);

        chooseWarsStatus.open(player);

        return false;
    }

    private void openActiveWarsGui(Player player, Guild userGuild) {

        Gui activeWars = Gui.gui()
                .title(Component.text("Aktywne wojny gildi"))
                .rows(3)
                .disableAllInteractions()
                .create();

        GuildManager guildManager = funnyGuilds.getGuildManager();

        Set<WarEntity> allActiveWarsByGuildId = warManager.getNonEndedGuildWars(userGuild.getUUID());
        Bukkit.broadcastMessage("allActiveWarsByGuildId: " + allActiveWarsByGuildId.size());

        allActiveWarsByGuildId.forEach(warEntity -> {
            UUID userGuildId = userGuild.getUUID();
            UUID enemyGuildId = warEntity.getInvaderGuildId().equals(userGuildId) ? warEntity.getInvadedGuildId() : warEntity.getInvaderGuildId();

            int userGuildPoints = warEntity.getInvaderGuildId().equals(userGuildId) ? warEntity.getInvaderScore() : warEntity.getInvadedScore();
            int enemyGuildPoints = warEntity.getInvaderGuildId().equals(enemyGuildId) ? warEntity.getInvaderScore() : warEntity.getInvadedScore();

            Option<Guild> enemyGuildOption = guildManager.findByUuid(enemyGuildId);
            if(enemyGuildOption.isEmpty()) return;
            Guild enemyGuild = enemyGuildOption.get();

            ItemStack warItem = ItemManager.endedWarResult(warEntity.getId(), userGuild.getTag(), enemyGuild.getTag(), userGuildPoints, enemyGuildPoints);
            activeWars.addItem(new GuiItem(warItem));
        });

        activeWars.open(player);
    }

    private void openEndedWarsGui(Player player, Guild guild) {
        PaginatedGui endedWars = Gui.paginated()
                .title(Component.text("Zakończone wojny gildi"))
                .rows(2)
                .disableAllInteractions()
                .create();

        // TODO blad nie dziala odbieranie nagrod, mozna odebrac z remisu i przegranej
        endedWars.setItem(2, 1, ItemBuilder.from(Material.ARROW).setName("Poprzednia strona").asGuiItem(event -> endedWars.previous()));
        endedWars.getFiller().fillBetweenPoints(2, 2, 2, 8, new GuiItem(Material.GREEN_STAINED_GLASS_PANE));
        endedWars.setItem(2, 9, ItemBuilder.from(Material.ARROW).setName("Nastepna strona").asGuiItem(event -> endedWars.next()));

        List<WarRecordEntity> allEndedWarsByGuildId = warManager.getAllEndedWarsByGuildId(guild.getUUID());

        allEndedWarsByGuildId.forEach(warRecord -> {
            ItemStack warResultItem = ItemManager.endedWarResult(warRecord.getId(), warRecord.getOwnerTag(), warRecord.getEnemyTag(), warRecord.getOwnerScore(), warRecord.getEnemyScore());
            GuiItem warResultGuiItem = new GuiItem(warResultItem);

            warResultGuiItem.setAction((a) -> {
                if(guild.getOwner().getUUID().equals(player.getUniqueId())) {
                    if(warManager.canCollectReward(warRecord)) {
                        warManager.collectReward(player, warRecord);
                        player.sendMessage(Messages.YOU_COLLECTED_REWARD);
                    } else {
                        player.sendMessage(Messages.REWARD_IS_ALREADY_COLLECTED);
                    }
                } else {
                    player.sendMessage(Messages.THIS_REWARD_CAN_BE_COLLECTED_ONLY_BY_GUILD_OWNER);
                }
            });

            endedWars.addItem(warResultGuiItem);

        });

        endedWars.open(player);
    }
    
}
