package de.dhsn_ooe.todo.UI.Components;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

/**
 * class that represents a input window for a title
 */
public class TitleInputWindow extends InputWindow {

    /**
     * textfield that the user can put text into
     */
    protected JTextField inputField;

    /**
     * constructs an empty title input window with the message "Name der neuen Liste eingeben:"
     */
    public TitleInputWindow() {
        this("Name der neuen Liste eingeben:", "Hinzufügen", "");
        setButtonEnabled(false);
    }

    /**
     * constructs a title input window
     * @param message message that will be displayed in the input window
     * @param action action that is going to be performed
     * @param content content that will be put in the input field by the user
     */
    public TitleInputWindow(String message, String action, String content) {
        super("Titel bearbeiten", message, action);
        inputField = new JTextField(content);
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (inputField.getText().isBlank()) {
                    setButtonEnabled(false);
                } else {
                    setButtonEnabled(true);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    fireActionEvent(new ActionEvent(e, 0, null));
                }
            }
        });
        setInputBar(inputField);
    }

    /**
     * gets the content of the textfield 
     * @return string content of the textfield
     */
    public String getTextContent() {
        return inputField.getText();
    }

    /**
     * gets the contraints for the layout of the inputwindow
     */
    @Override
    protected GridBagConstraints getInputConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 1;
        c.weightx = 1.0;
        c.insets = new Insets(10, 10, 10, 10);
        return c;
    }
}
