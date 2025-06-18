package de.dhsn_ooe.todo.UI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;

import de.dhsn_ooe.todo.Controller.TodoListController;
import de.dhsn_ooe.todo.Controller.TodoNoteController;
import de.dhsn_ooe.todo.Events.WindowManager;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoNote;
import de.dhsn_ooe.todo.UI.Components.Title;
import de.dhsn_ooe.todo.UI.Components.TitleInputWindow;
import de.dhsn_ooe.todo.UI.Components.TodoInputWindow;
import de.dhsn_ooe.todo.UI.Helpers.ThemeManager;
import de.dhsn_ooe.todo.UI.Views.TodoListList;

/**
 * class that represents the dashboard of the app
 * creates the title and the layout of the dashboard
 * creates a scrollbar
 */
public class Dashboard extends JPanel {

    /**
     * constructs the dashboard with its elements
     */
    public Dashboard() {
        super();
        TodoListList list = new TodoListList();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.NORTH;
        this.add(createTopBar(), c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.weighty = 1.0;
        c.weightx = 1.0;
        c.insets = new Insets(10, 10, 10, 10);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollPane, c);

    }

    private JPanel createTopBar() {
        JPanel panel = new JPanel();
        JLabel title = new Title("Todo-App");
        JButton addButton = new JButton();
        JButton addNoteButton = new JButton();
        //panel.setLayout(new BorderLayout());
        addButton.setMargin(new Insets(5, 5, 5, 5));
        addNoteButton.setMargin(addButton.getMargin());
        addButton.setIcon(
                FontIcon.of(MaterialDesignP.PLAYLIST_PLUS, 24, ThemeManager.getDefaults().getColor("Label.foreground")));
        addNoteButton.setIcon(FontIcon.of(MaterialDesignF.FILE_PLUS, 24, ThemeManager.getDefaults().getColor("Label.foreground")));
        ThemeManager.setTransparentButton(addButton);
        ThemeManager.setTransparentButton(addNoteButton);
        addButton.addActionListener(e -> {
            TitleInputWindow window = new TitleInputWindow();
            
            window.addActionListener(l -> {
                TodoCheckList newList = new TodoCheckList(window.getTextContent());
                new TodoListController().create(newList);
                window.dispose();
            });
        });

        addNoteButton.addActionListener(e -> {
            TitleInputWindow window = new TitleInputWindow();
            
            window.addActionListener(l -> {
                TodoNote newList = new TodoNote(window.getTextContent());
                new TodoNoteController().create(newList);
                window.dispose();
            });
        });

        panel.add(title);
        panel.add(addButton);
        panel.add(addNoteButton);
        return panel;
    }
}
