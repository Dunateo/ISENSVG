package fr.graphics.isvg;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Buttons extends JButton{

    private  String mString;
    public JButton mButton;

    Buttons(String name){
        this.mString = name;
        this.mButton = new JButton(mString);
        //this.mButton.addActionListener((ActionListener) this);


    }


    public String getmString() {
        return mString;
    }



}
