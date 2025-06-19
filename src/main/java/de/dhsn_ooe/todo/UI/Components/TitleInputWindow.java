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

    protected JTextField inputField;

    /**
     * constructs a title input window
     */
    public TitleInputWindow() {
        this("Name der neuen Liste eingeben:", "Hinzufügen", "");
        setButtonEnabled(false);
    }

    /**
     * constructs a title input window
     * @param message ..
     * @param action ..
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

    public String getTextContent() {
        return inputField.getText();
    }

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
