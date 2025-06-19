package de.dhsn_ooe.todo.UI.Components;



import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * class that represents a input window for a todo item
 */
public class TodoInputWindow extends InputWindow {

    protected JTextArea area;
    public TodoInputWindow(String action, String content) {
        super("Todo hinzufügen","Beschreibung eingeben:", action);
        area = new JTextArea(content);
        area.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (area.getText().isBlank()) {
                    setButtonEnabled(false);
                } else {
                    setButtonEnabled(true);
                }
            }
        });
        setButtonEnabled(false);
        JScrollPane pane = new JScrollPane(area);
        setInputBar(pane);
    }
    public TodoInputWindow() {
        this("Hinzufügen", "");
    }

    public String getTextContent() {
        return area.getText();
    }
}
