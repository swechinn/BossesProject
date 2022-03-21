package ru.brenlike.custombossapi.api.event;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.MatchRecord;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;

import java.util.Set;

public class BossDeathEvent extends Event {
    public static HandlerList handlers = new HandlerList();
    private final SpawnedBoss boss;
    private final Set<MatchRecord> killers;
    private final World world;

    public BossDeathEvent(SpawnedBoss boss, World world, Set<MatchRecord> killers) {
        this.boss = boss;
        this.killers = killers;
        this.world = world;
    }

    public @NotNull SpawnedBoss getBoss() {
        return boss;
    }

    public @NotNull Set<MatchRecord> getKillers() {
        return killers;
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
