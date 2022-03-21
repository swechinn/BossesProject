package ru.brenlike.custombossapi.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.Nullable;
import ru.brenlike.custombossapi.CustomBossApi;
import ru.brenlike.custombossapi.api.MatchRecord;
import ru.brenlike.custombossapi.api.boss.Boss;
import ru.brenlike.custombossapi.api.boss.BossStyle;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;
import ru.brenlike.custombossapi.api.boss.impl.SpawnedBossImpl;
import ru.brenlike.custombossapi.api.event.BossDeathEvent;
import ru.brenlike.custombossapi.api.event.PlayerDamageBossEvent;
import ru.brenlike.custombossapi.db.PlayerStatsDB;

import java.util.Set;

public class EntityListener implements Listener {
    private final CustomBossApi plugin;

    public EntityListener(CustomBossApi p_4808_) {
        this.plugin = p_4808_;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        if (!(e.getEntity() instanceof LivingEntity)) return;
        if (isBoss(e.getEntity())) return;

        LivingEntity entity = (LivingEntity) e.getEntity();
        Player p = (Player) e.getDamager();
        Boss b = buildBoss(entity);

        BossStyle style = CustomBossApi.styles().get(b.key());
        SpawnedBoss spawned = new SpawnedBossImpl(entity, b, style);

        sendHealth(p, entity);

        PlayerStatsDB.updatePreStat(entity, p, e.getDamage());

        Bukkit.getPluginManager().callEvent(new PlayerDamageBossEvent(p, spawned, p.getWorld()));
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (isBoss(e.getEntity())) return;

        LivingEntity entity = e.getEntity();
        Boss b = buildBoss(entity);

        BossStyle style = CustomBossApi.styles().get(b.key());
        SpawnedBoss spawned = new SpawnedBossImpl(entity, b, style);

        Set<MatchRecord> results = PlayerStatsDB.killEvent(entity);
        Bukkit.getPluginManager().callEvent(new BossDeathEvent(spawned, entity.getWorld(), results));

        // Todo

    }

    private boolean isBoss(Entity entity) {
        if (!plugin.v_4750_.containsKey(entity.getUniqueId())) return true;

        Boss boss = buildBoss(entity);

        return boss == null;
    }

    private @Nullable Boss buildBoss(Entity entity) {
        return CustomBossApi.registry().boss(plugin.v_4750_.get(entity.getUniqueId()));
    }

    private void sendHealth(Player target, LivingEntity entity) {
        String health = CustomBossApi.m_7011_().getMessage("boss.actionbar_health")
                .replace("{health}", String.valueOf(entity.getHealth()))
                .replace("{max_health}", String.valueOf(entity.getMaxHealth()));

        target.sendActionBar(Component.text(health));
    }
}
