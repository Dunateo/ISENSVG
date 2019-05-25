package fr.parseur;

import org.w3c.dom.DOMException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import java.io.IOException;
import java.io.StringReader;

public class Parseur_launch {

    public Xml_parseur par;
    protected String error;


    public Parseur_launch(String name) {
        boolean value;
        par = new Xml_parseur();

        if (name.contains(".isvg") || name.contains(".xml") ){
            value = true;
        }else {
            value =  false;
        }
        xmlReader(name,value);
    }

    public void xmlReader(String Xmlname,boolean valeur) {
        error = "";
    	try {
            SAXParser p = SAXParserFactory.newInstance().newSAXParser();
            XMLReader xr = p.getXMLReader();

            xr.setContentHandler(par);
            if(valeur == true) {
                xr.parse(Xmlname);
            } else {
                xr.parse(new InputSource(new StringReader(Xmlname)));
            }
            
        } catch (DOMException e) {
            e.printStackTrace();
            error = e.getMessage();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            error = e.getMessage();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
            error = e.getMessage();
        } catch(SAXException e) {
            e.printStackTrace();
            error = e.getMessage();
        } catch(IOException e) {
            e.printStackTrace();
            error = e.getMessage();
        }
    }

    public String getParseurEror(){
        return error;

    }
}
