package me.xxgradzix.gradzixcore.clansExtension.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.WarRecordEntity;
import me.xxgradzix.gradzixcore.clansExtension.items.ItemManager;
import me.xxgradzix.gradzixcore.clansExtension.managers.WarManager;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class WarsCommand implements CommandExecutor {

    private final WarManager warManager;

    public WarsCommand(WarManager warManager) {
        this.warManager = warManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        UserEntity user = UserManager.getOrCreateUserEntity(player);

        Optional<ClanEntity> clanOptional = ClanManager.getClanEntityOfMember(user);
        if(!clanOptional.isPresent()) {
            player.sendMessage(Messages.YOU_DONT_HAVE_CLAN);
            return true;
        }
        ClanEntity playerGuild = clanOptional.get();

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

    private void openActiveWarsGui(Player player, ClanEntity clanEntity) {

        Gui activeWars = Gui.gui()
                .title(Component.text("Aktywne wojny klanów"))
                .rows(3)
                .disableAllInteractions()
                .create();

        Set<WarEntity> allActiveWarsByGuildId = warManager.getNonEndedGuildWars(clanEntity.getUuid());

        allActiveWarsByGuildId.forEach(warEntity -> {
            UUID userGuildId = clanEntity.getUuid();
            UUID enemyGuildId = warEntity.getInvaderGuildId().equals(userGuildId) ? warEntity.getInvadedGuildId() : warEntity.getInvaderGuildId();

            int userGuildPoints = warEntity.getInvaderGuildId().equals(userGuildId) ? warEntity.getInvaderScore() : warEntity.getInvadedScore();
            int enemyGuildPoints = warEntity.getInvaderGuildId().equals(enemyGuildId) ? warEntity.getInvaderScore() : warEntity.getInvadedScore();

            Optional<ClanEntity> enemyGuildOption = ClanManager.getClanEntityByUUID(enemyGuildId);
            if(!enemyGuildOption.isPresent()) return;
            ClanEntity enemyClan = enemyGuildOption.get();

            ItemStack warItem = ItemManager.currentWarItem(warEntity.getId(), clanEntity.getTag(), enemyClan.getTag(), userGuildPoints, enemyGuildPoints);
            activeWars.addItem(new GuiItem(warItem));
        });

        activeWars.open(player);
    }

    private void openEndedWarsGui(Player player, ClanEntity clan) {
        PaginatedGui endedWars = Gui.paginated()
                .title(Component.text("Zakończone wojny klanów"))
                .rows(2)
                .disableAllInteractions()
                .create();

        // TODO blad nie dziala odbieranie nagrod, mozna odebrac z remisu i przegranej
        endedWars.setItem(2, 1, ItemBuilder.from(Material.ARROW).setName("Poprzednia strona").asGuiItem(event -> endedWars.previous()));
        endedWars.getFiller().fillBetweenPoints(2, 2, 2, 8, new GuiItem(Material.GREEN_STAINED_GLASS_PANE));
        endedWars.setItem(2, 9, ItemBuilder.from(Material.ARROW).setName("Nastepna strona").asGuiItem(event -> endedWars.next()));

        List<WarRecordEntity> allEndedWarsByGuildId = warManager.getAllEndedWarsByGuildId(clan.getUuid());

        allEndedWarsByGuildId.forEach(warRecord -> {
            ItemStack warResultItem = ItemManager.endedWarResult(warRecord.getId(), warRecord.getOwnerTag(), warRecord.getEnemyTag(), warRecord.getOwnerScore(), warRecord.getEnemyScore(), warRecord.isRewardCollected());
            GuiItem warResultGuiItem = new GuiItem(warResultItem);

            warResultGuiItem.setAction((a) -> {
                if(clan.getLeader().getUuid().equals(player.getUniqueId())) {
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
