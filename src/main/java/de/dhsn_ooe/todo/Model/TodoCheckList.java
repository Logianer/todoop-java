package de.dhsn_ooe.todo.Model;


/**
 * class the represents a CheckList as part of the TodoList
 */
public class TodoCheckList extends AbstractTodoList {

    /**
     * Constructor that creates a TodoCheckList with an empty list of checkItems
     */
    public TodoCheckList() {
    }

    /**
     * Constructor that creates the title for the TodoCheckList
     * @param title title of the CheckList
     */
    public TodoCheckList(String title) {
        this();
        this.setTitle(title);
    }
}
