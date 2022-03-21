package ru.brenlike.proboss;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.brenlike.custombossapi.api.boss.Boss;
import ru.brenlike.custombossapi.api.boss.BossStyle;

public class BossInfoLoader {
    private final FileConfiguration config;

    public BossInfoLoader(@NotNull FileConfiguration p_4808_) {
        this.config = p_4808_;
    }

    public @NotNull Boss.Builder builder(@NotNull String key, @NotNull EntityType type) {
        Validate.notNull(key, "Boss key cannot be null!");
        Validate.notNull(type, "Boss type cannot be null!");

        Boss.Builder builder = new Boss.Builder(key, type);

        int p_2003_ = config.getInt(m_8006_(key, "health"));
        if (p_2003_ == 0) throw new IllegalArgumentException("Boss max health cannot be null or zero!");

        double p_2005_ = config.getDouble(m_8006_(key, "damage"));
        if (p_2005_ == 0) throw new IllegalArgumentException("Boss damage cannot be null or zero!");

        return builder;
    }

    public @NotNull BossStyle style(@NotNull String path) {
        Validate.notNull(path, "Configuration path cannot be null!");

        String p_2000_ = config.getString(m_8006_(path, "name"));
        if (p_2000_ == null) throw new NullPointerException("Boss display name cannot be null!");

        long p_2001_ = config.getLong(m_8006_(path, "spawn.time"));
        if (p_2001_ == 0) throw new IllegalArgumentException("Boss spawn period cannot be null or zero!");

        Location p_2002_ = m_8007_(m_8006_(path, "spawn.position"));
        if (p_2002_ == null) throw new NullPointerException("Boss spawn position cannot be null! Check ./plugins/CustomBossApi/config.yml");

        int p_2004_ = config.getInt(m_8006_(path, "online"));
        if (p_2004_ == 0) throw new IllegalArgumentException("Boss spawn per online param cannot be null or zero!");

        return new BossStyle(p_2000_, p_2001_, p_2004_, p_2002_);
    }

    private @NotNull String m_8006_(@NotNull String key, @NotNull String path) {
        return String.format("bosses.%s.%s", key, path);
    }

    private @Nullable Location m_8007_(String path) {
        double x = config.getDouble(path + "x");
        if (x == 0) return null;

        double y = config.getDouble(path + "y");
        if (y == 0) return null;

        double z = config.getDouble(path + "z");
        if (z == 0) return null;

        String worldName = config.getString(path + "world");
        if (worldName == null) return null;

        World world = Bukkit.getWorld(path + worldName);
        if (world == null) return null;

        return new Location(world, x, y, z);
    }
}
