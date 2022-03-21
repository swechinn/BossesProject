package ru.brenlike.custombossapi.api.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Use it for setting default values to
 * yaml configuration
 */
public class YamlDefaults {
    private final FileConfiguration config;

    public YamlDefaults(FileConfiguration configuration) {
        this.config = configuration;
    }

    /**
     * Sets default string value if
     * it not exists in configuration
     * @param path path to value
     * @param value default value
     */
    public void set(@NotNull String path, @NotNull String value) {
        if (!config.contains(path)) {
            config.set(path, value);
        }
    }

    /**
     * Sets default array value if
     * it not exists in configuration
     * @param path path to value
     * @param values default value
     */
    public void set(@NotNull String path, @NotNull String... values) {
        set(path, Arrays.asList(values));
    }

    /**
     * Sets default array value if
     * it not exists in configuration
     * @param path path to value
     * @param values default value
     */
    public void set(@NotNull String path, @NotNull List<String> values) {
        if (!config.contains(path)) {
            config.set(path, values);
        }
    }

    /**
     * Returns configurations
     * @return file configuration
     */
    public FileConfiguration yaml() {
        return config;
    }
}
