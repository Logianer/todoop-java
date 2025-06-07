package de.dhsn_ooe.todo.Model;

/**
 * class that represents a single note in the TodoList
 */
public class TodoNote extends AbstractTodoList {

    private String htmlContent;

    /**
     * constructs an empty note
     */
    public TodoNote() {}

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
    public String getHtmlContent() {
        return htmlContent;
    }

    /**
     * sets the html content of a given note
     * @param htmlContent content that the note should have
     */
    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    /**
     * ...
     * @return ...
     */
    public String getContent() {
        return null;
    }

}
