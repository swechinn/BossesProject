package ru.brenlike.custombossapi.api.entity;

import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @see EntityDamageEvent.DamageCause
 */
public enum DamageSource {
    CONTACT(EntityDamageEvent.DamageCause.CONTACT),
    ENTITY_ATTACK(EntityDamageEvent.DamageCause.ENTITY_ATTACK),
    ENTITY_SWEEP_ATTACK(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK),
    PROJECTILE(EntityDamageEvent.DamageCause.PROJECTILE),
    SUFFOCATION(EntityDamageEvent.DamageCause.SUFFOCATION),
    FALL(EntityDamageEvent.DamageCause.FALL),
    FIRE(EntityDamageEvent.DamageCause.FIRE),
    FIRE_TICK(EntityDamageEvent.DamageCause.FIRE_TICK),
    MELTING(EntityDamageEvent.DamageCause.MELTING),
    LAVA(EntityDamageEvent.DamageCause.LAVA),
    DROWNING(EntityDamageEvent.DamageCause.DROWNING),
    BLOCK_EXPLOSION(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION),
    ENTITY_EXPLOSION(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION),
    VOID(EntityDamageEvent.DamageCause.VOID),
    LIGHTNING(EntityDamageEvent.DamageCause.LIGHTNING),
    SUICIDE(EntityDamageEvent.DamageCause.SUICIDE),
    STARVATION(EntityDamageEvent.DamageCause.STARVATION),
    POISON(EntityDamageEvent.DamageCause.POISON),
    MAGIC(EntityDamageEvent.DamageCause.MAGIC),
    WITHER(EntityDamageEvent.DamageCause.WITHER),
    FALLING_BLOCK(EntityDamageEvent.DamageCause.FALLING_BLOCK),
    THORNS(EntityDamageEvent.DamageCause.THORNS),
    DRAGON_BREATH(EntityDamageEvent.DamageCause.DRAGON_BREATH),
    CUSTOM(EntityDamageEvent.DamageCause.CUSTOM),
    FLY_INTO_WALL(EntityDamageEvent.DamageCause.FLY_INTO_WALL),
    HOT_FLOOR(EntityDamageEvent.DamageCause.HOT_FLOOR),
    CRAMMING(EntityDamageEvent.DamageCause.CRAMMING),
    DRYOUT(EntityDamageEvent.DamageCause.DRYOUT);

    private static final Map<EntityDamageEvent.DamageCause, DamageSource> sources = new HashMap<>();

    private final EntityDamageEvent.DamageCause source;

    DamageSource(EntityDamageEvent.DamageCause source) {
        this.source = source;
    }

    static {
        for (DamageSource damageSource : values()) {
            sources.put(damageSource.getCause(), damageSource);
        }
    }

    @NotNull
    public EntityDamageEvent.DamageCause getCause() {
        return source;
    }

    @NotNull
    public static DamageSource getSource(EntityDamageEvent.DamageCause cause) {
        return sources.get(cause);
    }
}
