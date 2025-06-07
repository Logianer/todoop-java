package de.dhsn_ooe.todo.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import de.dhsn_ooe.todo.Events.TodoCheckListListener;
import de.dhsn_ooe.todo.Exception.TodoItemNotFoundException;


/**
 * class the represents a CheckList as part of the TodoList
 */
public class TodoCheckList extends AbstractTodoList implements Collection<TodoItem> {

    private Collection<TodoItem> checkItems;
    private List<TodoCheckListListener> listeners = new ArrayList<>();

    /**
     * Constructor that creates a TodoCheckList with an empty list of checkItems
     */
    public TodoCheckList() {
        checkItems = new ArrayList<>();
    }

    /**
     * Constructor that creates the title for the TodoCheckList
     * @param title title of the CheckList
     */
    public TodoCheckList(String title) {
        this();
        this.setTitle(title);
    }

    /**
    * gets all the checkItems of a TodoCheckList
    * @return checkItems of the list
    */
    public Collection<TodoItem> getCheckItems() {
        return checkItems;
    }


    /**
     * changes the state of an item of the list 
     * @param id id of the item that should be changed
     * @return new state of the item
     * @throws TodoItemNotFoundException exeption for no item for the given id
     */
    public boolean toggleCheckItemById(long id) throws TodoItemNotFoundException {
        for (TodoItem item : checkItems) {
            if (item.getId() == id) {
                return item.toggleState();
            }
        }
        throw new TodoItemNotFoundException(id);
    }

    /**
     * adds listeners to the list
     * @param listener listener for the items of the check list
     */
    public void addListener(TodoCheckListListener listener) {
        listeners.add(listener);
    }

    /**
     * removes listeners from the list
     * @param listener listener for the items of the check list
     */
    public void removeListener(TodoCheckListListener listener) {
        listeners.remove(listener);
    }

    /**
     * notifys listeners if changes took place
     * @param list list the event should take effect on 
     */
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
