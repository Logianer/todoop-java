package de.dhsn_ooe.todo.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import de.dhsn_ooe.todo.Events.TodoControllerListener;
import de.dhsn_ooe.todo.Exception.ItemNotFoundException;
import de.dhsn_ooe.todo.Exception.OrphanedRelationException;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoItem;

public class TodoItemController implements CRUDController<TodoItem> {

    private static List<TodoControllerListener<TodoItemController>> listeners = new ArrayList<>();

    /**
     * adds listeners to the list
     * 
     * @param listener listener for the items of the check list
     */
    public void addListener(TodoControllerListener<TodoItemController> listener) {
        listeners.add(listener);
    }

    /**
     * removes listeners from the list
     * 
     * @param listener listener for the items of the check list
     */
    public void removeListener(TodoControllerListener<TodoItemController> listener) {
        listeners.remove(listener);
    }

    /**
     * notifies listeners if changes took place
     * 
     */
    protected void fireEvent() {
        for (TodoControllerListener<TodoItemController> listener : listeners) {
            listener.listChanged(this);
        }
    }

    @Override
    public int create(TodoItem object) {
        try {
            int id = GenericDBQuery.insertRecord("todo_item", Map.of("checked", object.getState() ? 1 : 0, "list_id",
                    object.getList().getId(), "content", object.getStringContent()));
            object.setId(id);
            this.fireEvent();
            return id;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return 0;
    }

    @Override
    public TodoItem getById(int id) throws ItemNotFoundException, OrphanedRelationException {
        try {
            PreparedStatement query = SQLiteDB.conn.prepareStatement("SELECT * from todo_item where item_id = ?");
            query.setInt(1, id);
            ResultSet result = query.executeQuery();
            TodoCheckList list = new TodoListController().getById(result.getInt("list_id"));
            TodoItem newItem = new TodoItem(list);
            newItem.setId(id);
            newItem.setState(result.getInt("checked") == 1);
            newItem.setStringContent(result.getString("content"));
            return newItem;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            throw new ItemNotFoundException(id);
        } catch (ItemNotFoundException e) {
            throw new OrphanedRelationException("Got orphaned Todo Item with no exisiting list.");
        }
    }

    @Override
    public boolean update(TodoItem object, int id) {
        PreparedStatement query;
        try {
            query = SQLiteDB.conn
                    .prepareStatement("UPDATE todo_item SET checked = ?, content = ? where item_id = ?");
            query.setInt(1, object.getState() ? 1 : 0);
            query.setString(2, object.getStringContent());
            query.setInt(3, object.getId());
            query.executeUpdate();
            this.fireEvent();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return false;
    }

    @Override
    public boolean delete(TodoItem object) {
        try {
            PreparedStatement query = SQLiteDB.conn
                    .prepareStatement("DELETE FROM todo_item where item_id = ?");
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
    public List<TodoItem> getAll() {
        List<TodoItem> lists = new ArrayList<>();
        try {
            ResultSet results = SQLiteDB.conn.createStatement().executeQuery("SELECT * from todo_item");

            List<TodoCheckList> allLists = new TodoListController().getAll();

            while (results.next()) {
                int listId = results.getInt("list_id");
                Optional<TodoCheckList> foundList = allLists.stream()
                        .filter(l -> l.getId() == listId)
                        .findFirst();
                foundList.ifPresent(list -> {
                    TodoItem newItem = new TodoItem(list);
                    try {
                        newItem.setId(results.getInt("item_id"));
                        newItem.setStringContent(results.getString("content"));
                    } catch (SQLException e) {
                        e.printStackTrace(System.err);
                    }
                    lists.add(newItem);
                });
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        return lists;
    }

}
