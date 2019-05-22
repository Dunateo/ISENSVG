package fr.fichier;

import test.test;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import fr.graphics.Fenetre;
import fr.parseur.Parseur_launch;

public class GestionFichier extends test implements ActionListener{
	
		// Boite de confirmation
		public int confirmBox () {
			
			int option = JOptionPane.showConfirmDialog(null,"Voulez-vous enregistrer le fichier ?","Selectionner une option ...",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);

			// 0=yes, 1=no, 2=cancel
			
			return option;
		}
		
		// Choix d'un fichier � ouvrir
		public File choixOpenFichier () throws IOException {
			JFileChooser dialogue = new JFileChooser(new File("."));
			File fichier = null;
			
			if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				fichier = dialogue.getSelectedFile();
			}
			
			return fichier;
		}
		
		
		// R�cup�ration du texte d'un fichier
		public String textFichier (File fichier) throws IOException {
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
		
		
		// Ouvre un fichier XML et l'affiche dans la zone d'�dition de code
		public void openXMLFile () {
			try {
				if(!mCode.getText().equals("")) {
					int conf = -1;
					
					conf = confirmBox();
					
					if(conf == 0) {
						registerXMLFile();
						
						File openFichier = choixOpenFichier();
						
						if(openFichier != null) {
							String codeText = textFichier(openFichier);
							
							mCode.setText(codeText);
							//mFenetre().setTitle(openFichier.getName());
						}
					} else if(conf == 1) {
						File openFichier = choixOpenFichier();
						
						if(openFichier != null) {
							String codeText = textFichier(openFichier);
							
							mCode.setText(codeText);
							//mFenetre().setTitle(openFichier.getName());
						}
					} else {}
				} else {
					File openFichier = choixOpenFichier();
					
					if(openFichier != null) {
						String codeText = textFichier(openFichier);
						
						mCode.setText(codeText);
						//mFenetre().setTitle(openFichier.getName());
						
						mParse = new Parseur_launch(openFichier.getAbsolutePath());
						mDessin.refreshParseur(mParse.par.getList());
					}
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		// Choix d'un fichier � sauvegarder
		public JFileChooser choixSaveFichier () throws IOException {
			JFileChooser dialogue = new JFileChooser();
			int retour = dialogue.showSaveDialog(null);
			
			if (retour == JFileChooser.APPROVE_OPTION) {
				// nom du fichier  choisi
				dialogue.getSelectedFile().getName();
				// chemin absolu du fichier choisi
				dialogue.getSelectedFile().getAbsolutePath();
			} else {
				dialogue = null;
			}
			
			return dialogue;
		}

		
		// Enregistrer fichier XML
		public void registerXMLFile () {
			try {
				if(!mCode.getText().equals("")) {
					JFileChooser saveFichier = choixSaveFichier();
					
					if(saveFichier != null) {
						String texte = mCode.getText();
						
						try (FileOutputStream fos = new FileOutputStream(saveFichier.getSelectedFile().getAbsolutePath() + ".isvg")) {
							fos.write(texte.getBytes());
						} catch(IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		// Cr�er nouveau code isvg
		public void nouveauFile () {
			if(!mCode.getText().equals("")) {
				
				int conf = -1;
				
				conf = confirmBox();
				
				if(conf == 0) {
					registerXMLFile();
					mCode.setText("");
				} else if(conf == 1) {
					mCode.setText("");
				} else {}
			}
		}
		
		
		// Quitter le logiciel
		public void quitFile () {
			if(!mCode.getText().equals("")) {
				
				int conf = -1;
				
				conf = confirmBox();
				
				if(conf == 0) {
					registerXMLFile();
					System.exit(0);
				} else if(conf == 1) {
					System.exit(0);
				} else {}
			} else {
				System.exit(0);
			}
		}
		
		
		// Exporter le dessin
		public void exportDessin() {
			try {
				if(mDessin.getList().isEmpty() == false) {
					JFileChooser choix = choixSaveFichier();
					
					if(choix != null) {
						BufferedImage bi = new BufferedImage(mDessin.getSize().width, mDessin.getSize().height, BufferedImage.TYPE_INT_ARGB);
						Graphics g = bi.createGraphics();
						
						mDessin.paint(g);
						g.dispose();
						
						try{
						    ImageIO.write(bi,"png",new File(choix.getSelectedFile().getAbsolutePath()+".png"));
						} catch (Exception e) {}
					}
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
}
