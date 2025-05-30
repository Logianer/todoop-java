package de.dhsn_ooe.todo.UI.Components;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Title extends JLabel {
    public Title(String text) {
        super();
        Font labelFont = this.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(labelFont.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        attributes.put(TextAttribute.SIZE, 24);
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setFont(labelFont.deriveFont(attributes));
        this.setText(text);
    }
}
