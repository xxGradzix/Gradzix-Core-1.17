package me.xxgradzix.gradzixcore.clansExtension.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ClanUpgradesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }

//    private final FunnyGuilds funnyGuilds;
//    private final ClanPerksManager clanPerksManager;
//    private final ClanPerksEntityManager clanPerksEntityManager;
//
//    public ClanUpgradesCommand(FunnyGuilds funnyGuilds, ClanPerksManager clanPerksManager, ClanPerksEntityManager clanPerksEntityManager) {
//        this.funnyGuilds = funnyGuilds;
//        this.clanPerksManager = clanPerksManager;
//        this.clanPerksEntityManager = clanPerksEntityManager;
//    }
//    @Override
//    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//        if(!(sender instanceof Player)) return false;
//
//        Player player = (Player) sender;
//
//        UserManager userManager = funnyGuilds.getUserManager();
//
//        Option<User> userOption = userManager.findByPlayer(player);
//
//        if(userOption.isEmpty()) return false;
//
//        User user = userOption.get();
//
//        Option<Guild> guildOption = user.getGuild();
//        if(guildOption.isEmpty()) {
//            player.sendMessage(Messages.YOU_DONT_HAVE_CLAN);
//            return true;
//        }
//
//        Guild playerGuild = guildOption.get();
//
//        Gui gui = Gui.gui()
//                .title(Component.text("Ulepszenia klanowe"))
//                .rows(3)
//                .disableAllInteractions()
//                .create();
//
//        setItemPerkButtonInGui(gui, 2, 4, ClanPerk.RANK, player, playerGuild);
//        setItemPerkButtonInGui(gui, 2, 6, ClanPerk.WAR_AMOUNT, player, playerGuild);
//
//        gui.open(player);
//
//        return true;
//    } // TODO ensure that other player cant buy perk for low level - only needs checking
//
//    private void setItemPerkButtonInGui(Gui gui, int row, int column, ClanPerk perk, Player player, Guild guild) {
//        ClanPerksEntity clanPerksEntity = clanPerksEntityManager.getClanPerksEntityByID(guild.getUUID());
//
//        GuiItem clanPerkButton = new GuiItem(ItemManager.createPerkUpgradeButtonForGuild(clanPerksEntity, perk));
//
//        clanPerkButton.setAction((a) -> {
//            if (!a.isLeftClick()) return;
//
//            boolean haveMonetForNextLevel;
//            try {
//                haveMonetForNextLevel = takePriceAndIncreasePerkLevel(player, guild, perk);
//            } catch (IllegalArgumentException e) {
//                player.sendMessage(Messages.MAX_PERK_LEVEL);
//                return;
//            }
//            if (!haveMonetForNextLevel) {
//                player.sendMessage(Messages.NOT_ENOUGH_MONEY);
//            } else {
//                switch (perk) {
//                    case RANK:
//                        player.sendMessage(Messages.UPGRADED_CLAN_PERK);
//                        break;
//                    case WAR_AMOUNT:
//                        player.sendMessage(Messages.UPGRADED_WAR_AMOUNT_PERK);
//                        break;
//                }
//                setItemPerkButtonInGui(gui, row, column, perk, player, guild);
//            }
//
//        });
//
//        gui.updateItem(row, column, clanPerkButton);
//        gui.update();
//    }
//    private boolean takePriceAndIncreasePerkLevel(Player player, Guild guild, ClanPerk perk) throws IllegalArgumentException {
//
//        PerkModifierEntity perkModifierEntity = PerkModifierEntityManager.getPerkModifierEntityByID(perk);
//
//        if(perkModifierEntity == null) throw new IllegalArgumentException("Unknown perk: " + perk);
//
//        int price = perkModifierEntity.getPerkPricePerLevel(clanPerksManager.getClanPerkLevel(perk, guild) + 1);
//
//        ItemStack priceItem = new ItemStack(Material.NETHER_STAR, price);
//
//        if(player.getInventory().containsAtLeast(priceItem, price)) {
//            removeItems(player, priceItem, price);
//            clanPerksManager.increaseClanPerkLevel(perk, guild);
//            return true;
//        }
//        return false;
//    }
//    public void removeItems(Player player, ItemStack itemStack, int amount) {
//        PlayerInventory inventory = player.getInventory();
//        int remainingAmount = amount;
//
//        for (int i = 0; i < inventory.getSize(); i++) {
//            ItemStack item = inventory.getItem(i);
//
//            if (item != null && item.isSimilar(itemStack)) {
//                int itemAmount = item.getAmount();
//
//                if (itemAmount <= remainingAmount) {
//                    remainingAmount -= itemAmount;
//                    inventory.setItem(i, null);
//                } else {
//                    item.setAmount(itemAmount - remainingAmount);
//                    break;
//                }
//            }
//        }
//    }
}
