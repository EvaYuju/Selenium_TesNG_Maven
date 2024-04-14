package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// Para leer propiedades en config.properties
public class Util {
    public static String readProperty(String key) throws IOException {

        // Archivo a leer
        String path = System.getProperty("user.dir") +"/src/main/resources/config.properties";
        // Inicializamos el objeto properties
        Properties properties = new Properties();

        try {
            FileInputStream input = new FileInputStream(path);
            properties.load(input);
            return properties.getProperty(key);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de propiedades: " + e.getMessage());
            throw e;
        }
    }
}
