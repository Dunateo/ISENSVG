package fr.graphics.isvg;

import javax.swing.*;
import java.util.ArrayList;

public class Menu extends JMenu {

    private String mName;
    public JMenu mMenu;
    private ArrayList<Menu_item> JMIlist;

    public Menu(String buttonText[][], String name){
        mName = name;
        mMenu = new JMenu(mName);
        JMIlist = new ArrayList<Menu_item>();
        createMenuItem(buttonText);

    }
    public Menu(String name){
        mName = name;
        mMenu = new JMenu(mName);

    }

    private void createMenuItem(String buttonText[][]){

        for (int i = 0; i < buttonText[0].length ; i++ ){
            Menu_item But = new Menu_item(buttonText[0][i]);

            if (i == Integer.parseInt(buttonText[1][0]) ){
                mMenu.addSeparator();
                mMenu.add(But.Item);

            }else {
                mMenu.add(But.Item);
            }
            JMIlist.add(But);

        }

    }

}
