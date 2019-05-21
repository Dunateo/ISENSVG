package fr.graphics.isvg;

import javax.swing.*;

public class Menu_bar extends JMenuBar {
    private String mName;
    public JMenuBar mBar;

    public  Menu_bar(String name){mName = name; mBar = new JMenuBar();}
    public Menu_bar(String menuItemText[] ,String buttonText[][], String name){
        mName = name;
        mBar = new JMenuBar();
    }
}
