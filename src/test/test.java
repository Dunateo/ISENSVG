package test;
import fr.graphics.isvg.*;


public class test {
    public static void main(String[] args) {
        String figButton[][] = {{"Ellipse","Cercle","Carre","Rectangle"},{"$"}};
        Fenetre Main = new Fenetre("ISENSVG");
        Panel pn = new Panel(figButton, 2,2);
        Main.contentPane.add(pn.mPan, "South");
        Main.setVisible(true);


    }
}
