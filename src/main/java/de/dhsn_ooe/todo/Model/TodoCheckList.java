package de.dhsn_ooe.todo.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import de.dhsn_ooe.todo.Exception.TodoItemNotFoundException;

public class TodoCheckList extends AbstractTodoList implements Collection<TodoItem>{

    private Collection<TodoItem> checkItems;

    public TodoCheckList() {
        checkItems = new ArrayList<>();
    }
    
    public TodoCheckList(String title) {
        this();
        this.setTitle(title);
    }

    public Collection<TodoItem> getCheckItems() {
        return checkItems;
    }
    public boolean toggleCheckItemById(long id) throws TodoItemNotFoundException {
        for (TodoItem item : checkItems) {
            if (item.getId() == id) {
                return item.toggleState();
            }
        }
        throw new TodoItemNotFoundException(id);
    }

    @Override
    public boolean add(TodoItem item) {
        return checkItems.add(item);
    }

    @Override
    public boolean addAll(Collection<? extends TodoItem> c) {
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public Iterator<TodoItem> iterator() {
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

}
