package assets.skin;

import javax.swing.*;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;


import java.util.Properties;

public class Theme {

    public Theme(){
        try {
            //change the look and feel to have no Jtattoo logo
            Properties props = new Properties();
            props.put("logoString", " ");
            GraphiteLookAndFeel.setCurrentTheme(props);
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
    }
