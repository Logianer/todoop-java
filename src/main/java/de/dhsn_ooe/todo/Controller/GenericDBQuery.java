package de.dhsn_ooe.todo.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;


/**
 * class that handles the querys in the database
 */
public class GenericDBQuery {

    /**
     * inserts a record into a table
     * @param table table that the record should be inserted into
     * @param args arguments that the object (record) has 
     * @return id of the inserted object
     * @throws SQLException exception the occurs if the inserting process into the database fails
     */
    public static int insertRecord(String table, Map<String, Object> args) throws SQLException {
        String columnNames = String.join(", ", args.keySet());
        String valueDummies = String.join(", ", Collections.nCopies(args.size(), "?"));

        PreparedStatement query = SQLiteDB.conn
                .prepareStatement("INSERT INTO " + table + " (" + columnNames + ") VALUES ("
                        + valueDummies + ")");

        int i = 1;
        for (Object value : args.values()) {
            if (value instanceof String) {
                query.setString(i, (String) value);
            } else if (value instanceof Integer) {
                query.setInt(i, (Integer) value);
            }
            i++;
        }
        query.executeUpdate();
        ResultSet results = SQLiteDB.conn.createStatement().executeQuery("SELECT last_insert_rowid();");
        int id = results.getInt(1);
        return id;
    }

    /**
     * selects all entrys of a table
     * @param table table that the entrys will be selected from
     * @return the result of the query (all entrys of the given table)
     * @throws SQLException exception that will be trown if the query fails
     */
    public static ResultSet selectAllRecords(String table) throws SQLException {
        ResultSet results = SQLiteDB.conn.createStatement().executeQuery("SELECT * from " + table);
        return results;
    }

    /**
     *  
     * @param table table that the entrys will be selected from
     * @param column name of the column
     * @param value 
     * @return
     * @throws SQLException exception that occurs if the query fails
     */
    public static ResultSet selectWhereEqualsRecords(String table, String column, Integer value) throws SQLException {
        PreparedStatement query = SQLiteDB.conn
                .prepareStatement("SELECT * from " + table + " where " + column + " = ?");
        query.setInt(1, value);
        ResultSet results = query.executeQuery();
        return results;
    }
}
