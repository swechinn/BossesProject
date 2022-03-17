package ru.brenlike.proboss.bosses.ability;

import org.bukkit.Location;
import org.bukkit.entity.*;
import ru.brenlike.custombossapi.api.boss.other.Ability;
import ru.brenlike.custombossapi.api.boss.inventory.BossInventory;

import java.util.*;

public class SummonAbility extends Ability {
    public SummonAbility() {
        super(20 * 60, -1);
    }

    @Override
    public void call(Monster boss, BossInventory inv, Location location, Random random) {
        int min = 1, max = 3;

        int zombies = (random.nextInt() * (max - min)) + min;

        for (int i = 0; i < zombies + 1; i++) { // Spawns 1-3 zombies
            Zombie zombie = location.getWorld().spawn(location, Zombie.class);
            zombie.setBaby();

            LivingEntity player = boss.getTarget();
            if (player != null) {
                if (player instanceof Player) zombie.setTarget(target((Player) player, random));
            }
        }

        period = period + ((60 * 3) * 20);
    }

    private LivingEntity target(Player player, Random random) {
        List<LivingEntity> entities = new ArrayList<>();

        entities.add(player);

        for (LivingEntity entity:
                player.getLocation().getNearbyLivingEntities(10)) {

            if (!(entity instanceof Tameable)) continue;
            Tameable pet = (Tameable) entity;

            if (pet.isTamed() && pet.getOwner().getUniqueId() == player.getUniqueId()) entities.add(pet);
        }

        int num = random.nextInt(entities.size());
        return entities.get(num);
    }
}
