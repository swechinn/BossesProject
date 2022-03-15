package ru.brenlike.custombossapi.api.db;

import org.apache.commons.lang.Validate;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractSqlDatabase {
    private final String path;
    private final boolean autoCommit;
    protected static Connection connection;

    /**
     * Abstract database class. Use for your custom database
     * <p>
     * Commits statements automatically
     * @param path database file
     */
    public AbstractSqlDatabase(@NotNull String path) {
        this(path, true);
    }

    /**
     * Abstract database class. Use for your custom database
     * @param path database file
     * @param autoCommit commit auto?
     */
    public AbstractSqlDatabase(@NotNull String path, boolean autoCommit) {
        Validate.notNull(path, "Database path cannot be null!");

        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.path = path;
        this.autoCommit = autoCommit;

    }

    /**
     * Calls when database enables
     * @throws SQLException if some error has occurred
     */
    protected abstract void onEnable() throws SQLException;

    /**
     * Calls when database disables
     * @throws SQLException if some error has occurred
     */
    protected void onDisable() throws SQLException {}

    /**
     * Method for your plugin main class.
     * Enables your database
     */
    public void enable() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
            connection.setAutoCommit(autoCommit);

            onEnable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for your plugin main class.
     * Disables your database
     */
    public void disable() {
        try {
            onDisable();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
