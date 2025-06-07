package de.dhsn_ooe.todo.Model;

/**
 * Class that represents the whole Todolist
 */
public abstract class AbstractTodoList {

    private Long id;
    private String title;

    /**
     * Constructs an empty Object from the class AbstractTodoList
     */
    protected AbstractTodoList() {}

    /**
     * Constructs an Object from the class AbstractTodoList with a title
     * @param title title of the List
     */
    public AbstractTodoList(String title) {
        this.title = title;
    }

    /**
     * gets the title of the list and returns it
     * @return title of the AbstractTodoList
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets the title of the list
     * @param title title of the List
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets the id of the list
     * @return id of the AbstractTodoList
     */
    public long getId() {
        return id;
    }
}
