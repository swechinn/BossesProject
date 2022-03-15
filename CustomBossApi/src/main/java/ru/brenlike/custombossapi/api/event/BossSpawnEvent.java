package ru.brenlike.custombossapi.api.event;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;

public class BossSpawnEvent extends Event {
    public static HandlerList handlers = new HandlerList();
    private final SpawnedBoss boss;
    private final World world;

    public BossSpawnEvent(SpawnedBoss boss, World world) {
        this.boss = boss;
        this.world = world;
    }

    public @NotNull SpawnedBoss getBoss() {
        return boss;
    }

    public @NotNull World getWorld() {
        return world;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }
}
