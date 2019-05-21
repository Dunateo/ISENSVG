package fr.graphics;

import javax.swing.*;
import java.awt.*;

public class Menu_item extends JMenuItem {

    public JMenuItem Item;
    private String mName;

    Menu_item(String name){
        mName = name;
        Item = new JMenuItem(mName);
       Item.setAccelerator(KeyStroke.getKeyStroke(mName.charAt(0), Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
    }


}
