package de.dhsn_ooe.todo.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import de.dhsn_ooe.todo.Exception.TodoItemNotFoundException;

public class TodoCheckList extends AbstractTodoList implements Collection<TodoItem> {

    private Collection<TodoItem> checkItems;
    private List<TodoCheckListListener> listeners = new ArrayList<>();

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

    public void addListener(TodoCheckListListener listener) {
        listeners.add(listener);
    }

    public void removeListener(TodoCheckListListener listener) {
        listeners.remove(listener);
    }

    public void fireEvent(TodoCheckList list) {
        for (TodoCheckListListener listener : listeners) {
            listener.listChanged(list);
        }
    }

    @Override
    public boolean add(TodoItem item) {
        Boolean success = checkItems.add(item);
        fireEvent(this);
        return success;
    }

    @Override
    public boolean addAll(Collection<? extends TodoItem> c) {
        Boolean success = checkItems.addAll(c);
        fireEvent(this);
        return success;
    }

    @Override
    public void clear() {
        checkItems.clear();
        fireEvent(this);
    }

    @Override
    public boolean contains(Object item) {
        boolean success = checkItems.contains(item);
        fireEvent(this);
        return success;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean success = checkItems.containsAll(c);
        fireEvent(this);
        return success;
    }

    @Override
    public boolean isEmpty() {
        boolean success = checkItems.isEmpty();
        fireEvent(this);
        return success;
    }

    @Override
    public Iterator<TodoItem> iterator() {
        Iterator<TodoItem> iterator = checkItems.iterator();
        fireEvent(this);
        return iterator;
    }

    @Override
    public boolean remove(Object item) {
        boolean success = checkItems.remove(item);
        fireEvent(this);
        return success;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean success = checkItems.removeAll(c);
        fireEvent(this);
        return success;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean success = checkItems.retainAll(c);
        fireEvent(this);
        return success;
    }

    @Override
    public int size() {
        int size = checkItems.size();
        fireEvent(this);
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] toArray = checkItems.toArray();
        fireEvent(this);
        return toArray;
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        T[] toArray = checkItems.toArray(arg0);
        fireEvent(this);
        return toArray;
    }
}
