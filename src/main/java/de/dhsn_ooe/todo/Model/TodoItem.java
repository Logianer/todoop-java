package de.dhsn_ooe.todo.Model;

/**
 * class that represents a single item of the TodoList
 */
public class TodoItem {

    private Long id;
    private String stringContent;
    private boolean doneState;

    /**
     * constructor for a empty TodoItem
     */
    public TodoItem() {
    }

    /**
     * sets the content for the specified item
     * @param stringContent content of the item
     */
    public void setStringContent(String stringContent) {
        this.stringContent = stringContent;
    }

    /**
     * gets the content of the specified item
     * @return stringContent that belongs to the item
     */
    public String getStringContent() {
        return stringContent;
    }

    /**
     * sets the state for the specified item
     * @param state state of the item (true/false)
     */
    public void setState(boolean state) {
        doneState = state;
    }

    /**
     * gets the state of the item
     * @return state of the item (true/false)
     */
    public boolean getState() {
        return doneState;
    }

    /**
     * reverses the state of a given item
     * @return new state of the item
     */
    public boolean toggleState() {
        setState(!getState());
        return getState();
    }

    /**
     * gets the id od a specified item
     * @return id of the item
     */
    public Long getId() {
        return id;
    }

}
