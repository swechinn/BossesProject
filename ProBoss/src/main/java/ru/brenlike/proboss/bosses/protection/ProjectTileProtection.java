package ru.brenlike.proboss.bosses.protection;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.brenlike.custombossapi.api.boss.Boss;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.custombossapi.api.boss.other.Protection;
import ru.brenlike.custombossapi.api.entity.DamageSource;

public class ProjectTileProtection extends Protection {
    public ProjectTileProtection() {
        super(DamageSource.PROJECTILE);
    }

    @Override
    public boolean canProtect(@NotNull Boss boss, @NotNull BossInventory inv, @Nullable Entity attacker) {
        return true;
    }
}
