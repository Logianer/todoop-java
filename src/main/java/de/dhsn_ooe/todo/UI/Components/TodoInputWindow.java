package de.dhsn_ooe.todo.UI.Components;



import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * class that represents a input window for a todo item
 */
public class TodoInputWindow extends InputWindow {

    public TodoInputWindow() {
        super("Todo hinzufügen","Beschreibung eingeben:", "Hinzufügen");
        JTextArea area = new JTextArea();
        JScrollPane pane = new JScrollPane(area);
        setInputBar(pane);
    }
}
