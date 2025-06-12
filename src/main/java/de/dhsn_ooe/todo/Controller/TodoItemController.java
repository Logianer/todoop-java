package de.dhsn_ooe.todo.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import de.dhsn_ooe.todo.Model.TodoItem;

public class TodoItemController implements CRUDController<TodoItem> {

    @Override
    public int create(TodoItem object) throws SQLException {
        PreparedStatement query = SQLiteDB.conn
                .prepareStatement("INSERT INTO todo_item (checked, content, list_id) VALUES (?,?,?)");
        query.setInt(1, object.getState() ? 1 : 0);
        query.setString(2, object.getStringContent());
        query.setInt(3, object.getList().getId());
        query.executeUpdate();
        ResultSet results = SQLiteDB.conn.createStatement().executeQuery("SELECT last_insert_rowid();");
        int id = results.getInt(1);
        object.setId(id);
        return id;
    }

    @Override
    public TodoItem getById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public void update(TodoItem object, int id) throws SQLException {
        PreparedStatement query = SQLiteDB.conn
                .prepareStatement("UPDATE todo_item SET checked = ?, content = ? where item_id = ?");
                query.setInt(1, object.getState() ? 1 : 0);
                query.setString(2, object.getStringContent());
        query.setInt(3, object.getId());
        query.executeUpdate();
    }

    @Override
    public boolean delete(TodoItem object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<TodoItem> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

}
