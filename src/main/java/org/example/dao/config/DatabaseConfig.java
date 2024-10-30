package org.example.dao.config;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConfig {
    Connection connect();
}
