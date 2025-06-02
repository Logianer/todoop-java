package de.dhsn_ooe.todo.UI.Helpers.Fonts;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import com.formdev.flatlaf.FlatLaf;

public class FontManager {
    public static Font getDefaultFont(FlatLaf theme) {
        return theme.getDefaults().getFont("Label.font");
    }

    public static Font modifyDefaultFont(FlatLaf theme, Map<TextAttribute, Object> newAttributes) {
        Map<TextAttribute, Object> attributes = new HashMap<>(getDefaultFont(theme).getAttributes());
        attributes.putAll(newAttributes);
        return getDefaultFont(theme).deriveFont(attributes);
    }
}
