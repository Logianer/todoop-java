package de.dhsn_ooe.todo.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.dhsn_ooe.todo.Exception.ItemNotFoundException;
import de.dhsn_ooe.todo.Exception.OrphanedRelationException;
import de.dhsn_ooe.todo.Model.TodoCheckList;
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
    public TodoItem getById(int id) throws ItemNotFoundException, OrphanedRelationException {
        try {
            PreparedStatement query = SQLiteDB.conn.prepareStatement("SELECT * from todo_item where item_id = ?");
            query.setInt(1, id);
            ResultSet result = query.executeQuery();
            TodoCheckList list = new TodoCheckListController().getById(result.getInt("list_id"));
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
        try {
            PreparedStatement query = SQLiteDB.conn
                    .prepareStatement("DELETE FROM todo_item where item_id = ?");
            query.setInt(1, object.getId());
            query.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return false;
        }
    }

    @Override
    public List<TodoItem> getAll() {
        // TODO get things right here because of the relation of Items and Lists...
        List<TodoItem> lists = new ArrayList<>();
        ResultSet results;
        try {
            results = SQLiteDB.conn.createStatement().executeQuery("SELECT * from todo_item");

            List<TodoCheckList> allLists = new TodoCheckListController().getAll();

            while (results.next()) {
                int listId = results.getInt("list_id");
                Optional<TodoCheckList> foundList = allLists.stream()
                        .filter(l -> l.getId() == listId)
                        .findFirst();
                foundList.ifPresent(list -> {
                    TodoItem newList = new TodoItem(list);
                    try {
                        newList.setId(results.getInt("item_id"));
                        newList.setStringContent(results.getString("content"));
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    lists.add(newList);
                });

            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        return lists;
    }

}
