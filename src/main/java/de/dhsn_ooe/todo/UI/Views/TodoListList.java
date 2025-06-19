package de.dhsn_ooe.todo.UI.Views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dhsn_ooe.todo.Controller.TodoListController;
import de.dhsn_ooe.todo.Controller.TodoNoteController;
import de.dhsn_ooe.todo.Events.TodoControllerListener;
import de.dhsn_ooe.todo.Model.AbstractTodoList;
import de.dhsn_ooe.todo.UI.Components.ListCard;

/**
 * class that represents all of the TodoLists in the home menu
 */
public class TodoListList extends JPanel {

    /**
     * layout of the list
     */
    protected GridLayout layout = new GridLayout(0, 3, 10, 10);

    /**
     * listener for the todolists that acts on triggered events
     */
    private TodoControllerListener<TodoListController> listListener = (TodoListController list) -> {
        repaintLists();
    };

    /**
     * listener for the todonotes that acts if events are triggered
     */
    private TodoControllerListener<TodoNoteController> noteListener = (TodoNoteController list) -> {
        repaintLists();
    };

    /**
     * contstructs an empty list with the desired layout
     * adds listeners to the components to trigger the right events on incoming
     * changes
     */
    public TodoListList() {
        super();
        new TodoListController().addListener(listListener);
        new TodoNoteController().addListener(noteListener);

        paintLists();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panelResized();
            }
        });
        this.panelResized();
    }

    /**
     * paints all the todolists and todonotes that are stored in the database on
     * listcards onto the dashboard
     */
    private void paintLists() {
        List<AbstractTodoList> lists = new ArrayList<>();
        lists.addAll(new TodoListController().getAll());
        lists.addAll(new TodoNoteController().getAll());

        if (lists.isEmpty()) {
            this.setLayout(new BorderLayout());
            JLabel hintText = new JLabel(
                    "<html>Noch keine Todo's vorhanden. <br>Füge eine Liste mit den Buttons oben rechts hinzu.</html>");
            hintText.setHorizontalAlignment(JLabel.CENTER);
            this.add(hintText);
        } else {
            this.setLayout(layout);
        }
        Collections.sort(lists, Collections.reverseOrder(new Comparator<AbstractTodoList>() {
            @Override
            public int compare(AbstractTodoList o1, AbstractTodoList o2) {
                int timeCompare = o1.getLastUpdated().compareTo(o2.getLastUpdated());
                int idCompare = Integer.compare(o1.getId(), o2.getId());
                if (timeCompare == 0) {
                    return idCompare;
                }
                return timeCompare;
            }
        }));
        for (AbstractTodoList list : lists) {
            ListCard card = new ListCard(list);
            this.add(card);
        }
    }

    /**
     * repaints the lists and notes on the dashboard (if changes occured e.g. a list
     * was added)
     */
    private void repaintLists() {
        this.removeAll();
        this.paintLists();
        this.panelResized();
        this.revalidate();
        this.repaint();
    }

    /**
     * changes the size of the panel if the size of the window is changed
     */
    private void panelResized() {
        int columnCount = Math.min(4, Math.max(1, this.getWidth() / 250));
        if (columnCount != layout.getColumns()) {
            layout.setColumns(columnCount);
            this.revalidate();
            this.repaint();
        }
    }

    /**
     * removes the listeners before the object is destroyed
     */
    public void onBeforeDestroy() {
        new TodoListController().removeListener(listListener);
        new TodoNoteController().removeListener(noteListener);
    }
}
