package de.dhsn_ooe.todo.UI;

import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.formdev.flatlaf.FlatLightLaf;

import de.dhsn_ooe.todo.Events.WindowManager;
import de.dhsn_ooe.todo.UI.Helpers.ThemeManager;

/**
 * class that represents the main frame of the app
 */
public class MainFrame extends JFrame {

    /**
     * a container of the frame
     */
    protected Container cp = this.getContentPane();

    /**
     * a child component of the frame
     */
    protected JComponent child;

    /**
     * constructs the mainframe and refreshes when events a triggered
     */
    public MainFrame() {
        super();
        FlatLightLaf.setup();
        ThemeManager.setTheme(new FlatLightLaf());
        this.setSize(800, 600);
        this.setTitle("Todo-App | Start");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        child = new Dashboard();
        WindowManager.dispatcher = (newWindow, newTitle) -> {
            if (newWindow != null) {
                cp.remove(child);
                child = newWindow;
                cp.add(child);
            }
            if (newTitle != null) {
                this.setTitle(newTitle);
            }
            revalidate();
            repaint();
        };
        cp.add(child);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
