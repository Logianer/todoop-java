package de.dhsn_ooe.todo.Events;

import javax.swing.JComponent;

public class WindowManager {
    public static WindowManagerDispatcher dispatcher;

    public static void changeWindow(JComponent window) {
        dispatcher.onWindowChanged(window);
    }
    public static void repaintWindow() {
        dispatcher.onWindowChanged(null);
    }
}
