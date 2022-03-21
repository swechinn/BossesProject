package ru.brenlike.custombossapi.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.brenlike.custombossapi.CustomBossApi;

public class PlayerListener implements Listener {
    private final CustomBossApi plugin;

    public PlayerListener(CustomBossApi p_4808_) {
        this.plugin = p_4808_;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

//        plugin.v_4752_.spawnHologram(p, );
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        plugin.v_4752_.removeHologram(p);
    }
}
