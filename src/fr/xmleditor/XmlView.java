package fr.xmleditor;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.text.PlainView;
import javax.swing.text.Segment;
import javax.swing.text.Utilities;

public class XmlView extends PlainView {

    private static HashMap<Pattern, Color> patternColors;
    private static String GENERIC_XML_NAME = "[A-Za-z]+[A-Za-z0-9\\-_]*(:[A-Za-z]+[A-Za-z0-9\\-_]+)?";
    private static String TAG_PATTERN = "(</?" + GENERIC_XML_NAME + ")";
    private static String TAG_END_PATTERN = "(>|/>)";
    private static String TAG_ATTRIBUTE_PATTERN = "(" + GENERIC_XML_NAME + ")\\w*\\=";
    private static String TAG_ATTRIBUTE_VALUE = "\\w*\\=\\w*(\"[^\"]*\")";
    private static String TAG_COMMENT = "(<\\!--[\\w ]*-->)";
    private static String TAG_CDATA = "(<\\!\\[CDATA\\[.*\\]\\]>)";

    static {
        // NOTE: the order is important!
        patternColors = new LinkedHashMap<Pattern, Color>();
        patternColors.put(Pattern.compile(TAG_PATTERN), new Color(174, 141, 244));
        patternColors.put(Pattern.compile(TAG_CDATA), Color.lightGray);
        patternColors.put(Pattern.compile(TAG_ATTRIBUTE_PATTERN), new Color(224, 212, 105));
        patternColors.put(Pattern.compile(TAG_END_PATTERN), new Color(174, 141, 244));
        patternColors.put(Pattern.compile(TAG_ATTRIBUTE_VALUE), new Color(239, 93, 93));
        patternColors.put(Pattern.compile(TAG_COMMENT), new Color(239, 93, 93));
    }

    public XmlView(Element element) {

        super(element);

        // Set tabsize to 4 (instead of the default 8)
        getDocument().putProperty(PlainDocument.tabSizeAttribute, 4);
    }

    @Override
    protected int drawUnselectedText(Graphics graphics, int x, int y, int p0,
            int p1) throws BadLocationException {

        Document doc = getDocument();
        String text = doc.getText(p0, p1 - p0);

        Segment segment = getLineBuffer();

        SortedMap<Integer, Integer> startMap = new TreeMap<Integer, Integer>();
        SortedMap<Integer, Color> colorMap = new TreeMap<Integer, Color>();

        // Match all regexes on this snippet, store positions
        for (Map.Entry<Pattern, Color> entry : patternColors.entrySet()) {

            Matcher matcher = entry.getKey().matcher(text);

            while (matcher.find()) {
                startMap.put(matcher.start(1), matcher.end());
                colorMap.put(matcher.start(1), entry.getValue());
            }
        }

        // TODO: check the map for overlapping parts

        int i = 0;

        // Colour the parts
        for (Map.Entry<Integer, Integer> entry : startMap.entrySet()) {
            int start = entry.getKey();
            int end = entry.getValue();

            if (i < start) {
                graphics.setColor(Color.white);
                doc.getText(p0 + i, start - i, segment);
                x = Utilities.drawTabbedText(segment, x, y, graphics, this, i);
            }

            graphics.setColor(colorMap.get(start));
            i = end;
            doc.getText(p0 + start, i - start, segment);
            x = Utilities.drawTabbedText(segment, x, y, graphics, this, start);
        }

        // Paint possible remaining text black
        if (i < text.length()) {
            graphics.setColor(Color.white);
            doc.getText(p0 + i, text.length() - i, segment);
            x = Utilities.drawTabbedText(segment, x, y, graphics, this, i);
        }

        return x;
    }
}