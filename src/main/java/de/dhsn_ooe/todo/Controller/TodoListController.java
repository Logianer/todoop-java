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

/**
 * class that controlls all the todolists
 */
public class TodoListController
        implements CRUDController<TodoCheckList>, RelationController<TodoCheckList, TodoItem> {

    /**
     * listeners for the lists that act if certain actions take place
     */    
    private static List<TodoControllerListener<TodoListController>> listeners = new ArrayList<>();

    /**
     * name of the table the lists are part of
     */
    private static final String TABLE_NAME = "todo_list";

    
    /**
     * creates a new todo list and inserts it into the specified table
     * @return the id of the created list, 
     * an error if the list couldn't be inserted into the table
     */
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

    /**
     * gets a todochecklist by it's id, creates a new list with the data found and returns the list
     * querys the database to look for the list in the todo_list table
     * shows an error if the list wasn't found
     * @return the list
     */
    @Override
    public TodoCheckList getById(int id) throws ItemNotFoundException {
        try {
            PreparedStatement query = SQLiteDB.conn.prepareStatement("SELECT * from todo_list where list_id = ?");
            query.setInt(1, id);
            ResultSet results = query.executeQuery();
            TodoCheckList list = new TodoCheckList(results.getString("title"));
            list.setId(results.getInt("list_id"));
            list.setLastUpdated(results.getTimestamp("updated_at"));
            return list;
        } catch (SQLException e) {
            throw new ItemNotFoundException(id);
        }
    }

    /**
     * updates an existing todochecklist
     * 
     * @param object the list that should be updated
     * @param id id of the list
     * @return true if the update was successfull, false if not 
     */
    @Override
    public boolean update(TodoCheckList object, int id) {
        try {
            PreparedStatement query = SQLiteDB.conn
                    .prepareStatement("UPDATE todo_list set title = ?, updated_at = CURRENT_TIMESTAMP where list_id = ?");
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

    /**
     * deletes a todochecklist from the table todo_list
     * @return true if the list was deleted successfully
     * false if the list couldn't be deleted
     */
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

    /**
     * gets all todochecklists from the table 
     * @return alls lists in the table (returns an error if the dbquery failed)
     */
    @Override
    public List<TodoCheckList> getAll() {
        List<TodoCheckList> lists = new ArrayList<>();
        try {
            ResultSet results = GenericDBQuery.selectWhereEqualsRecords(TABLE_NAME, "list_type", TodoCheckList.TYPE);
            while (results.next()) {
                TodoCheckList newList = new TodoCheckList(results.getString("title"));
                newList.setId(results.getInt("list_id"));
                newList.setLastUpdated(results.getTimestamp("updated_at"));
                lists.add(newList);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        return lists;

    }

    /**
     * gets all the items that are related to a todochecklist
     * @param object list who's related item should be found
     * @return a list of the related items
     */
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
                item.setLastUpdated(results.getTimestamp("updated_at"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return items;
    }

}
