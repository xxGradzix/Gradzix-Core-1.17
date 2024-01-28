package me.xxgradzix.gradzixcore.shulker;

import me.xxgradzix.gradzixcore.Gradzix_Core;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.block.BlockState;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ShulkerRework implements Listener {

    private final HashMap<UUID, ShulkerUser> shulkers = new HashMap<>();

    private NamespacedKey key;
    private final Gradzix_Core plugin;

    public ShulkerRework(Gradzix_Core plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {
//        plugin.getServer().getPluginManager().registerEvents(new OpenShulkerListener(plugin), plugin);
    }

    public void onDisable() {
        for (UUID uuid : this.shulkers.keySet()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null)
                player.closeInventory();
        }
    }
    @EventHandler(ignoreCancelled = true)
    public void handle(PlayerInteractEntityEvent event) {
        if (!this.shulkers.containsKey(event.getPlayer().getUniqueId()))
            return;
        event.setCancelled(true);
    }

    @EventHandler
    public void handle(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !event.getAction().equals(Action.RIGHT_CLICK_AIR))
            return;
        ItemStack item = event.getItem();
        if (item == null)
            return;
//        if (!Tag.SHULKER_BOXES.isTagged((Keyed)item.getType()))
//            return;
        event.setCancelled(true);
        if (event.getHand() == null) {
//            Platform.getServer().sendError(event.getPlayer().getUniqueId(), MessageP.CHAT, "&cBlad...");
            return;
        }
        if (this.shulkers.containsKey(event.getPlayer().getUniqueId())) {
//            Platform.getServer().sendError(event.getPlayer().getUniqueId(), MessagePosition.CHAT, "&cJestew trakcie otwierania shulkera!");
            return;
        }
        this.shulkers.put(event.getPlayer().getUniqueId(), new ShulkerUser(item, event.getHand().equals(EquipmentSlot.OFF_HAND) ? -1 : event.getPlayer().getInventory().getHeldItemSlot()));
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.getPersistentDataContainer().set(this.key, PersistentDataType.BYTE, Byte.valueOf((byte)1));
        item.setItemMeta(itemMeta);
        BlockStateMeta meta = (BlockStateMeta)itemMeta;
        ShulkerBox shulker = (ShulkerBox)meta.getBlockState();
        event.getPlayer().updateInventory();
        event.getPlayer().openInventory(shulker.getInventory());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void handle(PlayerDeathEvent event) {
        if (!this.shulkers.containsKey(event.getEntity().getUniqueId()))
            return;
        event.getEntity().closeInventory();
    }

//    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
//    public void handle(InventoryClickEvent event) {
//        if (!this.shulkers.containsKey(event.getWhoClicked().getUniqueId()))
//            return;
////        if ((event.getCurrentItem() != null && Tag.SHULKER_BOXES.isTagged((Keyed)event.getCurrentItem().getType())) || (event
////                .getCursor() != null && Tag.SHULKER_BOXES.isTagged((Keyed)event.getCursor().getType()))) {
////            event.setCancelled(true);
////            return;
////        }
//        if (event.getAction() == InventoryAction.HOTBAR_SWAP || event.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD) {
//            ItemStack hotbarItem = (event.getHotbarButton() == -1) ? event.getWhoClicked().getInventory().getItemInOffHand() : event.getWhoClicked().getInventory().getItem(event.getHotbarButton());
////            if (hotbarItem != null && Tag.SHULKER_BOXES.isTagged((Keyed)hotbarItem.getType()))
////                event.setCancelled(true);
//        }
//    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void handle(PlayerSwapHandItemsEvent event) {
        if (!this.shulkers.containsKey(event.getPlayer().getUniqueId()))
            return;
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void handle(BlockPlaceEvent event) {
        if (!this.shulkers.containsKey(event.getPlayer().getUniqueId()))
            return;
        event.setCancelled(true);
//        Platform.getServer().sendError(event.getPlayer().getUniqueId(), MessagePosition.CHAT, "&cNie motego zrobiw trakcie modyfikowania shulkera!");
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void handle(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;
        Player player = (Player)event.getEntity();
        if (!this.shulkers.containsKey(player.getUniqueId()))
            return;
        player.closeInventory();
//        Platform.getServer().sendError(player.getUniqueId(), MessagePosition.CHAT, "&cOtrzymaobrazapisuje shulker!");
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void handle(PlayerDropItemEvent event) {
        ShulkerUser shulkerUser = this.shulkers.get(event.getPlayer().getUniqueId());
        if (shulkerUser == null)
            return;
        ItemStack item = event.getItemDrop().getItemStack();
//        if (!Tag.SHULKER_BOXES.isTagged((Keyed)item.getType()))
//            return;
        if (!item.getItemMeta().getPersistentDataContainer().has(this.key, PersistentDataType.BYTE))
            return;
        event.setCancelled(false);
        event.getItemDrop().remove();
        event.getPlayer().closeInventory();
    }

    @EventHandler
    public void handle(InventoryCloseEvent event) {
        if (!event.getInventory().getType().equals(InventoryType.SHULKER_BOX))
            return;
        if (!(event.getPlayer() instanceof Player))
            return;
        Player player = (Player)event.getPlayer();
        ShulkerUser shulkerUser = this.shulkers.get(player.getUniqueId());
        if (shulkerUser == null)
            return;
        ItemStack itemStack = shulkerUser.getItemStack();
        if (itemStack == null) {
            this.shulkers.remove(player.getUniqueId());
//            getLogger().warning("error1 " + player.getName());
            return;
        }
        try {
            boolean give = false;
//            if (!Tag.SHULKER_BOXES.isTagged((Keyed)itemStack.getType())) {
//                getLogger().warning("error2 " + player.getName() + " " + itemStack.getType().toString());
//                itemStack = new ItemStack(shulkerUser.getType());
//                give = true;
//            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.getPersistentDataContainer().remove(this.key);
            BlockStateMeta meta = (BlockStateMeta)itemMeta;
            ShulkerBox shulker = (ShulkerBox)meta.getBlockState();
            shulker.getInventory().setContents(event.getInventory().getContents());
            meta.setBlockState((BlockState)shulker);
            itemStack.setItemMeta((ItemMeta)meta);
            if (give)
                event.getPlayer().getInventory().setItem(shulkerUser.getSlot(), itemStack);
//            Platform.getScheduler().getThreadPool().schedule(() -> this.shulkers.remove(player.getUniqueId()), 500L, TimeUnit.MILLISECONDS);
            Bukkit.getScheduler().runTaskLater((Plugin)this, player::updateInventory, 1L);
        } catch (Exception ex) {
            ex.printStackTrace();
//            getLogger().warning("error3 " + player.getName() + " " + itemStack.getType().toString());
            this.shulkers.remove(player.getUniqueId());
//            PlayerUtils.addItems(player, Arrays.asList(event.getInventory().getContents()));
        }
    }
}
