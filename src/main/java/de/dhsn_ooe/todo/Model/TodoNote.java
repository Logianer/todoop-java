package de.dhsn_ooe.todo.Model;

/**
 * class that represents a single note in the TodoList
 */
public class TodoNote extends AbstractTodoList {

    private String noteContent;
    public static final int TYPE = 2;

    /**
     * constructs a note with a title
     * @param title title of the note
     */
    public TodoNote(String title) {
        this.setTitle(title);
    }

    /**
     * gets the html content of the specified note
     * @return content of the note 
     */
    public String getContent() {
        return noteContent;
    }

    /**
     * sets the html content of a given note
     * @param htmlContent content that the note should have
     */
    public void setContent(String htmlContent) {
        this.noteContent = htmlContent;
    }

    public int getType() {
        return TYPE;
    }
}
