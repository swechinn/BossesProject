package ru.brenlike.proboss.bosses.ability;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;
import ru.brenlike.custombossapi.api.boss.util.Ability;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;

import java.util.Random;

public class RageAbility extends Ability {
    public RageAbility(int health) {
        super(-1, health);
    }

    @Override
    public void call(@NotNull SpawnedBoss boss, @NotNull BossInventory inv, @NotNull Location location, @NotNull Random random) {
        inv.rightHand(new ItemStack(Material.IRON_AXE));
    }
}
