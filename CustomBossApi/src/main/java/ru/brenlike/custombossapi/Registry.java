package ru.brenlike.custombossapi;

import org.apache.commons.lang.Validate;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.brenlike.custombossapi.api.boss.Boss;

import java.util.HashMap;
import java.util.Map;

/**
 * Boss registry. Registers your boss.
 * Implementation: {@link CustomBossApi#registry()}
 * @see BossDict
 */
@ApiStatus.NonExtendable
public interface Registry {
    /**
     * Registers boss
     * @param boss your boss
     */
    void register(@NotNull Boss boss);

    /**
     * Returns registered boss or
     * null if it not exists
     * @param key boss key
     * @return {@link Boss} class
     */
    @Nullable Boss boss(@NotNull String key);

    class Impl implements Registry {
        private final Map<String, Boss> bosses = new HashMap<>();
        private final Plugin plugin;

        public Impl(Plugin plugin) {
            this.plugin = plugin;
        }

        @Override
        public void register(@NotNull Boss boss) {
            Validate.notNull(boss, "Boss value cannot be null!");
            bosses.put(boss.key(), boss);
        }

        @Override
        public @Nullable Boss boss(@NotNull String key) {
            Validate.notNull(key, "Boss key cannot be null!");
            return bosses.get(key);
        }

        public void m_9007_() {
            bosses.clear();
        }
    }
}
