package de.dhsn_ooe.todo.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.dhsn_ooe.todo.Events.TodoControllerListener;
import de.dhsn_ooe.todo.Exception.ItemNotFoundException;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoItem;
import de.dhsn_ooe.todo.Model.TodoNote;

public class TodoListController
        implements CRUDController<TodoCheckList>, RelationController<TodoCheckList, TodoItem> {

    private static List<TodoControllerListener<TodoListController>> listeners = new ArrayList<>();
    private static final String TABLE_NAME = "todo_list";

    @Override
    public int create(TodoCheckList object) {
        int id;
        try {
            id = GenericDBQuery.insertRecord(TABLE_NAME, Map.of("title", object.getTitle(), "list_type", object.getType()));
            object.setId(id);
            this.fireEvent();
            return id;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return 0;
    }

    /**
     * adds listeners to the list
     * 
     * @param listener listener for the items of the check list
     */
    public void addListener(TodoControllerListener<TodoListController> listener) {
        listeners.add(listener);
    }

    /**
     * removes listeners from the list
     * 
     * @param listener listener for the items of the check list
     */
    public void removeListener(TodoControllerListener<TodoListController> listener) {
        listeners.remove(listener);
    }

    /**
     * notifies listeners if changes took place
     * 
     */
    protected void fireEvent() {
        for (TodoControllerListener<TodoListController> listener : listeners) {
            listener.listChanged(this);
        }
    }

    @Override
    public TodoCheckList getById(int id) throws ItemNotFoundException {
        try {
            PreparedStatement query = SQLiteDB.conn.prepareStatement("SELECT * from todo_list where list_id = ?");
            query.setInt(1, id);
            ResultSet results = query.executeQuery();
            TodoCheckList list = new TodoCheckList(results.getString("title"));
            list.setId(results.getInt("list_id"));
            return list;
        } catch (SQLException e) {
            throw new ItemNotFoundException(id);
        }
    }

    @Override
    public boolean update(TodoCheckList object, int id) {
        try {
            PreparedStatement query = SQLiteDB.conn
                    .prepareStatement("UPDATE todo_list set title = ? where list_id = ?");
            query.setString(1, object.getTitle());
            query.setInt(2, id);
            query.executeUpdate();
            this.fireEvent();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return false;
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
    public List<TodoCheckList> getAll() {
        List<TodoCheckList> lists = new ArrayList<>();
        try {
            ResultSet results = GenericDBQuery.selectWhereEqualsRecords(TABLE_NAME, "list_type", 1);
            while (results.next()) {
                TodoCheckList newList = new TodoCheckList(results.getString("title"));
                newList.setId(results.getInt("list_id"));
                lists.add(newList);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return lists;

    }

    @Override
    public List<TodoItem> getRelatedItems(TodoCheckList object) {
        List<TodoItem> items = new ArrayList<>();
        try {
            ResultSet results = GenericDBQuery.selectWhereEqualsRecords("todo_item", "list_id", object.getId());
            while (results.next()) {
                TodoItem item = new TodoItem(object);
                item.setId(results.getInt("item_id"));
                item.setState(results.getInt("checked") == 1);
                item.setStringContent(results.getString("content"));
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return items;
    }

}
