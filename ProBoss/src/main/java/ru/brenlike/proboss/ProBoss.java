package ru.brenlike.proboss;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.CustomBossApi;
import ru.brenlike.custombossapi.api.boss.Boss;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;
import ru.brenlike.proboss.bosses.protection.KnockBackProtection;
import ru.brenlike.proboss.bosses.ability.DashAbility;
import ru.brenlike.proboss.bosses.ability.RageAbility;
import ru.brenlike.proboss.bosses.protection.PotionProtection;
import ru.brenlike.proboss.bosses.protection.ProjectTileProtection;
import ru.brenlike.proboss.bosses.ability.ArmamentAbility;
import ru.brenlike.proboss.bosses.ability.SummonAbility;

import java.util.logging.Logger;

public final class ProBoss extends JavaPlugin {
    @Override
    public void onEnable() {
        m_20400_(); // Configurations
        m_30499_(); // Bosses

        Logger l = getLogger();
        l.info("");
        l.info("Plugin enabled!");
        l.info("Version: " + getDescription().getVersion());
        l.info("Authors: " + getDescription().getAuthors());
        l.info("");
    }

    @Override
    public void onDisable() {
    }

    public void m_20400_() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    private void m_30499_() {
        BossInfoLoader loader = new BossInfoLoader(getConfig());

        CustomBossApi.registry().register(m_30500_(loader));
        CustomBossApi.registry().register(m_30501_(loader));
    }

    private Boss m_30500_(@NotNull BossInfoLoader loader) {
        BossInventory inv = new BossInventory();

        inv.rightHand(new ItemStack(Material.BONE));

        return loader.builder("zombie_summoner", EntityType.ZOMBIE)
                .abilities(new ArmamentAbility(this), new SummonAbility())
                .inventory(inv)
                .protections(new PotionProtection(), new ProjectTileProtection())
                .build();
    }

    private Boss m_30501_(@NotNull BossInfoLoader loader) {
        BossInventory inv = new BossInventory();

        ItemStack crossbow = new ItemStack(Material.CROSSBOW);
        //crossbow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
        crossbow.addEnchantment(Enchantment.MULTISHOT, 1);
        inv.rightHand(crossbow);

        return loader.builder("pillager_ravager", EntityType.PILLAGER)
                .abilities(new DashAbility(), new RageAbility(50))
                .inventory(inv)
                .protection(new KnockBackProtection())
                .build();
    }
}
