package fr.geometrie;

import java.awt.*;

public class Rounded_rectangle extends Rectangle {
    protected  Point axeRadius;


    public Rounded_rectangle(Point p, int longueur, int largeur, Color couleur, Color colorStroke, int epaisseur, int rx, int ry) {
        super(p, longueur, largeur,couleur, colorStroke, epaisseur );
            axeRadius = new Point(rx,ry);
    }

    public Rounded_rectangle(Point p, int longueur, int largeur, Color couleur, Color colorStroke, int epaisseur, int rx, int ry, Point translate, int rotate) {
        super(p, longueur, largeur,couleur, colorStroke, epaisseur, translate, rotate);
        axeRadius = new Point(rx,ry);
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(c);
        g.fillRoundRect(origine.getX(),origine.getY(),largeur,longueur,axeRadius.getX(),axeRadius.getY());
        g.setColor(stroke);
        g.setStroke(new BasicStroke(this.strokeWidth));
        g.drawRoundRect(origine.getX(),origine.getY(), largeur,longueur,axeRadius.getX(),axeRadius.getY());
        trans.drawTransform(g,trans);
    }

}
