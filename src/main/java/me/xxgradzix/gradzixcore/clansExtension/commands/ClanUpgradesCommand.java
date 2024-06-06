package me.xxgradzix.gradzixcore.clansExtension.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerk;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerksEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.PerkModifierEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.ClanPerksEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.PerkModifierEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.items.ItemManager;
import me.xxgradzix.gradzixcore.clansExtension.managers.ClanPerksManager;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ClanUpgradesCommand implements CommandExecutor {

    private final ClanPerksManager clanPerksManager;
    private final ClanPerksEntityManager clanPerksEntityManager;

    public ClanUpgradesCommand(ClanPerksManager clanPerksManager, ClanPerksEntityManager clanPerksEntityManager) {
        this.clanPerksManager = clanPerksManager;
        this.clanPerksEntityManager = clanPerksEntityManager;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;


        UserEntity user = UserManager.getOrCreateUserEntity(player);


        Optional<ClanEntity> guildOption = ClanManager.getClanEntityOfMember(user);
        if(!guildOption.isPresent()) {
            player.sendMessage(Messages.YOU_DONT_HAVE_CLAN);
            return true;
        }

        ClanEntity clanEntity = guildOption.get();

        Gui gui = Gui.gui()
                .title(Component.text("Ulepszenia klanowe"))
                .rows(3)
                .disableAllInteractions()
                .create();

        setItemPerkButtonInGui(gui, 2, 4, ClanPerk.RANK, player, clanEntity);
        setItemPerkButtonInGui(gui, 2, 6, ClanPerk.WAR_AMOUNT, player, clanEntity);

        gui.open(player);

        return true;
    } // TODO ensure that other player cant buy perk for low level - only needs checking

    private void setItemPerkButtonInGui(Gui gui, int row, int column, ClanPerk perk, Player player, ClanEntity clanEntity) {
        ClanPerksEntity clanPerksEntity = clanPerksEntityManager.getClanPerksEntityByID(clanEntity.getUuid());

        GuiItem clanPerkButton = new GuiItem(ItemManager.createPerkUpgradeButtonForGuild(clanPerksEntity, perk));

        clanPerkButton.setAction((a) -> {
            if (!a.isLeftClick()) return;

            boolean haveMonetForNextLevel;
            try {
                haveMonetForNextLevel = takePriceAndIncreasePerkLevel(player, clanEntity, perk);
            } catch (IllegalArgumentException e) {
                player.sendMessage(Messages.MAX_PERK_LEVEL);
                return;
            }
            if (!haveMonetForNextLevel) {
                player.sendMessage(Messages.NOT_ENOUGH_MONEY);
            } else {
                switch (perk) {
                    case RANK:
                        player.sendMessage(Messages.UPGRADED_CLAN_PERK);
                        break;
                    case WAR_AMOUNT:
                        player.sendMessage(Messages.UPGRADED_WAR_AMOUNT_PERK);
                        break;
                }
                setItemPerkButtonInGui(gui, row, column, perk, player, clanEntity);
            }

        });

        gui.setItem(row, column, clanPerkButton);
        gui.update();
    }
    private boolean takePriceAndIncreasePerkLevel(Player player, ClanEntity clanEntity, ClanPerk perk) throws IllegalArgumentException {

        PerkModifierEntity perkModifierEntity = PerkModifierEntityManager.getPerkModifierEntityByID(perk);

        if(perkModifierEntity == null) throw new IllegalArgumentException("Unknown perk: " + perk);

        int price = perkModifierEntity.getPerkPricePerLevel(clanPerksManager.getClanPerkLevel(perk, clanEntity) + 1);

        ItemStack priceItem = new ItemStack(Material.NETHER_STAR, price);

        if(player.getInventory().containsAtLeast(priceItem, price)) {
            removeItems(player, priceItem, price);
            clanPerksManager.increaseClanPerkLevel(perk, clanEntity);
            return true;
        }
        return false;
    }
    public void removeItems(Player player, ItemStack itemStack, int amount) {
        PlayerInventory inventory = player.getInventory();
        int remainingAmount = amount;

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);

            if (item != null && item.isSimilar(itemStack)) {
                int itemAmount = item.getAmount();

                if (itemAmount <= remainingAmount) {
                    remainingAmount -= itemAmount;
                    inventory.setItem(i, null);
                } else {
                    item.setAmount(itemAmount - remainingAmount);
                    break;
                }
            }
        }
    }
}
