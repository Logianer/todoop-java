package de.dhsn_ooe.todo.UI;

import java.awt.Container;

import javax.swing.JFrame;

import com.formdev.flatlaf.FlatLightLaf;

public class MainFrame extends JFrame {

    Container cp = this.getContentPane();

    public MainFrame() {
        super();
        FlatLightLaf.setup();

        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);
        this.add(new Dashboard());
        this.setTitle("LOL");
    }
}
