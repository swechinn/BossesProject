package ru.brenlike.custombossapi.api;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.brenlike.custombossapi.CustomBossApi;
import ru.brenlike.custombossapi.api.boss.Boss;
import ru.brenlike.custombossapi.api.boss.BossStyle;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;

import java.util.*;


/**
 * Spawns bosses and holograms.
 * @see CustomBossApi#spawner()
 */
public interface BossSpawner {
    /**
     * Spawns unique hologram for player
     * @param player player
     * @param location where hologram will spawn
     */
    void spawnHologram(@NotNull Player player, @NotNull Location location);

    /**
     * Removes hologram if it's living
     * @param player who will not see
     */
    void removeHologram(@NotNull OfflinePlayer player);

    /**
     * Checks entity to hologram
     * @param entity entity
     * @return true if entity is hologram
     */
    boolean isHologram(@NotNull Entity entity);

    /**
     * Spawns boss
     * @param boss boss properties
     * @param style boss style
     */
    void spawnBoss(@NotNull Boss boss, @NotNull BossStyle style);

    /**
     * Returns spawned boss
     * @param id entity unique id
     * @return boss
     */
    @Nullable SpawnedBoss getBoss(UUID id);

    /**
     * Checks entity to boss
     * @param entity entity
     * @return true if it's boss
     */
    boolean isBoss(@NotNull Entity entity);

    /**
     * Returns boss properties class
     * @param entity entity
     * @return returns properties or null if it not exists
     */
    @Nullable Boss buildBoss(@NotNull Entity entity);
}
