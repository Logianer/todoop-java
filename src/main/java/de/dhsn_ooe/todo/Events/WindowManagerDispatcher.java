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
     * @param windowTitle title that the window has
     */
    void onWindowChanged(JComponent event, String windowTitle);

}
