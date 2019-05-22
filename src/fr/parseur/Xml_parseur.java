package fr.parseur;

import java.awt.*;
import java.util.ArrayList;

import fr.geometrie.*;
import fr.geometrie.Point;
import fr.geometrie.Rectangle;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;


public class Xml_parseur implements ContentHandler{

    //array list fot figure
    private ArrayList<Figure> list = new ArrayList<Figure>();
    private String ContentT;
    private boolean stateT = false;
    private int x,y;
    private Color c,contour;


    @Override
    public void startElement(String arg0, String arg1, String nom, Attributes arg3) throws SAXException {

        System.out.println(nom);
        if (nom.equals("rect"))
        {

            Rectangle f= recupRect(arg3);
            list.add(f);
        }
        else if(nom.equals("circle"))
        {
            Cercle f=recupCircle(arg3);
            list.add(f);
        }else if (nom.equals("text")){
            stateT =true;
            recupTextAttri(arg3);

        }else if (nom.equals("isen_magic")){
            Pictures f = recupPict(arg3);
            list.add(f);
        }


    }

    /**
     * Text recup attributs
     * @param arg3
     */
    public void recupTextAttri(Attributes arg3){
        x = Integer.parseInt(arg3.getValue("x"));
        y=Integer.parseInt(arg3.getValue("y"));
        c= Color.decode(arg3.getValue("stroke"));
    }

    public Pictures recupPict(Attributes arg3){
        x = Integer.parseInt(arg3.getValue("x"));
        y=Integer.parseInt(arg3.getValue("y"));
        Pictures pict = new Pictures(new Point(x,y), "src/assets/pictures/toulon.png");
        return pict;
    }
    /**
     * Text creation
     * @return
     */
    public Text recupText() {

        Text text = new Text(new Point(x,y), c,ContentT);
        return text;
    }

    /**
     * rect recup attributs
     * @param arg3
     * @return
     */
    public Rectangle recupRect(Attributes arg3)
    {
        int l,L,ep;
        Point pt ;
        String except;
        x=Integer.parseInt(arg3.getValue("x"));
        y=Integer.parseInt(arg3.getValue("y"));
        L=Integer.parseInt(arg3.getValue("height"));   //Valeur en int de l'attribue "..."
        l=Integer.parseInt(arg3.getValue("width"));
        ep=Integer.parseInt(arg3.getValue("stroke-width"));

        except =arg3.getValue("fill");
        if (except.equals("none")){
            c = new Color(0,0,0,1);
        }else {
            c= Color.decode(except);
        }
        contour=Color.decode(arg3.getValue("stroke"));
        pt = new Point(x,y);
        Rectangle r = new Rectangle(pt,L,l,c,contour,ep);
        return r;
    }

    /**
     * Circle attributs recup
     * @param arg3
     * @return
     */
    public Cercle recupCircle(Attributes arg3)
    {
        int x,y,r,ep;
        String except;
        Point pt;
        Color c,contour;
        x=Integer.parseInt(arg3.getValue("cx"));
        y=Integer.parseInt(arg3.getValue("cy"));
        r=Integer.parseInt(arg3.getValue("r"));   //Valeur en int de l'attribue "..."
        ep=Integer.parseInt(arg3.getValue("stroke-width"));
        except = arg3.getValue("fill");

        if (except.equals("none")){
            c = new Color(0,0,0,1);
        }else {
            c= Color.decode(except);
        }
        contour=Color.decode(arg3.getValue("stroke"));
        pt = new Point(x,y);
        Cercle cercle = new Cercle(pt,r,c,contour,ep);
        return cercle;
    }

    public ArrayList<Figure> getList() {
        return list;
    }

    private void testExceptions(){

    }

    @Override
    public void startPrefixMapping(String arg0, String arg1) throws SAXException {


    }


    @Override
    public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
        if (stateT){
             ContentT = new String(arg0, arg1, arg2);
        }


    }

    @Override
    public void endDocument() throws SAXException {


    }

    @Override
    public void endElement(String arg0, String arg1, String arg2) throws SAXException {
        if (arg2.equals("text")){
            Text f = recupText();
            list.add(f);
            stateT = false;
        }

    }

    @Override
    public void endPrefixMapping(String arg0) throws SAXException {

    }

    @Override
    public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {

    }

    @Override
    public void processingInstruction(String arg0, String arg1) throws SAXException {

    }

    @Override
    public void setDocumentLocator(Locator arg0) {

    }

    @Override
    public void skippedEntity(String arg0) throws SAXException {


    }

    @Override
    public void startDocument() throws SAXException {

    }
}
