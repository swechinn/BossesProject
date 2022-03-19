package ru.brenlike.proboss.bosses.protection;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.custombossapi.api.boss.util.Protection;
import ru.brenlike.custombossapi.api.entity.DamageSource;

import java.util.Random;

public class PotionProtection extends Protection {
    public PotionProtection() {
        super(DamageSource.MAGIC);
    }

    @Override
    public boolean protect(@NotNull SpawnedBoss boss, @NotNull BossInventory inv, @Nullable Entity attacker, @NotNull Random random) {
        return true;
    }
}
