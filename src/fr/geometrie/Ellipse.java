package fr.geometrie;

import java.awt.*;

public class Ellipse extends Figure {
    public static final float PI =3.14f;

    protected int grandAxe;
    protected int petitAxe;

    Ellipse(Point p){
        super(p);
    }

    Ellipse(Point p, Color c){
        super(p,c);
        grandAxe = 0;
        petitAxe = 0;
    }

    Ellipse(int grandAxe, int petitAxe){
        super(new Point(0,0));
        this.grandAxe = grandAxe;
        this.petitAxe = petitAxe;

    }

    public double getSurface(){
        return (grandAxe * petitAxe * PI)/4;

    }
    public void setBoundingBox(int hauteurBB,int largueurBB){
        grandAxe = hauteurBB;
        petitAxe = largueurBB;
    }
    public void draw(Graphics g){
        g.setColor(c);
        g.fillOval(origine.getX(),origine.getY(),petitAxe,grandAxe);
    }
    public void drawDragged(Graphics g){
        g.setColor(c);
        g.drawOval(origine.getX(),origine.getY(),petitAxe,grandAxe);

    }
    public String figureName(){
        return "Ellipse " + grandAxe + ";" + petitAxe;
    }

    public  double getPerimetre(){
        return PI*(this.grandAxe+this.petitAxe);
    }
    public int getGrandAxe() {
        return grandAxe;
    }

    public int getPetitAxe() {
        return petitAxe;
    }

    public void setGrandAxe(int grandAxe) {
        this.grandAxe = grandAxe;
    }

    public void setPetitAxe(int petitAxe) {
        this.petitAxe = petitAxe;
    }
}
