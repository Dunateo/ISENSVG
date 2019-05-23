package fr.geometrie;

import java.awt.*;
import java.util.ArrayList;

public class Polyline extends Figure {
    protected ArrayList<Line> list = new ArrayList<Line>();


    public Polyline(String[] Point, Color stroke, int strokeWidth, Point orig){
        super(new Point(orig.getX(),orig.getY()));
        for (int i=0; i<Point.length-1; i++){
            String[] p1 = Point[i].split(",");
            String[] p2 = Point[i+1].split(",");
            Point Bp = new Point(Integer.parseInt(p1[0]),Integer.parseInt(p1[1]));
            Point Ep = new Point(Integer.parseInt(p2[0]),Integer.parseInt(p2[1]));
            addLine(Bp,Ep, stroke, strokeWidth);
        }

    }
    private void addLine(Point Bp, Point Ep, Color stroke, int strokeWidth){
        Line e = new Line(Bp, Ep, stroke, strokeWidth);
        list.add(e);

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
        for ( Line f: list){
            f.draw(g);
        }
    }
    public  void drawDragged(Graphics2D g){

    }

    public  String figureName(){return "Line "  + origine.getX() + ";" + origine.getY(); }
}
