package fr.parseur;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

import fr.geometrie.*;
import fr.geometrie.Point;
import fr.geometrie.Polygon;
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
    private int x,y, rotate, ep;
    private Color c,contour;
    Point translate;

    /**s
     * Element start
     * @param arg0
     * @param arg1
     * @param nom
     * @param arg3
     * @throws SAXException
     */
    @Override
    public void startElement(String arg0, String arg1, String nom, Attributes arg3) throws SAXException {

        System.out.println(nom);
        if (nom.equals("rect")) {
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
        }else if (nom.equals("line")){
            Line f = recupLine(arg3);
            list.add(f);
        }else if (nom.equals("polyline") ){
            Polyline f = recupPolyline(arg3);
            list.add(f);
        }else if (nom.equals("polygon")){
            Polygon f = recupPolygon(arg3);
            list.add(f);
        }else if (nom.equals("ellipse")){
            Ellipse f = recupEllipse(arg3);
            list.add(f);
        }


    }

    public Ellipse recupEllipse(Attributes arg3){
        x = Integer.parseInt(arg3.getValue("rx"));
        y = Integer.parseInt(arg3.getValue("ry"));
        StrokeTest(arg3);

        Ellipse el = new Ellipse(translate, x,y,c);
        return el;

    }

    /**
     * Recup the polygon attributes
     * @param arg3
     * @return
     */
    public Polygon recupPolygon(Attributes arg3){
        String Cont;
        StrokeTest(arg3);
        Cont = arg3.getValue("points");
        String[] tab = ReformatandCut(Cont);
        String[] orig = tab[0].split(",");
        c = stringToColor(arg3.getValue("fill"));
        Point p = new Point(Integer.parseInt(orig[0]),Integer.parseInt(orig[1]));
        Polygon pol = new Polygon(tab,c,contour,ep,p);
        return pol;
    }

    /**
     * Recup the polyline attributes
     * @param arg3
     * @return
     */
    public Polyline recupPolyline(Attributes arg3){
        String content;
        StrokeTest(arg3);
        content = arg3.getValue("points");
        String[] tab = ReformatandCut(content);
        String[] orig = tab[0].split(",");
        Point p = new Point(Integer.parseInt(orig[0]),Integer.parseInt(orig[1]));

        Polyline l = new Polyline(tab,contour,ep, p);
        return l;

    }

    /**
     * assignation stroke
     * @param arg3
     */
    private void StrokeTest(Attributes arg3){
        if (arg3.getValue("stroke") != null){
            contour = stringToColor(arg3.getValue("stroke"));
        }else if (arg3.getValue("stroke-width") != null){
            ep = Integer.parseInt(arg3.getValue("stroke-width"));
        }else if (arg3.getValue("fill") != null){
            String ct = arg3.getValue("fill");
            if (ct.equals("none")){
                c = new Color(0,0,0,1);
            }else {
                c = stringToColor(ct);
            }
        }
    }
    /**
     * Reformat and cut a String with ;
     * @param content
     * @return
     */
    private String[] ReformatandCut(String content){
        //refactor the string
        String regular = content.replaceAll(" +", ";");
        //Split by points
        String[] tab = regular.split(";");

        return tab;

    }
    /**
     * Recup the attributes of a line
     * @param arg3
     * @return
     */
    public  Line recupLine(Attributes arg3){
        int x2,y2, ep;
        x = Integer.parseInt(arg3.getValue("x1"));
        y = Integer.parseInt(arg3.getValue("y1"));
        x2 = Integer.parseInt(arg3.getValue("x2"));
        y2 = Integer.parseInt(arg3.getValue("y2"));

        if (arg3.getValue("stroke") != null){
            c = stringToColor(arg3.getValue("stroke"));
        }
        ep = Integer.parseInt(arg3.getValue("stroke-width"));
        Line lin = new Line(new Point(x,y), new Point(x2,y2),c,ep);
        return lin;
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


        }else if (arg3.getValue("stroke") != null){
            c = stringToColor(arg3.getValue("stroke"));

        }else if (arg3.getValue("stroke-width") != null){
            ep = Integer.parseInt(arg3.getValue("stroke-width"));
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

    /**
     * recup Attributes fof a pictures
     * @param arg3
     * @return
     */
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
                Color g = xmlBasicColor(value);
                return g;
            } catch (Exception ce) {
                return Color.black;
            }
        }
    }

    /**
     * the basics color for xml
     * @param value
     * @return
     */
    static private Color xmlBasicColor(final String value){

    if (value.equals("maroon")){
        return Color.decode("#800000");
    }else if (value.equals("red")){
        return Color.decode("#ff0000");
    }else if (value.equals("orange")){
        return Color.decode("#ffA500");
    }else if (value.equals("yellow")){
        return Color.decode("#ffff00");
    }else if (value.equals("olive")){
        return Color.decode("#808000");
    }else if (value.equals("purple")){
        return Color.decode("#800080");
    }else if (value.equals("fuchsia")){
        return Color.decode("#ff00ff");
    }else if (value.equals("white")){
        return Color.decode("#ffffff");
    }else if (value.equals("lime")){
        return Color.decode("#00ff00");
    }else if (value.equals("green")){
        return Color.decode("#008000");
    }else if (value.equals("navy")){
        return Color.decode("#000080");
    }else if (value.equals("blue")){
        return Color.decode("#0000ff");
    }else if (value.equals("aqua")){
        return Color.decode("#00ffff");
    }else if (value.equals("teal")){
        return Color.decode("#008080");
    }else if (value.equals("black")){
        return Color.decode("#000000");
    }else if (value.equals("silver")){
        return Color.decode("#c0c0c0");
    }else if (value.equals("gray")){
        return Color.decode("#808080");
    }else{
        return Color.black;
    }

}

}


