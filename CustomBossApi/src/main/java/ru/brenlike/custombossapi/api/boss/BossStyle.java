package ru.brenlike.custombossapi.api.boss;

import org.bukkit.Location;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Properties of your boss
 */
public class BossStyle {
    private final String name;
    private final long period;
    private final int online;
    private final Location location;

    public BossStyle(@NotNull String displayName,
                     long spawnPeriod,
                     int spawnOnline,
                     @NotNull Location spawnLocation) {
        this.name = displayName;
        this.period = spawnPeriod;
        this.online = spawnOnline;
        this.location = spawnLocation;
    }

    /**
     * Boss display name
     * @return display name
     */
    public @NotNull String name() {
        return name;
    }

    /**
     * Boss spawn period in ticks
     * @return spawn period
     */
    public long spawnPeriod() {
        return period;
    }

    /**
     * Returns online in which boss spawns
     * @return online size
     */
    public int spawnOnline() {
        return online;
    }

    /**
     * Boss spawn location
     * @return spawn location
     */
    public @NotNull Location spawnLocation() {
        return location;
    }
}
