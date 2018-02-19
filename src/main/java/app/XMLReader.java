package app;

import app.exceptions.LanguageNotFoundException;
import app.exceptions.XMLException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLReader {
    private String filePath = "data/language.xml";
    private File languageFile;
    private NodeList languages;
    private String languageName;


    public XMLReader() {

        PropertiesReader properties = new PropertiesReader();

        try {
            languageName = properties.getLanguage();
        } catch (LanguageNotFoundException languageNotFoundException) {
            languageName = "en";
        }

        try {
            this.languageFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(this.languageFile);
            doc.getDocumentElement().normalize();
            languages = doc.getElementsByTagName("language");
        } catch (Exception e) {
            GUI.log.error("cannot read xml!");
        }
    }

    public String getWord(String word) throws XMLException {
        for (int temp = 0; temp < languages.getLength(); temp++) {
            Node nNode = languages.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getAttribute("name").equals(languageName)) {

                   if(eElement.getElementsByTagName(word).item(0).getTextContent().equals("")) throw new XMLException();

                    return eElement.getElementsByTagName(word).item(0).getTextContent();
                }
            }
        }
        return "languageERROR";
    }
}



