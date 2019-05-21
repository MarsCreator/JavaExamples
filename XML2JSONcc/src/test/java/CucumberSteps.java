import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CucumberSteps {

    private Main main = new Main();

    @Когда("чтение файла из {string}")
    public void ReadXML(String fileName) throws ParserConfigurationException, SAXException, IOException {
         Assert.assertTrue("Error! Incorrect file!", main.ReadXML(fileName));
    }

    @Пусть("чтение строки {string}")
    public void ReadString(String stringXML){
        Assert.assertTrue("Error! Incorrect string!", main.ReadXMLString(stringXML));
    }

    @Тогда("корректная строка {string}")
    public void ChekStringJSON(String fileName) throws IOException {
        Assert.assertTrue("Error! Incorrect JSON string!", main.JSONchecker(fileName));
    }

    @Тогда("результат {string}")
    public void JSONchecker(String fileName) throws IOException {
        Assert.assertTrue("Error! Incorrect JSON file!", main.JSONchecker(fileName));
    }

    @Тогда("Проверка подстроки {string}")
    public void ChekSubstring(String subString) throws IOException {
        String file = "src/main/resources/data/XMLString.json";
        String fileString = readFile(file, StandardCharsets.UTF_8);
        System.out.println(fileString);
        Assert.assertTrue("Не содержит подстроку", fileString.contains(subString));
    }

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
