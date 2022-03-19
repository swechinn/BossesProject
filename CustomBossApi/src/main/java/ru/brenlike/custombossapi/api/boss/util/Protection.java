package ru.brenlike.custombossapi.api.boss.util;

import org.apache.commons.lang.Validate;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.custombossapi.api.entity.DamageSource;

import java.util.Random;

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
     * @param random the randomized values
     * @return true if this can protect boss
     */
    public abstract boolean protect(@NotNull SpawnedBoss boss,
                                    @NotNull BossInventory inv,
                                    @Nullable Entity attacker,
                                    @NotNull Random random);
}
