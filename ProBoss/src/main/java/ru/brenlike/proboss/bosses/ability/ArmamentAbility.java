package ru.brenlike.proboss.bosses.ability;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.api.boss.SpawnedBoss;
import ru.brenlike.custombossapi.api.boss.util.Ability;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.proboss.ProBoss;

import java.util.Random;

public class ArmamentAbility extends Ability {
    private final ProBoss plugin;

    public ArmamentAbility(ProBoss plugin) {
        super((60 * 10) * 20, -1);
        this.plugin = plugin;
    }

    @Override
    public void call(@NotNull SpawnedBoss boss, @NotNull BossInventory inv, @NotNull Location location, @NotNull Random random) {
        BossInventory cloned = inv.clone();

        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        inv.helmet(helmet);

        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        inv.chestplate(chestplate);

        ItemStack leggins = new ItemStack(Material.LEATHER_LEGGINGS);
        leggins.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        inv.leggins(leggins);

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        inv.boots(boots);

        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        inv.rightHand(sword);

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if (Bukkit.getEntity(boss.uniqueId()) == null) return;

            inv.from(cloned);

        }, (60 * 10) * 20);
    }
}
