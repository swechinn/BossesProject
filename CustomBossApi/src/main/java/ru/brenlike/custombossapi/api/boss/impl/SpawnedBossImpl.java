package ru.brenlike.custombossapi.api.boss.impl;

import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.Boss;
import ru.brenlike.custombossapi.api.boss.BossStyle;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;

import java.util.UUID;

public class SpawnedBossImpl implements SpawnedBoss {
    private final UUID id;
    private final Boss boss;
    private final BossStyle style;

    public SpawnedBossImpl(@NotNull UUID p_4700_, @NotNull Boss p_4701_, @NotNull BossStyle p_4702_) {
        this.id = p_4700_;
        this.boss = p_4701_;
        this.style = p_4702_;
    }

    @Override
    public @NotNull UUID uniqueId() {
        return id;
    }

    @Override
    public @NotNull Boss boss() {
        return boss;
    }

    @Override
    public @NotNull BossStyle style() {
        return style;
    }
}
