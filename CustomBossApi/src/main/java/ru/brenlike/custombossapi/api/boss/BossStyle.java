package ru.brenlike.custombossapi.api.boss;

import org.bukkit.Location;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Params of your boss from config.yml
 */
@ApiStatus.NonExtendable
public interface BossStyle {
    /**
     * Boss display name
     * @return display name
     */
    @NotNull String name();

    /**
     * Boss spawn period in ticks
     * @return spawn period
     */
    long spawnPeriod();

    /**
     * Returns online in which boss spawns
     * @return online size
     */
    int spawnOnline();

    /**
     * Boss spawn location
     * @return spawn location
     */
    @NotNull Location spawnLocation();

    /**
     * Returns boss max health
     * @return max health
     */
    int health();

    /**
     * Returns boss base damage
     * @return base damage
     */
    double damage();
}
