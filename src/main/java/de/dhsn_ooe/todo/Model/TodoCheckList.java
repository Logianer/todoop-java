package de.dhsn_ooe.todo.Model;


/**
 * class the represents a CheckList as part of the TodoList
 */
public class TodoCheckList extends AbstractTodoList {

    /**
     * Constructor that creates the title for the TodoCheckList
     * @param title title of the CheckList
     */
    public TodoCheckList(String title) {
        super();
        this.setTitle(title);
    }

    public int getType() {
        return 1;
    }
}
