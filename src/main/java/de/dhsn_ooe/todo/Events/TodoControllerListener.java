package de.dhsn_ooe.todo.Events;

import java.util.EventListener;

/**
 * listener for the checklist that acts if the list has changed
 */
public interface TodoControllerListener<T> extends EventListener {
    /**
     * shows if the list has changed
     * @param list list that is monitored
     */
    void listChanged(T list);
}
