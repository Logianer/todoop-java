package de.dhsn_ooe.todo.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TodoList {

    private @Id
    @GeneratedValue Long id;
    private TodoListType type;
    private String title;
    private String htmlContent;

    public TodoList() {}
    
    public TodoList(TodoListType type, String title) {
        this.type = type;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public TodoListType getType() {
        return type;
    }
    public String getHtmlContent() {
        return htmlContent;
    }
    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
    public long getId() {
        return id;
    }
}
