package com.app.db;

import com.app.config.AppConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton para gestión de conexiones JDBC.
 * En proyectos reales se reemplaza por un pool (HikariCP, c3p0...).
 */
public class ConnectionManager {

    private static ConnectionManager instance;
    private final AppConfig config = AppConfig.getInstance();

    private ConnectionManager() {
        try {
            Class.forName(config.getDbDriver());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC no encontrado", e);
        }
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            synchronized (ConnectionManager.class) {
                if (instance == null) instance = new ConnectionManager();
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                config.getDbUrl(),
                config.getDbUser(),
                config.getDbPassword());
    }
}