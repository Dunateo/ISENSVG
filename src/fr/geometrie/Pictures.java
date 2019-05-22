package fr.geometrie;

import java.awt.*;

public class Pictures extends Figure{

    protected String Path;

    public Pictures(Point p , String pat){
        super(p);
        this.Path = pat;
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
        Image img = Toolkit.getDefaultToolkit().getImage(Path);
        g.drawImage(img, origine.getX(),origine.getY(),null);
    }
    public  void drawDragged(Graphics2D g){

    }
    public  String figureName(){return "Picture "  + Path; }
}
