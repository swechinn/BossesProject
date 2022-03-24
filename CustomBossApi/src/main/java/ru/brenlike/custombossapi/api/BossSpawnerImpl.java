package ru.brenlike.custombossapi.api;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.brenlike.custombossapi.CustomBossApi;
import ru.brenlike.custombossapi.api.boss.Boss;
import ru.brenlike.custombossapi.api.boss.BossStyle;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;
import ru.brenlike.custombossapi.api.boss.impl.SpawnedBossImpl;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.custombossapi.api.boss.util.Ability;

import java.util.*;

public class BossSpawnerImpl implements BossSpawner {
    private final Map<UUID, UUID> holograms = new HashMap<>();
    private final CustomBossApi plugin;

    public BossSpawnerImpl(CustomBossApi p_4808_) {
        this.plugin = p_4808_;
    }

    @Override
    public void spawnHologram(@NotNull Player player, @NotNull Location location) {
        World world = location.getWorld();
        ArmorStand hologram = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);

        hologram.setVisible(false);
        hologram.setCanMove(false);
        hologram.setCustomNameVisible(true);

        holograms.put(player.getUniqueId(), hologram.getUniqueId());

        for (Player target:
                Bukkit.getOnlinePlayers()) {
            CustomBossApi.m_7010_().hideEntity(target, hologram);
        }
        CustomBossApi.m_7010_().showEntity(player, hologram);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                if (holograms.containsKey(player.getUniqueId())) {
                    updateHologram(player.getUniqueId());
                    return;
                }
                cancel();
            }
        }, 20, 20);
    }

    @Override
    public void removeHologram(@NotNull OfflinePlayer player) {
        Entity entity = Bukkit.getEntity(holograms.get(player.getUniqueId()));
        if (entity == null) return;

        holograms.remove(player.getUniqueId());
        entity.remove();
    }

    @Override
    public boolean isHologram(@NotNull Entity entity) {
        if (!holograms.containsKey(entity.getUniqueId())) return false;
        return entity.getType() == EntityType.ARMOR_STAND;
    }

    // UUID of player
    private void updateHologram(@NotNull UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        Entity entity = Bukkit.getEntity(holograms.get(uuid));

        if (player == null) return;
        if (entity == null) return;

        String text = CustomBossApi.m_7011_().compacted("boss.spawn.hologram");
        text = PlaceholderAPI.setPlaceholders(player, text);

        entity.setCustomName(text);
    }

    @Override
    public void spawnBoss(@NotNull Boss boss, @NotNull BossStyle style) {
        String worldName = style.spawnLocation().getWorld().getName();
        World world = Bukkit.getWorld(worldName);
        assert world != null;

        Location location = style.spawnLocation();
        location.setWorld(world);

        LivingEntity entity = (LivingEntity) world.spawnEntity(location, boss.type());

        if (entity instanceof InventoryHolder) {
            Inventory inventory = ((InventoryHolder) entity).getInventory();

            setInventoryContents(boss.inventory(), inventory);
            startAbilities(entity.getUniqueId(), boss.abilities());
        }

        entity.setCustomName(style.name());

    }

    public @Nullable SpawnedBoss getBoss(UUID id) {
        Entity entity = Bukkit.getEntity(id);
        if (entity == null) return null;
        if (!isBoss(entity)) return null;

        Boss boss = buildBoss(entity);
        if (boss == null) return null;

        BossStyle style = CustomBossApi.styles().get(boss.key());
        return new SpawnedBossImpl((LivingEntity) entity, boss, style);
    }

    @Override
    public boolean isBoss(@NotNull Entity entity) {
        if (!plugin.v_4750_.containsKey(entity.getUniqueId())) return true;

        Boss boss = buildBoss(entity);

        return boss == null;
    }

    @Override
    public @Nullable Boss buildBoss(@NotNull Entity entity) {
        return CustomBossApi.registry().boss(plugin.v_4750_.get(entity.getUniqueId()));
    }

    private void startAbilities(UUID boss, @NotNull Set<Ability> abilities) {
        for (Ability ability:
                abilities) {
            scheduleAbility(boss, ability);
        }
    }

    private void scheduleAbility(UUID uuid, @NotNull Ability ability) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                SpawnedBoss boss = getBoss(uuid);
                if (boss == null) {
                    cancel();
                    return;
                }

                int health = (int) boss.entity().getHealth();
                Ability changed = ability.clone();

                if (changed.canUse(health)) {
                    BossInventory inv = boss.boss().inventory();
                    changed.call(boss, inv, boss.entity().getLocation(), new Random());

                    if (boss.entity() instanceof InventoryHolder)
                        setInventoryContents(inv, ((InventoryHolder) boss.entity()).getInventory());
                }

                scheduleAbility(uuid, changed);
            }
        }, ability.period());
    }

    private void setInventoryContents(@NotNull BossInventory bossInventory, @NotNull Inventory inventory) {
        ItemStack[] contents = new ItemStack[] { // Check it!
                bossInventory.rightHand(),
                bossInventory.leftHand(),
                bossInventory.boots(),
                bossInventory.leggins(),
                bossInventory.chestplate(),
                bossInventory.helmet()
        };

        inventory.setContents(contents);
    }

    public void clear() {
        holograms.clear();
    }
}
