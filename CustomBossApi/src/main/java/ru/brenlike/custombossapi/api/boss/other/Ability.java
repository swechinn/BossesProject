package ru.brenlike.custombossapi.api.boss.other;

import org.bukkit.Location;
import org.bukkit.entity.Monster;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.custombossapi.api.entity.DamageSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Ability {
    protected long period;
    protected int health;
    protected Set<DamageSource> damageSources;

    public Ability(long period, int health, @NotNull DamageSource... damageTypes) {
        this.period = period;
        this.health = health;
        this.damageSources = new HashSet<>(Arrays.asList(damageTypes));
    }

    public boolean canUse(int health, @NotNull DamageSource damage) {
        boolean can = false;

        if (this.health != -1)
            if (this.health == health) can = true;

        else if (this.damageSources.contains(damage)) can = true;

        return can;
    }

    /**
     * Calls by triggers
     * @param boss your boss
     * @param location location where the boss is
     * @param random the randomized values
     */
    public abstract void call(Monster boss, BossInventory inv, Location location, Random random);
}
