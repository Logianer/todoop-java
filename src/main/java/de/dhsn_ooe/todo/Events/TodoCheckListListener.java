package de.dhsn_ooe.todo.Events;

import java.util.EventListener;

import de.dhsn_ooe.todo.Model.TodoCheckList;

/**
 * listener for the checklist that acts if the list has changed
 */
public interface TodoCheckListListener extends EventListener {
    /**
     * shows if the list has changed
     * @param list list that is monitored
     */
    void listChanged(TodoCheckList list);
}
