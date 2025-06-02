package de.dhsn_ooe.todo.UI.Components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListCard extends JPanel {

    private String title;
    public ListCard(String title) {
        super();
        this.title = title;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridy = 0;
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        this.add(createActionBar(), c);
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1.0;
        this.add(new JLabel("Lorem ipsum dorlor sit amet."), c);
        
    }

    private JPanel createActionBar() {
        JPanel bar = new JPanel();
        bar.setLayout(new GridBagLayout());
        GridBagConstraints labelC = new GridBagConstraints();
        labelC.anchor = GridBagConstraints.NORTHWEST;
        labelC.gridy = 0;
        labelC.gridx = 0;
        labelC.weightx = 1.0;
        JLabel listName = new JLabel(title);
        GridBagConstraints btnC = new GridBagConstraints();
        btnC.fill = GridBagConstraints.HORIZONTAL;
        btnC.anchor = GridBagConstraints.NORTHEAST;
        btnC.insets = new Insets(0, 2, 0, 2);
        btnC.gridy = 0;
        JButton deleteButton = new JButton("D");
        JButton editButton = new JButton("E");
        JButton openButton = new JButton("O");

        bar.add(listName, labelC);

        bar.add(openButton, btnC);
        bar.add(editButton, btnC);
        bar.add(deleteButton, btnC);

        return bar;
    }
}
