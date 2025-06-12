package de.dhsn_ooe.todo.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.dhsn_ooe.todo.Events.TodoCheckListListener;
import de.dhsn_ooe.todo.Exception.ItemNotFoundException;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoItem;

public class TodoCheckListController
        implements CRUDController<TodoCheckList>, RelationController<TodoCheckList, TodoItem> {

    private List<TodoCheckListListener> listeners = new ArrayList<>();

    @Override
    public int create(TodoCheckList object) throws SQLException {
        PreparedStatement query = SQLiteDB.conn
                .prepareStatement("INSERT INTO todo_list (title, list_type) VALUES (?,?)");
        query.setString(1, object.getTitle());
        query.setInt(2, 1);
        query.executeUpdate();
        ResultSet results = SQLiteDB.conn.createStatement().executeQuery("SELECT last_insert_rowid();");
        int id = results.getInt(1);
        object.setId(id);

        this.fireEvent();
        return id;
    }

    /**
     * adds listeners to the list
     * 
     * @param listener listener for the items of the check list
     */
    public void addListener(TodoCheckListListener listener) {
        listeners.add(listener);
    }

    /**
     * removes listeners from the list
     * 
     * @param listener listener for the items of the check list
     */
    public void removeListener(TodoCheckListListener listener) {
        listeners.remove(listener);
    }

    /**
     * notifies listeners if changes took place
     * 
     */
    public void fireEvent() {
        for (TodoCheckListListener listener : listeners) {
            listener.listChanged(this);
        }
    }

    @Override
    public TodoCheckList getById(int id) throws ItemNotFoundException {
        try {
            PreparedStatement query = SQLiteDB.conn.prepareStatement("SELECT * from todo_list where list_id = ?");
            query.setInt(1, id);
            ResultSet results = query.executeQuery();
            TodoCheckList list = new TodoCheckList();
            list.setId(results.getInt("list_id"));
            list.setTitle(results.getString("title"));
            return list;
        } catch (SQLException e) {
            throw new ItemNotFoundException(id);
        }
    }

    @Override
    public void update(TodoCheckList object, int id) {
        try {
            PreparedStatement query = SQLiteDB.conn
                    .prepareStatement("UPDATE todo_list set title = ? where list_id = ?");
            query.setString(1, object.getTitle());
            query.setInt(2, id);
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.fireEvent();
        }
    }

    @Override
    public boolean delete(TodoCheckList object) {
        try {
            PreparedStatement query = SQLiteDB.conn
                    .prepareStatement("DELETE FROM todo_list where list_id = ?");
            query.setInt(1, object.getId());
            query.executeUpdate();
            this.fireEvent();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return false;
        }
    }

    @Override
    public List<TodoCheckList> getAll() throws SQLException {
        List<TodoCheckList> lists = new ArrayList<>();
        ResultSet results = SQLiteDB.conn.createStatement().executeQuery("SELECT * from todo_list");

        while (results.next()) {
            TodoCheckList newList = new TodoCheckList();
            newList.setId(results.getInt("list_id"));
            newList.setTitle(results.getString("title"));
            lists.add(newList);
        }

        return lists;

    }

    @Override
    public List<TodoItem> getRelatedItems(TodoCheckList object) throws SQLException {
        List<TodoItem> items = new ArrayList<>();
        PreparedStatement query = SQLiteDB.conn.prepareStatement("SELECT * from todo_item where list_id = ?");
        query.setInt(1, object.getId());
        ResultSet results = query.executeQuery();
        while (results.next()) {
            TodoItem item = new TodoItem(object);
            item.setId(results.getInt("item_id"));
            item.setState(results.getInt("checked") == 1);
            item.setStringContent(results.getString("content"));
            items.add(item);
        }
        return items;
    }

}
