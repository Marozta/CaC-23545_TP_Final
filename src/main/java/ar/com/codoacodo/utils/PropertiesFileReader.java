package ar.com.codoacodo.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {
    private static Properties prop = null;
    private static final String propFileName = "config.properties";

    private static void loadPropValues() throws IOException {
        prop = new Properties();
        InputStream inputStream = PropertiesFileReader.class.getClassLoader().getResourceAsStream(propFileName);

        if (inputStream == null) {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        prop.load(inputStream);
    }

    public static String getValue(String key) throws IOException {
        if (prop == null) {
            loadPropValues();
        }
        return prop.getProperty(key);
    }

    public static String getValue(String key, String defaultvalue) throws IOException {
        if (prop == null) {
            loadPropValues();
        }
        return prop.getProperty(key, defaultvalue);
    }
}