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
     * @param hand hand item
     */
    public void rightHand(@NotNull ItemStack hand) {
        this.rightHand = hand;
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
     * @param hand hand item
     */
    public void leftHand(@NotNull ItemStack hand) {
        this.leftHand = hand;
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
     * @param helmet helmet item
     */
    public void helmet(@NotNull ItemStack helmet) {
        this.helmet = helmet;
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
     * @param chestplate chestplate item
     */
    public void chestplate(@NotNull ItemStack chestplate) {
        this.chestplate = chestplate;
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
     * @param leggins leggins item
     */
    public void leggins(@NotNull ItemStack leggins) {
        this.leggins = leggins;
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
     * @param boots boots item
     */
    public void boots(@NotNull ItemStack boots) {
        this.boots = boots;
    }

    /**
     * Returns boots item
     * @return item
     */
    public @NotNull ItemStack boots() {
        return boots;
    }

    /**
     * Sets params from inventory template
     * @param template new custom boss inventory
     * @return this class
     */
    public @NotNull BossInventory from(BossInventory template) {
        this.rightHand = template.rightHand;
        this.leftHand = template.leftHand;
        this.helmet = template.helmet;
        this.chestplate = template.chestplate;
        this.leggins = template.leggins;
        this.boots = template.boots;

        return this;
    }

    @Override
    public @NotNull BossInventory clone() {
        BossInventory cloned = new BossInventory();

        cloned.rightHand = this.rightHand;
        cloned.leftHand = this.leftHand;
        cloned.helmet = this.helmet;
        cloned.chestplate = this.chestplate;
        cloned.leggins = this.leggins;
        cloned.boots = this.boots;

        return cloned;
    }
}
