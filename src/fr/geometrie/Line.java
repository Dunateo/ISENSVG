package fr.geometrie;

import java.awt.*;

public class Line extends Figure {

    protected Point endLine;

    public Line(Point Bp, Point Ep, Color stroke, int strokeWidth){
        super(new Point(Bp.getX(), Bp.getY()), stroke, strokeWidth);
        endLine = new Point(Ep.getX(), Ep.getY());
    }

    public  double getPerimetre(){
        return origine.getX();
    }
    public double getSurface(){
        return origine.getY();
    }
    public  void setBoundingBox(int hauteurBB,int largueurBB){

    }
    public  void draw(Graphics2D g){
        g.setColor(this.stroke);
        g.setStroke(new BasicStroke(this.strokeWidth));
        g.drawLine(origine.getX(),origine.getY(),endLine.getX(), endLine.getY());
    }
    public  void drawDragged(Graphics2D g){

    }
    public  String figureName(){return "Line "  + origine.getX() + ";" + origine.getY(); }

}
