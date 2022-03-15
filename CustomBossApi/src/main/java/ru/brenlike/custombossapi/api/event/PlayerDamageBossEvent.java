package ru.brenlike.custombossapi.api.event;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;

public class PlayerDamageBossEvent extends PlayerEvent {
    public static HandlerList handlers = new HandlerList();
    private final SpawnedBoss boss;
    private final World world;

    public PlayerDamageBossEvent(@NotNull Player who, @NotNull SpawnedBoss boss, @NotNull World world) {
        super(who);
        this.boss = boss;
        this.world = world;
    }

    public SpawnedBoss getBoss() {
        return boss;
    }

    public World getWorld() {
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
