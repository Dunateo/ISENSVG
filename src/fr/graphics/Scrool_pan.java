package fr.graphics;

import javax.swing.*;

public class Scrool_pan extends JScrollPane {
    public  JScrollPane mPan;
    public Text_area mText;

    public Scrool_pan(String content){
        mText = new Text_area(content);
        mPan = new JScrollPane(mText);
    }
}
