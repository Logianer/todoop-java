package de.dhsn_ooe.todo.UI.Components;



import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * class that represents a input window for a single todo item
 */
public class TodoInputWindow extends InputWindow {

    /**
     * variable that stands for the field displayed to the user for input
     */
    protected JTextArea area;

    /**
     * constructs an input window for a todo item
     * @param action action that will be performed (edit, add...)
     * @param content content that will be put into the textfield by the user
     */
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
    
    /**
     * constructs an empty window with no content and sets the action text
     */
    public TodoInputWindow() {
        this("Hinzufügen", "");
    }

    /**
     * gets the text that is put into the textfield
     * @return text of the field
     */
    public String getTextContent() {
        return area.getText();
    }
}
