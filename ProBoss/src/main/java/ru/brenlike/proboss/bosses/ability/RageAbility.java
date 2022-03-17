package ru.brenlike.proboss.bosses.ability;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Monster;
import org.bukkit.inventory.ItemStack;
import ru.brenlike.custombossapi.api.boss.other.Ability;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;

import java.util.Random;

public class RageAbility extends Ability {
    public RageAbility(int health) {
        super(-1, health);
    }

    @Override
    public void call(Monster boss, BossInventory inv, Location location, Random random) {
        inv.rightHand(new ItemStack(Material.IRON_AXE));
    }
}
