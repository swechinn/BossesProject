package ru.brenlike.custombossapi.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ru.brenlike.custombossapi.CustomBossApi;
import ru.brenlike.custombossapi.api.event.BossDeathEvent;
import ru.brenlike.custombossapi.api.event.BossSpawnEvent;
import ru.brenlike.custombossapi.api.event.BossStyleChangeEvent;

import java.util.UUID;

public class BossListener implements Listener {
    private final CustomBossApi plugin;

    public BossListener(CustomBossApi p_4808_) {
        this.plugin = p_4808_;
    }

    @EventHandler
    public void onBossSpawn(BossSpawnEvent e) {
        plugin.v_4751_.put(e.getBoss().boss().key(), e.getBoss().uniqueId());
    }

    @EventHandler
    public void onBossDeath(BossDeathEvent e) {
        plugin.v_4751_.remove(e.getBoss().boss().key());
    }

    @EventHandler
    public void onBossStyleChange(BossStyleChangeEvent e) {
        UUID id = plugin.v_4751_.get(e.getKey());
        if (id == null) return;

        Entity boss = Bukkit.getEntity(id);
        if (boss == null) return;

        boss.setCustomName(e.getNewStyle().name());
    }
}
