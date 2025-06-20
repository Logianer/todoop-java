package de.dhsn_ooe.todo.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * class that represents the database for the todoapp 
 * creates tables for todonotes, todoitems and todolists, with all attributes needed for persistence
 */

public class SQLiteDB {

    /**
     * connection to the database
     */
    static Connection conn;

    /**
     * initiates the database with all tables and their attributes if they do not exist
     * if an exception occurs an error will be shown
     */
    static public void init() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:storage.db");

            Statement query = conn.createStatement();
            query.executeUpdate("PRAGMA foreign_keys = ON;");
            query.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS todo_list (list_id INTEGER PRIMARY KEY, title TEXT, list_type INT NOT NULL, updated_at DATETIME DEFAULT CURRENT_TIMESTAMP);");
            query.executeUpdate("CREATE TABLE IF NOT EXISTS todo_note (\n" + //
                    "    note_id INTEGER PRIMARY KEY,\n" + //
                    "    content TEXT,\n" + //
                    "    list_id INT UNIQUE,\n" + //
                    "    FOREIGN KEY (list_id)\n" + //
                    "        REFERENCES todo_list (list_id)\n" + //
                    "            ON UPDATE RESTRICT\n" + //
                    "            ON DELETE CASCADE\n" + //
                    ");");

            query.executeUpdate("CREATE TABLE IF NOT EXISTS todo_item (\n" + //
                    "    item_id INTEGER PRIMARY KEY,\n" + //
                    "    checked INT NOT NULL,\n" + //
                    "    content TEXT,\n" + //
                    "    list_id INT,\n" + //
                    "    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP, \n" +
                    "    FOREIGN KEY (list_id)\n" + //
                    "        REFERENCES todo_list (list_id)\n" + //
                    "            ON UPDATE RESTRICT\n" + //
                    "            ON DELETE CASCADE\n" + //
                    ");");
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}
