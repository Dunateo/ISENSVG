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
    public Ellipse(Point p, int gAxe, int pAxe, Color couleur, Color colorStroke, int epaisseur) {
        super(p, couleur, colorStroke,epaisseur );
        this.grandAxe = gAxe*2;
        this.petitAxe = pAxe*2;
    }
    public Ellipse(Point p, int gAxe, int pAxe, Color couleur) {
        super(p, couleur );
        this.grandAxe = gAxe*2;
        this.petitAxe = pAxe*2;
        this.stroke = new Color(0,0,0,1);
        this.strokeWidth = 0;
    }

    Ellipse(int grandAxe, int petitAxe){
        super(new Point(0,0));
        this.grandAxe = grandAxe*2;
        this.petitAxe = petitAxe*2;

    }

    public double getSurface(){
        return (grandAxe * petitAxe * PI)/4;

    }
    public void setBoundingBox(int hauteurBB,int largueurBB){
        grandAxe = hauteurBB;
        petitAxe = largueurBB;
    }
    public void draw(Graphics2D g){
        int x = origine.getX() - grandAxe/2;
        int y = origine.getY() - petitAxe/2;
        g.setColor(c);
        g.fillOval(x,y,petitAxe,grandAxe);
        g.setColor(stroke);
        g.setStroke(new BasicStroke(strokeWidth));
        g.drawOval(x,y,petitAxe,grandAxe);
    }
    public void drawDragged(Graphics2D g){
        g.setColor(stroke);
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
