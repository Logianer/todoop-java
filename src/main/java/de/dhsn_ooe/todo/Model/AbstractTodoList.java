package de.dhsn_ooe.todo.Model;

public abstract class AbstractTodoList {

    private Long id;
    private String title;
    
    protected AbstractTodoList() {}
    
    public AbstractTodoList(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public long getId() {
        return id;
    }
}
