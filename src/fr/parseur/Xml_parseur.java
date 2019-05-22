package fr.parseur;

import java.awt.*;
import java.lang.reflect.Field;
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
    private boolean stateT = false, stateG = false ;
    private int x,y, rotate;
    private Color c,contour;
    Point translate;


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
        }else if (nom.equals("g")){
            stateG = true;
            recupG(arg3);
        }


    }

    /**
     * Assign the transform parameter to his goal
     * @param arg3
     */
    public void recupG(Attributes arg3){

        if (arg3.getValue("transform") != null){
            String[] sN,sT;
            String tmp = arg3.getValue("transform");
            String number = tmp.replaceAll("[a-z[()]]+","");
            String text  = tmp.replaceAll("[0-9[()-]]+", "");

            //split the both String
            sN = number.split(" ");
            sT = text.split(" ");

            //check the arguments order
            if (sT[0].equals("translate")){
                translate = new Point(Integer.parseInt(sN[0]),Integer.parseInt(sN[1]));
                rotate = Integer.parseInt(sN[2]);
            }else {
                translate = new Point(Integer.parseInt(sN[1]),Integer.parseInt(sN[2]));
                rotate = Integer.parseInt(sN[0]);
            }


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
        int l,L,ep, rx,ry;
        Point pt ;
        String except;
        x=Integer.parseInt(arg3.getValue("x"));
        y=Integer.parseInt(arg3.getValue("y"));
        L=Integer.parseInt(arg3.getValue("height"));   //Valeur en int de l'attribue "..."
        l=Integer.parseInt(arg3.getValue("width"));
        except =arg3.getValue("fill");
        pt = new Point(x,y);

        //check if there is stroke or no
        if (arg3.getValue("stroke")!= null){
            //decode the color
            contour =  stringToColor(arg3.getValue("stroke"));
            ep=Integer.parseInt(arg3.getValue("stroke-width"));
        }else {
            contour = new Color(0,0,0,1);
            ep = 0;
        }

        //exception for transparent background
        if (except.equals("none")){
            c = new Color(0,0,0,1);
        }else {
            c= stringToColor(except);
        }


        //rounded rect or normal one
        if (arg3.getValue("rx") == null && arg3.getValue("ry") == null){
            Rectangle r;
            if (stateG){
                r = new Rectangle(pt,L,l,c,contour,ep,translate,rotate);
            }else {
                r = new Rectangle(pt,L,l,c,contour,ep);
            }
            return r;
        } else if (arg3.getValue("rx") == null){
            ry = Integer.parseInt(arg3.getValue("ry"));
            rx = ry;
            Rounded_rectangle r;
            if (stateG){
                r= new Rounded_rectangle(pt,L,l,c,contour,ep,rx,ry, translate, rotate);
            }else {
                r= new Rounded_rectangle(pt,L,l,c,contour,ep,rx,ry);
            }
            return r;
        }else if (arg3.getValue("ry") == null){
            rx = Integer.parseInt(arg3.getValue("rx"));
            ry = rx;
            Rounded_rectangle r ;
            if (stateG){
                r= new Rounded_rectangle(pt,L,l,c,contour,ep,rx,ry, translate, rotate);
            }else {
                r= new Rounded_rectangle(pt,L,l,c,contour,ep,rx,ry);
            }
            return r;
        }else {
            rx = Integer.parseInt(arg3.getValue("rx"));
            ry = Integer.parseInt(arg3.getValue("ry"));
            Rounded_rectangle r;
            if (stateG){
                r= new Rounded_rectangle(pt,L,l,c,contour,ep,rx,ry, translate, rotate);
            }else {
                r= new Rounded_rectangle(pt,L,l,c,contour,ep,rx,ry);
            }
            return r;

        }

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
        r=Integer.parseInt(arg3.getValue("r"));   //Valeur en int de l'attribue
        except = arg3.getValue("fill");

        if (except.equals("none")){
            c = new Color(0,0,0,1);
        }else {
            c= stringToColor(except);
        }

        //check if there is stroke or no
        if (arg3.getValue("stroke")!= null){
            //decode the color
            contour =  stringToColor(arg3.getValue("stroke"));
            ep=Integer.parseInt(arg3.getValue("stroke-width"));
        }else {
            contour = new Color(0,0,0,1);
            ep = 0;
        }

        pt = new Point(x,y);
        Cercle cercle = new Cercle(pt,r,c,contour,ep);
        return cercle;
    }

    /**
     * getter for the array list
     * @return
     */
    public ArrayList<Figure> getList() {
        return list;
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
        System.out.println("</"+arg2+">" );
        if (arg2.equals("text")){
            Text f = recupText();
            list.add(f);
            stateT = false;
        }if (arg2.equals("g")){
            stateG = false;
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
//color decode
    public static Color stringToColor(final String value) {

        if (value == null) {
            return Color.black;
        }
        try {
            return Color.decode(value);
        } catch (NumberFormatException nfe) {
            try {
                final Field f = Color.class.getField(value);
                return (Color) f.get(null);
            } catch (Exception ce) {
                return Color.black;
            }
        }
    }

}


