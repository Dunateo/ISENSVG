package fr.geometrie;

public class Point {

    private int X;
    private int Y;


    public Point(){
        this.X = 0;
        this.Y = 0;

    }
    public Point(int x, int y) {
        X = x;
        Y = y;
    }


    public int getY() {
        return Y;
    }

    public int getX() {
        return X;
    }


}