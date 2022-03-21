package ru.brenlike.custombossapi;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.Boss;
import ru.brenlike.custombossapi.api.boss.BossStyle;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BossSpawner {
    //          1: Player, 2: Entity
    private final Map<UUID, UUID> entities = new HashMap<>();
    private final CustomBossApi plugin;

    public BossSpawner(CustomBossApi p_4808_) {
        this.plugin = p_4808_;
    }

    public void spawnHologram(@NotNull Player player, @NotNull Location location) {
        World world = location.getWorld();
        ArmorStand hologram = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);

        hologram.setVisible(false);
        hologram.setCanMove(false);
        hologram.setCustomNameVisible(true);

        entities.put(player.getUniqueId(), hologram.getUniqueId());

        for (Player target:
             Bukkit.getOnlinePlayers()) {
            CustomBossApi.m_7010_().hideEntity(target, hologram);
        }
        CustomBossApi.m_7010_().showEntity(player, hologram);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                if (entities.containsKey(player.getUniqueId())) {
                    updateHologram(player.getUniqueId());
                    return;
                }
                cancel();
            }
        }, 20, 20);
    }

    public void removeHologram(@NotNull OfflinePlayer player) {
        Entity entity = Bukkit.getEntity(entities.get(player.getUniqueId()));
        if (entity == null) return;

        entities.remove(player.getUniqueId());
        entity.remove();
    }

    // UUID of player
    private void updateHologram(@NotNull UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        Entity entity = Bukkit.getEntity(entities.get(uuid));

        if (player == null) return;
        if (entity == null) return;

        String text = CustomBossApi.m_7011_().compacted("boss.spawn.hologram");
        text = PlaceholderAPI.setPlaceholders(player, text);

        entity.setCustomName(text);

    }

    public void scheduleSpawn(Boss boss, BossStyle style) {

    }

    private boolean isHologram(Entity entity) {
        if (!entities.containsKey(entity.getUniqueId())) return false;
        return entity.getType() == EntityType.ARMOR_STAND;
    }
}
