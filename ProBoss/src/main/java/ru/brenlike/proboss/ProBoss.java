package ru.brenlike.proboss;

import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;
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

public final class ProBoss extends JavaPlugin {

    @Override
    public void onEnable() {
        m_30499_();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void m_30499_() {
        CustomBossApi.registry().register(m_30500_());
        CustomBossApi.registry().register(m_30501_());
    }

    private Boss m_30500_() {
        BossInventory inv = new BossInventory();

        return new Boss.Builder("zombie_summoner", EntityType.ZOMBIE)
                .abilities(new ArmamentAbility(this), new SummonAbility())
                .inventory(inv)
                .protections(new PotionProtection(), new ProjectTileProtection())
                .build();
    }

    private Boss m_30501_() {
        BossInventory inv = new BossInventory();

        return new Boss.Builder("pillager_ravager", EntityType.PILLAGER)
                .abilities(new DashAbility(), new RageAbility(50))
                .inventory(inv)
                .protection(new KnockBackProtection())
                .build();
    }
}
