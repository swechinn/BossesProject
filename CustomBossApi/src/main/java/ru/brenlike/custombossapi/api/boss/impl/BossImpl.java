package ru.brenlike.custombossapi.api.boss.impl;

import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.custombossapi.api.boss.Ability;
import ru.brenlike.custombossapi.api.boss.Boss;

import java.util.Set;

public final class BossImpl implements Boss {
    private final String key;
    private final EntityType type;
    private final Set<Ability> abilities;
    private final Set<EntityDamageEvent.DamageCause> immunities;
    private final BossInventory inventory;
    private final double speed;

    public BossImpl(@NotNull String p_4800_, @NotNull EntityType p_4801_, @NotNull Set<Ability> p_4802_,
                @NotNull Set<EntityDamageEvent.DamageCause> p_4803_, @NotNull BossInventory p_4804_, double p_4805_) {
        this.key = p_4800_;
        this.type = p_4801_;
        this.abilities = p_4802_;
        this.immunities = p_4803_;
        this.inventory = p_4804_;
        this.speed = p_4805_;
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
    public @NotNull Set<EntityDamageEvent.DamageCause> damageImmunities() {
        return immunities;
    }

    @Override
    public @NotNull BossInventory inventory() {
        return inventory;
    }

    @Override
    public double speed() {
        return speed;
    }
}
