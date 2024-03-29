package ru.brenlike.custombossapi.api.config;

import me.clip.placeholderapi.PlaceholderAPI;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * You can use it in your project!
 * @author 25BrenLike
 */
public class Messages {
    private final FileConfiguration file;

    /**
     * Class for your main class
     * @param file your locale config file
     */
    public Messages(@NotNull FileConfiguration file) {
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

        String value = path;
        Object obj = file.get(path);

        if (obj == null) {
            Bukkit.getLogger().severe(String.format("%s translation not found!", path));

        } else {
            if (obj instanceof String)
                value = file.getString(path);

            else if (obj instanceof List<?>) {
                value = String.join("\n", file.getStringList(path));
            }
        }

        String prefix = file.getString("prefix");
        if (prefix == null) throw new NullPointerException("Prefix not found!");

        return ChatColor.translateAlternateColorCodes('&', value
                .replace("{prefix}", prefix));
    }

    public @NotNull List<String> lines(@NotNull String path) {
        Validate.notNull(path, "Translation path cannot be null!");

        List<String> value = new ArrayList<>();
        Object obj = file.get(path);

        if (obj == null) {
            Bukkit.getLogger().severe(String.format("%s translation not found!", path));

        } else {
            if (obj instanceof String)
                value.add(file.getString(path));

            else if (obj instanceof List<?>) {
                value = file.getStringList(path);
            }
        }

        String prefix = file.getString("prefix");
        if (prefix == null) throw new NullPointerException("Prefix not found!");

        for (int i = 0; i < value.size(); i++) {
            String val = value.get(i);
            value.set(i, ChatColor.translateAlternateColorCodes('&', val.replace("{prefix}", prefix)));
        }

        return value;
    }

    public String compacted(String path) {
        return String.join("\n", lines(path));
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
        List<String> texts = lines(path);
        for (String text:
                texts) {
            sender.sendMessage(text);
        }
    }

    /**
     * Sets placeholders and sends message to player
     * @param player who
     * @param path path to translation
     */
    public void send(@NotNull Player player, @NotNull String path) {
        List<String> texts = lines(path);
        texts = PlaceholderAPI.setPlaceholders(player, texts);

        for (String text:
             texts) {
            player.sendMessage(text);
        }
    }
}
