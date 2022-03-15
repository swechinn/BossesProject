package ru.brenlike.custombossapi.api.boss;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@ApiStatus.NonExtendable
public interface SpawnedBoss {
    /**
     * Returns spawned boss unique id
     * @return entity id
     */
    @NotNull UUID uniqueId();

    /**
     * Returns boss properties
     * @return properties
     */
    @NotNull Boss boss();

    /**
     * Returns boss style properties
     * @return boss style
     */
    @NotNull BossStyle style();

    /**
     * Returns boss entity
     * @return entity
     */
    default @Nullable Entity entity() {
        return Bukkit.getEntity(uniqueId());
    }
}
