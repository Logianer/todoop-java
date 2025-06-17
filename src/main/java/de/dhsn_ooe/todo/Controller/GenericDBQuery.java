package de.dhsn_ooe.todo.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import de.dhsn_ooe.todo.Model.TodoCheckList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericDBQuery {
    public static int insertRecord(String table, Map<String, Object> args) throws SQLException {
        String columnNames = String.join(", ", args.keySet());
        String valueDummies = String.join(", ", Collections.nCopies(args.size(), "?"));

        PreparedStatement query = SQLiteDB.conn
                .prepareStatement("INSERT INTO " + table + " (" + columnNames + ") VALUES ("
                        + valueDummies + ")");

        int i = 1;
        for (Object value : args.values()) {
            i++;
            if (value instanceof String) {
                query.setString(i, (String) value);
            } else if (value instanceof Integer) {
                query.setInt(i, (Integer) value);
            }
        }
        query.executeUpdate();
        ResultSet results = SQLiteDB.conn.createStatement().executeQuery("SELECT last_insert_rowid();");
        int id = results.getInt(1);
        return id;
    }

    public static ResultSet selectAllRecords(String table) throws SQLException {
        ResultSet results = SQLiteDB.conn.createStatement().executeQuery("SELECT * from " + table);
        return results;
    }

    public static ResultSet selectWhereEqualsRecords(String table, String column, Integer value) throws SQLException {
        PreparedStatement query = SQLiteDB.conn
                .prepareStatement("SELECT * from " + table + " where " + column + " = ?");
        query.setInt(1, value);
        ResultSet results = query.executeQuery();
        return results;
    }
}
