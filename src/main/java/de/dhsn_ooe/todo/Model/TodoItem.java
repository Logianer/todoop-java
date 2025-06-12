package de.dhsn_ooe.todo.Model;

/**
 * class that represents a single item of the TodoList
 */
public class TodoItem {

    private int id;
    private String stringContent;
    private boolean doneState;
    private final TodoCheckList parentList;

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

}
