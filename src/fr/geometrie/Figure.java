package fr.geometrie;

import java.awt.*;

public abstract class Figure {

    protected Point origine;
    protected Color c;

    public Figure(Point p) {
        this.origine = p;
    }

    public Figure(Point p, Color col){
        this.origine = p;
        this.c = col;
    }
    public abstract double getPerimetre();
    public abstract double getSurface();
    public abstract void setBoundingBox(int hauteurBB,int largueurBB);
    public abstract void draw(Graphics g);
    public abstract void drawDragged(Graphics g);
    public abstract String figureName();

    public Point getOrigine() {
        return origine;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "origine=" + origine +
                '}';
    }


}
