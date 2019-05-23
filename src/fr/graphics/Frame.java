package fr.graphics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.BorderFactory;

import fr.parseur.Parseur_launch;
import fr.xmleditor.XmlTextPane;
import fr.action_listenner.Button_act;
import fr.fichier.*;

public class Frame extends GestionFichier implements ActionListener {
	public Scrool_pan mEditCode;
	public Dessin mDessin;
	public Parseur_launch mParse;
	public Fenetre principale;
	
	public Frame () {
		
		mEditCode = new Scrool_pan();
		mDessin = new Dessin();
	}
	
	
	public void runFrame ()  {
			
			principale = new Fenetre("ISENSVG <No Name>");
		
			// Déclaration des array
	        String Item[][] = {{"Ouvrir","Nouveau","Enregistrer","Exporter","|","Quitter"},{"Refresh"}};
	        String nomMenu[] = {"File","Edition"};
	        
	        Menu_bar Bar = new Menu_bar(nomMenu, Item, "moi");
	        Button_act B1 = new Button_act(Bar.getMListI(0).getJMIlist(),this);
	        Button_act B2 = new Button_act(Bar.getMListI(1).getJMIlist(),this);
	        Panel centerPan = new Panel(1,1);
	
	        // Ajout des composants
	        centerPan.mPan.add(mEditCode.mPan, "West");
	        centerPan.mPan.add(mDessin, "East");
	        
	        principale.contentPane.add(centerPan.mPan, "Center");
	        principale.setJMenuBar(Bar.mBar);
	        
	        principale.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		System.out.println("bouton presse = " + cmd + " : " + mEditCode.xmlTextPane.getText());
		
		if(cmd.equals("Quitter")) {
			quitFile(mEditCode.xmlTextPane);
		} else if(cmd.equals("Ouvrir")) {
			openXMLFile(mEditCode.xmlTextPane,mDessin,mParse,principale);
		} else if(cmd.equals("Nouveau")) {
			nouveauFile(mEditCode.xmlTextPane,mDessin,principale);
		} else if(cmd.equals("Enregistrer")) {
			registerXMLFile(mEditCode.xmlTextPane,principale);
		} else if(cmd.equals("Exporter")) {
			exportDessin(mDessin);
		} else if(cmd.equals("Refresh")) {
			File tempFile = new File("tempFile.isvg");
			
			try (FileOutputStream fos = new FileOutputStream("tempFile.isvg")) {
				fos.write(mEditCode.xmlTextPane.getText().getBytes());
			} catch(IOException ex) {
				ex.printStackTrace();
			}
			
			mParse = new Parseur_launch("tempFile.isvg");
			mDessin.refreshParseur(mParse.par.getList());
			
			tempFile.delete();
			
		} else {
			System.err.println(cmd);
		}
	}
}

