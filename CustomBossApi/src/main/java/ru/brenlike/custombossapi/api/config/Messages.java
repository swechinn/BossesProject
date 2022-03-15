package ru.brenlike.custombossapi.api.config;

import me.clip.placeholderapi.PlaceholderAPI;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * You can use it in your project!
 * @author 25BrenLike
 */
public class Messages {
    private final YamlConfiguration file;

    /**
     * Class for your main class
     * @param file your locale config file
     */
    public Messages(@NotNull YamlConfiguration file) {
        Validate.notNull(file, "Messages config file cannot be null!");
        this.file = file;
    }

    /**
     * Returns message from translations
     * file or default message
     * @param path path to translation
     * @return translated or default message
     * @throws NullPointerException if prefix not found
     */
    public @NotNull String getMessage(@NotNull String path) {
        Validate.notNull(path, "Translation path cannot be null!");

        String prefix = file.getString("prefix"),
                value = file.getString(path);

        if (prefix == null) throw new NullPointerException("Prefix not found!");
        if (value == null) {
            value = path;
            Bukkit.getLogger().severe(String.format("%s translation not found!", path));
        }

        return ChatColor.translateAlternateColorCodes('&', prefix + value);
    }

    /**
     * Sends message to sender.
     * Sender can be replaced to player.
     * @param sender what
     * @param path path to translation
     */
    public void send(@NotNull CommandSender sender, @NotNull String path) {
        if (sender instanceof Player) {
            send((Player) sender, path);
        }
        sender.sendMessage(getMessage(path));
    }

    /**
     * Sets placeholders and sends message to player
     * @param player who
     * @param path path to translation
     */
    public void send(@NotNull Player player, @NotNull String path) {
        String text = getMessage(path);
        text = PlaceholderAPI.setPlaceholders(player, text);

        player.sendMessage(text);
    }
}
