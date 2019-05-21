package test;
import fr.graphics.Fenetre;
import fr.graphics.Menu_bar;
import fr.graphics.Panel;
import fr.graphics.Scrool_pan;


public class test {
    public static void main(String[] args) {

        //déclaration des array
        String Item[] = {"Ellipse","Cercle","Carre","|","Rectangle"};
        String nomMenu[] = {"File"};
        String contentArea;
        //la fenetre
        Fenetre Main = new Fenetre("ISENSVG");
        Menu_bar Bar = new Menu_bar(nomMenu, Item, "moi");
        Panel centerPan = new Panel(1,1);
        Scrool_pan Text = new Scrool_pan("Salut à tous les amis");
        Panel westPan = new Panel();

        //ajout des composants
        westPan.add(Text.mPan);
        Main.contentPane.add(westPan.mPan, "West");
        Main.contentPane.add(centerPan.mPan, "Center");
        Main.setJMenuBar(Bar.mBar);
        Main.setVisible(true);


    }
}
