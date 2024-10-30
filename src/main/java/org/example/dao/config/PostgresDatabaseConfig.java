package org.example.dao.config;

import org.example.dao.config.annotation.Configuration;
import org.example.dao.config.exception.PostgresConnectException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class PostgresDatabaseConfig implements DatabaseConfig {

    private static final String USERNAME = "Sardorbek";
    private static final String PASSWORD = "sardor0304";
    private static final String DATABASE = "jdbc_project";

    @Override
    public Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DATABASE, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PostgresConnectException("Postgres Database Connection Failed");
        }
    }
}
