package ru.brenlike.custombossapi.api.event;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;

public class BossDeathEvent extends Event {
    public static HandlerList handlers = new HandlerList();
    private final SpawnedBoss boss;
    private final Player killer;
    private final World world;

    public BossDeathEvent(SpawnedBoss boss, Player killer, World world) {
        this.boss = boss;
        this.killer = killer;
        this.world = world;
    }

    public @NotNull SpawnedBoss getBoss() {
        return boss;
    }

    public @NotNull Player getKiller() {
        return killer;
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
