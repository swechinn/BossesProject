package ru.brenlike.custombossapi.db;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.brenlike.custombossapi.CustomBossApi;
import ru.brenlike.custombossapi.api.MatchRecord;
import ru.brenlike.custombossapi.api.db.AbstractSqlDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerStatsDB extends AbstractSqlDatabase {
    private final CustomBossApi plugin;

    public PlayerStatsDB(@NotNull CustomBossApi p_4856_, @NotNull String p_4857_) {
        super(p_4857_, true);
        this.plugin = p_4856_;
    }

    @Override
    protected void onEnable() throws SQLException {
        connection.prepareStatement("CREATE TABLE IF NOT EXISTS `stats` (\n" +
                "\t`boss` TEXT NOT NULL,\n" +
                "\t`killer` VARCHAR(64) NOT NULL,\n" +
                "\t`damage` DOUBLE(255,30) NOT NULL,\n" +
                "\t`timestamp` BIGINT NOT NULL\n" +
                ");").execute();

        connection.prepareStatement("CREATE TABLE IF NOT EXISTS `pre_stats` (\n" +
                "\t`boss` VARCHAR(64) NOT NULL,\n" +
                "\t`killer` VARCHAR(64) NOT NULL,\n" +
                "\t`damage` DOUBLE(255,30) NOT NULL\n" +
                ");");
    }

    private static void addPreStat(@NotNull Entity boss, @NotNull Player player, double damage) {
        try {

            String sql = "INSERT INTO `pre_stats` VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, boss.getUniqueId().toString());
            statement.setString(2, player.getUniqueId().toString());
            statement.setDouble(3, damage);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePreStat(@NotNull Entity boss, @NotNull Player player, double damage) {
        try {

            String sql = "UPDATE `pre_stats` SET (`damage` = `damage` + ?) WHERE (`killer` = ?) AND (`boss` = ?);";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, damage);
            statement.setString(2, player.getUniqueId().toString());
            statement.setString(3, boss.getUniqueId().toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static @NotNull Set<MatchRecord> killEvent(@NotNull Entity boss) {
        try {
            String sql = "SELECT * FROM `pre_stats` WHERE (`boss` = ?) ORDER BY `damage` DESC;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, boss.getUniqueId().toString());

            ResultSet result = statement.executeQuery();
            Set<MatchRecord> records = new HashSet<>();
            int top = 0;

            while (result.next()) {
                MatchRecord record = new MatchRecord(
                        Bukkit.getOfflinePlayer(UUID.fromString(result.getString("killer"))),
                        result.getString("boss"),
                        top,
                        result.getDouble("damage")
                );
                records.add(record);
                top++;
            }

            statement = connection.prepareStatement("DELETE FROM `pre_stats` WHERE (`boss` = ?);");
            statement.setString(1, boss.getUniqueId().toString());
            statement.executeUpdate();

            return records;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }
}
