package de.dhsn_ooe.todo.Events;

import java.util.EventListener;

import javax.swing.JComponent;

public interface WindowManagerDispatcher extends EventListener {
    void onWindowChanged(JComponent event);
}
