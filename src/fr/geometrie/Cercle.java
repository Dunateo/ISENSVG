package fr.geometrie;

import java.awt.*;

public class Cercle extends Ellipse{


    Cercle(Point p, Color c){
        super(p,c);
    }
    Cercle(int axe){
        super(axe,axe);
    }
    public Cercle(Point p, int Axe, Color couleur, Color colorStroke, int epaisseur){
        super(p,Axe, Axe,couleur, colorStroke,epaisseur);
    }

    public void setGrandAxe(int grandAxe) {
        this.grandAxe = grandAxe;
        this.petitAxe = grandAxe;
    }

    public void setPetitAxe(int petitAxe) {
        this.petitAxe = petitAxe;
        this.grandAxe = petitAxe;
    }
    public void setBoundingBox(int hauteurBB,int largueurBB){
        this.petitAxe = hauteurBB;
        this.grandAxe = hauteurBB;
    }
    public String figureName(){
        return "Cercle " + grandAxe ;
    }
}
