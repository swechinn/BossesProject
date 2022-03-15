package ru.brenlike.proboss.ability.ravager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Monster;
import org.bukkit.inventory.ItemStack;
import ru.brenlike.custombossapi.api.boss.ability.Ability;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;

import java.util.Random;

public class Rage extends Ability {
    public Rage(int health) {
        super(-1, health);
    }

    @Override
    public void call(Monster boss, BossInventory inv, Location location, Random random) {
        inv.rightHand(new ItemStack(Material.IRON_AXE));
    }
}
