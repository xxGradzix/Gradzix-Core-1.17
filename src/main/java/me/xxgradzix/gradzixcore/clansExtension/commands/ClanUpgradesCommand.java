package me.xxgradzix.gradzixcore.clansExtension.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerk;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.ClanPerksEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.entities.PerkModifierEntity;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.ClanPerksEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.data.database.managers.PerkModifierEntityManager;
import me.xxgradzix.gradzixcore.clansExtension.items.ItemManager;
import me.xxgradzix.gradzixcore.clansExtension.managers.ClanPerksManager;
import me.xxgradzix.gradzixcore.clansExtension.messages.Messages;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.guild.Guild;
import net.dzikoysk.funnyguilds.user.User;
import net.dzikoysk.funnyguilds.user.UserManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import panda.std.Option;

import java.util.Optional;

public class ClanUpgradesCommand implements CommandExecutor {

    private final FunnyGuilds funnyGuilds;
    private final ClanPerksManager clanPerksManager;
    private final ClanPerksEntityManager clanPerksEntityManager;

    public ClanUpgradesCommand(FunnyGuilds funnyGuilds, ClanPerksManager clanPerksManager, ClanPerksEntityManager clanPerksEntityManager) {
        this.funnyGuilds = funnyGuilds;
        this.clanPerksManager = clanPerksManager;
        this.clanPerksEntityManager = clanPerksEntityManager;
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
            return true;
        }
        Guild playerGuild = guildOption.get();

        Gui gui = Gui.gui()
                .title(Component.text("Ulepszenia klanowe"))
                .rows(3)
                .disableAllInteractions()
                .create();

        ClanPerksEntity clanPerksEntity = clanPerksEntityManager.getClanPerksEntityByID(playerGuild.getUUID());


        GuiItem clanRankPerkButton = new GuiItem(ItemManager.createPerkUpgradeButtonForGuild(clanPerksEntity, ClanPerk.RANK));

        clanRankPerkButton.setAction((a) -> {
            if(!a.isLeftClick()) return;
            gui.update();
            
            PerkModifierEntity perkModifierEntityByID = PerkModifierEntityManager.getPerkModifierEntityByID(ClanPerk.RANK);

            int perkPriceForNextLevel = perkModifierEntityByID
                    .getPerkPricePerLevel(clanPerksEntity.getClanPerkLevel(ClanPerk.RANK) + 1);

            if(!takePriceAndReturn(player, perkPriceForNextLevel)) {
                player.sendMessage(Messages.NOT_ENOUGH_MONEY);
                return;
            }

            clanPerksManager.increaseClanPerkLevel(ClanPerk.RANK, playerGuild);
            player.sendMessage(Messages.UPGRADED_CLAN_PERK);
        });

        gui.setItem(2, 3, clanRankPerkButton);

        GuiItem warAmountPerkButton = new GuiItem(ItemManager.createPerkUpgradeButtonForGuild(clanPerksEntity, ClanPerk.WAR_AMOUNT));

        warAmountPerkButton.setAction((a) -> {
            if(!a.isLeftClick()) return;
            gui.update();

            PerkModifierEntity perkModifierEntityByID = PerkModifierEntityManager.getPerkModifierEntityByID(ClanPerk.WAR_AMOUNT);

            int perkPriceForNextLevel = perkModifierEntityByID
                    .getPerkPricePerLevel(clanPerksEntity.getClanPerkLevel(ClanPerk.WAR_AMOUNT) + 1);

            if(!takePriceAndReturn(player, perkPriceForNextLevel)) {
                player.sendMessage(Messages.NOT_ENOUGH_MONEY);
                return;
            }
        
            clanPerksManager.increaseClanPerkLevel(ClanPerk.WAR_AMOUNT, playerGuild);

            player.sendMessage(Messages.UPGRADED_WAR_AMOUNT_PERK);
        });

        gui.setItem(2, 7, warAmountPerkButton);

        gui.open(player);

        return true;

    }

    private boolean takePriceAndReturn(Player player, int price) {
        ItemStack priceItem = new ItemStack(Material.NETHER_STAR, price);
        if(player.getInventory().containsAtLeast(priceItem, price)) {
            removeItems(player, priceItem, price);
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
