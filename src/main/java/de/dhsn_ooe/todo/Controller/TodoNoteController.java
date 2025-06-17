package de.dhsn_ooe.todo.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dhsn_ooe.todo.Events.TodoControllerListener;
import de.dhsn_ooe.todo.Exception.ItemNotFoundException;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoNote;

public class TodoNoteController
        implements CRUDController<TodoNote> {

    private static List<TodoControllerListener<TodoNoteController>> listeners = new ArrayList<>();

    private static final String TABLE_NAME = "todo_list";

    @Override
    public int create(TodoNote object) {
        int id;
        try {
            id = GenericDBQuery.insertRecord(TABLE_NAME,
                    Map.of("title", object.getTitle(), "list_type", object.getType()));
            GenericDBQuery.insertRecord("todo_note", Map.of("list_id", id));
            object.setId(id);
            this.fireEvent();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * adds listeners to the list
     * 
     * @param listener listener for the items of the check list
     */
    public void addListener(TodoControllerListener<TodoNoteController> listener) {
        listeners.add(listener);
    }

    /**
     * removes listeners from the list
     * 
     * @param listener listener for the items of the check list
     */
    public void removeListener(TodoControllerListener<TodoNoteController> listener) {
        listeners.remove(listener);
    }

    /**
     * notifies listeners if changes took place
     * 
     */
    protected void fireEvent() {
        for (TodoControllerListener<TodoNoteController> listener : listeners) {
            listener.listChanged(this);
        }
    }

    @Override
    public TodoNote getById(int id) throws ItemNotFoundException {
        try {
            PreparedStatement query = SQLiteDB.conn.prepareStatement("SELECT * from todo_list where list_id = ?");
            query.setInt(1, id);
            ResultSet results = query.executeQuery();
            TodoNote list = new TodoNote(results.getString("title"));
            list.setId(results.getInt("list_id"));
            return list;
        } catch (SQLException e) {
            throw new ItemNotFoundException(id);
        }
    }

    @Override
    public boolean update(TodoNote object, int id) {
        try {
            PreparedStatement query = SQLiteDB.conn
                    .prepareStatement(
                            "UPDATE todo_list set title = ? where list_id = ?");
            PreparedStatement query2 = SQLiteDB.conn
                    .prepareStatement("UPDATE todo_note set content = ? where list_id = ?");
            query.setString(1, object.getTitle());
            query.setInt(2, id);
            query2.setString(1, object.getContent());
            query2.setInt(2, id);
            query.executeUpdate();
            query2.executeUpdate();
            this.fireEvent();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return false;
    }

    @Override
    public boolean delete(TodoNote object) {
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
    public List<TodoNote> getAll() {
        List<TodoNote> lists = new ArrayList<>();
        try {
            ResultSet results = GenericDBQuery.selectWhereEqualsRecords(TABLE_NAME, "list_type", TodoNote.TYPE);
            while (results.next()) {
                TodoNote newList = new TodoNote(results.getString("title"));
                newList.setId(results.getInt("list_id"));
                newList.setContent(getNoteRecord(newList));
                lists.add(newList);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return lists;

    }

    protected String getNoteRecord(TodoNote note) {
        try {
            ResultSet results = GenericDBQuery.selectWhereEqualsRecords("todo_note", "list_id", note.getId());
            return results.getString("content");
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

}
