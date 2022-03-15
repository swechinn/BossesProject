package ru.brenlike.custombossapi.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.brenlike.custombossapi.CustomBossApi;
import ru.brenlike.custombossapi.api.boss.Boss;
import ru.brenlike.custombossapi.api.boss.BossStyle;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;
import ru.brenlike.custombossapi.api.boss.impl.SpawnedBossImpl;
import ru.brenlike.custombossapi.api.event.PlayerDamageBossEvent;

public class EntityListener implements Listener {
    private final CustomBossApi plugin;

    public EntityListener(CustomBossApi p_4808_) {
        this.plugin = p_4808_;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        if (!(e.getEntity() instanceof LivingEntity)) return;
        if (!isBoss(e.getEntity())) return;

        LivingEntity entity = (LivingEntity) e.getEntity();
        Player p = (Player) e.getDamager();
        Boss b = boss(entity);

        BossStyle style = CustomBossApi.bossDict().style(b.key());
        SpawnedBoss sb = new SpawnedBossImpl(entity.getUniqueId(), b, style);

        health(p, entity);

        Bukkit.getPluginManager().callEvent(new PlayerDamageBossEvent(p, sb, p.getWorld()));
    }

    private boolean isBoss(Entity entity) {
        if (!plugin.v_4750_.containsKey(entity.getUniqueId())) return false;

        Boss boss = boss(entity);

        return boss != null;
    }

    private @Nullable Boss boss(Entity entity) {
        return CustomBossApi.registry().boss(plugin.v_4750_.get(entity.getUniqueId()));
    }

    private void health(Player target, LivingEntity entity) {
        String health = CustomBossApi.m_7011_().getMessage("")
                .replace("{health}", String.valueOf(entity.getHealth()))
                .replace("{max_health}", String.valueOf(entity.getMaxHealth()));

        target.sendActionBar(Component.text(health));
    }
}
