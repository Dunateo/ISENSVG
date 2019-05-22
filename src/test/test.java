package test;
import javax.swing.JTextArea;

import fr.action_listenner.Button_act;
import fr.graphics.*;
import fr.parseur.Parseur_launch;


public class test {
	public static Fenetre mFenetre;
	public static Text_area mCode;
	public static Dessin mDessin;
	public static Parseur_launch mParse;
	

	public static void main(String[] args) {

        //déclaration des array
        String Item[][] = {{"Ouvrir","Nouveau","Enregistrer","Exporter","|","Quitter"},{"Refresh"}};
        String nomMenu[] = {"File"};
        String contentArea = "ma bite";
        //la fenetre
        Fenetre Main = new Fenetre("ISENSVG");
        mFenetre = Main;
        Menu_bar Bar = new Menu_bar(nomMenu, Item, "moi");
        Panel centerPan = new Panel(1,1);
        Scrool_pan Text = new Scrool_pan(contentArea);
        mCode = Text.mText;
        Dessin mDraw = new Dessin();
        //mDessin = new Dessin();
        mDessin = mDraw;
        
        //ajout des composants
        centerPan.mPan.add(Text.mPan, "West");
        centerPan.mPan.add(mDraw, "East");
        //centerPan.mPan.add(mDessin, "East");
        
        //mDessin = mDraw;
        
        Main.contentPane.add(centerPan.mPan, "Center");
        Main.setJMenuBar(Bar.mBar);
        Main.setVisible(true);
        
        
        //mParse = new Parseur_launch("testisen.isvg");
       
    }
}
