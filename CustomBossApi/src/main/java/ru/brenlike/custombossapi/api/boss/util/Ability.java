package ru.brenlike.custombossapi.api.boss.util;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.custombossapi.api.entity.DamageSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Ability {
    protected long period;
    protected int health;

    public Ability(long period, int health) {
        this.period = period;
        this.health = health;
    }

    public boolean canUse(int health) {
        boolean can = false;

        if (this.health != -1)
            if (this.health == health) can = true;

        return can;
    }

    /**
     * Returns new period
     * @return period in ticks
     */
    public long period() {
        return period;
    }

    /**
     * Calls by triggers
     * @param boss your boss
     * @param inv boss inventory
     * @param location location where the boss is
     * @param random the randomized values
     */
    public abstract void call(@NotNull SpawnedBoss boss,
                              @NotNull BossInventory inv,
                              @NotNull Location location,
                              @NotNull Random random);

    /**
     * Clones ability class
     * @return cloned ability class
     */
    @Override
    public Ability clone() {
        return this;
    }
}
