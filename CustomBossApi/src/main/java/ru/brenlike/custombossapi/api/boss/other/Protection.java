package ru.brenlike.custombossapi.api.boss.other;

import org.apache.commons.lang.Validate;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.brenlike.custombossapi.api.boss.Boss;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.custombossapi.api.entity.DamageSource;

/**
 * Abstract boss protection
 */
public abstract class Protection {
    protected final DamageSource condition;

    public Protection(@Nullable DamageSource condition) {
        this.condition = condition;
    }

    /**
     * Returns using condition result
     * @param type type of damage
     * @return true if boss can use this protection
     */
    public final boolean canUse(@NotNull DamageSource type) {
        Validate.notNull(type, "Damage source cannot be null!");
        if (condition == null) return true;
        return condition == type;
    }

    /**
     * Calls when player attacks
     * Returns true if boss can be protected,
     * false if boss have to be attacked.
     * @param boss attacked boss
     * @param inv boss inventory
     * @param attacker who attacked boss
     * @return true if this protection can work
     */
    public abstract boolean canProtect(@NotNull Boss boss, final @NotNull BossInventory inv, @Nullable Entity attacker);
}
