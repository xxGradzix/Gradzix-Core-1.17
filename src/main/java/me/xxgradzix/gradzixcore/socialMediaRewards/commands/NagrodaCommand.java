package me.xxgradzix.gradzixcore.socialMediaRewards.commands;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.socialMediaRewards.SocialMediaRewards;
import me.xxgradzix.gradzixcore.socialMediaRewards.data.database.DataManager;
import me.xxgradzix.gradzixcore.socialMediaRewards.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class NagrodaCommand implements CommandExecutor {

    private final DataManager dataManager;
    private final Gradzix_Core plugin;

    public NagrodaCommand(DataManager dataManager, Gradzix_Core plugin) {
        this.dataManager = dataManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        Gui gui = Gui.gui()
                .title(Component.text(ChatColor.DARK_GRAY + "Nagrody!"))
                .rows(3)
                .disableAllInteractions()
                .create();
        HashMap<DataManager.Reward, GuiItem> buttons = new HashMap<>();
        for(DataManager.Reward reward : DataManager.Reward.values()) {
            GuiItem button = ItemBuilder.from(ItemManager.createRewardButton(reward, dataManager.isRewardCollected(player, reward))).asGuiItem();
            buttons.put(reward, button);
        }

        buttons.get(DataManager.Reward.TIKTOK).setAction((action) -> {
            player.sendMessage(ChatColor.GRAY + "Wejdz w " + ChatColor.BLUE + SocialMediaRewards.TIKTOK_LINK);
            runTask(player, DataManager.Reward.TIKTOK);
            gui.close(player);
        });

        buttons.get(DataManager.Reward.YOUTUBE).setAction((action) -> {
            player.sendMessage(ChatColor.GRAY + "Wejdz w " + ChatColor.BLUE + SocialMediaRewards.YOUTUBE_LINK);
            runTask(player, DataManager.Reward.YOUTUBE);
            gui.close(player);
        });
        buttons.get(DataManager.Reward.FACEBOOK).setAction((action) -> {
            player.sendMessage(ChatColor.GRAY + "Wejdz w " + ChatColor.BLUE + SocialMediaRewards.FACEBOOK_LINK);
            runTask(player, DataManager.Reward.FACEBOOK);
//            player.spigot().sendMessage(createClickableMessage(DataManager.Reward.FACEBOOK));
            gui.close(player);
        });
        buttons.get(DataManager.Reward.DISCORD).setAction((action) -> {
            player.sendMessage(ChatColor.GRAY + "Wejdz w " + ChatColor.BLUE + SocialMediaRewards.DISCORD_LINK);
//            runTask(player, DataManager.Reward.DISCORD);
            gui.close(player);
        });
        gui.setItem(2, 2, buttons.get(DataManager.Reward.TIKTOK));
        gui.setItem(2, 4, buttons.get(DataManager.Reward.YOUTUBE));
        gui.setItem(2, 6, buttons.get(DataManager.Reward.DISCORD));
        gui.setItem(2, 8, buttons.get(DataManager.Reward.FACEBOOK));

        gui.open(player);

        return false;
    }

    private void runTask(Player player, DataManager.Reward reward) {

        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            if(dataManager.isRewardCollected(player, reward)) {
                player.sendMessage("Odebrałeś już tą nagrodę");
                return;
            }
            Bukkit.broadcastMessage(ChatColor.GRAY + "Gracz " + player.getName() + " odebrał nagrodę " + ChatColor.GREEN + reward.name());
            Bukkit.broadcastMessage(ChatColor.GRAY + "Nagrody znajdziesz pod komendą /nagroda");
            // TODO nadaj nagrode
            dataManager.setRewardCollectStatus(player, true, reward);

        }, 20 * 20);
    }
//    private TextComponent createClickableMessage(DataManager.Reward reward) {
//        String link = "";
//        switch (reward) {
//            case YOUTUBE: link = SocialMediaRewards.YOUTUBE_LINK; break;
//            case DISCORD: link = SocialMediaRewards.DISCORD_LINK; break;
//            case FACEBOOK: link = SocialMediaRewards.FACEBOOK_LINK; break;
//            case TIKTOK: link = SocialMediaRewards.TIKTOK_LINK; break;
//        }
//
//        TextComponent message = new TextComponent(ChatColor.GRAY + "Wejdz na " + ChatColor.AQUA + link);
//
//        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/nagroda " + link));
//        message.setClickEvent(ClickEvent.Action.OPEN_URL, );
//
//        return message;
//    }
}
