package test;
import fr.graphics.*;


public class test {
    public static void main(String[] args) {

        //déclaration des array
        String Item[][] = {{"Ouvrir","Nouveau","Enregistrer","|","Quitter"}, {"Refresh"}};
        String nomMenu[] = {"File", "Edition"};
        String contentArea = " ";
        //la fenetre
        Fenetre Main = new Fenetre("ISENSVG");
        Menu_bar Bar = new Menu_bar(nomMenu, Item, "moi");
        Panel centerPan = new Panel(1,1);
        Scrool_pan Text = new Scrool_pan(contentArea);
        Dessin mDraw = new Dessin();

        //ajout des composants
        centerPan.mPan.add(Text.mPan, "West");
        centerPan.mPan.add(mDraw, "East");
        Main.contentPane.add(centerPan.mPan, "Center");
        Main.setJMenuBar(Bar.mBar);
        Main.setVisible(true);


    }
}
