package de.dhsn_ooe.todo.Events;

import javax.swing.JComponent;

/**
 * class that manages the window of the application
 */
public class WindowManager {

    /**
     * dispatcher of the windowmanager
     */
    public static WindowManagerDispatcher dispatcher;

    /**
     * changes the window on the demand
     * @param window window that should be changed
     */
    public static void changeWindow(JComponent window) {
        dispatcher.onWindowChanged(window);
    }

    /**
     * repaints the window on demand
     */
    public static void repaintWindow() {
        dispatcher.onWindowChanged(null);
    }
}
