package de.dhsn_ooe.todo.Model;

import jakarta.persistence.Entity;

@Entity
public class TodoNote extends AbstractTodoList {

    private String htmlContent;

    public TodoNote() {}
    
    public TodoNote(String title) {
        this.setTitle(title);
    }

    public String getHtmlContent() {
        return htmlContent;
    }
    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getContent() {
        return null;
    }

}
