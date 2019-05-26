package test;

import fr.geometrie.Figure;
import fr.parseur.Parseur_launch;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Xml_parseurTest {
    Parseur_launch mParse = new Parseur_launch("test.xml");

    @Test
    void recupEllipse() {
    }

    @Test
    void recupPolygon() {
    }

    @Test
    void recupPolyline() {
    }

    @Test
    void recupLine() {
    }

    @Test
    void recupG() {
    }

    @Test
    void recupTextAttri() {
    }

    @Test
    void recupPict() {
    }

    @Test
    void recupText() {
    }

    @Test
    void recupRect() {
    }

    @Test
    void recupCircle() {
    }

    @Test
    void getList() {
        ArrayList<Figure> list = mParse.par.getList();
        //test les balises et si chaque assignation est bonne
        String[] listName = {"Rectangle 398;1198", "Line 50;375","Rectangle 200;400" };
        int i =0;
        for (Figure f : list){
            assertEquals(f.figureName(), listName[i]);
            i++;
        }
    }
}