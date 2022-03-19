package ru.brenlike.custombossapi;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.BossStyle;
import ru.brenlike.custombossapi.api.event.BossStyleChangeEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Boss styles vault.
 * @see CustomBossApi#styles()
 */
@ApiStatus.NonExtendable
public interface BossDict {
    /**
     * Returns boss style class
     * @param key boss key
     * @return boss style class
     */
    @NotNull BossStyle get(@NotNull String key);

    /**
     * Adds or changes old style
     * @param key boss key
     * @param style boss style
     */
    void put(@NotNull String key, @NotNull BossStyle style);

    class Impl implements BossDict {
        private final Map<String, BossStyle> styles = new HashMap<>();

        public Impl() {
        }

        @Override
        public @NotNull BossStyle get(@NotNull String key) {
            Validate.notNull(key, "Boss key cannot be null!");
            return styles.get(key);
        }

        @Override
        public void put(@NotNull String key, @NotNull BossStyle style) {
            Validate.notNull(key, "Boss key cannot be null!");
            Validate.notNull(key, "Boss style cannot be null!");

            if (styles.containsKey(key)) {
                Bukkit.getPluginManager().callEvent(new BossStyleChangeEvent(key, styles.get(key), style));
            }

            styles.put(key, style);
        }

        public void m_9007_() {
            styles.clear();
        }
    }
}
