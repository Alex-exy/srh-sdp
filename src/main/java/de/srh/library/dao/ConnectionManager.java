package de.srh.library.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
  private static final String DB_PROPERTIES = "db.properties";

  private static final Properties properties;

  static {
    properties = loadProperties();
    loadDriver();
  }

  public static Connection getConnection() {
    try {
      return DriverManager.getConnection(
          properties.getProperty("jdbc.url"),
          properties.getProperty("jdbc.username"),
          properties.getProperty("jdbc.password"));
    } catch (SQLException e) {
      throw new RuntimeException("Failed to connect to the database.", e);
    }
  }

  private static Properties loadProperties() {
    try (InputStream input =
        ConnectionManager.class.getClassLoader().getResourceAsStream(DB_PROPERTIES)) {
      if (input == null) {
        throw new RuntimeException("Unable to find " + DB_PROPERTIES);
      }
      Properties properties = new Properties();
      properties.load(input);
      return properties;
    } catch (IOException e) {
      throw new RuntimeException("Failed to load properties file: " + DB_PROPERTIES, e);
    }
  }

  private static void loadDriver() {
    try {
      Class.forName(properties.getProperty("jdbc.driver"));
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("PostgreSQL driver not found.", e);
    }
  }
}
