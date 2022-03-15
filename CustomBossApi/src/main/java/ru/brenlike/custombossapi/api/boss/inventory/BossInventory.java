package ru.brenlike.custombossapi.api.boss.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Boss inventory record
 */
public final class BossInventory {
    private ItemStack rightHand;
    private ItemStack leftHand;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggins;
    private ItemStack boots;

    public BossInventory() {
        this.rightHand = new ItemStack(Material.AIR);
        this.leftHand = new ItemStack(Material.AIR);

        this.helmet = new ItemStack(Material.AIR);
        this.chestplate = new ItemStack(Material.AIR);
        this.leggins = new ItemStack(Material.AIR);
        this.boots = new ItemStack(Material.AIR);
    }

    /**
     * Sets main hand item
     * @return this class
     */
    public @NotNull BossInventory rightHand(ItemStack hand) {
        this.rightHand = hand;
        return this;
    }

    /**
     * Returns main hand item
     * @return item
     * @see BossInventory#leftHand()
     */
    public @NotNull ItemStack rightHand() {
        return rightHand;
    }

    /**
     * Sets left hand item
     * @return this class
     */
    public @NotNull BossInventory leftHand(ItemStack hand) {
        this.leftHand = hand;
        return this;
    }

    /**
     * Returns left hand item
     * @return item
     * @see BossInventory#rightHand()
     */
    public @NotNull ItemStack leftHand() {
        return leftHand;
    }

    /**
     * Sets helmet item
     * @return this class
     */
    public @NotNull BossInventory helmet(ItemStack helmet) {
        this.helmet = helmet;
        return this;
    }

    /**
     * Returns helmet item
     * @return item
     */
    public @NotNull ItemStack helmet() {
        return helmet;
    }

    /**
     * Sets chestplate item
     * @return this class
     */
    public @NotNull BossInventory chestplate(ItemStack chestplate) {
        this.chestplate = chestplate;
        return this;
    }

    /**
     * Returns chestplate item
     * @return item
     */
    public @NotNull ItemStack chestplate() {
        return chestplate;
    }

    /**
     * Sets leggins item
     * @return this class
     */
    public @NotNull BossInventory leggins(ItemStack leggins) {
        this.leggins = leggins;
        return this;
    }

    /**
     * Returns leggins item
     * @return item
     */
    public @NotNull ItemStack leggins() {
        return leggins;
    }

    /**
     * Sets boots item
     * @return this class
     */
    public @NotNull BossInventory boots(ItemStack boots) {
        this.boots = boots;
        return this;
    }

    /**
     * Returns boots item
     * @return item
     */
    public @NotNull ItemStack boots() {
        return boots;
    }
}
