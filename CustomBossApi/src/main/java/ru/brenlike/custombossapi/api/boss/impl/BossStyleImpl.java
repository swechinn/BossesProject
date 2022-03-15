package ru.brenlike.custombossapi.api.boss.impl;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.BossStyle;

public final class BossStyleImpl implements BossStyle {
    private final String name;
    private final long period;
    private final Location location;
    private final int health;
    private final double damage;
    private final int online;

    public BossStyleImpl(@NotNull String p_4800_, long p_4801_, @NotNull Location p_4802_,
                         int p_4803_, int p_4804_, double p_4805_) {
        this.name = ChatColor.translateAlternateColorCodes('&', p_4800_);
        this.period = p_4801_;
        this.location = p_4802_;
        this.health = p_4803_;
        this.online = p_4804_;
        this.damage = p_4805_;
    }

    @Override
    public @NotNull String name() {
        return name;
    }

    @Override
    public long spawnPeriod() {
        return period;
    }

    @Override
    public int spawnOnline() {
        return online;
    }

    @Override
    public @NotNull Location spawnLocation() {
        return location;
    }

    @Override
    public int health() {
        return health;
    }

    @Override
    public double damage() {
        return damage;
    }
}
