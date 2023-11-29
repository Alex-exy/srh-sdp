package de.srh.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDAO {
  private final Connection connection;

  public BaseDAO() {
    this.connection = ConnectionManager.getConnection();
  }

  public int insertOne(String tableName, String[] columns, Object[] values) throws SQLException {
    StringBuilder sqlBuilder =
        new StringBuilder("INSERT INTO ")
            .append(tableName)
            .append(" (")
            .append(String.join(", ", columns))
            .append(") VALUES (");

    for (int i = 0; i < values.length; i++) {
      if (i > 0) {
        sqlBuilder.append(", ");
      }
      sqlBuilder.append("?");
    }

    sqlBuilder.append(")");

    try (PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString())) {
      for (int i = 0; i < values.length; i++) {
        statement.setObject(i + 1, values[i]);
      }
      return statement.executeUpdate();
    }
  }

  public int update(
      String tableName,
      String[] columns,
      Object[] values,
      String conditionColumn,
      Object conditionValue)
      throws SQLException {
    StringBuilder sqlBuilder = new StringBuilder("UPDATE ").append(tableName).append(" SET ");

    for (int i = 0; i < columns.length; i++) {
      if (i > 0) {
        sqlBuilder.append(", ");
      }
      sqlBuilder.append(columns[i]).append(" = ?");
    }

    sqlBuilder.append(" WHERE ").append(conditionColumn).append(" = ?");

    try (PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString())) {
      for (int i = 0; i < columns.length; i++) {
        statement.setObject(i + 1, values[i]);
      }
      statement.setObject(columns.length + 1, conditionValue);
      return statement.executeUpdate();
    }
  }

  public int delete(String tableName, String conditionColumn, Object conditionValue)
      throws SQLException {
    String sql = "DELETE FROM " + tableName + " WHERE " + conditionColumn + " = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setObject(1, conditionValue);
      return statement.executeUpdate();
    }
  }

  public List<Object[]> select(
      String tableName, String[] columns, String conditionColumn, Object conditionValue)
      throws SQLException {
    List<Object[]> resultList = new ArrayList<>();
    StringBuilder sqlBuilder = new StringBuilder("SELECT ");
    if (columns.length > 0) {
      sqlBuilder.append(String.join(", ", columns));
    } else {
      sqlBuilder.append("*");
    }
    sqlBuilder.append(" FROM ").append(tableName);

    if (conditionColumn != null && conditionValue != null) {
      sqlBuilder.append(" WHERE ").append(conditionColumn).append(" = ?");
    }

    try (PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString())) {
      if (conditionColumn != null && conditionValue != null) {
        statement.setObject(1, conditionValue);
      }
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
          for (int i = 1; i <= row.length; i++) {
            row[i - 1] = resultSet.getObject(i);
          }
          resultList.add(row);
        }
      }
    }
    return resultList;
  }
}
