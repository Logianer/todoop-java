package de.dhsn_ooe.todo.UI.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.kordamp.ikonli.materialdesign2.MaterialDesignD;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignO;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;

import de.dhsn_ooe.todo.Controller.TodoListController;
import de.dhsn_ooe.todo.Controller.TodoNoteController;
import de.dhsn_ooe.todo.Events.WindowManager;
import de.dhsn_ooe.todo.Model.AbstractTodoList;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoNote;
import de.dhsn_ooe.todo.UI.Helpers.ThemeManager;
import de.dhsn_ooe.todo.UI.Views.SingleTodoList;
import de.dhsn_ooe.todo.UI.Views.SingleTodoNote;

/**
 * class that repesents a card of a list at the home menu of the app
 */
public class ListCard extends JPanel {

    /**
     * title of the card
     */
    private AbstractTodoList list;

    /**
     * constructs a card with its layout and title
     * 
     * @param list list that is displayed on the card
     */
    public ListCard(AbstractTodoList list) {
        super();
        this.list = list;
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(200, 150));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridy = 0;

        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.add(createActionBar(), c);
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        c.weighty = 1.0;
        this.add(new JLabel("PREVIEW"), c);

    }

    /**
     * creates the action bar (open, edit, delete) for a card
     * 
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
        JLabel listName = new JLabel(list.getTitle());

        // set Icon depending on List Type
        if (list.getType() == TodoCheckList.TYPE) {
            listName.setIcon(FontIcon.of(MaterialDesignF.FORMAT_LIST_CHECKS, 24,
                    ThemeManager.getDefaults().getColor("textInactiveText")));
        } else if (list.getType() == TodoNote.TYPE) {
            listName.setIcon(FontIcon.of(MaterialDesignF.FILE_DOCUMENT_OUTLINE, 24,
                    ThemeManager.getDefaults().getColor("textInactiveText")));
        }
        GridBagConstraints btnC = new GridBagConstraints();
        btnC.fill = GridBagConstraints.HORIZONTAL;
        btnC.anchor = GridBagConstraints.NORTHEAST;
        btnC.insets = new Insets(0, 4, 0, 2);
        btnC.gridy = 0;
        JButton deleteButton = new JButton();
        deleteButton.setIcon(
                FontIcon.of(MaterialDesignD.DELETE, 16, ThemeManager.getDefaults().getColor("Label.foreground")));
        deleteButton.setToolTipText("Liste löschen");
        JButton editButton = new JButton();
        editButton.setIcon(
                FontIcon.of(MaterialDesignP.PENCIL, 16, ThemeManager.getDefaults().getColor("Label.foreground")));
        editButton.setToolTipText("Titel bearbeiten");

        JButton openButton = new JButton();
        openButton.setIcon(
                FontIcon.of(MaterialDesignO.OPEN_IN_NEW, 16, ThemeManager.getDefaults().getColor("Label.foreground")));
        openButton.setToolTipText("Liste öffnen");

        bar.add(listName, labelC);

        bar.add(openButton, btnC);
        bar.add(editButton, btnC);
        bar.add(deleteButton, btnC);

        openButton.addActionListener(e -> {
            if (list.getType() == TodoCheckList.TYPE) {
                WindowManager.changeWindow(new SingleTodoList((TodoCheckList) list),
                        "Todo-App | \"" + list.getTitle() + "\"");
            } else if (list.getType() == TodoNote.TYPE) {
                WindowManager.changeWindow(new SingleTodoNote((TodoNote) list), "Todo-App | \"" + list.getTitle() + "\"");
            }
        });
        editButton.addActionListener(e -> {
            TitleInputWindow window = new TitleInputWindow("Neuen Titel für \"" + list.getTitle() + "\" eingeben",
                    "Bearbeiten", list.getTitle());
            window.addActionListener(l -> {
                list.setTitle(window.getTextContent());
                if (list.getType() == TodoCheckList.TYPE) {
                    new TodoListController().update((TodoCheckList) list, list.getId());
                } else if (list.getType() == TodoNote.TYPE) {
                    new TodoNoteController().update((TodoNote) list, list.getId());
                }
                window.dispose();
            });
        });
        deleteButton.addActionListener(e -> {
            if (list.getType() == TodoCheckList.TYPE) {
                new TodoListController().delete((TodoCheckList) list);
            } else if (list.getType() == TodoNote.TYPE) {
                new TodoNoteController().delete((TodoNote) list);
            }
        });
        return bar;
    }
}
