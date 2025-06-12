package de.dhsn_ooe.todo.UI.Components;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * class that represents a input window for a title
 */
public class TitleInputWindow extends InputWindow{


    public TitleInputWindow(){
        super();
        cp.add(createTopBar(), BorderLayout.NORTH);
        cp.add(createInputBar(), BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    
    private JPanel createTopBar() {
        JPanel panel = new JPanel();
        Title title = new Title("Neuen Listentitel eingeben: ", 15);
        panel.setLayout(new BorderLayout());
        panel.add(title, BorderLayout.CENTER);
        return panel;
    }

    private JScrollPane createInputBar(){
        JTextArea area = new JTextArea();
        JScrollPane scrollbar = new JScrollPane(area);
        return scrollbar;
    }
}
