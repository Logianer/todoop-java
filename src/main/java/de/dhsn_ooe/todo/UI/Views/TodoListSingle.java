package de.dhsn_ooe.todo.UI.Views;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.swing.FontIcon;

import de.dhsn_ooe.todo.Controller.TodoListController;
import de.dhsn_ooe.todo.Events.WindowManager;
import de.dhsn_ooe.todo.Exception.ItemNotFoundException;
import de.dhsn_ooe.todo.Model.TodoCheckList;
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
    /**
     * constructs a list with the given elements, a top bar and the layout of the panel
     */
    public TodoListSingle(int listId) {
        super();
        this.setLayout(layout);
        try {
            this.list = new TodoListController<TodoCheckList>().getById(listId);
        } catch (ItemNotFoundException e) {
            this.list = new TodoCheckList("NOTFOUND");
        }
        TodoCheckboxList check = new TodoCheckboxList(list);
        
        this.add(createTopBar(), BorderLayout.NORTH);
        this.add(check, BorderLayout.CENTER);
    }

    /**
     * creates the top bar of the list with a title an a back button
     * @return panel with all elements
     */
    private JPanel createTopBar() {
        JPanel panel = new JPanel();
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        Title title = new Title(list.getTitle(), 15);
        panel.setLayout(new BorderLayout());
        button1.setIcon(FontIcon.of(MaterialDesignA.ARROW_LEFT, 16, ThemeManager.getDefaults().getColor("Label.foreground")));
        button1.addActionListener(e -> WindowManager.changeWindow(new Dashboard()));
        button2.setIcon(FontIcon.of(MaterialDesignP.PLUS, 16, ThemeManager.getDefaults().getColor("Label.foreground")));
        button2.addActionListener(e -> new TodoInputWindow());

        panel.add(title, BorderLayout.CENTER);
        panel.add(button1, BorderLayout.WEST);
        panel.add(button2, BorderLayout.EAST);
        return panel;
    }
}
