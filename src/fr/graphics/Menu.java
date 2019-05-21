package fr.graphics;

import fr.action_listenner.Button_act;

import javax.swing.*;
import java.util.ArrayList;

public class Menu extends JMenu {

    private String mName;
    public JMenu mMenu;
    private ArrayList<Menu_item> JMIlist;

    public Menu(String buttonText[], String name){
        mName = name;
        mMenu = new JMenu(mName);
        JMIlist = new ArrayList<Menu_item>();
        createMenuItem(buttonText);
        actionEvent();

    }
    public Menu(String name){
        mName = name;
        mMenu = new JMenu(mName);

    }

    private void createMenuItem(String[] buttonText){
        int i = 0;


            while (i < buttonText.length) {

                if (buttonText[i].equals("|")) {
                    mMenu.addSeparator();
                } else {
                    Menu_item But = new Menu_item(buttonText[i]);

                    mMenu.add(But.Item);
                    JMIlist.add(But);
                }

                i++;
            }

    }

    public ArrayList<Menu_item> getJMIlist() {
        return JMIlist;
    }

    private void actionEvent(){

             Button_act Action = new Button_act(JMIlist);

    }

}
