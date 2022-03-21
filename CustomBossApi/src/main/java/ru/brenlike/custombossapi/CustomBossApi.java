package ru.brenlike.custombossapi;

import com.comphenix.protocol.ProtocolLibrary;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.config.YamlDefaults;
import ru.brenlike.custombossapi.api.config.Messages;
import ru.brenlike.custombossapi.api.db.AbstractSqlDatabase;
import ru.brenlike.custombossapi.api.entity.EntityHider;
import ru.brenlike.custombossapi.command.CustomBossCommand;
import ru.brenlike.custombossapi.db.PlayerStatsDB;
import ru.brenlike.custombossapi.listener.BossListener;
import ru.brenlike.custombossapi.listener.EntityListener;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public final class CustomBossApi extends JavaPlugin {
    private static EntityHider entityHider;
    private static Messages messages;
    private static Registry.Impl registry;
    private static BossDict.Impl dict;
    public final Map<UUID, String> v_4750_ = new HashMap<>();
    public final Map<String, UUID> v_4751_ = new HashMap<>();
    public final BossSpawner v_4752_ = new BossSpawner(this);
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

    // private
    public static EntityHider m_7010_() {
        return entityHider;
    }

    // private
    public static Messages m_7011_() {
        return messages;
    }

    @Override
    public void onLoad() {
        Logger l = getLogger();

        l.info("Loading config...");
        m_2010_();

        l.info("Loading databases...");
        stats = new PlayerStatsDB(this, getDataFolder() + "/" + getConfig().getString("database.filename", "data.db"));
    }

    @Override
    public void onEnable() {
        Logger l = getLogger();

        l.info("Initializing variables...");
        entityHider = new EntityHider(this);
        dict = new BossDict.Impl();
        registry = new Registry.Impl(this);

        l.info("Initializing locale settings...");
        m_2011_();

        l.info("Initializing protocol listeners...");
        ProtocolLibrary.getProtocolManager().addPacketListener(entityHider);

        l.info("Initializing listeners...");
        getServer().getPluginManager().registerEvents(new EntityListener(this), this);
        getServer().getPluginManager().registerEvents(new BossListener(this), this);

        l.info("Initializing commands...");
        Objects.requireNonNull(getServer().getPluginCommand("customboss")).setExecutor(new CustomBossCommand(this));
        Objects.requireNonNull(getServer().getPluginCommand("customboss")).setTabCompleter(new CustomBossCommand(this));

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

    public void m_2010_() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        reloadConfig();
    }

    public void m_2011_() {
        String path = getConfig().getString("localization.filename");

        File file = new File(getDataFolder(), path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileConfiguration yml = m_100_(YamlConfiguration.loadConfiguration(file));

        try {
            yml.save(file);

        } catch (IOException e) {
            e.printStackTrace();
        }

        messages = new Messages(yml);
    }

    private FileConfiguration m_100_(@NotNull YamlConfiguration yaml) {
        YamlDefaults def = new YamlDefaults(yaml);

        def.set("prefix", "&8[&bCustomBoss&3Api&7]");
        def.set("plugin.reloaded", "{prefix} &AПлагин перезагружен!");
        def.set("command.error.only_in_game", "{prefix} &cТолько в игре!");
        def.set("command.error.unknown_argument", "{prefix} &cНеизвестный аргумент команды.");
        def.set("command.error.little_args", "{prefix} &cМало аргументов команды.");
        def.set("command.error.permission", "{prefix} &cНедостаточно прав на выполнение этой команды.");
        def.set("boss.actionbar_health", "Здоровье босса {boss}: {boss_health}");
        def.set("boss.death",
                "&e{boss} &a- Был убит!",
                "&a1. &b{top_1_name} {top_1_damage}",
                "&e2. &b{top_2_name} {top_2_damage}",
                "&61. &b{top_3_name} {top_3_damage}");
        def.set("boss.spawn.hologram",
                "&aПривет, %player_name%!",
                "&e{boss} появится здесь, через {spawn_time}");
        def.set("boss.spawn.spawned",
                "{boss} появился");
        def.set("time.seconds", "сек.");
        def.set("time.minutes", "мин.");
        def.set("time.hours", "ч.");

        return def.yaml();
    }


}
