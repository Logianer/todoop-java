package de.dhsn_ooe.todo.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.dhsn_ooe.todo.UI.Components.Title;
import de.dhsn_ooe.todo.UI.Views.TodoListList;

public class Dashboard extends JPanel {

    public Dashboard() {
        super();
        JLabel title = new Title("Todo-App");
        TodoListList list = new TodoListList();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.NORTH;
        this.add(title, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.weighty = 1.0;
        c.weightx = 1.0;
        c.insets = new Insets(10, 10,10,10);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollPane, c);

    }
}
