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

    public Parseur_launch(String name, boolean val) {
        par = new Xml_parseur();
        xmlReader(name,val);
    }

    public void xmlReader(String Xmlname,boolean valeur) {
        
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
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch(SAXException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
