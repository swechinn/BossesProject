package ru.brenlike.proboss.bosses.ability;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.brenlike.custombossapi.api.boss.other.Ability;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;

import java.util.Random;

public class DashAbility extends Ability {
    public DashAbility() {
        super((5 * 60) * 20, -1);
    }

    @Override
    public void call(Monster boss, BossInventory inv, Location location, Random random) {
        if (inv.rightHand().getType() != Material.IRON_AXE) return;

        PotionEffect effect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 5, 20);

        boss.addPotionEffect(effect);

        Entity target = boss.getTarget();
        if (target != null) boss.teleport(target);
    }
}
