package fr.graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.*;

import fr.parseur.Parseur_launch;
import fr.action_listenner.Button_act;
import fr.fichier.*;

public class Frame extends GestionFichier implements ActionListener, KeyListener {
	public Scrool_pan mEditCode;
	public Dessin mDessin;
	public Parseur_launch mParse;
	public Fenetre principale;
	private boolean StateAutoRefresh = false;
	private String version = "Version: Beta 0.2";
	
	public Frame () {
		
		mEditCode = new Scrool_pan();
		mDessin = new Dessin();
	}
	
	
	public void runFrame ()  {
			
			principale = new Fenetre("ValouML", "src/assets/pictures/logo-app.png");
		
			// Dclaration des array
	        String Item[][] = {{"Ouvrir", "Nouveau", "Sauvegarder", "Exporter", "|", "Quitter"}, {"Refresh", "Auto-Refresh"}, {"¤Version", "¤Auteur"}};;
	        String nomMenu[] = {"File","Edition","About"};
	        
	        Menu_bar Bar = new Menu_bar(nomMenu, Item, "moi");
	        Button_act B1 = new Button_act(Bar.getMListI(0).getJMIlist(),this);
	        Button_act B2 = new Button_act(Bar.getMListI(1).getJMIlist(),this);
			Button_act B3 = new Button_act(Bar.getMListI(2).getJMIlist(),this);
			mEditCode.xmlTextPane.addKeyListener(this);
	        Panel centerPan = new Panel(1,1);
			mEditCode.xmlTextPane.setOpaque(true);
			mEditCode.xmlTextPane.setBackground(Color.decode("#263238"));
			mEditCode.xmlTextPane.setCaretColor(Color.white);

	        // Ajout des composants
	        centerPan.mPan.add(mEditCode.mPan, "West");
	        centerPan.mPan.add(mDessin, "East");
	        
	        principale.contentPane.add(centerPan.mPan, "Center");
	        principale.setJMenuBar(Bar.mBar);
	        principale.setVisible(true);
	}


	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Quitter")) {
			quitFile(mEditCode.xmlTextPane);
		} else if(cmd.equals("Ouvrir")) {
			openXMLFile(mEditCode.xmlTextPane,mDessin,mParse,principale);
		} else if(cmd.equals("Nouveau")) {
			nouveauFile(mEditCode.xmlTextPane,mDessin,principale);
		} else if(cmd.equals("Sauvegarder")) {
			registerXMLFile(mEditCode.xmlTextPane,principale);
		} else if(cmd.equals("Exporter")) {
			exportDessin(mDessin);
		} else if(cmd.equals("Refresh")) {

			mParse = new Parseur_launch(mEditCode.xmlTextPane.getText(),false);
			mDessin.refreshParseur(mParse.par.getList());

			
		}else if (cmd.equals("Auto-Refresh")){
			if (StateAutoRefresh){
				StateAutoRefresh = false;
			}else {
				StateAutoRefresh = true;
			}

		} else if (cmd.equals("Version")){
			JOptionPane.showMessageDialog((Component) e.getSource(), version,"Version", JOptionPane.INFORMATION_MESSAGE);
		}else if (cmd.equals("Auteur")){
			JOptionPane.showMessageDialog((Component) e.getSource(), "ValouML editor by Valentin Bru","Auteur", JOptionPane.INFORMATION_MESSAGE);

		}else {
			System.err.println(cmd);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (StateAutoRefresh ){
			mParse = new Parseur_launch(mEditCode.xmlTextPane.getText(),false);
			mDessin.refreshParseur(mParse.par.getList());
		}

	}
}
