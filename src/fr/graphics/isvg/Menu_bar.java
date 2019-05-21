package fr.graphics.isvg;

import javax.swing.*;
import java.util.ArrayList;

public class Menu_bar extends JMenuBar {
    private String mName;
    public JMenuBar mBar;
    private ArrayList<Menu> Mlist;

    public  Menu_bar(String name){mName = name; mBar = new JMenuBar();}
    public Menu_bar(String menuItemText[] ,String buttonText[][], String name){
        mName = name;
        mBar = new JMenuBar();
        Mlist = new ArrayList<Menu>();
        createMenu(menuItemText,buttonText);
    }

    private void createMenu(String menuItemText[] ,String buttonText[][]){

        for (int i = 0; i < buttonText[0].length ; i++ ){
            Menu menuName = new Menu(buttonText, menuItemText[i]);
            mBar.add(menuName.mMenu);
            Mlist.add(menuName);

        }

    }

    public String getmName() {
        return mName;
    }
}
