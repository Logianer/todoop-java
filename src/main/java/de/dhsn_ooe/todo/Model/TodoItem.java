package de.dhsn_ooe.todo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TodoItem {

    private @Id
    @GeneratedValue Long id;
    private String stringContent;
    private boolean doneState;
    public TodoItem() {}
    
    public void setStringContent(String stringContent) {
        this.stringContent = stringContent;
    }
    public String getStringContent() {
        return stringContent;
    }
    public void setState(boolean state) {
        doneState = state;
    }
    public boolean getState() {
        return doneState;
    }
    public boolean toggleState() {
        setState(!getState());
        return getState();
    }
    public Long getId() {
        return id;
    }
}
