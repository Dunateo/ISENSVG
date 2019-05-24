package fr.geometrie;

import java.awt.*;

public class Transform {
    protected Point translate;
    protected int rotate;
    protected boolean transform = false;

    public Transform(Point p, int deg, boolean State){
        translate = new Point(p.getX(),p.getY());
        rotate = deg;
        transform = State;
    }
    public Transform(boolean State){
        transform = State;
    }

    public void drawTransform(Graphics2D g, Transform tr){
        if (tr.isStateG() ){
            g.translate(tr.translate.getX(), tr.translate.getY());
            g.rotate(Math.toRadians(tr.rotate));
        }
    }

    public Point getTranslate() {
        return translate;
    }

    public int getRotate() {
        return rotate;
    }

    public boolean isStateG() {
        return transform;
    }

}
