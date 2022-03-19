package ru.brenlike.proboss.bosses.ability;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;
import ru.brenlike.custombossapi.api.boss.util.Ability;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;

import java.util.Random;

public class DashAbility extends Ability {
    public DashAbility() {
        super((5 * 60) * 20, -1);
    }

    @Override
    public void call(@NotNull SpawnedBoss boss, @NotNull BossInventory inv, @NotNull Location location, @NotNull Random random) {
        if (!(boss instanceof Monster)) return;
        if (inv.rightHand().getType() != Material.IRON_AXE) return;

        PotionEffect effect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 5, 20);

        ((Monster) boss.entity()).addPotionEffect(effect);

        Entity target = ((Monster) boss.entity()).getTarget();
        if (target != null) boss.entity().teleport(target);
    }
}
