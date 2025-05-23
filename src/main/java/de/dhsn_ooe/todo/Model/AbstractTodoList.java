package de.dhsn_ooe.todo.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;

@Inheritance
@Entity
@Table(name = "list")
public abstract class AbstractTodoList {

    private @Id
    @GeneratedValue Long id;
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
