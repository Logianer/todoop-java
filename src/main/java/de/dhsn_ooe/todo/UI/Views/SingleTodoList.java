package de.dhsn_ooe.todo.UI.Views;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;

import de.dhsn_ooe.todo.Controller.TodoItemController;
import de.dhsn_ooe.todo.Controller.TodoListController;
import de.dhsn_ooe.todo.Events.WindowManager;
import de.dhsn_ooe.todo.Exception.ItemNotFoundException;
import de.dhsn_ooe.todo.Model.TodoCheckList;
import de.dhsn_ooe.todo.Model.TodoItem;
import de.dhsn_ooe.todo.UI.Components.SingleTodoItem;
import de.dhsn_ooe.todo.UI.Components.Title;
import de.dhsn_ooe.todo.UI.Components.TodoCheckboxList;
import de.dhsn_ooe.todo.UI.Components.TodoInputWindow;
import de.dhsn_ooe.todo.UI.Dashboard;
import de.dhsn_ooe.todo.UI.Helpers.ThemeManager;

/**
 * class that represents a single list that can be opened from the main menu
 */
public class SingleTodoList extends JPanel {

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
    public SingleTodoList(TodoCheckList list) {
        super();
        this.setLayout(layout);
        this.list = list;
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
        backButton.setMargin(new Insets(5, 5, 5, 5));
        backButton.setToolTipText("Zurück");
        addButton.setMargin(new Insets(5, 5, 5, 5));
        addButton.setToolTipText("Item hinzufügen");
        backButton.setIcon(
                FontIcon.of(MaterialDesignA.ARROW_LEFT, 24, ThemeManager.getDefaults().getColor("Label.foreground")));
        ThemeManager.setTransparentButton(backButton);
        backButton.addActionListener(e -> {
            listDisplay.onBeforeDestroy();
            WindowManager.changeWindow(new Dashboard(), "Todo-App | Start");
        });
        addButton.setIcon(
                FontIcon.of(MaterialDesignP.PLUS, 24, ThemeManager.getDefaults().getColor("Label.foreground")));
        ThemeManager.setTransparentButton(addButton);

        addButton.addActionListener(e -> {
            TodoInputWindow window = new TodoInputWindow();
            window.addActionListener(l -> {
                TodoItem item = new TodoItem(list);
                item.setStringContent(window.getTextContent());
                new TodoItemController().create(item);
                window.dispose();
            });
        });

        panel.add(title, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.WEST);
        panel.add(addButton, BorderLayout.EAST);
        return panel;
    }
}
