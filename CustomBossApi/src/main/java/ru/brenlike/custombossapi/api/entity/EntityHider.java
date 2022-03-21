package ru.brenlike.custombossapi.api.entity;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.apache.commons.lang.Validate;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Special entity hider, you can use it in your plugin!
 */
public class EntityHider extends PacketAdapter {
    private Map<Integer, Set<UUID>> hidden = new HashMap<>();

    /**
     * Default constructor
     * @param plugin your plugin
     */
    public EntityHider(Plugin plugin) {
        super(plugin, PacketType.Play.Server.SPAWN_ENTITY);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        super.onPacketSending(event);

        int entityId = event.getPacket().getIntegers().read(0);

        Player player = event.getPlayer();
        Entity entity = ProtocolLibrary.getProtocolManager().getEntityFromID(player.getWorld(), entityId);

        if (isHidden(player, entity)) {
            event.setCancelled(true);
        }
    }

    /**
     * Hides entity for player
     * @param player for whom hide
     * @param entity who hide
     */
    public synchronized void hideEntity(@NotNull OfflinePlayer player, @NotNull Entity entity) {
        Validate.notNull(player, "Player cannot be null!");
        Validate.notNull(entity, "Entity cannot be null!");

        if (isHidden(player, entity)) return;

        Set<UUID> ids = getList(entity.getEntityId());
        ids.add(player.getUniqueId());

        hidden.put(entity.getEntityId(), ids);
    }

    /**
     * Shows entity for player
     * @param player for whom show
     * @param entity who show
     */
    public synchronized void showEntity(@NotNull OfflinePlayer player, @NotNull Entity entity) {
        Validate.notNull(player, "Player cannot be null!");
        Validate.notNull(entity, "Entity cannot be null!");

        if (!isHidden(player, entity)) return;

        Set<UUID> ids = getList(entity.getEntityId());
        ids.remove(player.getUniqueId());

        hidden.put(entity.getEntityId(), ids);
    }

    /**
     * Returns true if entity hidden for player
     * @param player for who
     * @param entity who may be hidden
     * @return true if hidden, false if shown
     */
    public synchronized boolean isHidden(@NotNull OfflinePlayer player, @NotNull Entity entity) {
        Validate.notNull(player, "Player cannot be null!");
        Validate.notNull(entity, "Entity cannot be null!");

        Set<UUID> ids = getList(entity.getEntityId());
        return ids.contains(player.getUniqueId());
    }

    private synchronized Set<UUID> getList(int entity) {
        Set<UUID> ids = hidden.get(entity);
        if (ids == null) ids = new HashSet<>();
        return ids;
    }
}
