package de.dhsn_ooe.todo.Events;

import java.util.EventListener;

import javax.swing.JComponent;

/**
 * listener for the window 
 */
public interface WindowManagerDispatcher extends EventListener {

    /**
     * acts if the window has changed
     * @param event event that occured
     */
    void onWindowChanged(JComponent event);
}
