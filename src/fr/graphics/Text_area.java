package fr.graphics;

import javax.swing.*;

public class Text_area extends JTextArea {
    public String mContent;
    public JTextArea mArea;

    Text_area(String content){
        mContent = content;
        mArea = new JTextArea(mContent);
    }
}
