package de.dhsn_ooe.todo.UI.Components;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import de.dhsn_ooe.todo.Main;

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
    protected JButton actionButton;
    private List<ActionListener> listeners = new ArrayList<>();

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
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        actionButton = new JButton(this.action);
        cp.add(actionButton, c);
        actionButton.addKeyListener(keyListener);
        actionButton.addActionListener(l -> fireActionEvent(l));
        this.setSize(250, 180);
        this.setMinimumSize(new Dimension(200, 200));
        this.setLocationRelativeTo(Main.mainFrame);
        this.setVisible(true);
    }

    private JComponent createTopBar() {
        JLabel title = new JLabel(this.message, SwingConstants.LEFT);
        return title;
    }

    public final void setInputBar(JComponent input) {
        if (this.input != null) {
            return;
        }
        this.input = input;
        this.input.addKeyListener(keyListener);
        cp.add(this.input, this.getInputConstraints());
    }

    private void onPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.dispose();
        }
    }

    protected GridBagConstraints getInputConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.5;
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.BOTH;
        return c;
    }

    public void addActionListener(ActionListener l) {
        listeners.add(l);
    }

    public void removeActionListener(ActionListener l) {
        listeners.remove(l);
    }

    protected void fireActionEvent(ActionEvent e) {
        actionButton.setEnabled(false);
        for (ActionListener listener : listeners) {
            listener.actionPerformed(e);

        }
        actionButton.setEnabled(true);
    }

    protected void setButtonEnabled(boolean state) {
        if (actionButton.isEnabled() == state)
            return;
        actionButton.setEnabled(state);
    }
}
