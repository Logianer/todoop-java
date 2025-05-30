package de.dhsn_ooe.todo.Model;

public class TodoItem {

    private Long id;
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
