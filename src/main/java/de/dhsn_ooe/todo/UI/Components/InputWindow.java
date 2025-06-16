package de.dhsn_ooe.todo.UI.Components;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

//title, text, backbutton
/**
 * class that represents a general input window
 */
public class InputWindow extends JFrame {

    protected BorderLayout layout = new BorderLayout();
    protected final Container cp = this.getContentPane();
    protected String message;
    protected JComponent input;
    protected String action;
    private final KeyAdapter keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            onPressed(e);
        }
    };

    public InputWindow(String title, String message, JComponent input, String action) {
        this(title, message, action);
        setInputBar(input);
    }

    public InputWindow(String title, String message, String action) {
        super();
        this.action = action;
        this.message = message;
        this.setTitle(title);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);
        cp.add(createTopBar(), c);

        c = new GridBagConstraints();
        c.gridy = 2;
        c.weighty = 1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.SOUTHEAST;
        c.insets = new Insets(10, 10, 15, 15);
        JButton btn = new JButton(this.action);
        cp.add(btn, c);
        btn.addKeyListener(keyListener);
        this.setMinimumSize(new Dimension(200, 200));
        this.setSize(new Dimension(250, 180));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JComponent createTopBar() {
        JLabel title = new JLabel(this.message, SwingConstants.LEFT);
        return title;
    }

    public void setInputBar(JComponent input) {
        if (this.input != null) {
            return;
        }
        this.input = input;
        this.input.addKeyListener(keyListener);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.5;
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.BOTH;
        cp.add(this.input, c);
    }

    private void onPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.dispose();
        }
    }
}
