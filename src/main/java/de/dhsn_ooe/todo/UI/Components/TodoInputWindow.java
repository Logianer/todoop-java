package de.dhsn_ooe.todo.UI.Components;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * class that represents a input window for a todo item
 */
public class TodoInputWindow extends InputWindow {

    public TodoInputWindow(){
        super();
        cp.add(createTopBar(), BorderLayout.NORTH);
        cp.add(createInputBar(), BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    private JPanel createTopBar() {
        JPanel panel = new JPanel();
        Title title = new Title("Element hinzufügen: ", 15);
        panel.setLayout(new BorderLayout());
        panel.add(title, BorderLayout.CENTER);
        return panel;
    }

       private JPanel createInputBar(){
        JPanel panel = new JPanel();
        JTextArea textfield = new JTextArea();
        panel.setLayout(new BorderLayout());
        panel.add(textfield, BorderLayout.CENTER);
        return panel;
    }
    
}
