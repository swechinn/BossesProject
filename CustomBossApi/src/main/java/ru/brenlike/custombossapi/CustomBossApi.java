package ru.brenlike.custombossapi;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.brenlike.custombossapi.api.config.Messages;
import ru.brenlike.custombossapi.api.db.AbstractSqlDatabase;
import ru.brenlike.custombossapi.db.PlayerStatsDB;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public final class CustomBossApi extends JavaPlugin {
    public final Map<UUID, String> v_4750_ = new HashMap<>();
    private static BossDict dict;
    private static Registry.Impl registry;
    private static Messages messages;
    private AbstractSqlDatabase stats;

    /**
     * Returns boss dict
     *
     * @return {@link BossDict} class
     */
    public static BossDict bossDict() {
        return dict;
    }

    /**
     * Returns boss registry
     *
     * @return registry class
     */
    public static Registry registry() {
        return registry;
    }

    // For private plugins
    public static Messages m_7011_() {
        return messages;
    }

    @Override
    public void onLoad() {
        Logger l = getLogger();

        l.info("Loading configs...");
        m_2010_();
        m_2011_();

        l.info("Loading static variables...");
        m_2020_();

        l.info("Loading databases...");
        stats = new PlayerStatsDB(this, getDataFolder() + getConfig().getString("database.file", "data.db"));
    }

    @Override
    public void onEnable() {
        Logger l = getLogger();

        l.info("Enabling databases...");
        stats.enable();

        l.info("Enabling other...");
        registry = new Registry.Impl(this);

        l.info("=======================");
        l.info("Success!");
        l.info(getDescription().getName() + " " + getDescription().getVersion() + " - plugin enabled!");
        l.info("=======================");

    }

    @Override
    public void onDisable() {
        Logger l = getLogger();

        l.info("Disabling databases...");
        stats.disable();

        l.info("Clearing variables...");
        registry.m_9007_();

        l.info(getDescription().getName() + " " + getDescription().getVersion() + " - plugin disabled!");
    }

    private YamlConfiguration m_100_() {
        YamlConfiguration yml = new YamlConfiguration();

        yml.addDefault("prefix", "&8[&bCustomBoss&3Api&7] ");
        yml.addDefault("plugin.reloaded", "&AПлагин перезагружен!");
        yml.addDefault("command.error.only_in_game", "&cТолько в игре!");
        yml.addDefault("command.error.unknown_argument", "&cНеизвестный аргумент команды.");
        yml.addDefault("command.error.little_args", "&cМало аргументов команды.");
        yml.addDefault("command.error.permission", "&cНедостаточно прав на выполнение этой команды.");

        return yml;
    }

    public void m_2010_() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    public void m_2011_() {
        String path = getConfig().getString("localization.file", "lang.yml");

        File file = new File(getDataFolder(), path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration yml = m_100_();

        try {
            yml.load(file);
            yml.save(file);

        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        messages = new Messages(yml);
    }

    public void m_2020_() {
        dict = new BossDict.Impl(getConfig());
    }
}
