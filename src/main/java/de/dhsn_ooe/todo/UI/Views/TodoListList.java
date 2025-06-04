package de.dhsn_ooe.todo.UI.Views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import de.dhsn_ooe.todo.UI.Components.ListCard;

public class TodoListList extends JPanel {

    protected GridLayout layout = new GridLayout(0, 3, 10,10);
    public TodoListList() {
        super();
        this.setLayout(layout);

        for (int i = 0; i < 10; i++) {
            ListCard card = new ListCard("Liste " + i);
            // defaultC.gridx = (i % 3);
            card.setPreferredSize(new Dimension(200, 150));
            this.add(card);
        }

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panelResized();
            }
        });
        this.panelResized();
    }
    private void panelResized() {
        int columnCount = Math.min(4, Math.max(1, this.getWidth() / 250));
        if(columnCount != layout.getColumns()) {
            layout.setColumns(columnCount);
            this.revalidate();
            this.repaint();
        }
    }
}
