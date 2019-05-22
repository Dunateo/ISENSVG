package fr.geometrie;

import java.awt.*;

public class Text extends Figure{

    protected String Content;

    public Text(Point p, Color c, String Content){
        super(p,c);
        this.Content = Content;
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
        g.setColor(this.c);
        g.drawString(Content, origine.getX(),origine.getY());
    }
    public  void drawDragged(Graphics2D g){

    }
    public  String figureName(){return "Text " +Content ; }
}
