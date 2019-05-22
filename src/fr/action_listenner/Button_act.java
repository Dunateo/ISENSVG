package fr.action_listenner;

import fr.graphics.Buttons;
import fr.graphics.Menu_item;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Button_act implements ActionListener {

    public Button_act(ArrayList<Buttons> list, ArrayList<Menu_item> Menulist){

        for ( Buttons f: list){
            f.mButton.addActionListener(this);
        }
        for ( Menu_item h: Menulist){
            h.Item.addActionListener(this);
        }

    }
    public Button_act(ArrayList<Menu_item> list){

        for ( Menu_item f: list){
            f.Item.addActionListener(this);
        }

    }
    public Button_act(Buttons btn){
            btn.mButton.addActionListener(this);

    }

    /**
     * action listener
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        System.out.println("Bouton presse = " + cmd);
        if( cmd.equals("Refresh")){


        }

    }



}
