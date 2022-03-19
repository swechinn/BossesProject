package ru.brenlike.custombossapi.api.boss;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

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
    @NotNull Entity entity();
}
