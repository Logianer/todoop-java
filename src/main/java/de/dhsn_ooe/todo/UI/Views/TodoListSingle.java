package de.dhsn_ooe.todo.UI.Views;

import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;

import de.dhsn_ooe.todo.Controller.TodoListController;
import de.dhsn_ooe.todo.Events.WindowManager;
import de.dhsn_ooe.todo.Exception.ItemNotFoundException;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.UI.Components.SingleTodoItem;
import de.dhsn_ooe.todo.UI.Components.Title;
import de.dhsn_ooe.todo.UI.Components.TodoCheckboxList;
import de.dhsn_ooe.todo.UI.Components.TodoInputWindow;
import de.dhsn_ooe.todo.UI.Dashboard;
import de.dhsn_ooe.todo.UI.Helpers.ThemeManager;

/**
 * class that represents a single list that can be opened from the main menu
 */
public class TodoListSingle extends JPanel {

    /**
     * layout of the list
     */
    protected BorderLayout layout = new BorderLayout();
    private TodoCheckList list;
    protected TodoCheckboxList listDisplay;

    /**
     * constructs a list with the given elements, a top bar and the layout of the
     * panel
     */
    public TodoListSingle(int listId) {
        super();
        this.setLayout(layout);
        try {
            this.list = new TodoListController().getById(listId);
        } catch (ItemNotFoundException e) {
            this.list = new TodoCheckList("NOTFOUND");
        }
        this.listDisplay = new TodoCheckboxList(list);
        JScrollPane scrollableList = new JScrollPane(listDisplay);
        scrollableList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableList.getVerticalScrollBar().setUnitIncrement(16);
        scrollableList.setBorder(BorderFactory.createEmptyBorder());
        this.add(createTopBar(), BorderLayout.NORTH);
        this.add(scrollableList, BorderLayout.CENTER);
    }

    /**
     * creates the top bar of the list with a title and a back button
     * 
     * @return panel with all elements
     */
    private JPanel createTopBar() {
        JPanel panel = new JPanel();
        JButton backButton = new JButton();
        JButton addButton = new JButton();
        Title title = new Title(list.getTitle(), 15);
        panel.setLayout(new BorderLayout());
        backButton.setIcon(
                FontIcon.of(MaterialDesignA.ARROW_LEFT, 24, ThemeManager.getDefaults().getColor("Label.foreground")));
        ThemeManager.transparentButton(backButton);
        backButton.addActionListener(e -> {
            listDisplay.onBeforeDestroy();
            WindowManager.changeWindow(new Dashboard());
        });
        addButton.setIcon(
                FontIcon.of(MaterialDesignP.PLUS, 24, ThemeManager.getDefaults().getColor("Label.foreground")));
        ThemeManager.transparentButton(addButton);

        addButton.addActionListener(e -> new TodoInputWindow());

        panel.add(title, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.WEST);
        panel.add(addButton, BorderLayout.EAST);
        return panel;
    }
}
