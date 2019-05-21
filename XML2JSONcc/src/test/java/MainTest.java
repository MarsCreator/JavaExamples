import io.qameta.allure.Attachment;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainTest {
    public Main main;

    @Attachment
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/resources/data", resourceName));
    }

    @Test
    public void TestOneElement() throws ParserConfigurationException, SAXException, IOException {
        Assert.assertTrue("One element don't complete!", main.ReadXML("src/main/resources/data/one_elem"));
        Assert.assertTrue("One element don't complete! Incorrect JSON file!", main.JSONchecker("src/main/resources/data/one_elem"));
        getBytes("one_elem.xml");
        getBytes("one_elem.json");
    }

    @Test
    public void TestFiveElements() throws ParserConfigurationException, SAXException, IOException {
        Assert.assertTrue("Five elements don't complete!", main.ReadXML("src/main/resources/data/five_elem"));
        Assert.assertTrue("Five elements don't complete! Incorrect JSON file!", main.JSONchecker("src/main/resources/data/five_elem"));
        getBytes("five_elem.xml");
        getBytes("five_elem.json");
    }

    @Test
    public void TestWrongXML() throws ParserConfigurationException, SAXException, IOException {
        Assert.assertTrue("Wrong XML file!", main.ReadXML("src/main/resources/data/error"));
        getBytes("error.xml");
        getBytes("error.json");
    }
}
