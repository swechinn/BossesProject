package ru.brenlike.custombossapi;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.brenlike.custombossapi.api.config.Messages;
import ru.brenlike.custombossapi.api.db.AbstractSqlDatabase;
import ru.brenlike.custombossapi.db.PlayerStatsDB;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public final class CustomBossApi extends JavaPlugin {
    public final Map<UUID, String> v_4750_ = new HashMap<>();
    public final Map<String, UUID> v_4751_ = new HashMap<>();
    private static Registry.Impl registry;
    private static BossDict.Impl dict;
    private static Messages messages;
    private AbstractSqlDatabase stats;

    /**
     * Returns boss registry
     *
     * @return registry class
     */
    public static Registry registry() {
        return registry;
    }

    public static BossDict styles() {
        return dict;
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

        l.info("Loading databases...");
        stats = new PlayerStatsDB(this, getDataFolder() + getConfig().getString("database.file", "data.db"));
    }

    @Override
    public void onEnable() {
        Logger l = getLogger();

        l.info("Initializing variables...");
        dict = new BossDict.Impl();
        registry = new Registry.Impl(this);

        l.info("Enabling databases...");
        stats.enable();

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
        v_4750_.clear();
        v_4751_.clear();
        registry.m_9007_();
        dict.m_9007_();

        l.info(getDescription().getName() + " " + getDescription().getVersion() + " - plugin disabled!");
    }

    private List<String> m_99_(String... args) {
        return Arrays.asList(args);
    }

    private YamlConfiguration m_100_() {
        YamlConfiguration yml = new YamlConfiguration();

        yml.addDefault("prefix", "&8[&bCustomBoss&3Api&7]");
        yml.addDefault("plugin.reloaded", "{prefix} &AПлагин перезагружен!");
        yml.addDefault("command.error.only_in_game", "{prefix} &cТолько в игре!");
        yml.addDefault("command.error.unknown_argument", "{prefix} &cНеизвестный аргумент команды.");
        yml.addDefault("command.error.little_args", "{prefix} &cМало аргументов команды.");
        yml.addDefault("command.error.permission", "{prefix} &cНедостаточно прав на выполнение этой команды.");
        yml.addDefault("boss.actionbar_health", "&eЗдоровье босса: &c{health}&6/{max_health}");
        yml.addDefault("boss.death", m_99_("&e{boss} &a- Был убит!",
                "&a1. &b{top_1_name} {top_1_damage}",
                "&e2. &b{top_2_name} {top_2_damage}",
                "&61. &b{top_3_name} {top_3_damage}"));
        yml.addDefault("boss.spawn.hologram", m_99_("&aПривет, %player_name%!",
                "&e{boss} появится здесь, через {spawn_time}"));
        yml.addDefault("boss.spawned", "{prefix} &eЗдоровье босса: &c{health}&6/{max_health}");
        yml.addDefault("time.seconds", "сек.");
        yml.addDefault("time.minutes", "мин.");
        yml.addDefault("time.hours", "ч.");

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
}
