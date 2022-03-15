package ru.brenlike.custombossapi.api.boss.ability;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.entity.DamageSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class Ability {
    protected final long period;
    protected final int health;
    protected final Set<DamageSource> damageSources;

    /**
     * Abstract ability constructor
     * @param period call period. Can be -1L for every tick
     * @param health health period. Can be -1 for ignoring
     * @param damageTypes damage source triggers. Can be empty for ignoring
     */
    public Ability(long period, int health, @NotNull DamageSource... damageTypes) {
        this.period = period;
        this.health = health;
        this.damageSources = new HashSet<>(Arrays.asList(damageTypes));
    }

    public boolean canUse(int health, @NotNull DamageSource damage) {
        if (this.health == -1) return true;
        return this.damageSources.contains(damage);
    }

    /**
     * Calls by triggers
     * @param boss your boss
     * @param location location where the boss is
     * @param world the world where the boss is
     */
    public abstract void call(LivingEntity boss, Location location, World world);
}
