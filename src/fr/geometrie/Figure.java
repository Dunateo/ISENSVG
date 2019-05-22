package fr.geometrie;

import java.awt.*;

public abstract class Figure {

    protected Point origine;
    protected Color c;
    protected Color stroke;
    protected int strokeWidth;

    public Figure(Point p) {
        this.origine = p;
    }

    public Figure(Point p, Color col){
        this.origine = p;
        this.c = col;
    }
    public Figure(Point p, Color col, Color contour, int strokeWidth){
        this.origine = p;
        this.c = col;
        this.stroke = contour;
        this.strokeWidth = strokeWidth;
    }

    public abstract double getPerimetre();
    public abstract double getSurface();
    public abstract void setBoundingBox(int hauteurBB,int largueurBB);
    public abstract void draw(Graphics2D g);
    public abstract void drawDragged(Graphics2D g);
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
    public Color getStroke() {
        return stroke;
    }

}
