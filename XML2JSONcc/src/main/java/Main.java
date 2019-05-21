import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class Main {
    public static void main(String[] args) throws Exception {
        ReadXML("src/main/resources/data/test");
        JSONchecker("src/main/resources/data/test");
    }

    public static boolean ReadXML(String fileName) throws IOException, SAXException, ParserConfigurationException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileName + ".xml");
            Element root = doc.getDocumentElement();
            Elements elemets = new Elements(root.getNodeName());
            ParsXML(root, elemets);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writer().withDefaultPrettyPrinter().writeValue(new File(fileName + ".json"), elemets);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean ReadXMLString(String stringXML){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = builder.parse(new InputSource(new StringReader(stringXML)));
            Element root = doc.getDocumentElement();
            Elements elemets = new Elements(root.getNodeName());
            ParsXML(root, elemets);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writer().withDefaultPrettyPrinter().writeValue(new File("src/main/resources/data/StringXML.json"), elemets);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean JSONchecker(String jsonFile) throws IOException {
        if (JSONchecker.JSONchecker(jsonFile + ".json")) {
            System.out.println("JSONFile: " + jsonFile + ".json" + " Correct!");
            return true;
        } else {
            System.out.println("JSONFile: " + jsonFile + ".json" + " Incorrect!");
            return false;
        }
    }


    private static void ParsXML(Node node, Elements elements) {
        if (node.hasChildNodes() && (node.getNodeType() == Node.ELEMENT_NODE)) {
            NodeList list = node.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Elements child = elements.addChild(list.item(i).getNodeName());
                    ParsXML(list.item(i), child);
                }
                if (list.item(i).getNodeType() == Node.TEXT_NODE) {
                    if (!node.getTextContent().contains("\n")) {
                        String text = elements.getText(node.getTextContent());
                    }
                }
            }
        }
    }
}

