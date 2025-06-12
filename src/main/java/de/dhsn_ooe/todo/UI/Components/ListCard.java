package de.dhsn_ooe.todo.UI.Components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.kordamp.ikonli.materialdesign2.MaterialDesignD;
import org.kordamp.ikonli.materialdesign2.MaterialDesignO;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;

import de.dhsn_ooe.todo.Controller.TodoCheckListController;
import de.dhsn_ooe.todo.Events.WindowManager;
import de.dhsn_ooe.todo.Exception.ItemNotFoundException;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.UI.Helpers.ThemeManager;
import de.dhsn_ooe.todo.UI.Views.TodoListSingle;


/**
 * class that repesents a card of a list at the home menu of the app 
 */
public class ListCard extends JPanel {

    /**
     * title of the card
     */
    private String title;
    private int listId;

    /**
     * constructs a card with its layout and title
     * @param title title of the card
     */
    public ListCard(TodoCheckList list) {
        super();
        this.title = list.getTitle();
        this.listId = list.getId();
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

    /**
     * creates the action bar (open, edit, delete) for a card
     * @return created bar 
     */
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
        btnC.insets = new Insets(0, 4, 0, 2);
        btnC.gridy = 0;
        JButton deleteButton = new JButton();
        deleteButton.setIcon(FontIcon.of(MaterialDesignD.DELETE, 16, ThemeManager.getDefaults().getColor("Label.foreground")));
        deleteButton.setToolTipText("Liste löschen");
        JButton editButton = new JButton();
        editButton.setIcon(FontIcon.of(MaterialDesignP.PENCIL,16,ThemeManager.getDefaults().getColor("Label.foreground")));
        editButton.setToolTipText("Titel bearbeiten");

        JButton openButton = new JButton();
        openButton.setIcon(FontIcon.of(MaterialDesignO.OPEN_IN_NEW, 16,ThemeManager.getDefaults().getColor("Label.foreground")));
        openButton.setToolTipText("Liste öffnen");


        bar.add(listName, labelC);

        bar.add(openButton, btnC);
        bar.add(editButton, btnC);
        bar.add(deleteButton, btnC);

        openButton.addActionListener(e -> WindowManager.changeWindow(new TodoListSingle(this.listId)));
        editButton.addActionListener(e -> {
            try {
                new TitleInputWindow(new TodoCheckListController().getById(this.listId));
            } catch (ItemNotFoundException err) {
                err.printStackTrace(System.err);
            }
        });
        deleteButton.addActionListener(e -> WindowManager.repaintWindow());
        return bar;
    }
}
