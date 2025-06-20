package de.dhsn_ooe.todo.Model;


/**
 * class the represents a CheckList as part of the TodoList
 */
public class TodoCheckList extends AbstractTodoList {

    /**
     * type of the list (for distinction between note and checklist)
     */
    public static final int TYPE = 1;

    /**
     * Constructor that creates the title for the TodoCheckList
     * @param title title of the CheckList
     */
    public TodoCheckList(String title) {
        super();
        this.setTitle(title);
    }

    /**
     * gets the type of the list
     */
    @Override
    public int getType() {
        return TYPE;
    }
}
