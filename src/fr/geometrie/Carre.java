package fr.geometrie;

import java.awt.*;

public class Carre extends Rectangle {

    Carre(int l){
        super(l,l);
    }

    Carre(Point p, Color c){
        super(p,c);
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
        this.largeur = longueur;
    }
    public void setLargeur(int largeur) {

        this.largeur = largeur;
        this.longueur = largeur;
    }
    public void setBoundingBox(int hauteurBB,int largueurBB){
        this.largeur = hauteurBB;
        this.longueur = hauteurBB;
    }
    public String figureName(){
        return "Carre " + longueur;
    }
}
