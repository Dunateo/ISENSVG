package fr.fichier;

import test.test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import fr.graphics.Fenetre;

public class OpenExistedFile extends test{
	
		// Choix d'un fichier XML
		public File choixFichierXML () throws IOException {
			JFileChooser dialogue = new JFileChooser(new File("."));
			File fichier = null;
			
			if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				fichier = dialogue.getSelectedFile();
			}
			
			return fichier;
		}
		
		// Récupération du texte d'un fichier XML
		public String textFichierXML (File fichier) throws IOException {
			String texte = "";
			
			BufferedReader lecteurAvecBuffer = null;
			String ligne;
			
			try {
				lecteurAvecBuffer = new BufferedReader(new FileReader(fichier));
			}
			catch(FileNotFoundException exc) {
				System.out.println("Erreur d'ouverture");
			}
			
			while ((ligne = lecteurAvecBuffer.readLine()) != null) {
				texte += ligne;
				texte += '\n';
			}
			
			lecteurAvecBuffer.close();
			
			return texte;
		}
		
		// Ouvre un fichier XML et l'affiche dans la zone d'édition de code
		public void openXMLFile () {
			try {
				File openFichier = choixFichierXML();
				if(openFichier != null) {
					String codeText = textFichierXML(openFichier);
					//this.setVisible(false);
					//Fenetre nouv = new Fenetre(openFichier.getName(),codeText);
					//Code.setText(codeText);
					mCode.setText(codeText);
					//mFenetre().setTitle(openFichier.getName());
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		// Enregistrer fichier XML
		public void registerXMLFile () {
			//String texte = Code.getText();
			String texte = mCode.getText();
			
			JFileChooser choix = new JFileChooser();
			int retour = choix.showSaveDialog(null);
			
			if(retour == JFileChooser.APPROVE_OPTION){
			   // nom du fichier  choisi
			   choix.getSelectedFile().getName();
			   // chemin absolu du fichier choisi
			   choix.getSelectedFile().getAbsolutePath();
			}
			
			try (FileOutputStream fos = new FileOutputStream(choix.getSelectedFile().getAbsolutePath() + ".isvg")) {
				fos.write(texte.getBytes());
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		
		
		// Enregistrer fichier XML
		public void nouveauFile () {
			mCode.setText("");
		}
}
