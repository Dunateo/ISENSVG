package fr.action_listenner;

import fr.graphics.Buttons;
import fr.graphics.Menu_item;
import fr.graphics.Dessin;
import fr.fichier.GestionFichier;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Button_act extends GestionFichier implements ActionListener {
		
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
        System.out.println("bouton presse = " + cmd);
        
        if(cmd.equals("Quitter")) {
        	quitFile();
        } else if(cmd.equals("Ouvrir")) {
        	openXMLFile();
        } else if(cmd.equals("Nouveau")) {
        	nouveauFile();
        } else if(cmd.equals("Enregistrer")) {
        	registerXMLFile();
        } else if(cmd.equals("Exporter")) {
        	exportDessin();
    	} else {
        	System.err.println(cmd);
        }

    }

	

}
