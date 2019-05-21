package fr.graphics;

import javax.swing.*;
import java.awt.*;

public class Text_area extends JTextArea {
    public String mContent;
    public JTextArea mArea;

    Text_area(String content){
        mContent = content;
        mArea = new JTextArea(mContent);
        mArea.setFont(new Font("Serif", Font.ITALIC, 16));

    }
}
