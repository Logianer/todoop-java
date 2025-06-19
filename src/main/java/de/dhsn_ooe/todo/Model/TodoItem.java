package de.dhsn_ooe.todo.Model;

import java.sql.Timestamp;

/**
 * class that represents a single item of the TodoList
 */
public class TodoItem implements Comparable<TodoItem> {

    private int id;
    private String stringContent = "";
    private boolean doneState = false;
    private final TodoCheckList parentList;
    private Timestamp lastUpdated;

    /**
     * constructor for a empty TodoItem
     */
    public TodoItem(TodoCheckList list) {
        this.parentList = list;
    }

    /**
     * sets the content for the specified item
     * 
     * @param stringContent content of the item
     */
    public void setStringContent(String stringContent) {
        this.stringContent = stringContent;
    }

    /**
     * gets the content of the specified item
     * 
     * @return stringContent that belongs to the item
     */
    public String getStringContent() {
        return stringContent;
    }

    /**
     * sets the state for the specified item
     * 
     * @param state state of the item (true/false)
     */
    public void setState(boolean state) {
        doneState = state;
    }

    /**
     * gets the state of the item
     * 
     * @return state of the item (@code{true} means it is checked and finished)
     */
    public boolean getState() {
        return doneState;
    }

    /**
     * gets the id od a specified item
     * 
     * @return id of the item
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (this.id == 0) {
            this.id = id;
        }
    }

    public TodoCheckList getList() {
        return this.parentList;
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

    public int compareTo(TodoItem o2) {
        boolean stateCompare = getState() == o2.getState();
        int timeCompare = o2.getLastUpdated().compareTo(getLastUpdated());
        if (stateCompare) {
            return timeCompare * (getState() ? 1 : -1);
        }
        return getState() ? 1 : -1;
    }
}
