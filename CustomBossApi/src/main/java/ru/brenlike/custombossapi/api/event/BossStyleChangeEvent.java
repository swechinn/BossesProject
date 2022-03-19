package ru.brenlike.custombossapi.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.BossStyle;

public class BossStyleChangeEvent extends Event {
    public static HandlerList handlers = new HandlerList();
    private final String key;
    private final BossStyle old;
    private final BossStyle changed;

    public BossStyleChangeEvent(String key, BossStyle old, BossStyle changed) {
        this.key = key;
        this.old = old;
        this.changed = changed;
    }

    /**
     * Returns boss key
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns old boss style
     * @return boss style
     */
    public BossStyle getOldStyle() {
        return old;
    }

    /**
     * Returns new boss style
     * @return boss style
     */
    public BossStyle getNewStyle() {
        return changed;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }
}
