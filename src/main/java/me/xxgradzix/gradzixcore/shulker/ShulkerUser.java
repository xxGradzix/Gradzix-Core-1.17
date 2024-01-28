package me.xxgradzix.gradzixcore.shulker;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ShulkerUser {
  private final ItemStack itemStack;

  private final int slot;

  private final Material type;

  public ShulkerUser(ItemStack itemStack, int slot) {
    this.itemStack = itemStack;
    this.slot = slot;
    this.type = itemStack.getType();
  }

  public ItemStack getItemStack() {
    return this.itemStack;
  }

  public int getSlot() {
    return this.slot;
  }

  public Material getType() {
    return this.type;
  }
}