package de.dhsn_ooe.todo.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.dhsn_ooe.todo.Events.TodoControllerListener;
import de.dhsn_ooe.todo.Exception.ItemNotFoundException;
import de.dhsn_ooe.todo.Model.TodoNote;

/**
 * class that controlls all the todo notes
 */
public class TodoNoteController
        implements CRUDController<TodoNote> {

    /**
     * listeners for the todonotecontroller
     */
    private static List<TodoControllerListener<TodoNoteController>> listeners = new ArrayList<>();

    /**
     * name of the table the notes are part of
     */
    private static final String TABLE_NAME = "todo_list";

    /**
     * creates a new todo note and inserts it into the specified table
     * @param object note that should be created
     * @return the id of the created object, 
     * an error if the object couldn't be inserted into the table
     */
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
            e.printStackTrace(System.err);
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
     */
    protected void fireEvent() {
        for (TodoControllerListener<TodoNoteController> listener : listeners) {
            listener.listChanged(this);
        }
    }

    /**
     * gets a todonote by it's id, creates a new note with the data found and returns the note
     * querys the database to look for the note in the todo_list table
     * shows an error if the note wasn't found
     * @param id id of the note
     * @return the note
     */
    @Override
    public TodoNote getById(int id) throws ItemNotFoundException {
        try {
            PreparedStatement query = SQLiteDB.conn.prepareStatement("SELECT * from todo_list where list_id = ?");
            query.setInt(1, id);
            ResultSet results = query.executeQuery();
            TodoNote list = new TodoNote(results.getString("title"));
            list.setId(results.getInt("list_id"));
            list.setLastUpdated(results.getTimestamp("updated_at"));
            return list;
        } catch (SQLException e) {
            throw new ItemNotFoundException(id);
        }
    }

    /**
     * updates an existing todonote 
     * 
     * @param object the note that should be updated
     * @param id id of the note 
     * @return true if the update was successfull, false if not
     */
    @Override
    public boolean update(TodoNote object, int id) {
        try {
            PreparedStatement query = SQLiteDB.conn
                    .prepareStatement(
                            "UPDATE todo_list set title = ?, updated_at = CURRENT_TIMESTAMP where list_id = ?");
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

    /**
     * deletes a todonote from the table todo_list
     *  @param object the note that should be deleted
     * @return true if the item was deleted successfully
     * false if the item couldn't be deleted
     */
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

    /**
     * gets all todonotes from the table 
     * @return list of all the objects (notes) in the table (returns an error if the dbquery failed)
     */
    @Override
    public List<TodoNote> getAll() {
        List<TodoNote> lists = new ArrayList<>();
        try {
            ResultSet results = GenericDBQuery.selectWhereEqualsRecords(TABLE_NAME, "list_type", TodoNote.TYPE);
            while (results.next()) {
                TodoNote newList = new TodoNote(results.getString("title"));
                newList.setId(results.getInt("list_id"));
                newList.setContent(getNoteRecord(newList));
                newList.setLastUpdated(results.getTimestamp("updated_at"));
                lists.add(newList);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        return lists;

    }

    /**
     * gets the content of a todonote
     * @param note note that the content should be got from
     * @return content of the note
     */
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
