package ru.brenlike.custombossapi.api.boss;

import org.apache.commons.lang.Validate;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.custombossapi.api.boss.impl.BossImpl;

import java.util.HashSet;
import java.util.Set;

@ApiStatus.NonExtendable
public interface Boss {
    /**
     * Returns boss key
     * @return key
     */
    @NotNull String key();

    /**
     * Returns boss entity type
     * @return entity type
     */
    @NotNull EntityType type();

    /**
     * Returns all boss abilities
     * @return abilities
     */
    @NotNull Set<Ability> abilities();

    /**
     * Returns all boss immunities
     * @return immunities
     */
    @NotNull Set<EntityDamageEvent.DamageCause> damageImmunities();

    /**
     * Returns boss inventory
     * @return inventory
     */
    @NotNull BossInventory inventory();

    /**
     * Returns boss movement speed
     * @return movement speed
     */
    double speed();

    class Builder {
        private final String key;
        private final EntityType type;
        private final Set<Ability> abilities;
        private final Set<EntityDamageEvent.DamageCause> immunities;
        private BossInventory inventory = new BossInventory();
        private double speed = 1;

        /**
         * Default boss builder constructor
         *
         * @param key boss key
         * @param type living entity type
         */
        public Builder(@NotNull String key, @NotNull EntityType type) {
            Validate.notNull(key, "Boss key cannot be null!");
            Validate.notNull(type, "Boss entity type cannot be null!");
            Validate.notEmpty(key, "Boss key cannot be empty!");

            this.key = key;
            this.type = type;
            this.abilities = new HashSet<>();
            this.immunities = new HashSet<>();
        }

        /**
         * Adds boss ability
         *
         * @param ability boss ability
         * @return this builder
         */
        public Builder ability(@NotNull Ability ability) {
            Validate.notNull(ability, "Boss ability cannot be null!");
            abilities.add(ability);
            return this;
        }

        /**
         * Adds boss damage immunity
         *
         * @param immunity boss immunity
         * @return this builder
         */
        public Builder ability(@NotNull EntityDamageEvent.DamageCause immunity) {
            Validate.notNull(immunity, "Boss damage immunity cannot be null!");
            immunities.add(immunity);
            return this;
        }

        /**
         * Sets boss inventory
         *
         * @return this builder
         */
        public Builder inventory(@NotNull BossInventory inventory) {
            Validate.notNull(inventory, "Boss inventory cannot be null!");
            this.inventory = inventory;
            return this;
        }

        /**
         * Sets boss speed
         *
         * @param speed boss speed
         * @return this builder
         */
        public Builder speed(double speed) {
            this.speed = speed;
            return this;
        }

        /**
         * Builds boss class
         *
         * @return boss
         */
        public Boss build() {
            return new BossImpl(key, type, abilities, immunities, inventory, speed);
        }
    }
}
