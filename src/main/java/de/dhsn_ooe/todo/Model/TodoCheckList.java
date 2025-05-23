package de.dhsn_ooe.todo.Model;

import java.util.ArrayList;
import java.util.Collection;

import de.dhsn_ooe.todo.Exception.TodoItemNotFoundException;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class TodoCheckList extends AbstractTodoList {


    @OneToMany(mappedBy = "id")
    private Collection<TodoItem> checkItems;

    public TodoCheckList() {
        checkItems = new ArrayList<TodoItem>();
    }
    
    public TodoCheckList(String title) {
        this();
        this.setTitle(title);
    }

    public Collection<TodoItem> getCheckItems() {
        return checkItems;
    }
    public boolean addCheckItem(TodoItem item) {
        return checkItems.add(item);
    }
    public boolean toggleCheckItemById(long id) throws TodoItemNotFoundException {
        for (TodoItem item : checkItems) {
            if (item.getId() == id) {
                return item.toggleState();
            }
        }
        throw new TodoItemNotFoundException(id);
    }

}
