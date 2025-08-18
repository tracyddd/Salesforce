package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;

    public ConfigReader() {
        // file path may vary
        String filePath = "src/test/resources/global.properties";
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load global.properties file.");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
