import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public final class JSONchecker {

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    private JSONchecker() {
    }

    public static boolean JSONchecker(String jsonFile) throws IOException {
        String s = readFile(jsonFile, StandardCharsets.UTF_8);
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(s);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}