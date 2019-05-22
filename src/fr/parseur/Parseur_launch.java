package fr.parseur;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import java.io.IOException;

public class Parseur_launch {

    public Xml_parseur par;

    public Parseur_launch(String name) {
        par = new Xml_parseur();
        xmlReader(name);
    }

    public void xmlReader(String Xmlname){
        try {
            SAXParser p = SAXParserFactory.newInstance().newSAXParser();
            XMLReader xr = p.getXMLReader();

            xr.setContentHandler(par);
            xr.parse(Xmlname);

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
