package ru.brenlike.custombossapi.api.boss;

import org.apache.commons.lang.Validate;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.other.Ability;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.custombossapi.api.boss.impl.BossImpl;
import ru.brenlike.custombossapi.api.boss.other.Protection;
import ru.brenlike.custombossapi.api.entity.DamageSource;

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
     * Returns all boss protections
     * @return protections
     */
    @NotNull Set<Protection> protections();

    /**
     * Returns all boss immunities
     * @return immunities
     */
    @NotNull Set<DamageSource> damageImmunities();

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
        private final Set<Ability> abilities = new HashSet<>();
        private final Set<Protection> protections = new HashSet<>();
        private final Set<DamageSource> immunities = new HashSet<>();
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
        }

        /**
         * Adds boss abilities
         *
         * @param abilities boss abilities
         * @return this builder
         */
        public Builder abilities(@NotNull Ability... abilities) {
            for (Ability ab: abilities) ability(ab);
            return this;
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
         * Adds boss protections
         *
         * @param protections boss protections
         * @return this builder
         */
        public Builder protections(@NotNull Protection... protections) {
            for (Protection p: protections) protection(p);
            return this;
        }

        /**
         * Adds boss protection
         *
         * @param protection boss protection
         * @return this builder
         */
        public Builder protection(@NotNull Protection protection) {
            Validate.notNull(protection, "Boss protection cannot be null!");
            protections.add(protection);
            return this;
        }

        /**
         * Adds boss damage immunity
         *
         * @param immunity boss immunity
         * @return this builder
         */
        public Builder immunity(@NotNull DamageSource immunity) {
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
            return new BossImpl(key, type, abilities, protections, immunities, inventory, speed);
        }
    }
}
