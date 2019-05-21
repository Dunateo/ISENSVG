package fr.geometrie;

class Point {

    private int X;
    private int Y;


    Point(){
        this.X = 0;
        this.Y = 0;

    }
    Point(int x, int y) {
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