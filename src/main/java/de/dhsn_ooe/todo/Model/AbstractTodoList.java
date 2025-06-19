package de.dhsn_ooe.todo.Model;

import java.sql.Timestamp;

/**
 * Class that represents the whole Todolist
 */
public abstract class AbstractTodoList {

    private int id;
    private String title;

    private Timestamp lastUpdated;

    /**
     * gets the type of the list (todoNote or todoList) and returns it
     * 
     * @return type of the list
     */
    public abstract int getType();

    /**
     * Constructs an empty Object from the class AbstractTodoList
     */
    protected AbstractTodoList() {
    }

    /**
     * gets the title of the list and returns it
     * 
     * @return title of the AbstractTodoList
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets the title of the list
     * 
     * @param title title of the List
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets the id of the list
     * 
     * @return id of the AbstractTodoList
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id of the list
     * 
     * @param id id that will be set for the list
     */
    public void setId(int id) {
        if (this.id == 0) {
            this.id = id;
        }
    }

    public Timestamp getLastUpdated() {
        if (lastUpdated == null) {
            lastUpdated = new Timestamp(0);
        }
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
