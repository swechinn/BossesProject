package ru.brenlike.custombossapi.db;

import org.jetbrains.annotations.NotNull;
import ru.brenlike.custombossapi.CustomBossApi;
import ru.brenlike.custombossapi.api.db.AbstractSqlDatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerStatsDB extends AbstractSqlDatabase {
    private final CustomBossApi plugin;

    public PlayerStatsDB(@NotNull CustomBossApi p_4856_, @NotNull String p_4857_) {
        super(p_4857_, true);
        this.plugin = p_4856_;
    }

    @Override
    protected void onEnable() throws SQLException {
        String sql = "CREATE IF NOT NULL `stats` (\n" +
                "\t`` ,\n" +
                "\t`` ,\n" +
                "\t`` ,\n";

        PreparedStatement statement = connection.prepareStatement(sql);
    }
}
