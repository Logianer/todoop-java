package de.dhsn_ooe.todo.UI.Views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TodoListList extends JPanel {

    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints defaultC = new GridBagConstraints();
    public TodoListList() {
        super();
        this.setLayout(layout);
        defaultC.insets = new Insets(5, 5, 5, 5);
        defaultC.anchor = GridBagConstraints.NORTHWEST;
        defaultC.gridx = 1;
        defaultC.weightx = 0.5;
        defaultC.weighty = 0.1;
        defaultC.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < 10; i++) {
            JButton button = new JButton("Button " + i);
            defaultC.gridx = (i % 3);
            this.add(button, defaultC);
        }
    }
}
