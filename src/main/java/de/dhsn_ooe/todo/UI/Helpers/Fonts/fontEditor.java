package de.dhsn_ooe.todo.UI.Helpers.Fonts;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;

public class fontEditor {
    Font defaultCheckboxFont;

    public void getDefaultCheckboxFont() {
        JCheckBox checkBox = new JCheckBox("Test");
        defaultCheckboxFont = checkBox.getFont();
    }

    public Font changeDefaultCheckboxFont(TextAttribute attribute1, Boolean attribute2){
        Map<TextAttribute, Object> attributes = new HashMap<>(defaultCheckboxFont.getAttributes());
        attributes.put(attribute1, attribute2);
        Font newCheckboxFont = new Font(attributes);
        return newCheckboxFont;
    }
}
