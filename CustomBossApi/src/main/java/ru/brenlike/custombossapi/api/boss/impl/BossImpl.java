package ru.brenlike.custombossapi.api.boss.impl;

import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.custombossapi.api.boss.util.Ability;
import ru.brenlike.custombossapi.api.boss.Boss;
import ru.brenlike.custombossapi.api.boss.util.Protection;
import ru.brenlike.custombossapi.api.entity.DamageSource;

import java.util.Set;

public final class BossImpl implements Boss {
    private final String key;
    private final EntityType type;
    private final Set<Ability> abilities;
    private final Set<Protection> protections;
    private final Set<DamageSource> immunities;
    private final BossInventory inventory;
    private final int health;
    private final double damage;
    private final double speed;

    public BossImpl(@NotNull String p_4800_, @NotNull EntityType p_4801_, @NotNull Set<Ability> p_4802_, @NotNull Set<Protection> p_4803_,
                    @NotNull Set<DamageSource> p_4804_, @NotNull BossInventory p_4805_, int p_4806_, double p_4807_, double p_4808_) {
        this.key = p_4800_;
        this.type = p_4801_;
        this.abilities = p_4802_;
        this.protections = p_4803_;
        this.immunities = p_4804_;
        this.inventory = p_4805_;
        this.health = p_4806_;
        this.damage = p_4807_;
        this.speed = p_4806_;
    }

    @Override
    public @NotNull String key() {
        return key;
    }

    @Override
    public @NotNull EntityType type() {
        return type;
    }

    @Override
    public @NotNull Set<Ability> abilities() {
        return abilities;
    }

    @Override
    public @NotNull Set<Protection> protections() {
        return protections;
    }

    @Override
    public @NotNull Set<DamageSource> damageImmunities() {
        return immunities;
    }

    @Override
    public @NotNull BossInventory inventory() {
        return inventory;
    }

    @Override
    public int health() {
        return health;
    }

    @Override
    public double damage() {
        return damage;
    }

    @Override
    public double speed() {
        return speed;
    }
}
