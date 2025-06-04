package de.dhsn_ooe.todo.UI;

import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.formdev.flatlaf.FlatLightLaf;

import de.dhsn_ooe.todo.Events.WindowManager;
import de.dhsn_ooe.todo.UI.Helpers.FontManager;

public class MainFrame extends JFrame {

    Container cp = this.getContentPane();
    JComponent child;

    public MainFrame() {
        super();
        FlatLightLaf.setup();
        FontManager.setTheme(new FlatLightLaf());
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.child = new Dashboard();
        WindowManager.dispatcher = event -> {
            if (event != null) {
                cp.remove(child);
                child = event;
                cp.add(child);
            }
            revalidate();
            repaint();
        };
        cp.add(this.child);
        this.setVisible(true);
    }
}
