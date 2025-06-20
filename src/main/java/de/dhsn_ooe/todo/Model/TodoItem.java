package de.dhsn_ooe.todo.Model;

import java.sql.Timestamp;

/**
 * class that represents a single item of the TodoList
 */
public class TodoItem implements Comparable<TodoItem> {

    /**
     * id of the todoitem (for identification purposes)
     */
    private int id;

    /**
     * content of the item 
     */
    private String stringContent = "";

    /**
     * state of the item (checked or not)
     */
    private boolean doneState = false;

    /**
     * list the the item belongs to
     */
    private final TodoCheckList parentList;

    /**
     * time that the item was last updated at (for sorting purposes)
     */
    private Timestamp lastUpdated;

    /**
     * constructor for an empty TodoItem
     * @param list parentlist of the item
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
     * gets the id of a specified item
     * 
     * @return id of the item
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id of a specified item
     * @param id id that the item should have
     */
    public void setId(int id) {
        if (this.id == 0) {
            this.id = id;
        }
    }

    /**
     * get the list that the item belongs to
     * @return list the item belongs to
     */
    public TodoCheckList getList() {
        return this.parentList;
    }

    /**
     * gets the time that the item was last uptdated
     * if it hasn't been updated the time defaults to zero
     * which is January 1st 1970 00:00:00 GMT
     * @return time the item was last updated at
     */
    public Timestamp getLastUpdated() {
        if (lastUpdated == null) {
            lastUpdated = new Timestamp(0);
        }
        return lastUpdated;
    }

    /**
     * sets the time that the item was updated last
     * @param lastUpdated time the update took place
     */
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * compares a todoitem with an other one
     * if the state of the two items is the same the timestamp will be compared
     * 
     * @param o2 item that the given item should be compared to
     */
    public int compareTo(TodoItem o2) {
        boolean stateCompare = getState() == o2.getState();
        int timeCompare = o2.getLastUpdated().compareTo(getLastUpdated());
        if (stateCompare) {
            return timeCompare * (getState() ? 1 : -1);
        }
        return getState() ? 1 : -1;
    }
}
