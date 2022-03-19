package ru.brenlike.proboss.bosses.protection;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.custombossapi.api.boss.util.Protection;
import ru.brenlike.custombossapi.api.entity.DamageSource;

import java.util.Random;

public class KnockBackProtection extends Protection {
    public KnockBackProtection() {
        super(DamageSource.ENTITY_ATTACK);
    }

    @Override
    public boolean protect(@NotNull SpawnedBoss boss, @NotNull BossInventory inv, @Nullable Entity attacker, @NotNull Random random) {
        if (attacker == null) return false;

        if (attacker instanceof Player) {
            ItemMeta meta = ((Player) attacker).getInventory().getItemInMainHand().getItemMeta();
            return meta.hasEnchant(Enchantment.KNOCKBACK);
        }

        return false;
    }
}
