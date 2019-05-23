package fr.geometrie;

import java.awt.*;

public class Polygon extends Figure{

    protected int Xpol[], Ypol[], nbPoints;

    public Polygon(String[] Point, Color c,Color stroke , int strokeWidth, Point orig){
        super(new Point(orig.getX(),orig.getY()), c,stroke, strokeWidth);
        decomposePoint(Point);

    }

    private void decomposePoint(String[] Point){
            Xpol = new int[Point.length];
            Ypol = new int[Point.length];

        for (int i = 0; i<Point.length; i++){
            String[] tmp = Point[i].split(",");
            Xpol[i] = (int) Float.parseFloat(tmp[0]);
            Ypol[i] = (int) Float.parseFloat(tmp[1]);
        }
        nbPoints = Point.length;


    }

    public  void draw(Graphics2D g){

        g.setColor(c);
        g.fillPolygon(Xpol,Ypol,nbPoints);
        g.setColor(stroke);
        g.drawPolygon(Xpol,Ypol,nbPoints);
    }

    public  double getPerimetre(){
        return origine.getX();
    }
    public double getSurface(){
        return origine.getY();
    }
    public  void setBoundingBox(int hauteurBB,int largueurBB){

    }
    public  void drawDragged(Graphics2D g){

    }
    public  String figureName(){return "Line "  + origine.getX() + ";" + origine.getY(); }
}
