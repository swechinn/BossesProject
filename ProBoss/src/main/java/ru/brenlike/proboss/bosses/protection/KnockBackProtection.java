package ru.brenlike.proboss.bosses.protection;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.brenlike.custombossapi.api.boss.Boss;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.custombossapi.api.boss.other.Protection;
import ru.brenlike.custombossapi.api.entity.DamageSource;

public class KnockBackProtection extends Protection {
    public KnockBackProtection() {
        super(DamageSource.ENTITY_ATTACK);
    }

    @Override
    public boolean canProtect(@NotNull Boss boss, @NotNull BossInventory inv, @Nullable Entity attacker) {
        if (attacker == null) return false;

        if (attacker instanceof Player) {
            ItemMeta meta = ((Player) attacker).getInventory().getItemInMainHand().getItemMeta();
            return meta.hasEnchant(Enchantment.KNOCKBACK);
        }

        return false;
    }
}
